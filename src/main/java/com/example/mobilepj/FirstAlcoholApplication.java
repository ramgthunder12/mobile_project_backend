package com.example.mobilepj;

import com.example.mobilepj.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class FirstAlcoholApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FirstAlcoholApplication.class, args);
        UserService userService = context.getBean(UserService.class);
    }
}
