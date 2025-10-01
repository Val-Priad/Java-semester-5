package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest {
    private BookController controller;

    @BeforeEach
    void setUp() {
        BookRepository repo = new BookRepository();
        controller = new BookController(repo);
    }

    @Test
    void testGetAllBooks() {
        Book[] books = controller.getAllBooks();
        assertEquals(11, books.length,
                     "There should be 11 books in the initial array");
    }

    @Test
    void testFindByAuthor() {
        Book[] books = controller.findByAuthor("George Orwell");
        assertEquals(2, books.length, "George Orwell should have 2 books");
        assertTrue(
                books[0].getTitle()
                        .equals("1984") ||
                books[1].getTitle()
                        .equals("Animal Farm")
        );
    }

    @Test
    void testFindByPublisher() {
        Book[] books = controller.findByPublisher("Bloomsbury");
        assertEquals(2, books.length, "Bloomsbury should have 2 books");
        assertTrue(
                books[0].getTitle()
                        .contains("Harry Potter") &&
                books[1].getTitle()
                        .contains("Harry Potter")
        );
    }

    @Test
    void testFindPublishedAfter() {
        Book[] books = controller.findPublishedAfter(1950);
        assertEquals(3, books.length);
        for (Book b : books) {
            assertTrue(b.getYear() > 1950,
                       "Book year must be greater than 1950");
        }
    }


    @Test
    void testFindByAuthorNotFound() {
        Book[] books = controller.findByAuthor("Unknown Author");
        assertEquals(0, books.length,
                     "Search by non-existing author should return empty array");
    }

    @Test
    void testFindByPublisherNotFound() {
        Book[] books = controller.findByPublisher("Nonexistent Publisher");
        assertEquals(0, books.length,
                     "Search by non-existing publisher should return empty array");
    }

    @Test
    void testFindPublishedAfterFutureYear() {
        Book[] books = controller.findPublishedAfter(3000);
        assertEquals(0, books.length,
                     "Search by year after all books should return empty array");
    }

    @Test
    void testSortByPublisher() {
        Book[] sorted = controller.sortByPublisher();
        assertEquals(11, sorted.length);

        for (int i = 1; i < sorted.length; i++) {
            String prev = sorted[i - 1].getPublisher()
                                       .toLowerCase();
            String curr = sorted[i].getPublisher()
                                   .toLowerCase();
            assertTrue(prev.compareTo(curr) <= 0,
                       "Books must be sorted by publisher");
        }
    }
}

