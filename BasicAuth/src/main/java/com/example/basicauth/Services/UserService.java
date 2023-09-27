package com.example.basicauth.Services;

import com.example.basicauth.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    List<User> list = new ArrayList<>();
    //fake Users
    UserService(){
        list.add(new User("Oli","oli","oli@gmial.com"));
        list.add(new User("Oli1","oli1","oli1@gmial.com"));
    }
    public List<User> getAllUser(){
        return list;
    }
    public User getUser(String name){
     return list.stream().filter(user -> user.getUserName().equals(name)).findAny().get();
    }
    public User addUser(User user){
      list.add(user);
      return user;
    }
}
