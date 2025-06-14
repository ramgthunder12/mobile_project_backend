package com.example.mobilepj.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobilepj.dto.AlcoholRequestDto;
import com.example.mobilepj.entity.Alcohol;
import com.example.mobilepj.service.AlcoholService;


@RestController
@RequestMapping("/alcohols")
public class AlcoholController {

    private final AlcoholService alcoholService;

    @Autowired
    public AlcoholController(AlcoholService alcoholService) {
        this.alcoholService = alcoholService;
    }
    ///serch/0010
    @GetMapping("/serch/{barcode}")
    public Alcohol serchingAlcoholBybarcode(@PathVariable String barcode) {
        return alcoholService.getAlcoholInfo(barcode);
    }
    ///1
    @GetMapping("/{alcoholNumber}")
    public Optional<Alcohol> serchingAlcoholByAlcoholNumber(@PathVariable int alcoholNumber) {
        return alcoholService.getAlcoholInfo(alcoholNumber);
    }

    @GetMapping("/")
    public List<Alcohol> alcoholList() {
        return alcoholService.getAllAlcohols();
    }

//    @GetMapping("/")
//    public List<Alcohol> alcoholList(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10") int size) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
//        return AlcoholService.getAllAlcohols();
//    }
    @PostMapping("/")
    public ResponseEntity<Alcohol> addAlcohol(@RequestBody AlcoholRequestDto dto) {
    	Alcohol saved = alcoholService.saveAlcohol(dto);
    	return ResponseEntity.ok(saved);
    }
}
