package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.service.DependencyManger;
import com.example.demo.util.PackageInfoNodeDeserializer;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class SnykEx {
	
	@Autowired
	public PackageInfoNodeDeserializer t;

	public static void main(String[] args) {
		SpringApplication.run(SnykEx.class, args);
	}

}

