package com.spring.jwt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.jwt.model.UserDto;
import com.spring.jwt.repository.UserRepository;

@SpringBootApplication
public class SpringBootJwtAltimetricApplication {

	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void initUsers() {
		List<UserDto> users = Stream.of(new UserDto(1,"test","test"),
		new UserDto(2,"root","root")).collect(Collectors.toList());
		userRepository.saveAll(users);
		}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtAltimetricApplication.class, args);
	}

}
