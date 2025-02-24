package com.johnverz.springboot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Springboot1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot1Application.class, args);
	}

	@GetMapping("/hello")
	public String hello(Model model){
		String message = "If life gives you lemons, then make lemonades then sell on the street and make a living!";
		model.addAttribute("message", message);

		Student student = new Student(123, "Jonas", "Ciubal", "de Leon", "Jr", "Male", "2005-09-11", "Lingsat, SFC");

		model.addAttribute("student", student);

		return "hello";
	}
}
