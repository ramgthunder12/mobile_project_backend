package com.example.mobilepj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mobilepj.entity.Tastenote;
import com.example.mobilepj.repository.TastenoteRepository;

@Service
public class TastenoteService {
    private final TastenoteRepository TastenoteRepository;

    @Autowired
    public TastenoteService(TastenoteRepository TastenoteRepository) {
        this.TastenoteRepository = TastenoteRepository;
    }
    public List<Tastenote> getAllTastenotes() {
        return TastenoteRepository.findAll();
    }
}
