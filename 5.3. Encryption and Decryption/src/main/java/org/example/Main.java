
package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            try {
                int mode = getMode();

                if (mode == 0) {
                    System.out.println("Exiting the program...");
                    break;
                }

                char keyChar = getKeyChar();
                String fileName = getFileName();

                switch (mode) {
                    case 1:
                        String userInput = getUserInput();
                        Cryption.encryption(userInput, keyChar, fileName);
                        System.out.println(
                                "String successfully encrypted and saved to file.");
                        break;

                    case 2:
                        String encrypted =
                                Cryption.readEncryptedStringFromFile(fileName);
                        String decrypted =
                                Cryption.decryption(keyChar, fileName);
                        System.out.println(
                                "Encrypted String from file: " + encrypted);
                        System.out.println("Decrypted String: " + decrypted);
                        break;

                    case 3:
                        String text = getUserInput();
                        Cryption.encryption(text, keyChar, fileName);
                        String enc =
                                Cryption.readEncryptedStringFromFile(fileName);
                        String dec = Cryption.decryption(keyChar, fileName);
                        System.out.println("Original String: " + text);
                        System.out.println("Encrypted String: " + enc);
                        System.out.println("Decrypted String: " + dec);
                        break;

                    default:
                        System.out.println("Invalid mode selected!");
                }

            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    public static int getMode() {
        System.out.println("Choose mode:");
        System.out.println("1 - Encrypt text to file");
        System.out.println("2 - Decrypt text from file");
        System.out.println("3 - Encrypt and then Decrypt");
        System.out.println("0 - Exit");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static String getUserInput() {
        System.out.println("Please enter text to encrypt: ");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        if (userInput.isEmpty()) {
            throw new IllegalArgumentException(
                    "You must enter at least one character!");
        }
        return userInput;
    }

    public static char getKeyChar() {
        System.out.println("Please enter the key character: ");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        if (userInput.length() != 1) {
            throw new IllegalArgumentException(
                    "You must enter exactly one character!");
        }
        return userInput.charAt(0);
    }

    public static String getFileName() {
        System.out.println("Please enter the file name: ");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException(
                    "You must enter a valid file name!");
        }
        return fileName;
    }
}
