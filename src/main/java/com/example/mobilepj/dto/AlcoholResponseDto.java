package com.example.mobilepj.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.mobilepj.entity.Alcohol;
import com.example.mobilepj.entity.Scent;
import com.example.mobilepj.entity.Taste;

import lombok.Data;

@Data
public class AlcoholResponseDto {
    private int alcoholNumber;
    private String name;
    private String barcode;
    private String category;
    private String volume;
    private String price;
    private BigDecimal content;
    private BigDecimal avgStar;
    private Integer ibu;
    private String tasteDetail;
    private String detail;
    private List<TasteDto> tastes;
    private List<ScentDto> scents;

    @Data
    public static class TasteDto {
        private int tasteNumber;
        private String tasteInfo;

        public static TasteDto from(Taste taste) {
            TasteDto dto = new TasteDto();
            dto.setTasteNumber(taste.getTasteNumber());
            dto.setTasteInfo(taste.getTasteInfo());
            return dto;
        }
    }

    @Data
    public static class ScentDto {
        private int scentNumber;
        private String scentInfo;

        public static ScentDto from(Scent scent) {
            ScentDto dto = new ScentDto();
            dto.setScentNumber(scent.getScentNumber());
            dto.setScentInfo(scent.getScentInfo());
            return dto;
        }
    }

    public static AlcoholResponseDto from(Alcohol alcohol) {
        AlcoholResponseDto dto = new AlcoholResponseDto();
        dto.setAlcoholNumber(alcohol.getAlcoholNumber());
        dto.setName(alcohol.getName());
        dto.setBarcode(alcohol.getBarcode());
        dto.setCategory(alcohol.getCategory());
        dto.setVolume(alcohol.getVolume());
        dto.setPrice(alcohol.getPrice());
        dto.setContent(alcohol.getContent());
        dto.setAvgStar(alcohol.getAvgStar());
        dto.setIbu(alcohol.getIbu());
        dto.setTasteDetail(alcohol.getTasteDetail());
        dto.setDetail(alcohol.getDetail());
        dto.setTastes(alcohol.getTastes() != null
                ? alcohol.getTastes().stream().map(TasteDto::from).collect(Collectors.toList())
                : Collections.emptyList());
        dto.setScents(alcohol.getScents() != null
                ? alcohol.getScents().stream().map(ScentDto::from).collect(Collectors.toList())
                : Collections.emptyList());
        return dto;
    }
}
