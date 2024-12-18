package com.kemal.spring;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringThymeleafJpaApplication {

	public static void main(String[] args) {
		 generateString();
		 SpringApplication.run(SpringThymeleafJpaApplication.class, args);
	}

	public static String generateString() {
		String uuid = UUID.randomUUID().toString();
		return "uuid = " + uuid;
	}
}
