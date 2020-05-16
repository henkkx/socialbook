package com.socialbook.socialbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SocialbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialbookApplication.class, args);
	}

}
