package com.kulkeez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kulkeez.model.Employee;


/**
 * 
 * 
 * @author kulkeez
 *
 */
// The @Repository annotation is a specialization of the @Component annotation 
// and marks this java class as a bean so the component-scanning mechanism of Spring can pick it up 
// and pull it into the application context.
@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Integer>{


}
