package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {
    private BookController controller;

    @BeforeEach
    void setUp() {
        BookRepository repo = new BookRepository();
        controller = new BookController(repo);
        controller.setInitialBooks();
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = controller.getAllBooks();
        assertEquals(11, books.size(),
                     "There should be 11 books in the initial array");
    }

    @Test
    void testFindByAuthor() {
        List<Book> books = controller.findByAuthor("George Orwell");
        assertEquals(2, books.size(), "George Orwell should have 2 books");
        assertTrue(
                books.get(0)
                     .getTitle()
                     .equals("1984") ||
                books.get(1)
                     .getTitle()
                     .equals("Animal Farm")
        );
    }

    @Test
    void testFindByPublisher() {
        List<Book> books = controller.findByPublisher("Bloomsbury");
        assertEquals(2, books.size(), "Bloomsbury should have 2 books");
        assertTrue(
                books.get(0)
                     .getTitle()
                     .contains("Harry Potter") &&
                books.get(1)
                     .getTitle()
                     .contains("Harry Potter")
        );
    }

    @Test
    void testFindPublishedAfter() {
        List<Book> books = controller.findPublishedAfter(1950);
        assertEquals(3, books.size());
        for (Book b : books) {
            assertTrue(b.getYear() > 1950,
                       "Book year must be greater than 1950");
        }
    }


    @Test
    void testFindByAuthorNotFound() {
        List<Book> books = controller.findByAuthor("Unknown Author");
        assertEquals(0, books.size(),
                     "Search by non-existing author should return empty array");
    }

    @Test
    void testFindByPublisherNotFound() {
        List<Book> books = controller.findByPublisher("Nonexistent Publisher");
        assertEquals(0, books.size(),
                     "Search by non-existing publisher should return empty array");
    }

    @Test
    void testFindPublishedAfterFutureYear() {
        List<Book> books = controller.findPublishedAfter(3000);
        assertEquals(0, books.size(),
                     "Search by year after all books should return empty array");
    }

    @Test
    void testSortByPublisher() {
        List<Book> sorted = controller.sortByPublisher();
        assertEquals(11, sorted.size());

        for (int i = 1; i < sorted.size(); i++) {
            String prev = sorted.get(i - 1)
                                .getPublisher()
                                .toLowerCase();
            String curr = sorted.get(i)
                                .getPublisher()
                                .toLowerCase();
            assertTrue(prev.compareTo(curr) <= 0,
                       "Books must be sorted by publisher");
        }
    }
}

