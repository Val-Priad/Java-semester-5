package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class BookController {
    BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    public Book[] getAllBooks() {
        return repo.getBooks();
    }

    public Book[] findByAuthor(String author) {
        Book[] arr = repo.getBooks();
        return Arrays.stream(arr)
                     .filter(b -> b.getAuthor()
                                   .equalsIgnoreCase(author))
                     .toArray(Book[]::new);
    }

    public Book[] findByPublisher(String publisher) {
        Book[] arr = repo.getBooks();
        return Arrays.stream(arr)
                     .filter(b -> b.getPublisher()
                                   .equalsIgnoreCase(publisher))
                     .toArray(Book[]::new);
    }

    public Book[] findPublishedAfter(int year) {
        Book[] arr = repo.getBooks();
        return Arrays.stream(arr)
                     .filter(b -> b.getYear() > year)
                     .toArray(Book[]::new);
    }

    public Book[] sortByPublisher() {
        Book[] arr = repo.getBooks();
        Comparator<Book> byPublisher = Comparator.comparing(Book::getPublisher,
                                                            String.CASE_INSENSITIVE_ORDER)
                                                 .thenComparing(Book::getTitle,
                                                                String.CASE_INSENSITIVE_ORDER);
        Arrays.sort(arr, byPublisher);
        return arr;
    }
}

