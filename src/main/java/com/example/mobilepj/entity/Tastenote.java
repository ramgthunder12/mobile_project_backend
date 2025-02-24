package com.example.mobilepj.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tastenote {
    @Id
    private int tastenoteNumber;
    private String nickname;
    private int alcoholNumber;
    private double tastenoteStarpoint;
    private Date creationDate;
    private String tastenoteInfo;
    private String tastenoteFormat;
    private int tasteNumber;
    private int scentNumber;
    private String open;
    private Date tastingDay;
    private String memo;
    private String firstScent;
    private Date firstScentDate;
    private String middleScent;
    private Date middleScentDate;
    private String finalScent;
    private Date finalScentDate;
    private String glass;
    private String color;
    private int viscosity;
    private int sugar;

    public Tastenote() {
        // 기본 생성자
    }

    public Tastenote(int tastenoteNumber, String nickname, int alcoholNumber, double tastenoteStarpoint, Date creationDate, String tastenoteInfo, String tastenoteFormat, int tasteNumber, int scentNumber, String open, Date tastingDay, String memo, String firstScent, Date firstScentDate, String middleScent, Date middleScentDate, String finalScent, Date finalScentDate, String glass, String color, int viscosity, int sugar) {
        this.tastenoteNumber = tastenoteNumber;
        this.nickname = nickname;
        this.alcoholNumber = alcoholNumber;
        this.tastenoteStarpoint = tastenoteStarpoint;
        this.creationDate = creationDate;
        this.tastenoteInfo = tastenoteInfo;
        this.tastenoteFormat = tastenoteFormat;
        this.tasteNumber = tasteNumber;
        this.scentNumber = scentNumber;
        this.open = open;
        this.tastingDay = tastingDay;
        this.memo = memo;
        this.firstScent = firstScent;
        this.firstScentDate = firstScentDate;
        this.middleScent = middleScent;
        this.middleScentDate = middleScentDate;
        this.finalScent = finalScent;
        this.finalScentDate = finalScentDate;
        this.glass = glass;
        this.color = color;
        this.viscosity = viscosity;
        this.sugar = sugar;
    }

    // Getter와 Setter 메소드들

    public int getTastenoteNumber() {
        return tastenoteNumber;
    }

    public void setTastenoteNumber(int tastenoteNumber) {
        this.tastenoteNumber = tastenoteNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAlcoholNumber() {
        return alcoholNumber;
    }

    public void setAlcoholNumber(int alcoholNumber) {
        this.alcoholNumber = alcoholNumber;
    }

    public double getTastenoteStarpoint() {
        return tastenoteStarpoint;
    }

    public void setTastenoteStarpoint(double tastenoteStarpoint) {
        this.tastenoteStarpoint = tastenoteStarpoint;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTastenoteInfo() {
        return tastenoteInfo;
    }

    public void setTastenoteInfo(String tastenoteInfo) {
        this.tastenoteInfo = tastenoteInfo;
    }

    public String getTastenoteFormat() {
        return tastenoteFormat;
    }

    public void setTastenoteFormat(String tastenoteFormat) {
        this.tastenoteFormat = tastenoteFormat;
    }

    public int getTasteNumber() {
        return tasteNumber;
    }

    public void setTasteNumber(int tasteNumber) {
        this.tasteNumber = tasteNumber;
    }

    public int getScentNumber() {
        return scentNumber;
    }

    public void setScentNumber(int scentNumber) {
        this.scentNumber = scentNumber;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public Date getTastingDay() {
        return tastingDay;
    }

    public void setTastingDay(Date tastingDay) {
        this.tastingDay = tastingDay;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getFirstScent() {
        return firstScent;
    }

    public void setFirstScent(String firstScent) {
        this.firstScent = firstScent;
    }

    public Date getFirstScentDate() {
        return firstScentDate;
    }

    public void setFirstScentDate(Date firstScentDate) {
        this.firstScentDate = firstScentDate;
    }

    public String getMiddleScent() {
        return middleScent;
    }

    public void setMiddleScent(String middleScent) {
        this.middleScent = middleScent;
    }

    public Date getMiddleScentDate() {
        return middleScentDate;
    }

    public void setMiddleScentDate(Date middleScentDate) {
        this.middleScentDate = middleScentDate;
    }

    public String getFinalScent() {
        return finalScent;
    }

    public void setFinalScent(String finalScent) {
        this.finalScent = finalScent;
    }

    public Date getFinalScentDate() {
        return finalScentDate;
    }

    public void setFinalScentDate(Date finalScentDate) {
        this.finalScentDate = finalScentDate;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getViscosity() {
        return viscosity;
    }

    public void setViscosity(int viscosity) {
        this.viscosity = viscosity;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }
}
