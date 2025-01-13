package com.example.JavaProjectPersonalLibrary.unit.controllers;

import com.example.JavaProjectPersonalLibrary.controllers.ReviewController;
import com.example.JavaProjectPersonalLibrary.entities.Review;
import com.example.JavaProjectPersonalLibrary.services.ReviewService;
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

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    @DisplayName("""
            Given reviews exist for a specific book
            When getReviewsByBook is called with the book ID
            Then it should return the list of reviews
            """)
    void testGetReviewsByBook() {
        Long bookId = 1L;

        Review review1 = new Review();
        review1.setId(1L);
        review1.setContent("Great book!");
        review1.setRating(5);

        Review review2 = new Review();
        review2.setId(2L);
        review2.setContent("Not bad.");
        review2.setRating(3);

        when(reviewService.getReviewsByBook(bookId)).thenReturn(Arrays.asList(review1, review2));


        List<Review> reviews = reviewController.getReviewsByBook(bookId);


        assertNotNull(reviews);
        assertEquals(2, reviews.size());
        assertEquals("Great book!", reviews.get(0).getContent());
        assertEquals(5, reviews.get(0).getRating());
        assertEquals("Not bad.", reviews.get(1).getContent());
        verify(reviewService, times(1)).getReviewsByBook(bookId);
    }

    @Test
    @DisplayName("""
            Given a valid review object
            When addReview is called
            Then it should save and return the review
            """)
    void testAddReview() {

        Review review = new Review();
        review.setId(1L);
        review.setContent("Excellent book!");
        review.setRating(5);

        when(reviewService.addReview(any(Review.class))).thenReturn(review);


        Review result = reviewController.addReview(review);


        assertNotNull(result);
        assertEquals("Excellent book!", result.getContent());
        assertEquals(5, result.getRating());
        verify(reviewService, times(1)).addReview(any(Review.class));
    }
}
