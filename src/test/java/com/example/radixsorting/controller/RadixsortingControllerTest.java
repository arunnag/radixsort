package com.example.radixsorting.controller;

import com.example.radixsorting.dto.IntegerList;
import com.example.radixsorting.service.RadixSortingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class RadixsortingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RadixSortingService radixSortingService;

    @InjectMocks
    private RadixSortingController radixSortingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(radixSortingController).build();
    }

    @Test
    public void testSortMixedIntegers() throws Exception {
        ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(-10, 25, -1000, 24000, 7, -3));
        ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(-1000, -10, -3, 7, 25, 24000));
        IntegerList integerList = new IntegerList();
        integerList.setIntegers(unsortedList);

        when(radixSortingService.sort(unsortedList)).thenReturn(sortedList);

        mockMvc.perform(post("/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"integers\":[-10,25,-1000,24000,7,-3]}"))
                .andExpect(status().isOk())
                .andExpect(content().json("[-1000,-10,-3,7,25,24000]"));
    }


    @Test
    public void testSortIntegers() throws Exception {
        ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(3, 1, 2));
        ArrayList<Integer> sortedList = new ArrayList<>(Arrays.asList(1, 2, 3));
        IntegerList integerList = new IntegerList();
        integerList.setIntegers(unsortedList);

        when(radixSortingService.sort(unsortedList)).thenReturn(sortedList);

        mockMvc.perform(post("/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"integers\":[3,1,2]}"))
                .andExpect(status().isOk())
                .andExpect(content().json("[1,2,3]"));
    }

    @Test
    public void testSortEmptyList() throws Exception {
        ArrayList<Integer> emptyList = new ArrayList<>();
        IntegerList integerList = new IntegerList();
        integerList.setIntegers(emptyList);

        when(radixSortingService.sort(emptyList)).thenReturn(emptyList);

        mockMvc.perform(post("/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"integers\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testSortSingleElementList() throws Exception {
        ArrayList<Integer> singleElementList = new ArrayList<>(Arrays.asList(1));
        IntegerList integerList = new IntegerList();
        integerList.setIntegers(singleElementList);

        when(radixSortingService.sort(singleElementList)).thenReturn(singleElementList);

        mockMvc.perform(post("/sort")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"integers\":[1]}"))
                .andExpect(status().isOk())
                .andExpect(content().json("[1]"));
    }
}

