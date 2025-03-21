package com.example.mobilepj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.mobilepj.entity.Review;
import com.example.mobilepj.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {


    private final ReviewService ReviewService;

    @Autowired
    public ReviewController(ReviewService ReviewService) {
        this.ReviewService = ReviewService;
    }

    @GetMapping("/{alcoholNumber}")
    public List<Review> reviewList(@PathVariable int alcoholNumber) {
        return ReviewService.reviewListByAlcoholNumber(alcoholNumber);
    }

    @PostMapping("/")
    public boolean addReview(@RequestBody Review review) {
        return ReviewService.addReview(review);
    }
}
