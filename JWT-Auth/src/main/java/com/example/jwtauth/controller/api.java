package com.example.jwtauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class api {
    @GetMapping("/welcome")
    public String welcome(){
        return "<h1>This is Private page, Not allowed for unauthenticated User</h1>";
    }
}
