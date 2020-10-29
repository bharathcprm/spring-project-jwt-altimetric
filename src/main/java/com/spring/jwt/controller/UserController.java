package com.spring.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.model.UserDto;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto userDto) {
		UserDto user = userService.register(userDto);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/user/{userName}")
	public ResponseEntity<?> getUser(@PathVariable String userName) {
		UserDto userDto = userService.getUser(userName);
		return ResponseEntity.ok(userDto);
	}
}
