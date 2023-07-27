package com.mmt.complaintform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value=ComplaintFormProperties.class) //properties file
public class ComplaintFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplaintFormApplication.class, args);
	}

}
