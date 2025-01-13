package com.example.JavaProjectPersonalLibrary.unit.controllers;


import com.example.JavaProjectPersonalLibrary.controllers.CategoryController;
import com.example.JavaProjectPersonalLibrary.entities.Category;
import com.example.JavaProjectPersonalLibrary.services.CategoryService;
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

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("""
            Given categories exist in the database
            When getAllCategories is called
            Then it should return a list of categories
            """)
    void testGetAllCategories() {
        // Arrange
        Category category1 = new Category();
        category1.setName("Fiction");

        Category category2 = new Category();
        category2.setName("Non-Fiction");

        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryController.getAllCategories();


        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertEquals("Fiction", categories.get(0).getName());
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    @DisplayName("""
            Given a valid category object
            When addCategory is called
            Then it should save and return the category
            """)
    void testAddCategory() {

        Category category = new Category();
        category.setName("Science");

        when(categoryService.addCategory(any(Category.class))).thenReturn(category);


        Category result = categoryController.addCategory(category);

        assertNotNull(result);
        assertEquals("Science", result.getName());
        verify(categoryService, times(1)).addCategory(any(Category.class));
    }

    @Test
    @DisplayName("""
            Given a category ID
            When deleteCategory is called
            Then it should delete the category and perform no return
            """)
    void testDeleteCategory() {
        Long categoryId = 1L;
        doNothing().when(categoryService).deleteCategory(categoryId);


        categoryController.deleteCategory(categoryId);

        verify(categoryService, times(1)).deleteCategory(categoryId);
    }
}
