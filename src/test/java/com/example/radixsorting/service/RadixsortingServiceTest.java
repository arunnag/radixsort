package com.example.radixsorting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RadixsortingServiceTest {

  private RadixsortingService radixSortingService;

  @BeforeEach
  public void setUp() {
    radixSortingService = new RadixsortingService();
  }

  @AfterEach
  public void tearDown() {
    radixSortingService = null;
  }

  /*
   * Tests the sorting of 100,000 random integers using the radix sorting algorithm.
   * It generates a list of random integers,
   * sorts them using the radix sorting algorithm,
   * and compares the result with the expected sorted list.
   */
  @Test
  public void testSort100kRandomIntegers() {

    Random random = new Random();
    ArrayList<Integer> unsortedList = new ArrayList<>();
    ArrayList<Integer> sortedList = new ArrayList<>();
    for (int i = 0; i < 100000; i++) {
      int nextInt = random.nextInt();
      unsortedList.add(nextInt);
      sortedList.add(nextInt);
    }

    Collections.sort(sortedList);
    assertEquals(sortedList, radixSortingService.sort(unsortedList));
  }

  /*
   * Tests the sorting of a list of random integers.
   * It generates a list of random integers,
   * sorts them using the radix sorting algorithm,
   * and compares the result with the expected sorted list.
   * The test uses a predefined list of integers to ensure the sorting algorithm works correctly.
   * The test checks if the sorted list is equal to the expected sorted list.
   */
  @Test
  public void testSortRandomIntegers() {
    ArrayList<Integer> unsortedList =
        new ArrayList<>(
            Arrays.asList(
                3, 1, 2, 10, 100, 1000, 10000, 100000, -3, -1, -2, -10, -100, -1000, -10000,
                -100000));
    ArrayList<Integer> sortedList =
        new ArrayList<>(
            Arrays.asList(
                -100000, -10000, -1000, -100, -10, -3, -2, -1, 1, 2, 3, 10, 100, 1000, 10000,
                100000));
    ArrayList<Integer> result = radixSortingService.sort(unsortedList);
    assertEquals(sortedList, result);
  }

  /*
   * Tests the sorting of a list of large numbers using the radix sorting algorithm.
   * It generates a list of large numbers,
   * sorts them using the radix sorting algorithm,
   * and compares the result with the expected sorted list.
   * The test uses a predefined list of integers to ensure the sorting algorithm works correctly.
   */
  @Test
  public void testSortLargeNumbers() {
    ArrayList<Integer> unsortedList =
        new ArrayList<>(
            Arrays.asList(100000, -100000, 0, 100, -100, 0, 100000, -100000, 0, 100, -100));
    ArrayList<Integer> sortedList =
        new ArrayList<>(
            Arrays.asList(-100000, -100000, -100, -100, 0, 0, 0, 100, 100, 100000, 100000));
    ArrayList<Integer> result = radixSortingService.sort(unsortedList);
    assertEquals(sortedList, result);
  }

  /*
   * Test sorting of just positive numbers with radix sorting algorithm.
   */
  @Test
  public void testSort() {
    ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(3, 1, 2));
    ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(1, 2, 3));
    ArrayList<Integer> result = radixSortingService.sort(unsortedList);
    assertEquals(sortedList, result);
  }

  /*
   * Test sorting of just negative numbers with radix sorting algorithm.
   */
  @Test
  public void testSortNegativeNumbers() {
    ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(-3, -1, -2));
    ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(-3, -2, -1));
    ArrayList<Integer> result = radixSortingService.sort(unsortedList);
    assertEquals(sortedList, result);
  }

  /*
   * Test sorting of a empty list with randix sorting algorithm.
   */
  @Test
  public void testSortEmptyList() {
    ArrayList<Integer> emptyList = new ArrayList<>();
    ArrayList<Integer> sortedList = new ArrayList<>();
    ArrayList<Integer> result = radixSortingService.sort(emptyList);
    assertEquals(sortedList, result);
  }

  /*
   * Test sorting of a null list with radix sorting algorithm.
   */
  @Test
  public void testSortNullList() {
    ArrayList<Integer> nullList = null;
    ArrayList<Integer> result = radixSortingService.sort(nullList);
    assertEquals(nullList, result);
  }
}
