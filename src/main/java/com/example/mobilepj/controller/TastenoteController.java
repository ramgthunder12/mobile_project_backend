package com.example.mobilepj.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobilepj.entity.Tastenote;
import com.example.mobilepj.service.TastenoteService;


@RestController
@RequestMapping("/tastenote")
public class TastenoteController {
    private final TastenoteService TastenoteService;

    @Autowired
    public TastenoteController(TastenoteService TastenoteService) {
        this.TastenoteService = TastenoteService;
    }

    // @GetMapping("/")
    // public List<Tastenote> tastenoteList() {
    //     return TastenoteService.getAllTastenotes();
    // }

    
}
