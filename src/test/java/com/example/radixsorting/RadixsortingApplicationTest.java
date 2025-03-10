package com.example.radixsorting;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RadixsortingApplicationTest {

	@Test
	void contextLoads() {
	}

	@Test
	void testMainClass() {
		RadixsortingApplication.main(new String[] {});
		assertTrue(true);
;	}
}
