package com.johnverz.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HomeController {
    ArrayList<Student> students;


    public HomeController(){
        students = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(75);
        s1.setFirstName("Petabyte");
        s1.setLastName("de Leon");

        Student s2 = new Student();
        s2.setId(76);
        s2.setLastName("Linatoc");
        s2.setFirstName("Gelo");


        Student s3 = new Student();
        s3.setId(77);
        s3.setLastName("Gacayan");
        s3.setFirstName("Zedric");


        students.add(s1);
        students.add(s2);
        students.add(s3);

    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/delete")
    public String destroy(@RequestParam int id){


//        for(Student student : students){
//            if(student.getId() == id){
//                index = students.indexOf(student);
//            }
//        }

//        for(int i = 0; i< students.size(); i++){
//            if(students.get(i).getId() == id){
//                students.remove(i);
//            }
//        }


        //remove the arraylist item if it matches teh condition
        students.removeIf(s -> s.getId() == id);


        return "redirect:/";
    }

    @GetMapping("/add")
    public String add(){
        return "new";
    }

    @PostMapping("/save")
    public String save(@RequestParam String firstName, @RequestParam String lastName){
        Student s = new Student();
        s.setId(students.getLast().getId() + 1);
        s.setFirstName(firstName);
        s.setLastName(lastName);
        //add new student to the array list
        students.add(s);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        for (Student student : students) {
            if (student.getId() == id) {
                model.addAttribute("student", student);
                return "edit";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@RequestParam int id,
                         @RequestParam String firstName,
                         @RequestParam String lastName) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setFirstName(firstName);
                student.setLastName(lastName);
                break;
            }
        }
        return "redirect:/";
    }
}
