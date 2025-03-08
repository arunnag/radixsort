package com.example.radixsorting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RadixSortServiceTest {

    private RadixSortService radixSortService;

    // setup and tear down for RadixSortService
    @BeforeEach
    public void setUp() {
        radixSortService = new RadixSortService();
    }

    @AfterEach
    public void tearDown() {
        radixSortService = null;
    }


    //test radix sort with random integers with varing number of digits both positive and negative
    @Test
    public void testSortRandomIntegers() {
        // create unsorted list
        ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(3, 1, 2, 10, 100, 1000, 10000, 100000, -3, -1, -2, -10, -100, -1000, -10000, -100000));
        // create sorted list
        ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(-100000, -10000, -1000, -100, -10, -3, -2, -1, 1, 2, 3, 10, 100, 1000, 10000, 100000));
        // sort unsorted list
        ArrayList<Integer> result = radixSortService.sort(unsortedList);
        // check if sorted list is equal to result
        assertEquals(sortedList, result);
    }


    // test raidx for with large numbers positive negative and zero and duplicates and small positive numbers
    @Test
    public void testSortLargeNumbers() {
        // create unsorted list
        ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(100000, -100000, 0, 100, -100, 0, 100000, -100000, 0, 100, -100));
        // create sorted list
        ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(-100000, -100000, -100, -100, 0, 0, 0, 100, 100, 100000, 100000));
        // sort unsorted list
        ArrayList<Integer> result = radixSortService.sort(unsortedList);
        // check if sorted list is equal to result
        assertEquals(sortedList, result);
    }
    
    // test radix sort returns sorted list
    @Test
    public void testSort() {
        // create unsorted list
        ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(3, 1, 2));
        // create sorted list
        ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(1, 2, 3));
        // sort unsorted list
        ArrayList<Integer> result = radixSortService.sort(unsortedList);
        // check if sorted list is equal to result
        assertEquals(sortedList, result);
    }

    // test radix sort with negative numbers -100000 to 100000
    @Test
    public void testSortNegativeNumbers() {
        // create unsorted list
        ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(-3, -1, -2));
        // create sorted list
        ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(-3, -2, -1));
        // sort unsorted list
        ArrayList<Integer> result = radixSortService.sort(unsortedList);
        // check if sorted list is equal to result
        assertEquals(sortedList, result);
    }

    // test radix sort with empty list
    @Test
    public void testSortEmptyList() {
        // create empty list
        ArrayList<Integer> emptyList = new ArrayList<>();
        // create empty list
        ArrayList<Integer> sortedList = new ArrayList<>();
        // sort empty list
        ArrayList<Integer> result = radixSortService.sort(emptyList);
        // check if sorted list is equal to result
        assertEquals(sortedList, result);
    }

}
