/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author Dev10
 */
public class UserIOConsoleImpl implements UserIO{/*class that implements userIO interface*/
    
    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double input = sc.nextDouble();
        sc.nextLine();
        return input;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double input = sc.nextDouble();
        while(input < min | input > max){
           System.out.println("Error! Please choose numbers between " + min 
            + " and " +  max);
        input = sc.nextDouble();
        }
        sc.nextLine();
           return input;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float input = sc.nextFloat();
        sc.nextLine();
        return input;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        float input = sc.nextFloat();
        while(input < min | input > max){
           System.out.println("Error! Please choose numbers between " + min 
           + " and " + max);
        input = sc.nextFloat();
        }
        sc.nextLine();
           return input;
    }

    @Override
    public int readInt(String prompt) { 
        System.out.println(prompt); 
        int input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int input = sc.nextInt();
        while(input < min | input > max){
           System.out.println("Error! Please choose numbers between " + min 
           + " and " + max);
        input = sc.nextInt();
        }
        sc.nextLine();
           return input;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt); 
        long input = sc.nextLong();
        sc.nextLine();
        return input;   
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        long input = sc.nextLong();
        while(input < min | input > max){
           System.out.println("Error! Please choose numbers between " + min + " and " 
            + max);
        input = sc.nextLong();
        }
        sc.nextLine();
           return input;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String input = sc.nextLine();
        return input;
    }
    
}
