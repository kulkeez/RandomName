package com.kulkeez.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kulkeez.model.Employee;
import com.kulkeez.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * The MockMvc comes from Spring Test and allows you, via a set of convenient builder classes, 
 * to send HTTP requests into the DispatcherServlet and make assertions about the result. 
 * Note the use of the @AutoConfigureMockMvc together with @SpringBootTest to inject a MockMvc instance. 
 * Having used @SpringBootTest we are asking for the whole application context to be created. 
 * 
 * @author kulkeez
 *
 */

@WebMvcTest(EmployeeController.class)
@Slf4j
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService service;

	@Test
	public void fetchAllEmployees() throws Exception {
		log.debug("fetchAllEmployees() called");

		when(service.retrieveAllEmployees()).thenReturn(
			Arrays.asList(
				new Employee(1, "James Gosling", 45, "Male", LocalDate.parse("1980-10-08"), 
							LocalDate.parse("2020-12-25"), "IT", LocalDate.parse("1979-12-25")),
				new Employee(2, "Linus Torvalds", 50, "Male", LocalDate.parse("1973-10-11"), 
							LocalDate.parse("2022-12-25"), "IT", LocalDate.parse("1969-12-25")))
			);						
			

		RequestBuilder request = MockMvcRequestBuilders
										.get("/api/v1/employees")
										.accept(MediaType.APPLICATION_JSON);
										
		MvcResult result = mockMvc.perform(request).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("[{id: 1, name:\"James Gosling\", age: 45}, {id: 2, name:\"Linus Torvalds\", age: 50}]"))
				.andReturn();
				
	}
	
}
