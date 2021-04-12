package ch02;

import java.io.IOException;
import java.io.OutputStream;

import sun.misc.BASE64Encoder;

public class MyEncoder extends BASE64Encoder {

    public void encodeAtom(OutputStream outStream, byte data[])
            throws IOException {
        encodeAtom(outStream, data, 0, data.length);
    }
}
