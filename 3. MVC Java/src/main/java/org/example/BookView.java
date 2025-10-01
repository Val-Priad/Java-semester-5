package org.example;

import java.util.Scanner;

public class BookView {
    private final BookController controller;
    private final Scanner scanner = new Scanner(System.in);

    public BookView(BookController controller) {
        this.controller = controller;
    }

    public void start() {
        System.out.println("=== Початковий масив книг ===");
        printBooks(controller.getAllBooks());

        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = scanner.nextLine()
                                   .trim();
            switch (choice) {
                case "1":
                    String randomAuthor =
                            controller.repo.getRandomAuthor();
                    System.out.println("Випадково обраний автор для пошуку: " +
                                       randomAuthor);
                    handleResult(controller.findByAuthor(randomAuthor));
                    break;
                case "2":
                    String randomPublisher =
                            controller.repo.getRandomPublisher();
                    System.out.println(
                            "Випадково обране видавництво для пошуку: " +
                            randomPublisher);
                    handleResult(controller.findByPublisher(randomPublisher));
                    break;
                case "3":
                    int randomYear = controller.repo.getRandomYear();
                    System.out.println(
                            "Випадково обраний рік (пошук книг після року): " +
                            randomYear);
                    handleResult(controller.findPublishedAfter(randomYear));
                    break;
                case "4":
                    System.out.println("Книги, відсортовані за видавництвом:");
                    printBooks(controller.sortByPublisher());
                    break;
                case "5":
                    handleManualSearch();
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
        System.out.println(
                "Введіть 1-по автору, 2-по видавництву, 3-по року (після року):");
        String opt = scanner.nextLine()
                            .trim();
        switch (opt) {
            case "1":
                System.out.print("Введіть автора: ");
                String a = scanner.nextLine()
                                  .trim();
                handleResult(controller.findByAuthor(a));
                break;
            case "2":
                System.out.print("Введіть видавництво: ");
                String p = scanner.nextLine()
                                  .trim();
                handleResult(controller.findByPublisher(p));
                break;
            case "3":
                System.out.print("Введіть рік: ");
                try {
                    int y = Integer.parseInt(scanner.nextLine()
                                                    .trim());
                    handleResult(controller.findPublishedAfter(y));
                } catch (NumberFormatException e) {
                    System.out.println("Невірний формат року.");
                }
                break;
            default:
                System.out.println("Невірний вибір.");
        }
    }

    private void handleResult(Book[] res) {
        if (res == null || res.length == 0) {
            System.out.println("За заданими параметрами книг не знайдено.");
        } else {
            printBooks(res);
        }
    }

    private void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println(
                "1 - Отримати список книг випадково обраного автора (з підготовлених даних)");
        System.out.println(
                "2 - Отримати список книг випадково обраного видавництва");
        System.out.println(
                "3 - Отримати список книг, виданих після випадково обраного року");
        System.out.println(
                "4 - Відсортувати книги за видавництвами (Comparator)");
        System.out.println("5 - Ручний ввід параметрів для пошуку");
        System.out.println("0 - Вихід");
        System.out.print("Вибір: ");
    }

    private void printBooks(Book[] books) {
        for (Book b : books) {
            System.out.println(b);
        }
    }
}
