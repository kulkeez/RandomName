package com.kulkeez.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kulkeez.RandomNameGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * The MockMvc comes from Spring Test and allows you, via a set of convenient builder classes, 
 * to send HTTP requests into the DispatcherServlet and make assertions about the result. 
 * Note the use of the @AutoConfigureMockMvc together with @SpringBootTest to inject a MockMvc instance. 
 * Having used @SpringBootTest we are asking for the whole application context to be created. 
 * 
 * @author kulkeez
 *
 */

@WebMvcTest(HelloController.class)
@Slf4j
public class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RandomNameGenerator service;

	@Test
	public void getHello() throws Exception {
		log.debug("==== in getHello() ====");

		when(service.randomName()).thenReturn("IMPRESSIVE_KICK");

		RequestBuilder request = MockMvcRequestBuilders.get("/");
		MvcResult result = mockMvc.perform(request).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{Application: \"Random Name Generator\", Message: \"Hello, IMPRESSIVE_KICK! Greetings from RandomName Generator!\"}"))
				.andReturn();
				
	}
	
}
