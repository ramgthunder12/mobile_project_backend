package com.example.mobilepj.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    private int reviewNumber;
    private String nickname;
    private String common;
    private double reviewStarpoint;
    private Date creationDate;
    private int alcoholNumber;
    private String reviewInfo;
    private byte[] picture;

    public Review() {
        // 기본 생성자
    }

    public Review(int reviewNumber, String nickname, String common, double reviewStarpoint, Date creationDate, int alcoholNumber, String reviewInfo, byte[] picture) {
        this.reviewNumber = reviewNumber;
        this.nickname = nickname;
        this.common = common;
        this.reviewStarpoint = reviewStarpoint;
        this.creationDate = creationDate;
        this.alcoholNumber = alcoholNumber;
        this.reviewInfo = reviewInfo;
        this.picture = picture;
    }

    // Getter와 Setter 메소드들

    public int getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public double getReviewStarpoint() {
        return reviewStarpoint;
    }

    public void setReviewStarpoint(double reviewStarpoint) {
        this.reviewStarpoint = reviewStarpoint;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getAlcoholNumber() {
        return alcoholNumber;
    }

    public void setAlcoholNumber(int alcoholNumber) {
        this.alcoholNumber = alcoholNumber;
    }

    public String getReviewInfo() {
        return reviewInfo;
    }

    public void setReviewInfo(String reviewInfo) {
        this.reviewInfo = reviewInfo;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}

