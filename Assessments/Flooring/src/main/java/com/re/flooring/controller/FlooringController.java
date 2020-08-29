/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.controller;

import com.re.flooring.dao.FlooringDao;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import com.re.flooring.ui.FlooringView;
import com.re.flooring.ui.UserIO;
import com.re.flooring.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author rober
 */
public class FlooringController {
    
    UserIO io = new UserIOConsoleImpl();
    FlooringDao dao;
    FlooringView view;
    
    public FlooringController(FlooringDao dao, FlooringView view){
        this.dao = dao;
        this.view = view;
    }
    
    /*runs a program used to manage flooring orders through CRUD operations*/
    public void run(){
        boolean keepGoing = true;
        int selection;
        
        /*Menu allows user to create, read, update, delete orders; 
        keep cycling back to menu after each user case until the "quit' option 
        is selected*/
        while(keepGoing){
            selection = view.displayMenuAndGetSelection("#");
            switch(selection){
                case 1:
                    createOrder();
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
                default:
                    io.print("INVALID ENTRY");
                    
            }
        }
        io.print("GOODBYE!");
    }
    
    private void createOrder(){
        List<State> stateList = dao.getAllStates();
        List<Product> productList = dao.getAllProducts();
        view.displayGetNewOrderInfo(stateList, productList);
    }
}
