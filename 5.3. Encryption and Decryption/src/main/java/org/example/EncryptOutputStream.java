package org.example;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {
    private final char key;

    public EncryptOutputStream(OutputStream out, char keyChar) {
        super(out);
        this.key = keyChar;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b + key);
    }

}
