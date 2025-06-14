package com.example.mobilepj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mobilepj.entity.Taste;

@Repository
public interface TasteRepository extends JpaRepository<Taste, Integer>{

}
