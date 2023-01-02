package com.kulkeez;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Using AssertJ Assertions
 * 
 * @author kulkeez
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class HttpRequestTest {
	
	// bind the above RANDOM_PORT
    @Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		log.debug("Using port: {}", port);
		
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Greetings from RandomName Generator");
	}
}
