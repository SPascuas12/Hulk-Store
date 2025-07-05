package com.sergiopascuas.hulk_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.sergiopascuas.hulk_store")
public class HulkStoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(HulkStoreApplication.class, args);
	}

}
