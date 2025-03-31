package com.johnverz.student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private ArrayList<Student> students;
    private final String FILE_NAME = "database.csv";

    public StudentService() {
        students = new ArrayList<>();
        //Read the CSV already just when the constructor is called
        readFromDisk();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void deleteStudent(int id){
        students.removeIf(s -> s.getId() == id);
        writeToDisk();
    }

    public List<Student> searchStudent(String keyword){
        if(keyword.trim().isEmpty()){
            return students;
        }

        return students.stream().filter(s ->
                s.getLastName().toLowerCase().contains(keyword.toLowerCase())
                || s.getFirstName().toLowerCase().contains(keyword.toLowerCase())
                || s.getAddress().toLowerCase().contains(keyword.toLowerCase())
        ).collect(Collectors.toList());
    }

    public Student getStudent(int id){
        for(Student s: students){
            if(s.getId() == id)
                return s;
        }

        return null;
    }

    public void updateStudent(int id, Student update){
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getId() == id){
                students.set(i, update);
                writeToDisk();
                break;
            }
        }
    }
    public void addStudent(Student student){
        students.add(student);
        writeToDisk();
    }



    public int getLastId(){
        if(students.isEmpty()){
            return 0;
        }
        return students.get(students.size()-1).getId();
    }

    /**
     * This saves the students ArrayList into a CSV file
     */
    public void writeToDisk(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))){
            //write the content of the arraylist into csv
            for(Student s : students){
                bw.write(s.getId() + ","
                        + s.getFirstName() + ","
                        + s.getMiddleName() + ","
                        + s.getLastName() + ","
                        + s.getSuffix() + ","
                        + s.getBirthDay2() + ","
                        + s.getGender() + ","
                        + s.getAddress() + ","
                        + s.getLevel()
                );
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println("Uh-oh! Error: " + e.getMessage());
        }
    }

    /**
     * This read the CSV file and loads it to the students ArrayList
     */
    public void readFromDisk(){
        File file = new File(FILE_NAME);
        if(!file.exists()){
            System.out.println("file not found");
            return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");

                Student s = new Student();
                s.setId(Integer.parseInt(data[0]));
                s.setFirstName(data[1]);
                s.setMiddleName(data[2]);
                s.setLastName(data[3]);
                s.setSuffix(data[4]);
                s.setBirthDay2(data[5]);
                s.setGender(data[6]);
                s.setAddress(data[7]);
                s.setLevel(Integer.parseInt(data[8]));
                //add the student the array list
                students.add(s);
            }
        }catch(IOException e){
            System.out.println("Uh-oh! Error: " + e.getMessage());
        }
    }
}
