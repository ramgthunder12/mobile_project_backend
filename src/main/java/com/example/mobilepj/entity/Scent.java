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
public class Scent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scentNumber;
	
	private String scentInfo;
	
	@ManyToMany(mappedBy = "scents")
	@JsonIgnore
	private List<Alcohol> alcohols;

	public int getScentNumber() {
		return scentNumber;
	}

	public void setScentNumber(int scentNumber) {
		this.scentNumber = scentNumber;
	}

	public String getScentInfo() {
		return scentInfo;
	}

	public void setScentInfo(String scentInfo) {
		this.scentInfo = scentInfo;
	}

	public List<Alcohol> getAlcohols() {
		return alcohols;
	}
}
