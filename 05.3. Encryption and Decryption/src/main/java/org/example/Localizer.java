package org.example;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Localizer {
        private static ResourceBundle messages;

    public static ResourceBundle getMessages() {
        return messages;
    }

    public static void chooseLanguage() {
        System.out.println("Select language / Виберіть мову / Vyberte jazyk:");
        System.out.println("1 - English");
        System.out.println("2 - Українська");
        System.out.println("3 - Čeština");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        Locale locale = switch (choice) {
            case 2 -> Locale.of("uk");
            case 3 -> Locale.of("cs");
            default -> Locale.of("en");
        };

        messages = ResourceBundle.getBundle("location.messages", locale);
    }
}
