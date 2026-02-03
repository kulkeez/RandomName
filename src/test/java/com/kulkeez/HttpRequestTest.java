package com.kulkeez;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericApplicationContext;

import com.kulkeez.controller.HelloController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpRequestTest {

	@Test
	public void helloControllerIndexReturnsExpectedMap() throws Exception {
		GenericApplicationContext ctx = new GenericApplicationContext();
		ctx.registerBean(RandomNameGenerator.class, () -> new RandomNameGenerator(0) {
			@Override
			public String randomName() { return "TEST_NAME"; }
		});
		ctx.refresh();

		HelloController controller = new HelloController(ctx);

		Map<String, String> result = controller.index();
		assertThat(result).containsEntry("Application", "Random Name Generator");
		assertThat(result.get("Message")).contains("TEST_NAME");
	}
}
