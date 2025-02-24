package com.example.mobilepj.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mobilepj.entity.Member;
import com.example.mobilepj.entity.User;
import com.example.mobilepj.repository.MemberRepository;

@Service
public class MemberService {
    private final MemberRepository MemberRepository;

    @Autowired
    public MemberService(MemberRepository MemberRepository) {
        this.MemberRepository = MemberRepository;
    }

    public List<Member> getAllMembers() {
        return MemberRepository.findAll();
    }

    public boolean checkIdExists(String id) {
        Optional<Member> memberOptional = MemberRepository.findById(id);
        return memberOptional.isPresent();
    }

    public boolean checkNicknameExists(String nickname) {
        Optional<Member> memberOptional = MemberRepository.findByNickname(nickname);
        return memberOptional.isPresent();
    }

    // public boolean checkPasswordExists(String password) {
    //     Optional<Member> memberOptional = MemberRepository.findByPassword(password);
    //     return memberOptional.isPresent();
    // }

    public boolean addMember(Member member) {
        // 회원 저장
        Member savedMember = MemberRepository.save(member);
        return savedMember != null; // 회원 저장 여부에 따라 true 또는 false 반환
    }

    public boolean checkIdAndPasswordExists(String id, String password) {
        Optional<Member> memberOptional = MemberRepository.findByIdAndPassword(id, password);
        return memberOptional.isPresent();
    }

    public Member getMemberById(String id) {
        return MemberRepository.findById(id).orElse(null);
    }
    // public boolean checkPhoneExists(String phone) {
    //     Optional<Member> memberOptional = MemberRepository.findByPhone(phone);
    //     return memberOptional.isPresent();
    // }


    // public Member addMember(Member Member) {
    //     return MemberRepository.save(Member);
    // }

    // public Member updateMember(Long id, Member Member) {
    //     Member existingMember = MemberRepository.findById(id).orElse(null);
    //     if (existingMember != null) {
    //         existingMember.setId(Member.getId());
    //         existingMember.setPassword(Member.getPassword());
    //         return MemberRepository.save(existingMember);
    //     }
    //     return null;
    // }

    public void deleteMember(String id) {
        MemberRepository.deleteById(id);
    }

    public Optional<Member> getMemberByNickname(String nickname) {
        Optional<Member> member = MemberRepository.findById(nickname);
        return member;
    }

}
