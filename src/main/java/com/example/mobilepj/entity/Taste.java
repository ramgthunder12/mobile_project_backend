package com.example.mobilepj.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Taste {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tasteNumber;
	
	private String tasteInfo;
	
	@ManyToMany(mappedBy = "tastes")//엔티티 필드명 찾아감
	@JsonIgnore
	private List<Alcohol> alcohols;

	public int getTasteNumber() {
		return tasteNumber;
	}

	public void setTasteNumber(int tasteNumber) {
		this.tasteNumber = tasteNumber;
	}

	public String getTasteInfo() {
		return tasteInfo;
	}

	public void setTasteInfo(String tasteInfo) {
		this.tasteInfo = tasteInfo;
	}

	public List<Alcohol> getAlcohols() {
		return alcohols;
	}
}
