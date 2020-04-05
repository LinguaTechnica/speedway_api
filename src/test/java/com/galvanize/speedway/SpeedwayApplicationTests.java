package com.galvanize.speedway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// ActiveProfile will read from resources/*.properties file when run manually.
// build.gradle also has it configured so that gradle runs it for all tests.
@SpringBootTest
@ActiveProfiles("test")
public class SpeedwayApplicationTests {

	@Test
	void contextLoads() {
	}

}

