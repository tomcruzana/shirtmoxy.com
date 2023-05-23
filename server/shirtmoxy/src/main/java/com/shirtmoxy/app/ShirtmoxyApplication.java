package com.shirtmoxy.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ShirtmoxyApplication implements CommandLineRunner {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ShirtmoxyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String serverPort = env.getProperty("server.port");
		System.out.println("Welcome to Shirt Moxy Local Dev Server!\nRunning on Port " + serverPort);

	}

}
