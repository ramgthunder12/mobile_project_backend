package com.example.mobilepj.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mobilepj.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
    List<Review> findAllByAlcoholNumber(int alcoholNumber);
}
