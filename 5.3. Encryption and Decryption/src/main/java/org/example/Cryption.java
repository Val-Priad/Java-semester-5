package org.example;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cryption {
        public static void encryption(String userInput, char keyChar,
                                      String fileName)
            throws IOException {
        FileOutputStream fileOutputStream =
                new FileOutputStream(fileName);
        EncryptOutputStream encryptOutputStream =
                new EncryptOutputStream(fileOutputStream, keyChar);
        encryptOutputStream.write(userInput.getBytes(), 0,
                                  userInput.length());
        encryptOutputStream.close();
    }

    public static String readEncryptedStringFromFile(String fileName)
            throws IOException {
        FileInputStream encryptedFileInputStream =
                new FileInputStream(fileName);
        ByteArrayOutputStream encryptedBuffer =
                new ByteArrayOutputStream();
        int e;
        while ((e = encryptedFileInputStream.read()) != -1) {
            encryptedBuffer.write(e);
        }
        encryptedFileInputStream.close();
        return encryptedBuffer.toString();
    }

    public static String decryption(char keyChar, String fileName)
            throws IOException {
        FileInputStream fileInputStream =
                new FileInputStream(fileName);
        DecryptInputStream decryptInputStream =
                new DecryptInputStream(fileInputStream, keyChar);
        ByteArrayOutputStream decryptedBuffer =
                new ByteArrayOutputStream();
        int c;
        while ((c = decryptInputStream.read()) != -1) {
            decryptedBuffer.write(c);
        }
        decryptInputStream.close();
        return decryptedBuffer.toString();
    }
}
