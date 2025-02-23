package com.johnverz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Controller
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Student List");
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1, "John", "Doe", "Smith", "Jr.", "M", "1990-01-01", "123 Main St."));
        students.add(new Student(2, "Jane", "Doe", "Smith", "Jr.", "F", "1990-01-01", "123 Main St."));
        students.add(new Student(3, "James", "Doe", "Smith", "Jr.", "M", "1990-01-01", "123 Main St."));
        students.add(new Student(4, "Jill", "Doe", "Smith", "Jr.", "F", "1990-01-01", "123 Main St."));
        model.addAttribute("students", students);
        System.out.println(students);
        return "home";
    }

}
