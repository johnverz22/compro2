package com.johnverz.student;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

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
        Student newStudent = new Student();
        newStudent.setGender("Male");
        model.addAttribute("newStudent", newStudent);
        //model.addAttribute("levels", new int[]{1,2,3,4});
        return "create";
    }

    @PostMapping("/save")
    public String store(@ModelAttribute("newStudent") @Valid Student student, BindingResult bindingResult) {

        studentService.addStudent(student);

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "create";
        }

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