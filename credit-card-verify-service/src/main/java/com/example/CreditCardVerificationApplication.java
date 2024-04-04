package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.pulsar.annotation.EnablePulsar;

@SpringBootApplication
@EnablePulsar
public class CreditCardVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardVerificationApplication.class, args);
	}

}
