package com.example.mobilepj.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AlcoholRequestDto {
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
    private List<Integer> tasteIds;
    private List<Integer> scentIds;
}
