package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {
    private BookRepository repo;

    @BeforeEach
    void setUp() {
        repo = new BookRepository();
    }

    @Test
    void testGetBooksReturnsCopy() {
        Book[] books1 = repo.getBooks();
        Book[] books2 = repo.getBooks();

        assertEquals(books1.length, books2.length);
        assertNotSame(books1, books2,
                      "getBooks() should return a new array, not the same reference");
    }

    @Test
    void testGetRandomAuthor() {
        String author = repo.getRandomAuthor();
        assertNotNull(author);
        assertFalse(author.isEmpty(), "Random author must not be empty");
    }

    @Test
    void testGetRandomPublisher() {
        String publisher = repo.getRandomPublisher();
        assertNotNull(publisher);
        assertFalse(publisher.isEmpty(), "Random publisher must not be empty");
    }

    @Test
    void testGetRandomYear() {
        int year = repo.getRandomYear();
        assertTrue(year > 0, "Random year must be positive");
    }
}

