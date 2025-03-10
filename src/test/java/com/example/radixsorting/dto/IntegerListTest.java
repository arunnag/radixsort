package com.example.radixsorting.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IntegerListTest {

    //generate get interger list test
    @Test
    void getIntegerList() {
        ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(1,2,3));
        ArrayList<Integer> expectedOutput = new ArrayList<Integer>(Arrays.asList(1,2,3));

        IntegerList integerList = new IntegerList(integers);
        assertEquals(expectedOutput, integerList.getIntegers());
    }

}
