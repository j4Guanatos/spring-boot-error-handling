package org.j4guanatos.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 ************************************************************************
 * SampleStudentApp.java spring-boot-sample-student project
 *
 * This class starts the server and sets the application ready to be used
 *
 * @since Nov 10, 2016
 *************************************************************************
 */
@SpringBootApplication
public class SampleStudentAppNoErrorHandling {

	public static void main(String[] args) {
		SpringApplication.run(SampleStudentAppNoErrorHandling.class, args);
	}
}
