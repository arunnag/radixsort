package com.example.radixsorting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

public class GlobalExceptionHandlerTest {

  private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

  @Test
  void testHandleGenericException() {
    Exception ex = new RuntimeException("Something went wrong");

    ResponseEntity<Map<String, String>> response = handler.handleGenericException(ex);

    // Validate status and body
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(response.getBody().get("error").contains("Something went wrong"));
  }

  @Test
  void testHandleHttpMessageNotReadableException() {
    HttpMessageNotReadableException ex =
        new HttpMessageNotReadableException("Invalid JSON", new Throwable());

    ResponseEntity<String> response = handler.handleHttpMessageNotReadableException(ex);

    // Validate status and body
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Malformed JSON request", response.getBody());
  }
}
