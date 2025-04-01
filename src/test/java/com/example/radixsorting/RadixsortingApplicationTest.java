package com.example.radixsorting;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RadixsortingApplicationTest {

  /*
   * Tests if the Spring application context loads successfully.
   * This is a basic test to ensure that the application can start without any issues.
   * It does not test any specific functionality or behavior of the application.
   */
  @Test
  void contextLoads() {}

  /*
   * Tests the main method of the RadixsortingApplication class.
   * It simulates the application startup by calling the main method.
   * This test ensures that the application can start without any issues.
   * It does not test any specific functionality or behavior of the application.
   */
  @Test
  void testMainClass() {
    RadixsortingApplication.main(new String[] {});
    assertTrue(true);
  }
}
