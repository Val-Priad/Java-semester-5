package org.example;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Localizer.chooseLanguage();
        ResourceBundle messages = Localizer.getMessages();

        boolean enableConsole = askLoggingPreference(messages);
        LoggingConfig.setupLogging(logger, enableConsole);

        logger.info("Program started");

        while (true) {
            try {
                int mode = getMode(messages);
                logger.info("Selected mode: " + mode);

                if (mode == 0) {
                    logger.info("Program exit");
                    System.out.println(messages.getString("menu.onClose"));
                    break;
                }

                char keyChar = getKeyChar(messages);
                String fileName = getFileName(messages);

                switch (mode) {
                    case 1 -> {
                        String userInput = getUserInput(messages);
                        logger.info("Encryption started");
                        Cryption.encryption(userInput, keyChar, fileName);
                        logger.info("Encryption finished");
                        System.out.println(
                                messages.getString("success.encrypted"));
                    }
                    case 2 -> {
                        logger.info("Decryption started");
                        String encrypted =
                                Cryption.readEncryptedStringFromFile(fileName);
                        String decrypted =
                                Cryption.decryption(keyChar, fileName);
                        logger.info("Decryption finished");
                        System.out.println(
                                "Encrypted String from file: " + encrypted);
                        System.out.println("Decrypted String: " + decrypted);
                    }
                    case 3 -> {
                        logger.info("Encryption + Decryption started");
                        String text = getUserInput(messages);
                        Cryption.encryption(text, keyChar, fileName);
                        String enc =
                                Cryption.readEncryptedStringFromFile(fileName);
                        String dec = Cryption.decryption(keyChar, fileName);
                        logger.info("Encryption + Decryption finished");
                        System.out.println("Original String: " + text);
                        System.out.println("Encrypted String: " + enc);
                        System.out.println("Decrypted String: " + dec);
                    }
                    default -> {
                        logger.warning("Invalid mode selected: " + mode);
                        System.out.println("Invalid mode selected!");
                    }
                }

            } catch (IOException | IllegalArgumentException e) {
                logger.log(Level.SEVERE, "Execution error: " + e.getMessage(), e);
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        logger.info("Program finished");
    }

    private static boolean askLoggingPreference(ResourceBundle messages) {
        System.out.println(messages.getString("logging.ask"));
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine().trim().toLowerCase();
        boolean enableConsole = answer.equals("y");

        if (enableConsole) {
            System.out.println(messages.getString("logging.enabled"));
        } else {
            System.out.println(messages.getString("logging.disabled"));
        }

        return enableConsole;
    }

    private static int getMode(ResourceBundle messages) {
        System.out.println(messages.getString("menu.choose"));
        System.out.println(messages.getString("menu.encrypt"));
        System.out.println(messages.getString("menu.decrypt"));
        System.out.println(messages.getString("menu.both"));
        System.out.println(messages.getString("menu.exit"));
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    private static String getUserInput(ResourceBundle messages) {
        System.out.println(messages.getString("input.text"));
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        if (userInput.isEmpty()) {
            logger.warning("Empty string entered for encryption");
            throw new IllegalArgumentException(messages.getString("error.emptyText"));
        }
        logger.fine("User entered a string with length " + userInput.length());
        return userInput;
    }

    private static char getKeyChar(ResourceBundle messages) {
        System.out.println(messages.getString("input.key"));
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        if (userInput.length() != 1) {
            logger.warning("Invalid key entered: " + userInput.length() + " characters");
            throw new IllegalArgumentException(messages.getString("error.invalidKey"));
        }
        return userInput.charAt(0);
    }

    private static String getFileName(ResourceBundle messages) {
        System.out.println(messages.getString("input.file"));
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
        if (fileName.isEmpty()) {
            logger.warning("Empty file name entered");
            throw new IllegalArgumentException(messages.getString("error.emptyFile"));
        }
        return fileName;
    }
}
