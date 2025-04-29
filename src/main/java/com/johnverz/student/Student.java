
package com.johnverz.student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Student {
    //instance vars, non-static
    private int id;
    @NotBlank(message="First name is required")
    private String firstName;
    @NotBlank(message="Last name is required? ")
    private String lastName;
    private String middleName;
    private String gender;
    private LocalDate birthDay;
    private String address;
    private String suffix;
    private int level;
    private String profilePicture;

    @NotBlank(message = "Email is required")
    @Email(message="Invalid email address")
    private String email;
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
                   String address,
                   int level, String profilePicture){

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDay = birthDay;
        this.address = address;
        this.suffix = suffix;
        this.level = level;
    }

    public int getAge(){
        //Period p = Period.between(birthDay, LocalDate.now());
        return Period.between(birthDay, LocalDate.now()).getYears();
    }

    public void setBirthDay(String bday){
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        birthDay = LocalDate.parse(bday,f );
    }

    public void setBirthDay2(String bday){
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        birthDay = LocalDate.parse(bday,f );
    }

    public void setBirthDay(LocalDate bday){
        this.birthDay = bday;
    }

    public String getBirthDay(){
        if(birthDay == null){
            return "";
        }
        return birthDay.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }

    public String getBirthDay2(){
        if(birthDay == null){
            return "";
        }
        return birthDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDate getBirthDate(){
        return birthDay;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMiddleName(){
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}