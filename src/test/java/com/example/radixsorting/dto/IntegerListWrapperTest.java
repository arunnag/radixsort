package com.example.radixsorting.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class IntegerListWrapperTest {

  /*
   * Tests the constructor of the IntegerListWrapper class.
   * It creates an instance of IntegerListWrapper with a list of integers
   * and checks if the list is initialized correctly.
   */
  @Test
  void getIntegerList() {
    ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    ArrayList<Integer> expectedOutput = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

    IntegerListWrapper integerList = new IntegerListWrapper(integers);
    assertEquals(expectedOutput, integerList.getIntegers());
  }

  /*
   * Tests the constructor of the IntegerListWrapper class.
   * It creates an instance of IntegerListWrapper with a list of integers
   * and checks if the list is initialized correctly.
   */
  @Test
  void testToString() {
    ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    IntegerListWrapper integerList = new IntegerListWrapper(integers);
    String expectedOutput = "IntegerListWrapper(integers=[1, 2, 3])";
    assertEquals(expectedOutput, integerList.toString());
  }

  /*
   * Tests the equals and hashCode methods of the IntegerListWrapper class.
   */
  @Test
  void testEqualsVerifier() {
    EqualsVerifier.simple().forClass(IntegerListWrapper.class).verify();
  }
}
