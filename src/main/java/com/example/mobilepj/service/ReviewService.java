package com.example.mobilepj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mobilepj.entity.Review;
import com.example.mobilepj.repository.ReviewRepository;

@Service
public class ReviewService {
        private final ReviewRepository ReviewRepository;

    @Autowired
    public ReviewService(ReviewRepository ReviewRepository) {
        this.ReviewRepository = ReviewRepository;
    }

    public boolean addReview(Review review) {
        return ReviewRepository.saveAndFlush(review) != null;
    }

    public List<Review> reviewListByAlcoholNumber(int alcoholNumber) {
        return ReviewRepository.findAllByAlcoholNumber(alcoholNumber);
    } 
}
