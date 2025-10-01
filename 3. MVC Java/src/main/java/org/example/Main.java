package org.example;


public class Main {
    public static void main(String[] args) {
        BookRepository repo = new BookRepository();
        BookController controller = new BookController(repo);
        BookView view = new BookView(controller);
        view.start();
    }
}
