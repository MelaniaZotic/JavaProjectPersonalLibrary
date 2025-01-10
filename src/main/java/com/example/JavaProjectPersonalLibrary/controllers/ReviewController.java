package com.example.JavaProjectPersonalLibrary.controllers;

import com.example.JavaProjectPersonalLibrary.entities.Review;
import com.example.JavaProjectPersonalLibrary.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Tag(name = "Review Management", description = "Operations related to managing book reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Operation(summary = "Get all reviews", description = "Retrieve all reviews for a specific book")
    @GetMapping("/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewService.getReviewsByBook(bookId);
    }

    @Operation(summary = "Add a review", description = "Add a new review for a book")
    @PostMapping
    public Review addReview(@RequestBody @Valid Review review) {
        return reviewService.addReview(review);
    }
}