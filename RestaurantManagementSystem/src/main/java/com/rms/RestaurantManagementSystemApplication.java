package com.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestaurantManagementSystemApplication extends SpringBootServletInitializer {
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setIncludeHeaders(false);
		loggingFilter.setMaxPayloadLength(10000);
		return loggingFilter;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(RestaurantManagementSystemApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementSystemApplication.class, args);
	}

}
