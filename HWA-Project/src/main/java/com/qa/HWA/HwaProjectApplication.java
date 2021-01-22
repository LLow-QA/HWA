package com.qa.HWA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;




@SpringBootApplication
public class HwaProjectApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(HwaProjectApplication.class, args);
	
		System.out.println("Start time: " + beanBag.getBean("theTime", String.class));
	}

}
