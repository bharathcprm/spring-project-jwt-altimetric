package com.spring.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.jwt.model.UserDto;
import com.spring.jwt.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UserDto userDto= userRepository.findByUserName(userName);
		if(userDto==null) {
			throw new UsernameNotFoundException("User Not Found :"+userName);
		}else {
			
			return new User(userDto.getUserName(), userDto.getPassword(), new ArrayList<>());
		}
	}
	
	public UserDto register(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		UserDto user = userRepository.save(userDto);
		return user;
	}

	public UserDto getUser(String userName) {
		UserDto userDto = userRepository.findByUserName(userName);
		return userDto;
	}

}
