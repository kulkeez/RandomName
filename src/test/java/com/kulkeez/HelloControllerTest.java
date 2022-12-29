package com.kulkeez;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@BeforeEach
	public void setUp() throws Exception {
		log.debug("==== in setup() ====");
		mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}

	//@Test
	public void getHello() throws Exception {
		log.debug("==== in getHello() ====");
		mvc.perform(MockMvcRequestBuilders.get("/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string("Greetings from RandomName Generator!"));
	}
	
}
