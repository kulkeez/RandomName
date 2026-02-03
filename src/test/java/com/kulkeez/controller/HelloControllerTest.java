package com.kulkeez.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericApplicationContext;

import com.kulkeez.RandomNameGenerator;
import com.kulkeez.controller.HelloController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloControllerTest {

	@Test
	public void indexReturnsMessageContainingGeneratedName() throws Exception {
		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.registerBean(RandomNameGenerator.class, () -> new RandomNameGenerator(0) {
			@Override
			public String randomName() { return "UNIT_NAME"; }
		});
		ctx.refresh();

		HelloController controller = new HelloController(ctx);

		Map<String, String> response = controller.index();
		assertThat(response.get("Application")).isEqualTo("Random Name Generator");
		assertThat(response.get("Message")).contains("UNIT_NAME");
	}
}
