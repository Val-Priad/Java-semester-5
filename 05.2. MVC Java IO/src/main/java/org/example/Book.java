package org.example;

import java.io.Serial;
import java.io.Serializable;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String title;
    private final String author;
    private final String publisher;
    private final int year;
    private final int pages;
    private final double price;

    public Book(String title, String author, String publisher, int year,
                int pages, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.price = price;
    }

    public Book(String title, String author, String publisher, int year) {
        this(title, author, publisher, year, 0, 0.0);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" — %s, %s, %d р., %d с., %.2f грн",
                             title, author, publisher, year, pages, price);
    }
}
