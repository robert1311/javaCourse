/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.controller;

import com.re.vendingmachine.dao.VendingMachineDao;
import com.re.vendingmachine.dao.VendingMachinePersistenceException;
import com.re.vendingmachine.dto.Item;
import com.re.vendingmachine.ui.UserIO;
import com.re.vendingmachine.ui.UserIOConsoleImpl;
import com.re.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rober
 */
public class VendingMachineController {

    
    UserIO io = new UserIOConsoleImpl();
    VendingMachineView view;
    VendingMachineDao dao;

    public VendingMachineController(VendingMachineView view, 
            VendingMachineDao dao){
        this.view = view;
        this.dao = dao;
    }
    /*Simulates functionaility similar to that of a typical vending 
    machine. This method is made to kick off the program*/
    public void run() {

        boolean keepGoing = false;
        int selection;
        //BigDecimal funds;
        double change = 0;
        
        try{
            dao.loadInventory();
        } catch (VendingMachinePersistenceException e){
            e.getMessage();
        }
        do {
            
            //display inventory to user throughout program
            displayVendingMachineItems();

            //greet user and ask to engage machine
            boolean engage = engageOrWalkAway();
            if (!engage) {
                exitMessage();
                System.exit(0);
            }

            //prompt user to add funds
            //prompt user to make selection
            selection = vendItem() - 1;
            /*update inventory and dispense change on successful vend; 
            display to user that item was vended*/
//            if (selection == dao.getAllItems().size()) {
//                keepGoing = false;
//            } else {
                
                io.print("Item vended! Your change is funds - price");
                
            //}

            /*cycle back to beginning and continue process until user chooses to exit
            program*/
        } while (keepGoing);
        exitMessage();
    }
    
    private void displayVendingMachineItems(){
        List<Item> itemList = dao.getAllItems();
        view.displayInventory(itemList);
    }
    
    private boolean engageOrWalkAway(){
        return view.askUserToEngage();
    }
    
    private int vendItem(){
        BigDecimal funds = view.promptToAddFunds();
        List<Item> itemList = dao.getAllItems();
        int selection = view.displayInventoryAndMakeSelection(itemList);
        return selection;
    }
    
    private void exitMessage(){
        view.exitMsg();
    }
}
