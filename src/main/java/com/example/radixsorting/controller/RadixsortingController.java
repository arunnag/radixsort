package com.example.radixsorting.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.radixsorting.dto.IntegerList;
import com.example.radixsorting.service.RadixsortingService;

import jakarta.validation.Valid;

import java.util.ArrayList;


/**
 * Controller class for sorting integers
 */
@RestController
public class RadixsortingController {

    /**
     * Service for radix sort
     */
    @Autowired
    private RadixsortingService radixSortingService;

    /**
     * Sorts the list of integers using radix sort service
     * @param integers list of integers to be sorted
     * @return sorted list of integers
     */

    @PostMapping("/sort")
    public ArrayList<Integer> sortntegers(@Valid @RequestBody IntegerList integers) {
        return radixSortingService.sort(integers.getIntegers());
    }
}
