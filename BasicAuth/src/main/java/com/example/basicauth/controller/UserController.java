package com.example.basicauth.controller;

import com.example.basicauth.Models.User;
import com.example.basicauth.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public String user(){
        return "<h1>I am User</h1>";
    }
    @GetMapping("/admin")
    public String admin(){
        return "<h1>I am Admin</h1>";
    }
    @GetMapping("/")
    public List<User> getAllUserMethod()
    {
        List<User> users = userService.getAllUser();
        return  users;
    }
    @GetMapping("/{username}")
    public User getOneUser(@PathVariable String username){
        User u = userService.getUser(username);
        return  u;
    }
    @PostMapping("/add")
    public User addUserMethod(@RequestBody User user){
     return userService.addUser(user);
    }
}
