package com.johnverz.student;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDay;
    private String email;

    public Student(){

    }

    /**
     *
     * @param id Student ID
     * @param lastName
     * @param firstName
     * @param middleName
     * @param birthDay
     * @param email
     */
    public Student(int id,
                   String lastName,
                   String firstName,
                   String middleName,
                   LocalDate birthDay,
                   String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDay = birthDay;
        this.email = email;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public int getAge(){
        Period age = Period.between(this.birthDay, LocalDate.now());
        return age.getYears();
    }

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}