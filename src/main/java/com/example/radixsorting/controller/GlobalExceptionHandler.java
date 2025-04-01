package com.example.radixsorting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
  @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadableException(
      org.springframework.http.converter.HttpMessageNotReadableException ex) {
    String errorMessage = "Malformed JSON request";
    logger.error("HttpMessageNotReadableException: {}", ex.getMessage());
    return ResponseEntity.badRequest().body(errorMessage);
  }
}
