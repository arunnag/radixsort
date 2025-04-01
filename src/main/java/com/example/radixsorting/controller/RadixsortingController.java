package com.example.radixsorting.controller;

import com.example.radixsorting.dto.IntegerListWrapper;
import com.example.radixsorting.service.RadixsortingService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RadixsortingController is a REST controller that provides an endpoint for sorting integers using
 * radix sort. It uses RadixsortingService to perform the sorting operation.
 *
 * <p>Endpoints:
 *
 * <ul>
 *   <li>POST /sort - Sorts a list of integers using radix sort.
 * </ul>
 *
 * <p>Dependencies:
 *
 * <ul>
 *   <li>RadixsortingService - Service for performing radix sort.
 * </ul>
 *
 * <p>Methods:
 *
 * <ul>
 *   <li>{@link #sortntegers(IntegerListWrapper)} - Sorts the provided list of integers.
 * </ul>
 */
@RestController
@RequestMapping("/api/v1")
@Validated
public class RadixsortingController {

  /** Logger for RadixsortingController */
  private static final Logger logger = LoggerFactory.getLogger(RadixsortingController.class);

  /** Service for radix sort */
  @Autowired private RadixsortingService radixSortingService;

  /**
   * Sorts a list of integers using radix sort algorithm.
   *
   * @param integers the list of integers to be sorted, wrapped in an IntegerList object
   * @return a sorted list of integers
   * @throws BadRequestException if the input list is invalid or null
   */
  @PostMapping("/sort")
  public ResponseEntity<IntegerListWrapper> sort(@Valid @RequestBody IntegerListWrapper integers) {
    logger.info("Received request to sort integers: {}", integers.getIntegers());
    return ResponseEntity.ok(
        new IntegerListWrapper(radixSortingService.sort(integers.getIntegers())));
  }
}
