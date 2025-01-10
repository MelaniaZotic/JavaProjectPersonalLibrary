package com.example.JavaProjectPersonalLibrary.controllers;

import com.example.JavaProjectPersonalLibrary.entities.Book;
import com.example.JavaProjectPersonalLibrary.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@Tag(name = "Library Management", description = "Operations related to managing the library")

public class LibraryController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Retrieve all books", description = "Get a list of all books in the library")
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Add a new book", description = "Add a new book to the library")
    @PostMapping("/book")
    public Book addBook(@RequestBody @Valid Book book) {
        return bookService.createBook(book);
    }

//    @PutMapping("/books/{id}/progress")
//    public Book updateProgress(@PathVariable Long id, @RequestBody @Valid BookProgressDto progressDto) {
//        return libraryService.updateReadingProgress(id, progressDto.getProgress());
//    }
}
