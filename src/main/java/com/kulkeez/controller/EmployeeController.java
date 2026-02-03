package com.kulkeez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kulkeez.model.Employee;
import com.kulkeez.service.EmployeeService;

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
public class EmployeeController {
    
	private EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@GetMapping(path = "/employees")
    public List<Employee> fetchAllEmployees() {
    	log.debug("fetchAllEmployees() called");

        return service.retrieveAllEmployees();
    }


}

