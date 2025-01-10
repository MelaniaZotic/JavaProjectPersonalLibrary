package com.example.JavaProjectPersonalLibrary.controllers;

import com.example.JavaProjectPersonalLibrary.entities.Category;
import com.example.JavaProjectPersonalLibrary.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category Management", description = "Operations related to managing book categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Retrieve all book categories")
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Operation(summary = "Add a new category", description = "Add a new category to the library")
    @PostMapping
    public Category addCategory(@RequestBody @Valid Category category) {
        return categoryService.addCategory(category);
    }

    @Operation(summary = "Delete a category", description = "Delete a category by ID")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
