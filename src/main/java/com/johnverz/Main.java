package com.johnverz;

import org.mindrot.jbcrypt.BCrypt;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String password = "Ilovejava";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
        System.out.println(hashed);

        String loginPassword = "Ilovejav";
        if(BCrypt.checkpw(loginPassword, hashed)){
            System.out.println("You are authorized!");
        }else{
            System.out.println("Are you a hacker?");
        }
    }
}