package com.johnverz;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main{
    public static void main(String[] args) {
        Student student1 = new Student(); //creating an object -> instantiating an object Student
        student1.setId(240414);

        System.out.println(student1.getId());
        student1.lastName = "Ciubal";
        student1.firstName = "Regin Jacob";
        student1.middleName = "Heruela";
        student1.email = "reginjacob.ciubal@lorma.edu";
        student1.birthDay = LocalDate.parse("2005-07-12", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println(student1.birthDay.getYear());
        Period age = Period.between(LocalDate.now(), student1.birthDay);
        int a = age.getYears();
        System.out.println("Age: " + Math.abs(a));


        LocalDate s2Bday = LocalDate.parse("2004-11-16", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Student s2 = new Student(2402497,
                "Nera",
                "Christian Jacob",
                "Torres",
                s2Bday,
                "christianjacob.nera@lorma.edu");// creating an object

        System.out.println(s2.getFullName());
        System.out.println(s2.getAge());

    }
}