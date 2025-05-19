package com.johnverz.student.controllers;

import com.johnverz.student.services.StudentService;
import com.johnverz.student.models.AppUser;
import com.johnverz.student.models.Student;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/catalog")
    public String catalog(Model model){
        model.addAttribute("students", studentService.getStudents());
        return "catalog";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "layouts/master";
    }

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, Model model, HttpSession session) {
        //check if user is logged in
        AppUser currentUser = (AppUser) session.getAttribute("user");
        if(currentUser == null){
            return "redirect:/login";
        }

        model.addAttribute("students", studentService.searchStudent(search));
        model.addAttribute("activeMenu", "home");

        return "index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id, HttpSession session) {
        //check if user is logged in
        AppUser currentUser = (AppUser) session.getAttribute("user");
        if(currentUser == null){
            return "redirect:/login";
        }

        studentService.deleteStudent(id);

        return "redirect:/";
    }

    @GetMapping("/new")
    public String create(Model model, HttpSession session) {
        //check if user is logged in
        AppUser currentUser = (AppUser) session.getAttribute("user");
        if(currentUser == null){
            return "redirect:/login";
        }

        int[] levels = {1,2,3,4};
        model.addAttribute("levels", levels);
        Student newStudent = new Student();
        newStudent.setGender("Male");
        model.addAttribute("newStudent", newStudent);
        model.addAttribute("activeMenu", "create");

        //model.addAttribute("levels", new int[]{1,2,3,4});
        return "create";
    }

    @PostMapping("/save")
    public String store(@ModelAttribute("newStudent") @Valid Student student, BindingResult bindingResult, @RequestParam("imageFile") MultipartFile profilePicture, HttpSession session) {
        //check if user is logged in
        AppUser currentUser = (AppUser) session.getAttribute("user");
        if(currentUser == null){
            return "redirect:/login";
        }

        //go back to form if errors are present then display them
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "create";
        }

        //handle the file uploaded
        if(!profilePicture.isEmpty()){
            String path = "data/profile_pictures/";
            File uploadFolder = new File(path);

            //create folder if not existing
            if(!uploadFolder.exists()){
                uploadFolder.mkdirs();
            }

//            String fileName = student.getId() + "_" + profilePicture.getOriginalFilename();
            String fileName = UUID.randomUUID() + profilePicture.getOriginalFilename().substring(profilePicture.getOriginalFilename().lastIndexOf(".")) ;

            try {
                profilePicture.transferTo(new File(uploadFolder.getAbsolutePath()+ File.separator +fileName));
                student.setProfilePicture(fileName);
            } catch (IOException e) {
                System.out.println("File upload error: " + e.getMessage());
            }
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

    @GetMapping("/student/{id}")
    public String view(@PathVariable int id, Model model, HttpSession session) {
        //check if user is logged in
        AppUser currentUser = (AppUser) session.getAttribute("user");
        if(currentUser == null){
            return "redirect:/login";
        }

        Student s = studentService.getStudent(id);
        //s.setProfilePicture(null);
        model.addAttribute("student", s);
        return "student";
    }
}