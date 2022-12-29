package com.kulkeez;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * 
 * 
 * @author kulkeez
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RandomNameApplicationTests {

	@Autowired
	private WebTestClient webClient;

	//@Test
	public void exampleTest() {
		this.webClient.get().uri("/").exchange().expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Greetings from RandomName Generator!");
	}
}
