package com.example.jwth2;

import com.example.jwth2.entity.User;
import com.example.jwth2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JwtH2Application {

	@Autowired
	private UserRepo userRepo;
	//add user Object in our Db
	@PostConstruct
	public void initUsers(){
		List<User> users = Stream.of(
				new User(101,"oli","root","oli123@gmail.com"),
		        new User(102,"oli1","root","oli1234@gmail.com")
		).collect(Collectors.toList());
		userRepo.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(JwtH2Application.class, args);
	}

}
