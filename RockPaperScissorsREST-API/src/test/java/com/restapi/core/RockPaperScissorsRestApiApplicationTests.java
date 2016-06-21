package com.restapi.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * The Class RockPaperScissorsRestApiApplicationTests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RockPaperScissorsMain.class)
@WebAppConfiguration
public class RockPaperScissorsRestApiApplicationTests {

	/**
	 * Context loads.
	 */
	@Test
	public void contextLoads() {
	}

}
