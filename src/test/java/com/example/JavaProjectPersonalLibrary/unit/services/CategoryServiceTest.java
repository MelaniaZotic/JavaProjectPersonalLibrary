package com.example.JavaProjectPersonalLibrary.unit.services;

import com.example.JavaProjectPersonalLibrary.entities.Category;
import com.example.JavaProjectPersonalLibrary.repositories.CategoryRepository;
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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

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

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));


        List<Category> categories = categoryService.getAllCategories();


        assertNotNull(categories);
        assertEquals(2, categories.size());
        verify(categoryRepository, times(1)).findAll();
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

        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.addCategory(category);

        assertNotNull(result);
        assertEquals("Science", result.getName());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    @DisplayName("""
            Given a category exists in the database
            When deleteCategory is called with the category ID
            Then it should delete the category
            """)
    void testDeleteCategory() {
        Long categoryId = 1L;
        doNothing().when(categoryRepository).deleteById(categoryId);
        categoryService.deleteCategory(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
