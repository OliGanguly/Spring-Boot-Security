package com.example.jwtauth.model;

public class JwtReq {
    String userName;
    String password;

    public JwtReq() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
