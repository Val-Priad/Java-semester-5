package org.example;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {
    private final char key;

    public DecryptInputStream(InputStream in,  char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int data = super.read();
        if (data == -1) {
            return data;
        }
        return data - key;
    }
}
