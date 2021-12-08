package ch17;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;

public class HiddenExamples {
    public static void main(String[] args) {
        var m = new HiddenExamples();
        try {
            m.run();
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException, IllegalAccessException {
        var fName = "/Users/ben/projects/books/resources/Ch15/ch15/Concat.class";
        var buffy = Files.readAllBytes(Path.of(fName));
        var lookup = MethodHandles.lookup();
        var hiddenLookup = lookup.defineHiddenClass(buffy, true);
        var klazz = hiddenLookup.lookupClass();
        System.out.println(klazz.getName());
    }
}
