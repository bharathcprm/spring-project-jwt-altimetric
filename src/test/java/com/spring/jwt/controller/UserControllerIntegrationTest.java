package com.spring.jwt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void shouldNotAccessMessageWithoutToken() throws Exception {
		
		mvc.perform(get("/message")).andExpect(status().isUnauthorized());
	}
	
	@Test
	public void shouldAccessMessageWithToken() {
		//TODO
	}
	
	
	@Test
	public void shouldAllowUserToRegister() {
		//TODO
	}
}
