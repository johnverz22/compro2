package com.johnverz;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        Student[] students = new Student[2];
        students[0] = new Student();
        students[0].setEmail("hello@gmail.com");
        System.out.println(students[0].getEmail());

//        var x = 100;   //type inferencing
//        x = "asdfsa";   //do not do this
       // ArrayList<> studentList = new ArrayList<>();

        ArrayList<String> ngolors = new ArrayList<>();
        ngolors.add("Mink");
        ngolors.add("murmle");
        ngolors.add("nred");

        System.out.println(ngolors.get(1));
        //not this ngolors[1]


        //var studentList = new ArrayList<Student>();
        //List<Student> studentList = new ArrayList<>();
        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1234, "de Tiger", "Ernest", "Marcos", LocalDate.now(), "grrr@gmai.com"));

        System.out.println(studentList.get(0).getFullName());
    }
}