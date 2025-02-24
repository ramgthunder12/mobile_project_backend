package com.example.mobilepj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mobilepj.entity.Alcohol;
import com.example.mobilepj.repository.AlcoholRepository;

@Service
public class AlcoholService {
    private final AlcoholRepository AlcoholRepository;

    @Autowired
    public AlcoholService(AlcoholRepository AlcoholRepository) {
        this.AlcoholRepository = AlcoholRepository;
    }

    public Alcohol getAlcoholInfo(String barcode) {
        return AlcoholRepository.findByBarcode(barcode);
    }

    public Optional<Alcohol> getAlcoholInfo(int alcoholNumber) {
        return AlcoholRepository.findById(alcoholNumber);
    }

    public List<Alcohol> getAllAlcohols() {
        return AlcoholRepository.findAll();
    }

}
