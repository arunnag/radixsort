package com.example.radixsorting.controller;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * GlobalExceptionHandler is a class that handles exceptions globally for the application.
 * It uses Spring's @RestControllerAdvice to handle exceptions thrown by any controller.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

  /** Logger for GlobalExceptionHandler */
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * Handles HttpMessageNotReadableException and returns a meaningful error response.
   *
   * @param ex the HttpMessageNotReadableException
   * @return a ResponseEntity with error details
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    String errorMessage = "Malformed JSON request";
    logger.error("HttpMessageNotReadableException: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(errorMessage);
  }

  /**
   * Handles all uncaught exceptions.
   *
   * <p><strong>Note:</strong> This is a generic fallback and should be used carefully. For
   * production systems, specific exception types should be handled individually.
   *
   * @param ex The exception thrown anywhere in the application.
   * @return A generic error message map with HTTP 500 Internal Server Error status.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
    Map<String, String> response = new HashMap<>();

    // Provide a generic error response with exception message
    response.put("error", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
