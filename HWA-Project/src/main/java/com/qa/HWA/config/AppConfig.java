package com.qa.HWA.config;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public LocalTime theTime() {
		
		return LocalTime.now();
		
	}
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
