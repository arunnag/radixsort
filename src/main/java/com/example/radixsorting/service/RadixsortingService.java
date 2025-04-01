package com.example.radixsorting.service;

import java.util.ArrayList;
import java.util.Collections;
import org.springframework.stereotype.Service;

/**
 * Radix sort is a non-comparative sorting algorithm that sorts integers by processing individual
 * digits. It works by sorting the numbers in multiple passes, each pass sorting the numbers based
 * on a specific digit.
 *
 * <p>The algorithm follows these steps: 1. Find the maximum number in the list to determine the
 * number of digits in the largest number. 2. Perform counting sort for each digit, starting from
 * the least significant digit to the most significant digit. - For each digit, group the numbers
 * based on the digit's value and sort them accordingly. - Counting sort is used as a subroutine to
 * sort the numbers based on the current digit. 3. Repeat the process for each digit until all
 * digits have been processed.
 *
 * <p>The counting sort subroutine works as follows: 1. Create a count array to store the count of
 * occurrences of each digit. 2. Modify the count array to store the actual position of each digit
 * in the output array. 3. Build the output array by placing the numbers in their correct positions
 * based on the current digit.
 *
 * <p>Radix sort is efficient for sorting large lists of integers, especially when the range of the
 * numbers is limited. It has a time complexity of O(n * k), where k is the number of digits, n is
 * the number of integers.
 */
@Service
public class RadixsortingService {
  /** Logger for RadixsortingService */
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RadixsortingService.class);

  /**
   * Sorts a list of integers using the Radix Sort algorithm. The method handles both positive and
   * negative integers by separating them, sorting them individually, and then combining the
   * results. Negative integers are sorted in reverse order after being converted to positive for
   * sorting.
   *
   * @param unSortedIntegers the list of integers to be sorted. Can contain both positive and
   *     negative integers. If the input is null or empty, it is returned as is.
   * @return a sorted list of integers in ascending order. Negative integers appear before positive
   *     integers in the sorted output.
   */
  public ArrayList<Integer> sort(ArrayList<Integer> unSortedIntegers) {
    logger.info("Starting radix sort for input: {}", unSortedIntegers);

    if (unSortedIntegers == null || unSortedIntegers.isEmpty()) {
      logger.warn("Input list is null or empty. Returning the input as is.");
      return unSortedIntegers;
    }

    ArrayList<Integer> positives = new ArrayList<>();
    ArrayList<Integer> negatives = new ArrayList<>();

    for (Integer num : unSortedIntegers) {
      if (num >= 0) {
        positives.add(num);
      } else {
        negatives.add(-num);
      }
    }

    logger.debug("Separated positives: {}, negatives: {}", positives, negatives);

    radixSortHelper(positives);
    logger.debug("Sorted positives: {}", positives);

    radixSortHelper(negatives);

    Collections.reverse(negatives);
    logger.debug("Sorted and reversed negatives: {}", negatives);

    ArrayList<Integer> sortedArr = new ArrayList<>();
    for (int num : negatives) {
      sortedArr.add(-num);
    }
    sortedArr.addAll(positives);

    logger.info("Radix sort completed. Sorted output: {}", sortedArr);
    return sortedArr;
  }

  /**
   * Helper method to perform radix sort on a list of integers. This method sorts the input list
   * in-place using the radix sort algorithm. It determines the maximum value in the list to
   * calculate the number of digits and iteratively sorts the list based on each digit's place
   * value.
   *
   * @param arr the list of integers to be sorted
   */
  private static void radixSortHelper(ArrayList<Integer> arr) {
    logger.debug("Starting radix sort helper for array: {}", arr);
    if (arr.isEmpty()) return;
    int max = Collections.max(arr);
    for (int exp = 1; max / exp > 0; exp *= 10) {
      countingSort(arr, exp);
    }
    logger.debug("Radix sort helper completed for array: {}", arr);
    logger.info("Sorted array: {}", arr);
  }

  /**
   * Performs counting sort on the given list of integers based on the specified digit's place
   * value. This method is a subroutine of the radix sort algorithm and sorts the input list
   * in-place.
   *
   * @param arr the list of integers to be sorted
   * @param exp the digit's place value to sort by (e.g., 1 for units, 10 for tens, etc.)
   */
  private static void countingSort(ArrayList<Integer> arr, int exp) {
    logger.debug("Starting counting sort for array: {} with exp: {}", arr, exp);
    int n = arr.size();
    ArrayList<Integer> output = new ArrayList<>(Collections.nCopies(n, 0));

    final int bucketSize = 10;
    int[] count = new int[bucketSize];

    for (int i = 0; i < n; i++) {
      count[(arr.get(i) / exp) % bucketSize]++;
    }

    for (int i = 1; i < bucketSize; i++) {
      count[i] += count[i - 1];
    }

    for (int i = n - 1; i >= 0; i--) {
      int index = (arr.get(i) / exp) % bucketSize;
      output.set(count[index] - 1, arr.get(i));
      count[index]--;
    }

    for (int i = 0; i < n; i++) {
      arr.set(i, output.get(i));
    }
    logger.debug("Counting sort completed. Sorted array: {}", arr);
  }
}
