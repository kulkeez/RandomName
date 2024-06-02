package com.kulkeez;

import java.util.Arrays;
import java.util.Locale;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.kulkeez.model.Employee;
import com.kulkeez.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Run this program and you will notice the various beans that are auto-wired by Spring Boot and it
 * launches Tomcat web server on port 8080. Browse to http://localhost:8080/ to see the welcome page
 * You can launch this application via command line by typing 'mvn spring-boot:run' from the 
 * root project directory. 
 * 
 * A Greeting REST end point is available: 
 * http://localhost:8080/greeting/ 
 * 
 * You can customize the greeting with an optional name parameter in the query string:
 * http://localhost:8080/greeting?name=Vikram
 * 
 * Various Actuator RESTful end points are added to the application such as /env, /health, /info, 
 * /metrics, /trace and /dump that can be queried as listed below: 
 * 
 * http://localhost:8080/actuator/health
 * http://localhost:8080/actuator/info 
 * http://localhost:8080/actuator/mappings 
 * http://localhost:8080/actuator/env
 * http://localhost:8080/actuator/metrics 
 * 
 * 
 * Notice below that the @EnableAutoConfiguration annotation used (indirectly) for this class tells 
 * Spring Boot to “guess�? how you want to configure Spring, based on the jar dependencies that you have added. 
 * Since spring-boot-starter-web added Tomcat and Spring MVC, the auto-configuration assumes that 
 * you are developing a web application and sets up Spring accordingly.
 * 
 * Autodiscovery is performed by Spring using the @ComponentScan which looks at classes that use
 * special stereotype annotations: @Component, @Controller, @Repository, @Service, @Configuration
 * 
 * @author <a href="mailto:kulkeez@yahoo.com">Vikram Kulkarni</a>
 *
 */
//convenience annotation that adds @Configuration, @EnableAutoConfiguration, @ComponentScan
@SpringBootApplication(exclude = SqlInitializationAutoConfiguration.class)
@Slf4j
public class RandomNameApplication {

	@Autowired
	private EmployeeRepository empRepository;

	/**
	 * Our main method delegates to Spring Boot’s SpringApplication class by calling run. 
	 * SpringApplication bootstraps our application, starting Spring, which, in turn, starts the 
	 * auto-configured Tomcat web server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Launch the application
		log.info("Launching the Spring Data application ...");

		Timestamp tOne = new Timestamp(System.currentTimeMillis());
        ApplicationContext ctx = SpringApplication.run(RandomNameApplication.class, args);

		log.info("Launched Spring Data application at time: {} ", tOne);
    }
	
	
	/**
	 * This method runs on start up, retrieves all the beans that were created either by your app or 
	 * were automatically added thanks to Spring Boot. It sorts them and prints them out.
	 * 
	 * The @Bean annotation used below tells Spring that this method will return a CommandLineRunner object 
	 * that should be registered as a bean in the Spring application context.
	 * 
	 * @param ctx
	 * @return
	 */
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            log.info("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);

            for (String beanName : beanNames) {
                log.debug(beanName);
            }
            log.info("Total Beans available in the Spring container: " + beanNames.length);

        };
    }


    /**
     * 
     * 
     * @param ctx
     * @return
     */
	@Bean
    public CommandLineRunner onBoot(ApplicationContext ctx) {
        return args -> {
        	log.info("onBoot(): perform some initialization on Spring boot here.");
        	
			// Populate some data into H2 database table EMPLOYEE
			empRepository.save(new Employee(1, 
									"David Palmer", 
									40, 
									"Male", 
									LocalDate.parse("1980-10-08"), 
									LocalDate.parse("2020-12-25"), 
									"HR", 
									LocalDate.parse("1979-12-25")));
			
			empRepository.save(new Employee(2, 
									"Vikram Kulkarni", 
									50, "Male", 
									LocalDate.parse("1973-10-11"), 
									LocalDate.parse("2003-12-25"), 
									"IT", 
									LocalDate.parse("1977-12-25")));
			
			log.info("Populated EMPLOYEE table with some records.");
			log.info("View the H2 Console here: http://localhost:8080/h2-console");
        };
	}  

	@Bean
    public NumberFormat defaultNumberFormat() {
        return NumberFormat.getCurrencyInstance(Locale.getDefault());
    }

}
