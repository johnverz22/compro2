package com.johnverz;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * Student Grade Calculator
 * Lets the user enter his prelim,
 * midterm, final grades
 * and the program will compute the
 * final rating and save the grades into file

 */
public class Main{
    public static final double MIN_GRADE = 50;
    public static final String FILE_DIR = "target/records";
    public static void main(String[] args){
        //datatype varName = initialValue;
        String name;
        String subject;
        double[] grades = new double[3];
        /*
        grades[0] = prelim
        grades[1] = midterm
        grades[2] = finals
         */

        String[] terms = {"Prelim", "Midterm", "Finals"};

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        name = scanner.nextLine();

        System.out.print("Enter subject: ");
        subject = scanner.nextLine();

        for(int i = 0; i < grades.length; i++){
            System.out.print("Enter grade for " + terms[i] + ": ");

            try {
                grades[i] = scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid grade");
                scanner.nextLine(); // to delete the enter key
                i--;
            }

            if(grades[i] < MIN_GRADE){
                System.out.println("Invalid grade");
                i--;
            }

        }

        String result = String.format("""
                Name: %s
                Subject: %s
                Prelim: %.2f
                Midterm: %.2f
                Finals: %.2f
                Final Rating: %.2f
                """, name, subject, grades[0], grades[1], grades[2], getFinalRating(grades));

        System.out.println("Grade Computation Results");
        System.out.println(result);
        writeToFile(name, result);
        System.out.println("File saved.");

        System.out.println("\n\nReading Files -----------------");
        readAllFiles();

    }

    /**
     * This fuction computes for the final rating
     * Formula: 30% of Prelim + 30 % of Midterm + 40% of Finals
     *
     * @param termGrades double[] - of 3 elements containing grade for prelims, midterm and finals
     * @return - double - the final rating
     */
    public static double getFinalRating(double[] termGrades){
        double finalRating = termGrades[0] * .3 + termGrades[1] * .3 + termGrades[2] * .4;
        return finalRating;
    }


    public static void writeToFile(String fileName,  String data){
        File folder = new File(FILE_DIR);
        if(folder.exists() == false){
            folder.mkdirs();
        }
        File file = new File(folder, fileName);
        try ( FileWriter fw = new FileWriter(file)) {
            fw.write(data);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
    public static void readAllFiles(){
        File folder = new File(FILE_DIR);
        File[] studentGradeFiles = folder.listFiles();
        for(int i = 0; i < studentGradeFiles.length; i++){
            if(studentGradeFiles[i].isFile()){
                readFile(studentGradeFiles[i]);
            }
        }
    }

    public static void readFile(File file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            System.out.println("--------------------------");
            String line;
            while((line = br.readLine()) !=null ){
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}