package com.example.radixsorting.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;



/**
 * Radix sort is a non-comparative sorting algorithm that sorts integers by processing individual digits. 
 * It works by sorting the numbers in multiple passes, each pass sorting the numbers based on a specific digit.
 * 
 * The algorithm follows these steps:
 * 1. Find the maximum number in the list to determine the number of digits in the largest number.
 * 2. Perform counting sort for each digit, starting from the least significant digit to the most significant digit.
 *    - For each digit, group the numbers based on the digit's value and sort them accordingly.
 *    - Counting sort is used as a subroutine to sort the numbers based on the current digit.
 * 3. Repeat the process for each digit until all digits have been processed.
 * 
 * The counting sort subroutine works as follows:
 * 1. Create a count array to store the count of occurrences of each digit.
 * 2. Modify the count array to store the actual position of each digit in the output array.
 * 3. Build the output array by placing the numbers in their correct positions based on the current digit.
 * 
 * Radix sort is efficient for sorting large lists of integers, especially when the range of the numbers is limited.
 * It has a time complexity of O(n * k), where k is the number of digits, n is the number of integers.
 */
@Service
public class RadixsortingService {
    /**
     * Sorts the list of Integers using radix sort algorithm
     * @param unSortedIntegers list of Integers to be sorted
     * @return sorted list of Integers
     */
    public ArrayList<Integer> sort(ArrayList<Integer> unSortedIntegers) {
        return radixSort(unSortedIntegers);
    }
    
    private ArrayList<Integer> radixSort(ArrayList<Integer> integers) {

        if (integers.size() == 0) {
            return integers;
        }

        // Find the maximum number using lambda
        Integer max = integers.stream().mapToInt(Math::abs).max().orElse(0);

        // Do counting sort for every digit. Note that instead of passing digit number,
        // exp is passed. exp is 10^i where i is the current digit number
        for (int exp = 1; max / exp > 0; exp *= 10) {
            integers = countingSortByDigit(integers, exp);
        }

        return integers;
    }

    private ArrayList<Integer> countingSortByDigit(ArrayList<Integer> integers, int exp) {
        int n = integers.size();
        ArrayList<Integer> output = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            output.add(0);
        }
        final int bucketLength = 19; // 20 buckets for digits -9 to 9
        final int bucketOffset = 9;
        int[] count = new int[bucketLength];

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            int index = (integers.get(i) / exp) % 10 + bucketOffset; // Shift range from -9 to 9 to 0 to 18
            count[index]++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of this digit in output[]
        for (int i = 1; i < bucketLength; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {            
            int index = (integers.get(i) / exp) % 10 + bucketOffset;
            output.set(count[index] - 1, integers.get(i));
            count[index]--;
        }

        return output;
    }
}
