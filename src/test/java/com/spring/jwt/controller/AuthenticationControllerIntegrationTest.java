package com.spring.jwt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jwt.model.UserDto;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void shouldCreateTokenForValidUser() throws Exception {

		UserDto userDto = new UserDto();
		userDto.setUserName("root");
		userDto.setPassword("root");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String body = objectMapper.writeValueAsString(userDto);
		
		mvc.perform(post("/autheticate").header("Content-type", "application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(body))
		.andExpect(status().isOk()).andReturn();
		
	}
	
	@Test
	public void shouldNotCreateTokenForInvalidUser() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setUserName("test");
		userDto.setPassword("test");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String body = objectMapper.writeValueAsString(userDto);
		
		mvc.perform(post("/autheticate").header("Content-type", "application/json")
				.accept(MediaType.APPLICATION_JSON)
				.content(body))
		.andExpect(status().isUnauthorized()).andReturn();
	}
	
	@Test
	public void shouldRefreshTokenForValidUser() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setUserName("root");
		userDto.setPassword("root");
		
		mvc.perform(get("/refresh/"+userDto.getUserName()).header("Content-type", "application/json")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andReturn();
	}
	@Test
	public void shouldRefreshTokenForInvalidUser() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setUserName("test");
		userDto.setPassword("test");
		
		mvc.perform(get("/refresh/"+userDto.getUserName()).header("Content-type", "application/json")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnauthorized()).andReturn();
	}
	
	
	
}
