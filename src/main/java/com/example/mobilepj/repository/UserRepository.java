package com.example.mobilepj.repository;

import com.example.mobilepj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 추가적인 메소드 정의

}
