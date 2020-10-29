package com.spring.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.model.UserDto;
import com.spring.jwt.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto userDto) {
		UserDto user = userRepository.save(userDto);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/user/{userName}")
	public ResponseEntity<?> getUser(@PathVariable String userName) {
		UserDto userDto = userRepository.findByUserName(userName);
		return ResponseEntity.ok(userDto);
	}
}
