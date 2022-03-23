package ch18;

import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.SegmentAllocator;
import org.libspng.spng_ihdr;

import static jdk.incubator.foreign.CLinker.toCString;
import static jdk.incubator.foreign.ResourceScope.newConfinedScope;
import static org.libspng.spng_h.*;

public class PanamaExamples {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: Panama <fname>");
            System.exit(1);
        }

        try (var scope = newConfinedScope()) {
            var allocator = SegmentAllocator.ofScope(scope);

            MemoryAddress ctx = spng_ctx_new(0);
            MemorySegment ihdr = allocator.allocate(spng_ihdr.$LAYOUT());

            spng_set_crc_action(ctx, SPNG_CRC_USE(), SPNG_CRC_USE());

            int limit = 1024 * 1024 * 64;
            spng_set_chunk_limits(ctx, limit, limit);

            var cFname = toCString(args[0], scope);
            var cMode = toCString("rb", scope);
            var png = fopen(cFname, cMode);
            spng_set_png_file(ctx, png);

            int ret = spng_get_ihdr(ctx, ihdr);

            if (ret != 0) {
                System.out.println("spng_get_ihdr() error: " + spng_strerror(ret));
                System.exit(2);
            }

            String colorTypeMsg;
            byte colorType = spng_ihdr.color_type$get(ihdr);

            if (colorType == SPNG_COLOR_TYPE_GRAYSCALE()) {
                colorTypeMsg = "grayscale";
            } else if (colorType == SPNG_COLOR_TYPE_TRUECOLOR()) {
                colorTypeMsg = "truecolor";
            } else if (colorType == SPNG_COLOR_TYPE_INDEXED()) {
                colorTypeMsg = "indexed color";
            } else if (colorType == SPNG_COLOR_TYPE_GRAYSCALE_ALPHA()) {
                colorTypeMsg = "grayscale with alpha";
            } else {
                colorTypeMsg = "truecolor with alpha";
            }

            System.out.println("File type: " + colorTypeMsg);
        }
    }

}
