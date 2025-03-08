package com.example.radixsorting.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.radixsorting.dto.IntegerList;
import com.example.radixsorting.service.RadixSortService;

import jakarta.validation.Valid;

import java.util.ArrayList;


/**
 * Controller class for sorting integers
 */
@RestController
public class RadixSortingController {

    /**
     * Service for shell sort
     */
    @Autowired
    private RadixSortService radixSortService;

    /**
     * Sorts the list of integers using radix sort service
     * @param integers list of integers to be sorted
     * @return sorted list of integers
     */

    @PostMapping("/sort")
    public ArrayList<Integer> sortntegers(@Valid @RequestBody IntegerList integers) {
        return radixSortService.sort(integers.getIntegers());
    }
}
