package com.example.radixsorting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the Radix Sorting application. This class is responsible for bootstrapping the
 * Spring Boot application.
 *
 * <p>The {@code RadixsortingApplication} class contains the main method which serves as the entry
 * point for the Spring Boot application.
 *
 * <p>Usage:
 *
 * <pre>{@code
 * java -jar radixsorting-application.jar
 * }</pre>
 *
 * <p>This application demonstrates the implementation of radix sorting algorithm.
 *
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 */
@SpringBootApplication
public class RadixsortingApplication {

  public static void main(String[] args) {
    SpringApplication.run(RadixsortingApplication.class, args);
  }
}
