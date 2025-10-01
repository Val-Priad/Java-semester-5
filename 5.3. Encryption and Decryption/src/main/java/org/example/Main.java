package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            String userInput = getUserInput();
            char keyChar = getKeyChar();
            String fileName = getFileName();

            Cryption.encryption(userInput, keyChar, fileName);

            String encrypted = Cryption.readEncryptedStringFromFile(fileName);

            String decrypted = Cryption.decryption(keyChar, fileName);

            System.out.println("Original String: " + userInput);
            System.out.println("Encrypted String: " + encrypted);
            System.out.println("Decrypted String: " + decrypted);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
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