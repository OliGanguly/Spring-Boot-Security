package com.example.jwth2.filter;

import com.example.jwth2.service.CustomUserDetailsService;
import com.example.jwth2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //extract authorization header
        String authorizationHeader = request.getHeader("Authorization");
        String token=null;
        String userName=null;

      //Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJvbGkxIiwiZXhwIjoxNjk1OTUzNDM5LCJpYXQiOjE2OTU5MTc0Mzl9.ZmfX3T-9F9ruepamPBt5_0PyJQ7U-RRotfUKTCm4i1Jaa5r0vlYLnSrDzCq6EHW5
      if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer")){
          token=authorizationHeader.substring(7);//get rest part
          try{
              userName = jwtUtil.extractUsername(token);
          }catch (Exception e){
                e.printStackTrace();
          }

      }

      if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
          UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
          if(jwtUtil.validateToken(token,userDetails)){
              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                      = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
              usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          }
      }

      filterChain.doFilter(request,response);
    }
}
