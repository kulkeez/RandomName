package com.kulkeez;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.springframework.http.MediaType;

import org.junit.jupiter.api.Order;

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

	@Test
	@Order(1)
	public void testIndexEndpointMessageNotEmpty() {
		this.webClient.get()
				.uri("/")
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.Message").isNotEmpty();
	}

	@Test
	@Order(2)
	public void testIndexEndpointApplicationText() {
		this.webClient.get()
				.uri("/")
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.Application").isEqualTo("Randome Name Generator");
	}	
}
