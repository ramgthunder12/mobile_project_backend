package com.example.mobilepj.controller;


import com.example.mobilepj.entity.User;
import com.example.mobilepj.service.UserService;

import java.util.List;

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
@RequestMapping("/users")
public class UserController {

    private final UserService UserService;

    @Autowired
    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return UserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return UserService.getUserById(id);
    }

    @PostMapping("/")
    public User addUser(@RequestBody User User) {
        return UserService.addUser(User);
    }

    @PostMapping("/login")
    public User login(@RequestBody User User) {
        return UserService.addUser(User);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User User) {
        return UserService.updateUser(id, User);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        UserService.deleteUser(id);
    }
    
    @GetMapping("/search")
    public User getUsersByIdAndName(@RequestParam Long id, @RequestParam String name) {
        return UserService.getUsersByIdAndName(id, name);
    }
}

