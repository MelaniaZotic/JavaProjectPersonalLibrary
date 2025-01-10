package com.example.JavaProjectPersonalLibrary.unit;


import com.example.JavaProjectPersonalLibrary.entities.Book;
import com.example.JavaProjectPersonalLibrary.repositories.BookRepository;
import com.example.JavaProjectPersonalLibrary.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inițializează mock-urile
    }

    @Test
    void testFindBookByTitle() {
        // Arrange
        String title = "Test Book";
        Book book = new Book();
        book.setTitle(title);

        when(bookRepository.findBookByTitle(title)).thenReturn(Optional.of(book));

        // Act
        Book result = bookService.findBookByTitle(title);

        // Assert
        assertNotNull(result);
        assertEquals(title, result.getTitle());
        verify(bookRepository, times(1)).findBookByTitle(title);
    }

    @Test
    void testUpdateReadingProgress() {
        // Arrange
        Long bookId = 1L;
        Double progress = 50.0;
        Book book = new Book();
        book.setId(bookId);
        book.setProgress(20.0);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        Book updatedBook = bookService.updateReadingProgress(bookId, progress);

        // Assert
        assertNotNull(updatedBook);
        assertEquals(progress, updatedBook.getProgress());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testCreateBook() {
        // Arrange
        Book book = new Book();
        book.setTitle("New Book");

        when(bookRepository.save(book)).thenReturn(book);

        // Act
        Book result = bookService.createBook(book);

        // Assert
        assertNotNull(result);
        assertEquals("New Book", result.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        Book book1 = new Book();
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setTitle("Book 2");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // Act
        List<Book> books = bookService.getAllBooks();

        // Assert
        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }
}