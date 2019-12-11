package com.chassot.auth.server;

/*
 * Test class added ONLY to cover main() invocation not covered by application tests.
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {Application.class})
public class ApplicationTest {

	@DisplayName("Default main method test.")
	@Test
	public void mainTest() {
		Application.main(new String[] {});
	}

}
