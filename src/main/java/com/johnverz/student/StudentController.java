package com.johnverz.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class StudentController {
    StudentService studentService;

    public StudentController() {
        studentService = new StudentService();
    }


    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, Model model) {
        model.addAttribute("students", studentService.searchStudent(search));

        return "index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        studentService.deleteStudent(id);

        return "redirect:/";
    }

    @GetMapping("/new")
    public String create(Model model){
        int[] levels = {1,2,3,4};
        model.addAttribute("levels", levels);
        //model.addAttribute("levels", new int[]{1,2,3,4});
        return "create";
    }

    @PostMapping("/save")
    public String store(@RequestParam String firstName,
                        @RequestParam String middleName,
                        @RequestParam String lastName,
                        @RequestParam String suffix,
                        @RequestParam(defaultValue = "Male") String gender,
                        @RequestParam(required = false) LocalDate birthDay,
                        @RequestParam(defaultValue = "") String address,
                        @RequestParam int level){


        Student s = new Student(studentService.getLastId() + 1,
                firstName,
                middleName,
                lastName,
                suffix,
                gender,
                birthDay,
                address,
                level
        );

        studentService.addStudent(s);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Student s = studentService.getStudent(id);
        if(s != null){
            int[] levels = {1,2,3,4};
            model.addAttribute("levels", levels);
            model.addAttribute("student", s);
            return "edit";
        }

        return "redirect:/";
    }

    @PostMapping("/update")
    public String store(@RequestParam int id,
                        @RequestParam String firstName,
                        @RequestParam String middleName,
                        @RequestParam String lastName,
                        @RequestParam String suffix,
                        @RequestParam(defaultValue = "Male") String gender,
                        @RequestParam(required = false) LocalDate birthDay,
                        @RequestParam(defaultValue = "") String address,
                        @RequestParam int level){

        Student s = studentService.getStudent(id);
        if(s != null){
            s.setFirstName(firstName);
            s.setLastName(lastName);
            s.setGender(gender);
            s.setMiddleName(middleName);
            s.setSuffix(suffix);
            s.setBirthDay(birthDay);
            s.setAddress(address);
            s.setLevel(level);

            studentService.updateStudent(id, s);
        }


        return "redirect:/";
    }
}