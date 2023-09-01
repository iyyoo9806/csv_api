package com.kopo.csv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kopo.*"})
public class CsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvApplication.class, args);
	}

}
