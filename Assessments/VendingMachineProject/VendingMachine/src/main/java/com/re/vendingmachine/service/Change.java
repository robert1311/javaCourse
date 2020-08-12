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
    public int calculateCoinReturn(Coin coin, double change) {
        
        int pennies = 0;
        int nickels = 0;
        int dimes = 0;
        int quarters = 0;
        
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
}
