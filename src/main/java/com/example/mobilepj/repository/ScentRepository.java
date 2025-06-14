package com.example.mobilepj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mobilepj.entity.Scent;

@Repository
public interface ScentRepository extends JpaRepository<Scent, Integer>{

}
