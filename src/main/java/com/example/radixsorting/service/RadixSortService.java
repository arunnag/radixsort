package com.example.radixsorting.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * Service class for shell sort
 */
@Service
public class RadixSortService {
    /**
     * Sorts the list of Integers using radix sort algorithm
     * @param unSortedIntegers list of Integers to be sorted
     * @return sorted list of Integers
     */
    public ArrayList<Integer> sort(ArrayList<Integer> unSortedIntegers) {
        return radixSort(unSortedIntegers);
    }
    

    // generate java doc for this method expalain different scenarios pass fail and talk about limits or edgecases
    /**
     * Sorts the list of Integers using radix sort algorithm
     * @param integers list of Integers to be sorted
     * @return sorted list of Integers
     */

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
            System.out.println("Sorted by digit: " + integers);
        }

        return integers;
    }

    private ArrayList<Integer> countingSortByDigit(ArrayList<Integer> integers, int exp) {
        int n = integers.size();
        ArrayList<Integer> output = new ArrayList<>(n); // output array
        for (int i = 0; i < n; i++) {
            output.add(0);
        }
        int[] count = new int[20]; // Since range is -1000000 to 1000000, we need 20 buckets for digits -9 to 9

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            int index = (integers.get(i) / exp) % 10 + 9; // Shift range from -9 to 9 to 0 to 18
            count[index]++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of this digit in output[]
        for (int i = 1; i < 20; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {            
            int index = (integers.get(i) / exp) % 10 + 9;
            output.set(count[index] - 1, integers.get(i));
            count[index]--;
        }

        return output;
    }
}
