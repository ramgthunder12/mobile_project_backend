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

	public List<Alcohol> getAlcohols() {
		return alcohols;
	}
}
