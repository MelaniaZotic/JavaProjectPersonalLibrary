package com.example.JavaProjectPersonalLibrary.integration;

import com.example.JavaProjectPersonalLibrary.entities.Book;
import com.example.JavaProjectPersonalLibrary.entities.Category;
import com.example.JavaProjectPersonalLibrary.entities.User;
import com.example.JavaProjectPersonalLibrary.repositories.CategoryRepository;
import com.example.JavaProjectPersonalLibrary.repositories.UserRepository;
import com.example.JavaProjectPersonalLibrary.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/library/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddBook() throws Exception {
        // Arrange
        User user = new User();
        user.setName("Test User");
        user = userRepository.save(user); // Salvează utilizatorul pentru a obține un ID valid

        Category category = new Category();
        category.setName("Fiction");
        category = categoryRepository.save(category);

        String bookJson = """
            {
                "title": "Test Book",
                "author": "Test Author",
                "progress": 50.0,
                "user": { "id": """ + user.getId() + """ },
                "category": { "id": """ + category.getId() + """ }
            }
            """;

        // Act & Assert
        mockMvc.perform(post("/library/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.user.id").value(user.getId()));
    }

}
