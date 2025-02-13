package com.johnverz;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Student {
    //instance vars, non-static
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String suffix;
    private String gender;
    private LocalDate birthDay;
    private String address;

    //parameterless or default constructor
    public Student(){

    }

    /**
     * Constructor to accept property values
     * @param id the id of the student
     * @param firstName first name of the student
     * @param middleName
     * @param lastName
     * @param suffix
     * @param gender
     * @param birthDay
     * @param address
     */
    public Student(int id,
                   String firstName,
                   String middleName,
                   String lastName,
                   String suffix,
                   String gender,
                   LocalDate birthDay,
                   String address){

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.suffix = suffix;
        this.gender = gender;
        this.birthDay = birthDay;
        this.address = address;
    }

    public int getAge(){
        //Period p = Period.between(birthDay, LocalDate.now());
        return Period.between(birthDay, LocalDate.now()).getYears();
    }

    public void setBirthDate(String bday){
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        birthDay = LocalDate.parse(bday,f );
    }

    //accessor or getter   and mutator or setter methods

    //setter/mutator
    public void setFirstName(String firstName){
        firstName = firstName.trim();
        //extract individual names into array, and recreate the name

        //e.g  adrian lester

        String[] names = firstName.split("\\s");
        this.firstName = "";
        for(String name: names){
            if(!this.firstName.isEmpty())
                this.firstName += " ";
            if(name.length() > 2)
                this.firstName += name.substring(0,1).toUpperCase() + name.substring(1, name.length());
            else
                this.firstName += name.toUpperCase();
        }
    }

    //getter/accessor
    public String getFirstName(){
        return firstName;
    }

}
