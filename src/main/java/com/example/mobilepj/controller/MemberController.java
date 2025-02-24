package com.example.mobilepj.controller;

import com.example.mobilepj.entity.Member;
import com.example.mobilepj.entity.User;
import com.example.mobilepj.service.MemberService;
import com.example.mobilepj.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService MemberService;

    @Autowired
    public MemberController(MemberService MemberService) {
        this.MemberService = MemberService;
    }

    @GetMapping("/")
    public List<Member> getAllMembers() {
        return MemberService.getAllMembers();
    }
    ///checkId?id=id
    @GetMapping("/checkId")
    public boolean checkIdExists(@RequestParam String id) {
        return MemberService.checkIdExists(id);
    }
    ///checkNickname?nickname=nickname
    @GetMapping("/checkNickname")
    public boolean checkNicknameExists(@RequestParam String nickname) {
        return MemberService.checkNicknameExists(nickname);
    }
    //{"nickname" : "TestNickna", "id" : "TestId", "password" : "TestPassword", "phone" : "010-0001-0002"}
    @PostMapping("/signup")
    public boolean signupMember(@RequestBody Member member) {
        return MemberService.addMember(member);
    }
    //{"id" : "TestId", "password" : "TestPassword"}
    @PostMapping("/login")
    public boolean login(@RequestBody Map<String, String> credentials) {
        String id = credentials.get("id");
        String password = credentials.get("password");
        return MemberService.checkIdAndPasswordExists(id, password);
    }
    ///Testid
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return MemberService.getMemberById(id);
    }
  
}
