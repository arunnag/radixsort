package com.example.radixsorting.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class IntegerListWrapperTest {

  @Test
  void getIntegerList() {
    ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    ArrayList<Integer> expectedOutput = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

    IntegerListWrapper integerList = new IntegerListWrapper(integers);
    assertEquals(expectedOutput, integerList.getIntegers());
  }

  @Test
  void testToString() {
    ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    IntegerListWrapper integerList = new IntegerListWrapper(integers);
    String expectedOutput = "IntegerListWrapper(integers=[1, 2, 3])";
    assertEquals(expectedOutput, integerList.toString());
  }

  @Test
  void testEqualsVerifier() {
    EqualsVerifier.simple().forClass(IntegerListWrapper.class).verify();
  }
}
