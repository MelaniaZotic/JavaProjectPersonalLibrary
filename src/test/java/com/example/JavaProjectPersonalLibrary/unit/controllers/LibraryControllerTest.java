package com.example.JavaProjectPersonalLibrary.unit.controllers;

import com.example.JavaProjectPersonalLibrary.controllers.LibraryController;
import com.example.JavaProjectPersonalLibrary.entities.Book;
import com.example.JavaProjectPersonalLibrary.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LibraryControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private LibraryController libraryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("""
            Given books exist in the library
            When getAllBooks is called
            Then it should return a list of all books
            """)
    void testGetAllBooks() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = libraryController.getAllBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
        assertEquals("Book 1", books.get(0).getTitle());
        assertEquals("Book 2", books.get(1).getTitle());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    @DisplayName("""
            Given a valid book object
            When addBook is called
            Then it should save and return the book
            """)
    void testAddBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("New Book");

        when(bookService.createBook(any(Book.class))).thenReturn(book);

        Book result = libraryController.addBook(book);

        assertNotNull(result);
        assertEquals("New Book", result.getTitle());
        verify(bookService, times(1)).createBook(any(Book.class));
    }

    @Test
    @DisplayName("""
            Given a book ID and progress value
            When updateProgress is called
            Then it should update and return the updated book
            """)
    void testUpdateProgress() {
        Long bookId = 1L;
        Double progress = 75.0;

        Book book = new Book();
        book.setId(bookId);
        book.setProgress(50.0);

        when(bookService.updateReadingProgress(bookId, progress)).thenReturn(book);

        Book result = libraryController.updateProgress(bookId, progress);

        assertNotNull(result);
        assertEquals(50.0, result.getProgress());
        verify(bookService, times(1)).updateReadingProgress(bookId, progress);
    }
}
