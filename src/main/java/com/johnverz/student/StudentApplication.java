package com.johnverz.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StudentApplication {
	@Autowired
	static UserService userService;

	public static void main(String[] args) {SpringApplication.run(StudentApplication.class, args);
	}

}
