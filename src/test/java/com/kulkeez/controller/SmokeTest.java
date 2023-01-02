package com.kulkeez.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 
 * 
 * @author kulkeez
 *
 */
// The @SpringBootTest annotation tells Spring Boot to look for a main configuration class (one with @SpringBootApplication)
@SpringBootTest
public class SmokeTest {
	
	@Autowired		// controller is injected before the test methods are run
	private HelloController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();		// convince ourself that the context is creating our controller
	}
}
