package com.example.radixsorting.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.radixsorting.dto.IntegerListWrapper;
import com.example.radixsorting.service.RadixsortingService;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RadixsortingControllerTest {

  private MockMvc mockMvc;

  @Mock private RadixsortingService radixSortingService;

  @InjectMocks private RadixsortingController radixSortingController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(radixSortingController).build();
  }

  /*
   * Tests the sorting of a positive integers, negative integers and zeros.
   */
  @Test
  public void testSortMixedIntegers() throws Exception {
    ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(-10, 25, -1000, 24000, 7, -3));
    ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(-1000, -10, -3, 7, 25, 24000));
    IntegerListWrapper integerList = new IntegerListWrapper();
    integerList.setIntegers(unsortedList);

    when(radixSortingService.sort(unsortedList)).thenReturn(sortedList);

    mockMvc
        .perform(
            post("/api/v1/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"integers\":[-10,25,-1000,24000,7,-3]}"))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"integers\":[-1000,-10,-3,7,25,24000]}"));
  }

  /*
   * Tests the sorting of a list of positive integers
   */
  @Test
  public void testSortPositiveIntegers() throws Exception {
    ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(3, 1, 2));
    ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(1, 2, 3));
    IntegerListWrapper integerList = new IntegerListWrapper();
    integerList.setIntegers(unsortedList);

    when(radixSortingService.sort(unsortedList)).thenReturn(sortedList);

    mockMvc
        .perform(
            post("/api/v1/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"integers\":[3,1,2]}"))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"integers\":[1,2,3]}"));
  }

  /*
   * Tests the sorting of a list of negative integers
   */
  @Test
  public void testSortNegativeIntegers() throws Exception {
    ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(-3, -1, -2));
    ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(-3, -2, -1));
    IntegerListWrapper integerList = new IntegerListWrapper();
    integerList.setIntegers(unsortedList);

    when(radixSortingService.sort(unsortedList)).thenReturn(sortedList);

    mockMvc
        .perform(
            post("/api/v1/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"integers\":[-3,-1,-2]}"))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"integers\":[-3,-2,-1]}"));
  }

  /*
   * Test for empty list of integers should result in bad request.
   */
  @Test
  public void testSortEmptyList() throws Exception {
    ArrayList<Integer> emptyList = new ArrayList<>();
    IntegerListWrapper integerList = new IntegerListWrapper();
    integerList.setIntegers(emptyList);

    when(radixSortingService.sort(emptyList)).thenReturn(emptyList);

    mockMvc
        .perform(
            post("/api/v1/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"integers\":[]}"))
        .andExpect(status().isBadRequest());
  }

  /*
   * Test for null list of integers should result in bad request.
   */
  @Test
  public void testSortSingleElementList() throws Exception {
    ArrayList<Integer> singleElementList = new ArrayList<>(Arrays.asList(1));
    IntegerListWrapper integerList = new IntegerListWrapper();
    integerList.setIntegers(singleElementList);

    when(radixSortingService.sort(singleElementList)).thenReturn(singleElementList);

    mockMvc
        .perform(
            post("/api/v1/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"integers\":[1]}"))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"integers\":[1]}"));
  }
}
