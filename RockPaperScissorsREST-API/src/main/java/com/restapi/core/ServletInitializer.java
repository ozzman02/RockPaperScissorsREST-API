package com.restapi.core;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * The Class ServletInitializer.
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/* (non-Javadoc)
	 * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RockPaperScissorsMain.class);
	}

}
