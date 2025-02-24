package com.example.mobilepj.repository;

import com.example.mobilepj.entity.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlcoholRepository extends JpaRepository<Alcohol, Integer> {
    Alcohol findByBarcode(String barcode);
}
