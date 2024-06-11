package com.origin.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = {"com.origin.template", "com.origin.security", "com.origin.standard", "com.origin.utilities"} )
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class OriginTemplate extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(OriginTemplate.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OriginTemplate.class);
	}
}
