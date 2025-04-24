package com.johnverz.student;

import jakarta.servlet.http.HttpSession;
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
    public String index(@RequestParam(defaultValue = "") String search, HttpSession session, Model model) {
        //check if user logged in
        AppUser user = (AppUser) session.getAttribute("loggedInUser");
        if(user == null) {
            return "redirect:/login";
        }

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
        //go back to form if errors are present then display them
        if(bindingResult.hasErrors()){
            return "create";
        }

        // save the object if form is valid or pass all rules
        studentService.addStudent(student);

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
    public String update(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult, Model model){
        //go back to form if errors are present then display them
        if (bindingResult.hasErrors()) {
            // Re-populate the necessary data for the form
            int[] levels = {1, 2, 3, 4};
            model.addAttribute("levels", levels);
            model.addAttribute("student", student); // Re-add the student object
            System.out.println(bindingResult.getAllErrors());
            return "edit";
        }

        Student existingStudent = studentService.getStudent(student.getId());
        if(existingStudent != null) {
            // save the object if form is valid or pass all rules
            studentService.updateStudent(student.getId(), student);
        }

        return "redirect:/";
    }
}