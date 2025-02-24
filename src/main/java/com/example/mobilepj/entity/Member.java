package com.example.mobilepj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Builder.Default;

@Entity
public class Member {
    @Id
    private String id;
    @Column(unique = true)
    private String nickname;
    private String password;
    @Column(unique = true)
    private String phone;
    @Column(columnDefinition = "int default 1")
    @Builder.Default
    private int grade = 1;
    private int point;
    private int view_num;
    private int tastenote_num;
    private float starpoint;

    public Member() {

    }

    public Member(String nickname, String id, String password, String phone) {
        this.nickname = nickname;
        this.id = id;
        this.password = password;
        this.phone = phone;
    }
    public String getNickname() {
        return this.nickname;
    }

    public void setNikname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getView_num() {
        return this.view_num;
    }

    public void setView_num(int view_num) {
        this.view_num = view_num;
    }

    public int getTastenote_num() {
        return this.tastenote_num;
    }

    public void setTastenote_num(int tastenote_num) {
        this.tastenote_num = tastenote_num;
    }

    public float getStarpoint() {
        return this.starpoint;
    }

    public void setStarpoint(float starpoint) {
        this.starpoint = starpoint;
    }

    public Member orElse(Object object) {
        return null;
    }

}
