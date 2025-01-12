package com.example.JavaProjectPersonalLibrary.unit;
import com.example.JavaProjectPersonalLibrary.entities.Review;
import com.example.JavaProjectPersonalLibrary.repositories.ReviewRepository;
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
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("""
            Given there are reviews for a specific book ID in the database
            When getReviewsByBook is called with that book ID
            Then it should return the list of reviews for the book
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

        when(reviewRepository.findAllByBookId(bookId)).thenReturn(Arrays.asList(review1, review2));

        List<Review> reviews = reviewService.getReviewsByBook(bookId);

        assertNotNull(reviews);
        assertEquals(2, reviews.size());
        assertEquals("Great book!", reviews.get(0).getContent());
        verify(reviewRepository, times(1)).findAllByBookId(bookId);
    }

    @Test
    @DisplayName("""
            Given a valid review object
            When addReview is called with the review
            Then it should save and return the review
            """)
    void testAddReview() {
        Review review = new Review();
        review.setId(1L);
        review.setContent("Amazing read!");
        review.setRating(5);

        when(reviewRepository.save(review)).thenReturn(review);

        Review savedReview = reviewService.addReview(review);

        assertNotNull(savedReview);
        assertEquals("Amazing read!", savedReview.getContent());
        assertEquals(5, savedReview.getRating());
        verify(reviewRepository, times(1)).save(review);
    }
}