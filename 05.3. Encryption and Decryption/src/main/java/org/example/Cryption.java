package org.example;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cryption {
    private static final Logger logger =
            Logger.getLogger(Cryption.class.getName());

    public static void encryption(String userInput, char keyChar,
                                  String fileName)
            throws IOException {
        logger.info("Encryption started for file: " + fileName);

        try (FileOutputStream fileOutputStream = new FileOutputStream(
                fileName);
             EncryptOutputStream encryptOutputStream = new EncryptOutputStream(
                     fileOutputStream, keyChar)) {

            encryptOutputStream.write(userInput.getBytes(), 0,
                                      userInput.length());
            logger.info("File successfully encrypted: " + fileName);

        } catch (IOException e) {
            logger.log(Level.SEVERE,
                       "Error while encrypting file: " + fileName, e);
            throw e;
        }
    }

    public static String readEncryptedStringFromFile(String fileName)
            throws IOException {
        logger.info("Reading encrypted string from file: " + fileName);

        try (FileInputStream encryptedFileInputStream = new FileInputStream(
                fileName);
             ByteArrayOutputStream encryptedBuffer = new ByteArrayOutputStream()) {

            int e;
            while ((e = encryptedFileInputStream.read()) != -1) {
                encryptedBuffer.write(e);
            }
            return encryptedBuffer.toString();

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while reading file: " + fileName,
                       e);
            throw e;
        }
    }

    public static String decryption(char keyChar, String fileName)
            throws IOException {
        logger.info("Decryption started for file: " + fileName);

        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             DecryptInputStream decryptInputStream = new DecryptInputStream(
                     fileInputStream, keyChar);
             ByteArrayOutputStream decryptedBuffer = new ByteArrayOutputStream()) {

            int c;
            while ((c = decryptInputStream.read()) != -1) {
                decryptedBuffer.write(c);
            }
            String result = decryptedBuffer.toString();
            logger.info("File successfully decrypted: " + fileName);
            return result;

        } catch (IOException e) {
            logger.log(Level.SEVERE,
                       "Error while decrypting file: " + fileName, e);
            throw e;
        }
    }
}
