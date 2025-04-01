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

  /*
   * Tests the /api/v1/sort endpoint of the RadixsortingController.
   * It sends a POST request with a list of integers and checks if the response
   * contains the sorted list of integers.
   * The test uses the TestRestTemplate to send the request and receive the response.
   * The test checks if the response status is OK (200) and if the body contains
   * the expected sorted list of integers.
   * The test also checks if the response body is not null and contains the expected
   * sorted list of integers.
   */
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

  /*
   * Tests the /api/v1/sort endpoint of the RadixsortingController.
   * It sends a POST request with a list of strings and checks if the response
   * contains a bad request status (400) with a message indicating that the input
   * is not valid.
   * The test uses the TestRestTemplate to send the request and receive the response.
   * The test checks if the response status is BAD_REQUEST (400) and if the body
   * contains the expected error message.
   */
  @Test
  public void testRadixSortEndpointWithStringList() {
    String url = "/api/v1/sort";
    ArrayList<String> invalidInputList = new ArrayList<>(Arrays.asList("a", "b", "c"));
    ResponseEntity<String> response =
        restTemplate.postForEntity(url, invalidInputList, String.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(response.getBody()).contains("Malformed Request body");
  }

  /*
   * Tests the /api/v1/sort endpoint of the RadixsortingController.
   * It sends a POST request with an empty request body and checks if the response
   * contains a bad request status (400) with a message indicating that the input
   * is not valid.
   * The test uses the TestRestTemplate to send the request and receive the response.
   * The test checks if the response status is BAD_REQUEST (400) and if the body
   * contains the expected error message.
   */
  @Test
  public void testRadixSortEndpointWithEmptyRequestBody() {
    String url = "/api/v1/sort";
    ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  /*
   * Tests the /api/v1/sort endpoint of the RadixsortingController.
   * It sends a POST request with a large input list of random integers
   * and checks if the response
   * contains the sorted list of integers.
   * The test uses the TestRestTemplate to send the request and receive the response.
   * The test checks if the response status is OK (200) and if the body contains
   * the expected sorted list of integers.
   * The test also checks if the response body is not null and contains the expected
   * sorted list of integers.
   */
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
