/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.controller;

import com.re.vendingmachine.ui.UserIO;
import com.re.vendingmachine.ui.UserIOConsoleImpl;

/**
 *
 * @author rober
 */
public class VendingMachineController {

    UserIO io = new UserIOConsoleImpl();

    /*Simulates functionaility similar to that of a typical vending 
    machine. This method is made to kick off the program*/
    public void run() {

        boolean keepGoing = false;
        int selection;
        double funds = 0;
        double change = 0;
        do {

            //display inventory to user throughout program
            io.print("Select an item\n"
                    + "Cola- 1.00\n"
                    + "Snickers - 1.50\n"
                    + "chips - 1.75\n"
                    + "fruit snacks - .85\n");

            //greet user and ask to engage machine
            String engage = io.readString("Thirsty?");
            if (engage.equalsIgnoreCase("n")) {
                io.print("Goodbye!");
                System.exit(0);
            }

            //prompt user to add funds
            funds = io.readDouble("Add funds");

            //prompt user to make selection
            selection = io.readInt("Select an item\n"
                    + "1) Cola- 1.00\n"
                    + "2) Snickers - 2.00\n"
                    + "3) chips - 3.00\n"
                    + "4) fruit snacks - 4.00\n"
                    + "5) Exit", 1, 5);
            /*update inventory and dispense change on successful vend; 
            display to user that item was vended*/
            if (selection == 5) {
                keepGoing = false;
            } else {
                change = funds - selection;
                io.print("Item vended! Your change is " + change);
                funds = 0;
            }

            /*cycle back to beginning and continue process until user chooses to exit
            program*/
        } while (keepGoing);
        io.print("GoodBye!");
    }
}
