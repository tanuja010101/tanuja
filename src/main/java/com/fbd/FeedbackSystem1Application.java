package com.fbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2

@SpringBootApplication


public class FeedbackSystem1Application {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackSystem1Application.class, args);
	}

}
