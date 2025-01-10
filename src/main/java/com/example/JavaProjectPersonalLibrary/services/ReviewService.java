package com.example.JavaProjectPersonalLibrary.services;

import com.example.JavaProjectPersonalLibrary.entities.Review;
import com.example.JavaProjectPersonalLibrary.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByBook(Long bookId) {
        return reviewRepository.findAllByBookId(bookId);
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}