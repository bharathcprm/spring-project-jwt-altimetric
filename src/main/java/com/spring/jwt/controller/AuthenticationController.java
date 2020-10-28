package com.spring.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createToken(){
		
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping("/refresh")
	public ResponseEntity<?> refreshToken(){
		
		return ResponseEntity.ok(HttpStatus.OK);
	}

}
