package org.example;

import java.io.*;
import java.util.*;

public class BookRepository {
    private List<Book> books;
    private final List<Book> fakeChoiceBooks;


    public BookRepository() {
        this.books = new ArrayList<>();
        this.fakeChoiceBooks = getFakeChoiceBooks();
    }

    public void setInitialBooks() {
        this.books = new ArrayList<>(List.of(
                new Book("1984", "George Orwell", "Secker & Warburg", 1949,
                         328, 199.0),
                new Book("Animal Farm", "George Orwell", "Secker & Warburg",
                         1945, 112, 180.0),
                new Book("Harry Potter and the Philosopher's Stone",
                         "J. K. Rowling", "Bloomsbury", 1997, 223, 500.0),
                new Book("Harry Potter and the Chamber of Secrets",
                         "J. K. Rowling", "Bloomsbury", 1998, 251, 520.0),
                new Book("The Hobbit", "J. R. R. Tolkien",
                         "George Allen & Unwin", 1937, 310, 450.0),
                new Book("The Lord of the Rings", "J. R. R. Tolkien",
                         "George Allen & Unwin", 1954, 1178, 1200.0),
                new Book(
                        "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe",
                        "C. S. Lewis", "Geoffrey Bles", 1950, 208, 400.0),
                new Book("Pride and Prejudice", "Jane Austen", "T. Egerton",
                         1813, 432, 350.0),
                new Book("Jane Eyre", "Charlotte Brontë", "Smith, Elder & Co.",
                         1847, 500, 370.0),
                new Book("Wuthering Heights", "Emily Brontë",
                         "Thomas Cautley Newby", 1847, 416, 360.0),
                new Book("Great Expectations", "Charles Dickens",
                         "Chapman & Hall", 1861, 544, 390.0)
        ));
    }

    private List<Book> getFakeChoiceBooks() {
        return new ArrayList<>(List.of(
                new Book("1984", "George Orwell", "Secker & Warburg", 1949,
                         328, 199.0),
                new Book("Animal Farm", "George Orwell", "Secker & Warburg",
                         1945, 112, 180.0),
                new Book("Harry Potter and the Philosopher's Stone",
                         "J. K. Rowling", "Bloomsbury", 1997, 223, 500.0),
                new Book("Harry Potter and the Chamber of Secrets",
                         "J. K. Rowling", "Bloomsbury", 1998, 251, 520.0),
                new Book("The Hobbit", "J. R. R. Tolkien",
                         "George Allen & Unwin", 1937, 310, 450.0),
                new Book("The Lord of the Rings", "J. R. R. Tolkien",
                         "George Allen & Unwin", 1954, 1178, 1200.0),
                new Book(
                        "The Chronicles of Narnia: The Lion, the Witch and the Wardrobe",
                        "C. S. Lewis", "Geoffrey Bles", 1950, 208, 400.0),
                // фейкові арабські книги
                new Book("ألف ليلة وليلة (One Thousand and One Nights)",
                         "غير معروف (Anonymous)", "دار الشروق", 1706,
                         704, 600.0),
                new Book(
                        "موسم الهجرة إلى الشمال (Season of Migration to the North)",
                        "الطيب صالح (Tayeb Salih)", "دار العودة", 1966,
                        320, 450.0),
                new Book("رجال في الشمس (Men in the Sun)",
                         "غسان كنفاني (Ghassan Kanafani)",
                         "المؤسسة العربية للدراسات", 1963, 110, 300.0)
        ));
    }

    public void add(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public String getRandomAuthor() {
        Random r = new Random();
        return fakeChoiceBooks.get(r.nextInt(fakeChoiceBooks.size()))
                              .getAuthor();
    }

    public String getRandomPublisher() {
        Random r = new Random();
        return fakeChoiceBooks.get(r.nextInt(
                                      fakeChoiceBooks.size()))
                              .getPublisher();
    }

    public int getRandomYear() {
        Random r = new Random();
        return fakeChoiceBooks.get(r.nextInt(fakeChoiceBooks.size()))
                              .getYear();
    }


    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(books);
            System.out.println("Книги успішно збережено у файл (серіалізація): " + filename);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні у файл: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            books = (List<Book>) ois.readObject();
            System.out.println("Книги успішно завантажено з файлу (серіалізація): " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }
}

