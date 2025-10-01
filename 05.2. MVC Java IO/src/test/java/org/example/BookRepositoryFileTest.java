package org.example;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryFileTest {
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("books", ".csv");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testSaveAndLoadBooks() {
        BookRepository repo = new BookRepository();
        repo.setInitialBooks();

        repo.saveToFile(tempFile.toString());

        BookRepository newRepo = new BookRepository();
        newRepo.loadFromFile(tempFile.toString());

        List<Book> original = repo.getBooks();
        List<Book> loaded = newRepo.getBooks();

        assertEquals(original.size(), loaded.size(),
                     "Размеры списков должны совпадать");

        for (int i = 0; i < original.size(); i++) {
            Book b1 = original.get(i);
            Book b2 = loaded.get(i);

            assertEquals(b1.getTitle(), b2.getTitle());
            assertEquals(b1.getAuthor(), b2.getAuthor());
            assertEquals(b1.getPublisher(), b2.getPublisher());
            assertEquals(b1.getYear(), b2.getYear());
            assertEquals(b1.getPages(), b2.getPages());
            assertEquals(b1.getPrice(), b2.getPrice(), 0.001);
        }
    }
}
