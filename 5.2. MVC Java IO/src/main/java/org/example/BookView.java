package org.example;

import java.util.List;
import java.util.Scanner;

public class BookView {
    private final BookController controller;
    private final Scanner scanner = new Scanner(System.in);

    public BookView(BookController controller) {
        this.controller = controller;
    }

    public void start() {
        System.out.println("Виберіть джерело даних:");
        System.out.println("1 - Завантажити з файлу");
        System.out.println("2 - Використати захардкоджені дані");
        String choice = scanner.nextLine().trim();

        if ("1".equals(choice)) {
            System.out.print("Введіть ім'я файлу: ");
            String fileName = scanner.nextLine().trim();
            controller.loadFromFile(fileName);
        } else {
            controller.setInitialBooks();
        }

        System.out.println("=== Початковий список книг ===");
        printBooks(controller.getAllBooks());

        boolean exit = false;
        while (!exit) {
            printMenu();
            String choiceMenu = scanner.nextLine().trim();
            switch (choiceMenu) {
                case "1":
                    String randomAuthor = controller.getRandomAuthor();
                    System.out.println("Випадково обраний автор для пошуку: " + randomAuthor);
                    handleResult(controller.findByAuthor(randomAuthor));
                    break;
                case "2":
                    String randomPublisher = controller.getRandomPublisher();
                    System.out.println("Випадково обране видавництво: " + randomPublisher);
                    handleResult(controller.findByPublisher(randomPublisher));
                    break;
                case "3":
                    int randomYear = controller.getRandomYear();
                    System.out.println("Випадково обраний рік: " + randomYear);
                    handleResult(controller.findPublishedAfter(randomYear));
                    break;
                case "4":
                    System.out.println("Книги, відсортовані за видавництвом:");
                    printBooks(controller.sortByPublisher());
                    break;
                case "5":
                    handleManualSearch();
                    break;
                case "6":
                    System.out.print("Введіть ім'я файлу для збереження: ");
                    String fileName = scanner.nextLine().trim();
                    controller.saveToFile(fileName);
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще.");
            }
        }

        System.out.println("Програма завершена.");
    }

    private void handleManualSearch() {
        System.out.println("Введіть 1-по автору, 2-по видавництву, 3-по року (після року):");
        String opt = scanner.nextLine().trim();
        switch (opt) {
            case "1":
                System.out.print("Введіть автора: ");
                String a = scanner.nextLine().trim();
                handleResult(controller.findByAuthor(a));
                break;
            case "2":
                System.out.print("Введіть видавництво: ");
                String p = scanner.nextLine().trim();
                handleResult(controller.findByPublisher(p));
                break;
            case "3":
                System.out.print("Введіть рік: ");
                try {
                    int y = Integer.parseInt(scanner.nextLine().trim());
                    handleResult(controller.findPublishedAfter(y));
                } catch (NumberFormatException e) {
                    System.out.println("Невірний формат року.");
                }
                break;
            default:
                System.out.println("Невірний вибір.");
        }
    }

    private void handleResult(List<Book> res) {
        if (res == null || res.isEmpty()) {
            System.out.println("За заданими параметрами книг не знайдено.");
        } else {
            printBooks(res);
        }
    }

    private void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1 - Отримати список книг випадково обраного автора");
        System.out.println("2 - Отримати список книг випадково обраного видавництва");
        System.out.println("3 - Отримати список книг, виданих після випадково обраного року");
        System.out.println("4 - Відсортувати книги за видавництвами");
        System.out.println("5 - Ручний ввід параметрів для пошуку");
        System.out.println("6 - Зберегти усі книжки до файлу");
        System.out.println("0 - Вихід");
        System.out.print("Вибір: ");
    }

    private void printBooks(List<Book> books) {
        for (Book b : books) {
            System.out.println(b);
        }
    }
}
