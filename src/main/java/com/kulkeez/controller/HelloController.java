package com.kulkeez.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kulkeez.lambda.RandomNameGenerator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * The class is flagged as a @RestController, meaning it's ready for use by Spring MVC to handle web requests. 
 * @RequestMapping maps / to the index() method. When invoked from a browser or using 
 * curl on the command line, the method returns pure text.
 * 
 * @author <a href="mailto:kulkeez@yahoo.com">Vikram Kulkarni</a>
 *
 */
@RestController
@Slf4j
public class HelloController {

	private static final String template = ": Hello, %s! Greetings from RandomName Generator!";
	
	@Autowired
    ApplicationContext ctx;
	
	/**
	 * 
	 * Here, we do not specify GET versus PUT, POST, and so forth. 
	 * By default @RequestMapping maps all HTTP operations. 
	 * You can use @GetMapping or @RequestMapping(method=GET) to narrow this mapping.
	 * 
	 * @return
	 */	
	@RequestMapping("/")
	public Map<String, String> index() {
		log.debug("index() called.");

		// By default, all Spring beans are singletons
		// Therefore, this Workaround: inject application context and get the bean manually
		String name = ((RandomNameGenerator) ctx.getBean(RandomNameGenerator.class)).randomName().toUpperCase();
		log.debug("Random Name: " + name);
		
    	// create JSON structure containing Greetings
    	HashMap<String, String> map = new HashMap<>();

	    map.put("Date", new Date().toString());
		map.put("Application", "Randome Name Generator");
	    map.put("Message", String.format(template, name));
    	
        return map;
    }
}
