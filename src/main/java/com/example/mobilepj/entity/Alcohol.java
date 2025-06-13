package com.example.mobilepj.entity;

import java.math.BigDecimal;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;

import java.util.ArrayList;
//import javax.persistence.*;
import java.util.List;

@Entity
public class Alcohol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
//    private String taste;
//    private String aroma;
    private String detail;
    
    @ManyToMany
    @JoinTable(
    		name = "alcohol_taste",
    		joinColumns = @JoinColumn(name = "alcohol_number"),
    		inverseJoinColumns = @JoinColumn(name = "taste_number")
    )
    private List<Taste> tastes = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
    		name = "alcohol_scent",
    		joinColumns = @JoinColumn(name = "alcohol_number"),
    		inverseJoinColumns = @JoinColumn(name = "scent_number")
    )
    private List<Scent> scents = new ArrayList<>();
    public Alcohol() {
    }

    public Alcohol(int alcoholNumber, String name, String barcode, String category, String volume, String price, BigDecimal content, BigDecimal avgStar, Integer ibu, String tasteDetail, String taste, String aroma, String detail, List<Taste> tastes, List<Scent> scents) {
        this.alcoholNumber = alcoholNumber;
        this.name = name;
        this.barcode = barcode;
        this.category = category;
        this.volume = volume;
        this.price = price;
        this.content = content;
        this.avgStar = avgStar;
        this.ibu = ibu;
        this.tasteDetail = tasteDetail;
//        this.taste = taste;
//        this.aroma = aroma;
        this.detail = detail;
        this.tastes = tastes;
        this.scents = scents;
    }

    public int getAlcoholNumber() {
        return this.alcoholNumber;
    }

    public void setAlcoholNumber(int alcoholNumber) {
        this.alcoholNumber = alcoholNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public BigDecimal getContent() {
        return this.content;
    }

    public void setContent(BigDecimal content) {
        this.content = content;
    }

    public BigDecimal getAvgStar() {
        return this.avgStar;
    }

    public void setAvgStar(BigDecimal avgStar) {
        this.avgStar = avgStar;
    }

    public Integer getIbu() {
        return this.ibu;
    }

    public void setIbu(Integer ibu) {
        this.ibu = ibu;
    }

    public String getTasteDetail() {
        return this.tasteDetail;
    }

    public void setTasteDetail(String tasteDetail) {
        this.tasteDetail = tasteDetail;
    }

//    public String getTaste() {
//        return this.taste;
//    }
//
//    public void setTaste(String taste) {
//        this.taste = taste;
//    }
//
//    public String getAroma() {
//        return this.aroma;
//    }
//
//    public void setAroma(String aroma) {
//        this.aroma = aroma;
//    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<Taste> getTastes() {
    	return this.tastes;
    }
    
	public void setTastes(List<Taste> tastes) {
		this.tastes = tastes;
	}

	public void setScents(List<Scent> scents) {
		this.scents = scents;
	}
    
	public List<Scent> getScents() {
		return this.scents;
	}
	
	public void addTaste(Taste taste) {
	    this.tastes.add(taste);
	    taste.getAlcohols().add(this); // 양방향 설정
	}

	public void addScent(Scent scent) {
	    this.scents.add(scent);
	    scent.getAlcohols().add(this);
	}
}
