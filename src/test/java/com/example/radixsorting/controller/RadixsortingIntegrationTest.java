package com.example.radixsorting.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.radixsorting.dto.IntegerListWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RadixsortingIntegrationTest {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void testRadixSortEndpoint() {
    String url = "/api/v1/sort";
    ArrayList<Integer> inputList = new ArrayList<>(Arrays.asList(170, 45, 75, 90, 802, 24, 2, 66));
    ResponseEntity<IntegerListWrapper> response =
        restTemplate.postForEntity(
            url, new IntegerListWrapper(inputList), IntegerListWrapper.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getIntegers()).containsExactly(2, 24, 45, 66, 75, 90, 170, 802);
  }

  @Test
  public void testRadixSortEndpointWithStringList() {
    String url = "/api/v1/sort";
    ArrayList<String> invalidInputList = new ArrayList<>(Arrays.asList("a", "b", "c"));
    ResponseEntity<String> response =
        restTemplate.postForEntity(url, invalidInputList, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(response.getBody()).contains("Malformed JSON request");
  }

  @Test
  public void testRadixSortEndpointWithEmptyRequestBody() {
    String url = "/api/v1/sort";
    ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  // add a test check if millions of integers are sorted correctly
  @Test
  public void testRadixSortEndpointWithLargeInput() {
    String url = "/api/v1/sort";
    ArrayList<Integer> largeInputList = new ArrayList<>();
    final int SIZE = 10000; // 1 million integers
    for (int i = 0; i < SIZE; i++) {
      largeInputList.add((int) (Math.random() * SIZE) - SIZE / 2); // Random integers
    }
    ResponseEntity<IntegerListWrapper> response =
        restTemplate.postForEntity(
            url, new IntegerListWrapper(largeInputList), IntegerListWrapper.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
  }
}
