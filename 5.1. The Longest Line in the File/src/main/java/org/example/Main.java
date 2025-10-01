package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введіть шлях до файлу (або 'quit'/'q' для виходу): ");
            String path = scanner.nextLine().trim();

            if (path.equalsIgnoreCase("quit") || path.equalsIgnoreCase("q")) {
                System.out.println("Завершення роботи програми...");
                break;
            }

            try {
                IOHandler processor = new IOHandler(path);
                LineResult result = processor.getLineWithMaxWords();

                if (result.maxWords() > 0) {
                    System.out.println("Рядок з максимальною кількістю слів:");
                    System.out.println(result.line());
                    System.out.println("Кількість слів: " + result.maxWords());
                } else {
                    System.out.println("Файл порожній або не містить слів.");
                }
            } catch (IOException e) {
                System.out.println("Помилка при роботі з файлом: " + e.getMessage());
            }
        }
    }
}
