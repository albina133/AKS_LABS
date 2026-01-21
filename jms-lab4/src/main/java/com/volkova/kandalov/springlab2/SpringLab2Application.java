package com.volkova.kandalov.springlab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class SpringLab2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringLab2Application.class, args);
	}

}
