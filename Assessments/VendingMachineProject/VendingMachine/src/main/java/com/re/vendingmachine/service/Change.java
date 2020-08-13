/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.service;

/**
 *
 * @author rober
 */
public class Change {

    /*takes the amount of change due to the user (in pennies) and then calculates 
    the number of quarters, dimes, nickels, and pennies due back to the user.*/
    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;
    private double total;
    
    public int calculateCoinReturn(Coin coin, int changeInPennies) {
        total =   changeInPennies;
        pennies = (int) total;
        quarters = (int) Math.ceil(pennies / 25) ;
        pennies = pennies % 25; 
        dimes = (int) Math.ceil(pennies / 10) ;
        pennies = pennies % 10;
        nickels = (int) Math.ceil(pennies / 5);
        pennies = pennies % 5;
        
        switch(coin){
            case PENNIES:
                return pennies;
            case NICKELS:
                return nickels;
            case DIMES:
                return dimes;
            case QUARTERS:
                return quarters;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public double getTotal() {
        return total / 100;
    }

    public int getPennies() {
        return pennies;
    }

    public int getNickels() {
        return nickels;
    }

    public int getDimes() {
        return dimes;
    }

    public int getQuarters() {
        return quarters;
    }
    
    
}
