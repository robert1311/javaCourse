/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.controller;

import com.re.flooring.ui.UserIO;
import com.re.flooring.ui.UserIOConsoleImpl;

/**
 *
 * @author rober
 */
public class FlooringController {
    
    UserIO io = new UserIOConsoleImpl();
    
    /*runs a program used to manage flooring orders through CRUD operations*/
    public void run(){
        boolean keepGoing = true;
        int selection;
        
        /*Menu allows user to create, read, update, delete orders; 
        keep cycling back to menu after each user case until the "quit' option 
        is selected*/
        while(keepGoing){
            selection = io.readInt("Choose an operation from the following.\n"
                    + "1) Create Order\n"
                    + "2) Get Order\n"
                    + "3) Get Orders By Date\n"
                    + "4) Update Order\n"
                    + "5) Remove Order\n"
                    + "6) Save & Quit\n", 1, 6);
            switch(selection){
                case 1:
                    io.print("CREATE ORDER");
                    break;
                case 2:
                    io.print("GET ORDER");
                    break;
                case 3:
                    io.print("GET ORDERS BY DATE");
                    break;
                case 4:
                    io.print("UPDATE ORDER");
                    break;
                case 5:
                    io.print("REMOVE ORDER");
                    break;
                case 6:
                    keepGoing = false;
                    io.print("QUIT");
                    break;
            }
        }
        io.print("GOODBYE!");
    }
}
