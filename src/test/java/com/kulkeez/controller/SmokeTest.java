package com.kulkeez.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericApplicationContext;

import com.kulkeez.RandomNameGenerator;

public class SmokeTest {

	@Test
	public void controllerInstantiated() throws Exception {
		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.registerBean(RandomNameGenerator.class, () -> new RandomNameGenerator(0));
		ctx.refresh();
		
		HelloController controller = new HelloController(ctx);
		assertThat(controller).isNotNull();
	}
}
