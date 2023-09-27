package com.example.basicauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigMy extends WebSecurityConfigurerAdapter {

    //create own user with name pass roles using builder design pattern
    //THIS IS AUTHENTICATION
    //inMemory is server memory
    //help us to create user
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
               .withUser("user")
               .password(this.getPasswordEncoder().encode("root"))
               .roles("USER")
               .and()
               .withUser("admin")
               .password("root")
               .roles("ADMIN");
    }



    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
    //if you dont encrypt your pass , if u want to store it in form of a plain text
    //NoOpPasswordEncoder is a singleton class
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }


    //authorization
    //   http.authorizeRequests().anyRequest().authenticated().and().httpBasic() - url become protected
    //Baic authentication
    //Insteade of usinng .authenticated() we can use denyAll to deny all req
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//     http.authorizeRequests()
//             .anyRequest()
//             .authenticated()
//             .and()
////              .httpBasic()
////              .and()
//              .formLogin();
//    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .antMatchers("/users/admin").hasRole("ADMIN")
                .antMatchers("/users/user").hasRole("USER")
                .antMatchers("/").permitAll()
                .and()
                .formLogin();
    }



}
