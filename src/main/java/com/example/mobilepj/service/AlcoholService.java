package com.example.mobilepj.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mobilepj.dto.AlcoholRequestDto;
import com.example.mobilepj.entity.Alcohol;
import com.example.mobilepj.entity.Scent;
import com.example.mobilepj.entity.Taste;
import com.example.mobilepj.repository.AlcoholRepository;
import com.example.mobilepj.repository.ScentRepository;
import com.example.mobilepj.repository.TasteRepository;

@Service
public class AlcoholService {
    private final AlcoholRepository alcoholRepository;

    @Autowired
    private TasteRepository tasteRepository;

    @Autowired
    private ScentRepository scentRepository;
    
    @Autowired
    public AlcoholService(AlcoholRepository alcoholRepository) {
        this.alcoholRepository = alcoholRepository;
    }

    public Alcohol getAlcoholInfo(String barcode) {
        return alcoholRepository.findByBarcode(barcode);
    }

    public Optional<Alcohol> getAlcoholInfo(int alcoholNumber) {
        return alcoholRepository.findById(alcoholNumber);
    }

    public List<Alcohol> getAllAlcohols() {
        return alcoholRepository.findAll();
    }
    
    @Transactional
    public Alcohol saveAlcohol(AlcoholRequestDto dto) {
    	Alcohol alcohol = new Alcohol();
        alcohol.setName(dto.getName());
        alcohol.setBarcode(dto.getBarcode());
        alcohol.setCategory(dto.getCategory());
        alcohol.setVolume(dto.getVolume());
        alcohol.setPrice(dto.getPrice());
        alcohol.setContent(dto.getContent());
        alcohol.setAvgStar(dto.getAvgStar());
        alcohol.setIbu(dto.getIbu());
        alcohol.setTasteDetail(dto.getTasteDetail());
        alcohol.setDetail(dto.getDetail());
        
        alcohol = alcoholRepository.save(alcohol);
        
        List<Taste> tastes = tasteRepository.findAllById(dto.getTasteIds());
        for (Taste taste : tastes) {
            alcohol.addTaste(taste);
        }
        
        List<Scent> scents = scentRepository.findAllById(dto.getScentIds());
        for (Scent scent : scents) {
            alcohol.addScent(scent);
        }
    	
        return alcoholRepository.save(alcohol);
    } 
}
