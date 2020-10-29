package com.spring.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jwt.config.JwtTokenUtil;
import com.spring.jwt.model.UserDto;
import com.spring.jwt.service.UserService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManger;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createToken(@RequestBody UserDto userDto) throws Exception {
		try {
			authenticationManger.authenticate(
					new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));

			UserDetails userDetails = userService.loadUserByUsername(userDto.getUserName());
			String token = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(token);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials", e);
		}

	}

	@GetMapping("/refresh/{userName}")
	public ResponseEntity<?> refreshToken(@PathVariable String userName) {

		UserDetails userDetails = userService.loadUserByUsername(userName);
		String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(token);
	}

}
