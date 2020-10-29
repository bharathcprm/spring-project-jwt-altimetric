package com.spring.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.jwt.model.UserDto;
import com.spring.jwt.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UserDto userDto= userRepository.findByUserName(userName);
		if(userDto==null) {
			throw new UsernameNotFoundException("User Not Found :"+userName);
		}else {
			
			return new User(userDto.getUserName(), userDto.getPassword(), new ArrayList<>());
		}
	}

}
