package com.johnverz.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.*;

@SpringBootApplication
public class StudentApplication {

	public static void main(String[] args) {
		//run();
		SpringApplication.run(StudentApplication.class, args);
	}

	public static void run(){
		String plainPassword = "secret";
		String hash = new BCryptPasswordEncoder().encode(plainPassword);
		System.out.println(hash);
	}

}
