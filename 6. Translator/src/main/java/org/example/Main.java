package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        boolean running = true;
        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1 - Додати слово в словник");
            System.out.println("2 - Перекласти фразу");
            System.out.println("3 - Вийти");
            System.out.print("Ваш вибір: ");
            String choice = scanner.nextLine()
                                   .trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Введіть англійське слово: ");
                    String en = scanner.nextLine()
                                       .trim();
                    if (!en.matches("[a-zA-Z]+")) {
                        System.out.println(
                                "Помилка: слово повинно містити лише англійські літери.");
                        break;
                    }

                    System.out.print("Введіть український переклад: ");
                    String ua = scanner.nextLine()
                                       .trim();
                    if (ua.isEmpty()) {
                        System.out.println(
                                "Помилка: переклад не може бути порожнім.");
                        break;
                    }

                    translator.addWord(en, ua);
                    System.out.println("Слово додано!");
                }
                case "2" -> {
                    System.out.print("Введіть англійську фразу: ");
                    String phrase = scanner.nextLine()
                                           .trim();
                    if (phrase.isEmpty()) {
                        System.out.println(
                                "Помилка: фраза не може бути порожньою.");
                        break;
                    }
                    String translation = translator.translate(phrase);
                    System.out.println("Переклад: " + translation);
                }
                case "3" -> {
                    running = false;
                    System.out.println("Вихід з програми...");
                }
                default -> System.out.println(
                        "Невірний вибір! Введіть 1, 2 або 3.");
            }
        }

        scanner.close();
    }
}
