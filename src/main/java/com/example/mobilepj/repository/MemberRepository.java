package com.example.mobilepj.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mobilepj.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    // 추가적인 메소드 정의
    Optional<Member> findByNickname(String nickname);
    // Optional<Member> findByPhone(String phone);
    Optional<Member> findByIdAndPassword(String id, String password);
}
