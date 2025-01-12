package com.example.JavaProjectPersonalLibrary.integration;


import com.example.JavaProjectPersonalLibrary.entities.Book;
import com.example.JavaProjectPersonalLibrary.entities.Category;
import com.example.JavaProjectPersonalLibrary.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("""
            Given there are books in the library
            When a request is made to get all books
            Then the response should contain the list of books
            """)
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/library/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray()); // Verifică că răspunsul este un array
    }

    @Test
    @DisplayName("""
        Given a valid book object with a user and category
        When a request is made to add the book
        Then the book should be added and returned in the response
        """)
    void testAddBook() throws Exception {
        // Arrange
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setProgress(30.0);


        Category category = new Category();
        category.setId(1L);
        book.setCategory(category);

        // Adaugă un utilizator valid
        User user = new User();
        user.setId(1L);
        book.setUser(user);


        mockMvc.perform(post("/library/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.user.id").value(1L))
                .andExpect(jsonPath("$.category.id").value(1L));
    }


    @Test
    @DisplayName("""
            Given a book exists with a specific ID
            When a request is made to update the progress
            Then the progress should be updated and returned in the response
            """)
    void testUpdateProgress() throws Exception {
        // Arrange
        Long bookId = 1L;
        Double newProgress = 50.0;

        // Act & Assert
        mockMvc.perform(put("/library/books/{id}/progress", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProgress)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(bookId))
                .andExpect(jsonPath("$.progress").value(newProgress));
    }
}
