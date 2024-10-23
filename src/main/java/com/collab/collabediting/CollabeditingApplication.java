package com.collab.collabediting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableConfigurationProperties
public class CollabeditingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CollabeditingApplication.class, args);
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOriginPatterns("**") // Allow all origins
				.allowedHeaders("**") // Allow all headers
				.allowedMethods("**") // Allow all methods
				.allowCredentials(true); // Allow credentials
	}

}
