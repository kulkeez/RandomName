package com.kulkeez.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author <a href="mailto:kulkeez@yahoo.com">Vikram Kulkarni</a>
 *
 */
@RequestMapping("/api/v1")
@RestController
@Slf4j
public class StatusController {
    	
	@GetMapping(path = "/status/{executionId}")
    public Map<String, String> showStatus(@PathVariable(value = "executionId") String executionId) {
    	log.debug("showStatus() called with Execution ID: {}", executionId);

    	String[] statusArray = new String[] {"Running", "Aborted", "Done"}; 
    	
    	// create mock/hard-coded JSON structure containing Test Suite execution information
    	HashMap<String, String> map = new HashMap<>();
	    
    	// Generate a random status by generating a number between 0 - 2
    	Random rand = new Random();
    	int randomNum = rand.nextInt((2 - 0) + 1) + 0;
    	
	    map.put("Execution ID", executionId);
	    map.put("Status", statusArray[randomNum]);

        return map;
    }
  
}

