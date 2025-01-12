package com.example.JavaProjectPersonalLibrary.integration;

import com.example.JavaProjectPersonalLibrary.entities.Category;
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
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("""
            Given there are categories in the database
            When the endpoint to get all categories is called
            Then it should return the list of categories with status 200 OK
            """)
    public void testGetAllCategories() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("""
            Given a valid category object
            When the category is posted to the endpoint
            Then it should create the category and return it with status 200 OK
            """)
    public void testAddCategory() throws Exception {
        Category category = new Category();
        category.setName("Test Category");

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Category"));
    }
    @Test
    @DisplayName("""
            Given a category exists in the database
            When the delete endpoint is called with the category ID
            Then it should delete the category with status 200 OK
            """)
    public void testDeleteCategory() throws Exception {
        mockMvc.perform(delete("/categories/{id}", 1L))
                .andExpect(status().isOk());
    }
}

