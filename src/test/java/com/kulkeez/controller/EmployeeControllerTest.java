package com.kulkeez.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kulkeez.model.Employee;
import com.kulkeez.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeControllerTest {

	@Test
	public void fetchAllEmployeesReturnsStubbedList() throws Exception {
		EmployeeService stubService = new EmployeeService() {
			@Override
			public List<Employee> retrieveAllEmployees() {
				return Arrays.asList(
						new Employee(1, "James Gosling", 45, "Male", LocalDate.parse("1980-10-08"), LocalDate.parse("2020-12-25"), "IT", LocalDate.parse("1979-12-25")),
						new Employee(2, "Linus Torvalds", 50, "Male", LocalDate.parse("1973-10-11"), LocalDate.parse("2022-12-25"), "IT", LocalDate.parse("1969-12-25"))
				);
			}
		};

		EmployeeController controller = new EmployeeController(stubService);

		List<Employee> result = controller.fetchAllEmployees();
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getName()).isEqualTo("James Gosling");
	}
}
