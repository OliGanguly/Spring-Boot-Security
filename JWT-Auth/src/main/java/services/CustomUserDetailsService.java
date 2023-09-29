package services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public class CustomUserDetailsService implements UserDetailsService {
    /*
    return User
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //here we use db
        if(username.equals("oli"))
        {
            return new User("oli","root",new ArrayList<>()); //user,pass,roles
        }else{
            throw new UsernameNotFoundException("User Not Found");
        }

    }
}
