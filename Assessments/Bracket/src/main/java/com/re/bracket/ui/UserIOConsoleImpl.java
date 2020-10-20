/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author rober
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        double number = 0;
        boolean hasError;
        do {
            hasError = false;
            try {
                System.out.println(prompt);
                number = sc.nextDouble();
            } catch (InputMismatchException e) {
                hasError = true;
                System.out.println("Please enter a valid input value.");
            }
            sc.nextLine();
        } while (hasError);
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {

        boolean hasError;
        double number = 0;
        do {
            hasError = false;
            try {
                System.out.println(prompt);
                number = sc.nextDouble();
            } catch (InputMismatchException e) {
                hasError = true;
                System.out.println("Please enter a valid input value.");
            }
            sc.nextLine();
            if (number < min | number > max) {
                hasError = true;
                System.out.println("Enter a value between " + min + " and " + max
                        + ".");
            }
        } while (hasError);

        return number;
    }

    @Override
    public float readFloat(String prompt) {

        float number = 0;
        boolean hasError;
        do {
            hasError = false;
            try {
                System.out.println(prompt);
                number = sc.nextFloat();
            } catch (InputMismatchException e) {
                hasError = true;
                System.out.println("Please enter a valid input value.");
            }
            sc.nextLine();
        } while (hasError);
        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        boolean hasError;
        float number = 0;
        do {
            hasError = false;
            try {
                System.out.println(prompt);
                number = sc.nextFloat();
            } catch (InputMismatchException e) {
                hasError = true;
                System.out.println("Please enter a valid input value.");
            }
            sc.nextLine();
            if (number < min | number > max) {
                hasError = true;
                System.out.println("Enter a value between " + min + " and " + max
                        + ".");
            }
        } while (hasError);

        return number;
    }

    @Override
    public int readInt(String prompt) {

        int number = 0;
        boolean hasError;
        do {
            hasError = false;
            try {
                System.out.println(prompt);
                number = sc.nextInt();
            } catch (InputMismatchException e) {
                hasError = true;
                System.out.println("Please enter a valid input value.");
            }
            sc.nextLine();
        } while (hasError);
        return number;
    }

    @Override
    public int readInt(String prompt, int min, int max) {

        boolean hasError;
        int number = 0;
        do {
            hasError = false;
            try {
                System.out.println(prompt);
                number = sc.nextInt();
            } catch (InputMismatchException e) {
                hasError = true;
                System.out.println("Please enter a valid input value.");
            }
            sc.nextLine();
            if (number < min | number > max) {
                hasError = true;
                System.out.println("Enter a value between " + min + " and " + max
                        + ".");
            }
        } while (hasError);

        return number;
    }

    @Override
    public long readLong(String prompt) {

        long number = 0;
        boolean hasError;
        do {
            hasError = false;
            try {
                System.out.println(prompt);
                number = sc.nextLong();
            } catch (InputMismatchException e) {
                hasError = true;
                System.out.println("Please enter a valid input value.");
            }
            sc.nextLine();
        } while (hasError);
        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max) {

        boolean hasError;
        long number = 0;
        do {
            hasError = false;
            try {
                System.out.println(prompt);
                number = sc.nextLong();
            } catch (InputMismatchException e) {
                hasError = true;
                System.out.println("Please enter a valid input value.");
            }
            sc.nextLine();
            if (number < min | number > max) {
                hasError = true;
                System.out.println("Enter a value between " + min + " and " + max
                        + ".");
            }
        } while (hasError);

        return number;
    }

    @Override
    public String readString(String prompt) {
        String input;
        System.out.println(prompt);
        input = sc.nextLine();
        return input;
    }

    @Override
    public boolean confirmString(String prompt) {
        boolean keepAsking;
        boolean confirmed;
        String input;
        do {
            keepAsking = true;
            System.out.println(prompt + " (y = yes, n = no)");
            input = sc.nextLine();

            switch (input) {
                case "y":
                    confirmed = true;
                    keepAsking = false;
                    break;
                case "n":
                    confirmed = false;
                    keepAsking = false;
                    break;
                default:
                    System.out.println("Error: Enter a valid input.");
                    confirmed = false;
                    break;
            }
        } while (keepAsking);

        return confirmed;
    }
}
