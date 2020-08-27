/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.controller;

import com.re.vendingmachine.dao.VendingMachinePersistenceException;
import com.re.vendingmachine.dto.Item;
import com.re.vendingmachine.dto.Reservoir;
import com.re.vendingmachine.service.Change;
import com.re.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.re.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.re.vendingmachine.service.VendingMachineServiceLayer;
import com.re.vendingmachine.ui.UserIO;
import com.re.vendingmachine.ui.UserIOConsoleImpl;
import com.re.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author rober
 */
public class VendingMachineController {

    
    UserIO io = new UserIOConsoleImpl();
    VendingMachineView view;
    VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, 
            VendingMachineServiceLayer service){
        this.view = view;
        this.service = service;
    }
    /*Simulates functionaility similar to that of a typical vending 
    machine. This method is made to kick off the program*/
    public void run() {

        boolean keepGoing = false;
        try{
            loadAllInventory();
            loadReservoir();
        } catch (VendingMachinePersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
        do {
            //display inventory to user throughout program
            displayVendingMachineItems();

            //greet user and ask to engage machine
            boolean engage = engageOrWalkAway();
            if (!engage) {
                break;
            }

            //prompt user to add funds and make selection
            /*update inventory and dispense change on successful vend; 
            display to user that item was vended*/
                try{
                keepGoing = vendItem();
                } catch(VendingMachinePersistenceException e){
                    view.displayErrorMessage(e.getMessage());
                }
            /*cycle back to beginning and continue process until user chooses to exit
            program*/
        } while (keepGoing);
        try{
            saveInventory();
            saveReservoir();
        } catch (VendingMachinePersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }
    
    private void displayVendingMachineItems(){
        List<Item> itemList = service.getFullItemList();
        view.displayInventory(itemList);
    }
    
    private boolean engageOrWalkAway(){
        return view.askUserToEngage();
    }
    
    private boolean vendItem() throws VendingMachinePersistenceException {
        boolean hasErrors;
        boolean keepGoing;
        boolean isReturn;
        Change changeDue = null;
        BigDecimal funds = new BigDecimal("0.00").setScale(2, 
                RoundingMode.HALF_UP);
        Reservoir in = service.getSpecReservoir("In");
        do{
            hasErrors = false;
            isReturn = false;
            funds = funds.add(view.promptToAddFunds(funds, in));
            service.saveApiReservoir();
            List<Item> itemList = service.getFullItemList();
            int selection = view.displayInventoryAndMakeSelection(itemList, 
                    funds);
            try{
            changeDue = service.validateFundsAndAvailability(funds, 
                    selection);
            service.updateReservoir(service.getSpecReservoir("Out"));
            } catch(VendingMachineInsufficientFundsException 
                    | VendingMachineNoItemInventoryException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while(hasErrors);
        try{
            saveReservoir();
            saveInventory();
        } catch (VendingMachinePersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
        if((int) changeDue.getTotal() == (int) funds.intValue()){
            isReturn = true;
        }
        
        keepGoing = view.displayChangeDueAndAskToExit(changeDue, isReturn);
        return keepGoing;
    }
    
    private void loadAllInventory() throws VendingMachinePersistenceException{
        service.loadApiInventory();
    }
    
    private void saveInventory() throws VendingMachinePersistenceException{
        service.saveApiInventory();
    }
    
    private void loadReservoir() throws VendingMachinePersistenceException{
        service.loadApiReservoir();
    }
    
     private void saveReservoir() throws VendingMachinePersistenceException{
        service.saveApiReservoir();
    }
    
    private void exitMessage(){
        view.exitMsg();
    }
}
