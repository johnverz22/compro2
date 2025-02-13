package com.johnverz;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Main{
    public static void main(String[] args) {
        Student s1 = new Student(); //creating an object of the class, calling one of the available constructors
        s1.setFirstName("mark john doe");

        System.out.println(s1.getFirstName());
//        s1.firstName = "Kate";
//        s1.middleName = "Mendoza";
//        s1.lastName = "Baltazar";
//        s1.id = 4903020;
//        s1.address = "San Juan";
//        s1.gender = "Male";

//        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate bday = LocalDate.parse("2005-07-24",f );
//
//        s1.birthDay = bday;
//        s1.birthDay = LocalDate.parse("2001-03-09", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        s1.setBirthDate("2001-02-16");
        //System.out.println(s1.firstName + " " + s1.lastName);


        System.out.println("Age now is " + s1.getAge());


        Student s2 = new Student(123456, "Adrian", "Mendoza", "Lim", "IV", "Male", LocalDate.now(), "Planet Mars");
        System.out.println("student 2 bday is " + s2.getAge());

    }
}