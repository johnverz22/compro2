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
    public static final int TOTAL_SUBJECTS = 64;
    public static void main(String[] args){
        //datatype varName = initialValue;
        String name;
        String subject;
        /*
        grades[0] = prelim
        grades[1] = midterm
        grades[2] = finals
         */

        String[] terms = {"Prelim", "Midterm", "Finals"};
        String[] subjects = new String[TOTAL_SUBJECTS];
        double[][] grades = new double[TOTAL_SUBJECTS][terms.length];

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        name = scanner.nextLine();

        for(int i = 0; i < TOTAL_SUBJECTS; i++){

            System.out.print("Enter subject " + (i+1) + ": ");
            subjects[i] = scanner.nextLine();

            for(int j = 0; j < terms.length; j++){
                System.out.print("\t" + terms[j] + ": ");
                try {
                    grades[i][j] = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("\tInvalid grade, try again");
                    j--; //go back and fill in the missed grade because of invalid input
                }

                if(grades[i][j] < MIN_GRADE){
                    System.out.println("\tInvalid grade, try again");
                    j--;
                }
            }

            System.out.print("Add subject (y/n): ");
            char c = scanner.nextLine().charAt(0);
            if(Character.toLowerCase(c) != 'y') break;


        }
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");

        sb.append(String.format("%-15s%-10s%-10s%-10s%-10s\n", "Subject", terms[0], terms[1], terms[2], "Final Rating"));

        for(int i = 0; i < TOTAL_SUBJECTS; i++) {

            sb.append(String.format("%-15s", subjects[i]));

            for(int j = 0; j < terms.length; j++) {
                sb.append(String.format("%8.2f", grades[i][j]));
            }
            sb.append(String.format("%8.2f", getFinalRating(grades[i])));


            sb.append("\n");

            if(subjects[i] == null) break;
        }

        System.out.println(sb.toString());
        writeToFile(name, sb.toString());

        /*
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
        */
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