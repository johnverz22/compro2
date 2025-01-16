package com.johnverz;

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
            grades[i] = scanner.nextInt();
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


    }

    public static double getFinalRating(double[] termGrades){
        double finalRating = termGrades[0] * .3 + termGrades[1] * .3 + termGrades[2] * .4;
        return finalRating;
    }

}