package com.lab.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Student Management System.
 *
 * @SpringBootApplication enables:
 *   - @Configuration       : marks this as a configuration class
 *   - @EnableAutoConfiguration : auto-configures Spring Boot components
 *   - @ComponentScan       : scans all sub-packages for components
 *
 * Run this class to start the embedded Tomcat server on port 8080.
 * Access the app at: http://localhost:8080/
 */
@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("  Student Management System is running!");
        System.out.println("  URL: http://localhost:8080/");
        System.out.println("  H2 Console: http://localhost:8080/h2-console");
        System.out.println("========================================\n");
    }
}
