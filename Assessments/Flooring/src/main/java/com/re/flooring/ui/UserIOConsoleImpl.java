/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.ui;

import java.math.BigDecimal;
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
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        String value = sc.nextLine();
        BigDecimal number = new BigDecimal(value);
        return number;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min,
            BigDecimal max) {
        BigDecimal number;
        do {
            System.out.println(prompt);
            String value = sc.nextLine();
            number = new BigDecimal(value);
        } while (number.compareTo(min) < 0 | number.compareTo(max) > 0);
        return number;
    }

    @Override
    public double readDouble(String prompt) {

        System.out.println(prompt);
        double number = sc.nextDouble();
        sc.nextLine();
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {

        double number;
        do {
            System.out.println(prompt);
            number = sc.nextDouble();
        } while (number < min || number > max);
        sc.nextLine();

        return number;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float number = sc.nextFloat();
        sc.nextLine();
        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float number;
        do {
            System.out.println(prompt);
            number = sc.nextFloat();
        } while (number < min || number > max);
        sc.nextLine();

        return number;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int number;
        do {
            System.out.println(prompt);
            number = sc.nextInt();
        } while (number < min || number > max);
        sc.nextLine();

        return number;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        long number = sc.nextLong();
        sc.nextLine();
        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long number;
        do {
            System.out.println(prompt);
            number = sc.nextLong();
        } while (number < min || number > max);
        sc.nextLine();

        return number;
    }

    @Override
    public String readString(String prompt) {
        String input;
        System.out.println(prompt);
        input = sc.nextLine();
        return input;
    }
}
