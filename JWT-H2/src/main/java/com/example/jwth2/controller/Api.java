package com.example.jwth2.controller;

import com.example.jwth2.entity.AuthRequest;
import com.example.jwth2.entity.AuthResp;
import com.example.jwth2.service.CustomUserDetailsService;
import com.example.jwth2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

    //we need few things to generate token
//    1.jwt, 2.CustomYserDetailsService,2.AuthenticationManager
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
     @Autowired
     private AuthenticationManager authenticationManager;
    @GetMapping("/")
    public String welcome(){
        return "<h1>Welcome </h1>";
    }

    //need to pass user Name and password so need to create a dto
    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        System.out.println("hiiii"+ authRequest);
        //authentication
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUser_name(),
                            authRequest.getPassword()
                    )
            );
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Invalid User Name and password");
        }
       //fine area
        //generate Token

        //fetch User deatils
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUser_name());
        final String token = jwtUtil.generateToken(userDetails);
        System.out.println("token"+token);
//        {"token":"Value"}
        return ResponseEntity.ok(new AuthResp(token));
        //Now use this token to access other endpoints
        //now tell spring to extract user name pass from that token
    }
}
