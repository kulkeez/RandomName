package com.kulkeez.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
/**
 * Check for Availability for Gaming consoles (switch, xbox, ps4)
 * 
 * http://localhost:8080/availability/switch 
 * http://localhost:8080/availability/ps4
 * http://localhost:8080/availability/xbox
 * 
 * @author kulkarvi
 *
 */
public class AvailabilityController {
 

    @GetMapping("/availability/{console}")
    public Map<String, Object> getAvailability(@PathVariable String console) {
    	log.info("/availability REST endpoint invoked...");
        return Map.of("console", console,
                "available", checkAvailability(console));
    }

    
    /**
     * Check Availability of a Gaming console 
     * 
     * @param console
     * @return
     */
    private boolean checkAvailability(String console) {
        Assert.state(validate(console), () -> "the console specified, " + console + ", is not valid.");
        return switch (console) {
            case "ps5" -> throw new RuntimeException("Service exception");
            case "xbox" -> true;
            default -> false;
        };
    }
    
	private boolean validate(String console) {
        return StringUtils.hasText(console) &&
               Set.of("ps5", "ps4", "switch", "xbox").contains(console);
    }
}