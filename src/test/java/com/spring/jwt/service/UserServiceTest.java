package com.spring.jwt.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.jwt.model.UserDto;
import com.spring.jwt.repository.UserRepository;

@RunWith(SpringRunner.class)
 public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;

	@Test
	public void shouldReturnUserForValidUser() {
		
		UserDto userDto = new UserDto(1,"root","root");
		when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(userDto);
		UserDetails userDetails= userService.loadUserByUsername("root");
		assertThat(userDetails).isNotNull();
	}
	
	@Test
	public void shouldThrowExceptionForInvalidUser() {
		
		Throwable throwable = catchThrowable(()-> userService.loadUserByUsername("test"));
		assertThat(throwable).isInstanceOf(UsernameNotFoundException.class);
	}

}
