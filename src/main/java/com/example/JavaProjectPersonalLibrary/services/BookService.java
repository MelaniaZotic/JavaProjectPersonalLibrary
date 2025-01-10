package com.example.JavaProjectPersonalLibrary.services;

import com.example.JavaProjectPersonalLibrary.entities.Book;
import com.example.JavaProjectPersonalLibrary.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title)
                .orElseThrow(() -> new RuntimeException("Book not found with title: " + title));
    }

    @Transactional
    public Book updateReadingProgress(Long id, Double progress) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
        book.setProgress(progress);
        return bookRepository.save(book);
    }

    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}