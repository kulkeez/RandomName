package com.kulkeez.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.util.Random;
import java.util.UUID;

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


	/**
	 * 
	 * 
	 */
	@GetMapping(path = "/report/{executionId}")
    public Map<String, String> generateTestReportForExecution(@PathVariable(value = "executionId") String executionId) {
    	log.debug("generateTestReportForExecution() called for executionId: {}", executionId);

    	// create mock/hard-coded JSON structure containing Test Suite Report information
    	HashMap<String, String> map = new HashMap<>();
	    
    	// Generate a random status by generating a number between 0 - 50
    	Random rand = new Random();
    	int randomTotal = rand.nextInt((50 - 0) + 1) + 0;
    	int passed = rand.nextInt((randomTotal - 0) + 1) + 0;
    			
	    map.put("Total Tests Executed", Integer.toString(randomTotal));
	    map.put("Tests Passed", Integer.toString(passed));
	    map.put("Tests Failed", Integer.toString((randomTotal - passed)));
    	map.put("Execution Id", executionId);
    	
        return map;
    } 

	/**
	 * 
	 * Fetch all JMeter Test Suites executed in non-GUI mode 
	 * 
	 * @return
	 * @throws IOException
	 * @throws ExecutionException
	 */
	@GetMapping(path = "/executions")
    public Map<String, String> getAllJMeterExecutions() {
    	log.debug("getAllJMeterExecutions() called");
    	
    	// create mock/hard-coded JSON structure containing Test Script information
    	HashMap<String, String> map = new HashMap<>();
	        
    	log.debug("Execution ID: {}", UUID.randomUUID().toString());
    	
	    map.put("Execution ID #1", UUID.randomUUID().toString());
		map.put("Test Suite Name #1", "TestSuite-1");
	    map.put("Test Script Name 1.1", "ConnectSMSFragment.jmx");
	    map.put("Test Script Name 1.2", "SendSuccessSMSReportFragment.jmx");

	    map.put("Execution ID #3", UUID.randomUUID().toString());
	    map.put("Execution ID #4", UUID.randomUUID().toString());
	    map.put("Execution ID #5", UUID.randomUUID().toString());

        return map;
    }
}

