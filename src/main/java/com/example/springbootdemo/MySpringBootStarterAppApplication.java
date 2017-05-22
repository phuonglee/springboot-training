package com.example.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.springbootdemo.configuration.JpaConfiguration;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.example.springbootdemo"})
public class MySpringBootStarterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootStarterAppApplication.class, args);
	}
}
