package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.pulsar.annotation.EnablePulsar;

@SpringBootApplication
@EnablePulsar
public class CreditCardGenerationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardGenerationApplication.class, args);
	}

}