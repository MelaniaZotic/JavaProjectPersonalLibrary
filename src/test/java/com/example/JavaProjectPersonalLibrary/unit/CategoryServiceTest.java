package com.example.JavaProjectPersonalLibrary.unit;

import com.example.JavaProjectPersonalLibrary.entities.Category;
import com.example.JavaProjectPersonalLibrary.repositories.CategoryRepository;
import com.example.JavaProjectPersonalLibrary.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
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
    void testGetAllCategories() {
        // Arrange
        Category category1 = new Category();
        category1.setName("Fiction");

        Category category2 = new Category();
        category2.setName("Non-Fiction");

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        // Act
        List<Category> categories = categoryService.getAllCategories();

        // Assert
        assertNotNull(categories);
        assertEquals(2, categories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testAddCategory() {
        // Arrange
        Category category = new Category();
        category.setName("Science");

        when(categoryRepository.save(category)).thenReturn(category);

        // Act
        Category result = categoryService.addCategory(category);

        // Assert
        assertNotNull(result);
        assertEquals("Science", result.getName());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testDeleteCategory() {
        // Arrange
        Long categoryId = 1L;

        doNothing().when(categoryRepository).deleteById(categoryId);

        // Act
        categoryService.deleteCategory(categoryId);

        // Assert
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
