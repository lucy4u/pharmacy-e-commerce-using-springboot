package com.pharm.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pharm.implement.repository.productrepository;

@SpringBootApplication
public class PharmacyApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PharmacyApplication.class, args); 
	}
	 
	@Autowired
	  private productrepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
