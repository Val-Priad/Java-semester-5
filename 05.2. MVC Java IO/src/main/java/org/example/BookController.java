package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookController {
    BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAllBooks() {
        return repo.getBooks();
    }

    public List<Book> findByAuthor(String author) {
        return repo.getBooks()
                   .stream()
                   .filter(b -> b.getAuthor()
                                 .equalsIgnoreCase(author))
                   .collect(Collectors.toList());
    }

    public List<Book> findByPublisher(String publisher) {
        return repo.getBooks()
                   .stream()
                   .filter(b -> b.getPublisher()
                                 .equalsIgnoreCase(publisher))
                   .collect(Collectors.toList());
    }

    public List<Book> findPublishedAfter(int year) {
        return repo.getBooks()
                   .stream()
                   .filter(b -> b.getYear() > year)
                   .collect(Collectors.toList());
    }

    public List<Book> sortByPublisher() {
        return repo.getBooks()
                   .stream()
                   .sorted(Comparator.comparing(Book::getPublisher,
                                                String.CASE_INSENSITIVE_ORDER)
                                     .thenComparing(Book::getTitle,
                                                    String.CASE_INSENSITIVE_ORDER))
                   .collect(Collectors.toList());
    }

    public void saveToFile(String filename) {
        repo.saveToFile(filename);
    }

    public String getRandomAuthor() {
        return repo.getRandomAuthor();
    }

    public String getRandomPublisher() {
        return repo.getRandomPublisher();
    }

    public int getRandomYear() {
        return repo.getRandomYear();
    }

    public void setInitialBooks() {
        repo.setInitialBooks();
    }

    public void loadFromFile(String filename) {
        repo.loadFromFile(filename);
    }

}