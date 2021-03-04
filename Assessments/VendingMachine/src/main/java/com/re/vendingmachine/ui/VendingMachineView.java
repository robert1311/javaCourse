/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.ui;

import com.re.vendingmachine.dto.Item;
import com.re.vendingmachine.dto.Reservoir;
import com.re.vendingmachine.service.Change;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author rober
 */
public class VendingMachineView {

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public boolean askUserToEngage() {
        String response = io.readString("Thirsty? (y/n)");
        return response.equalsIgnoreCase("y");
    }

    public void displayInventory(List<Item> itemList) {
        int menuSize = itemList.size() + 1;
        int itemCounter = 1;
        String stock;
        io.print("========Vending Machine ========");

        for (Item item : itemList) {
            stock = "";
            if (item.getCount() < 1) {
                stock = "SOLD OUT!";
            }
            io.print("" + itemCounter + ") " + item.getName() + " - "
                    + "$" + item.getCost() + " " + stock);
            itemCounter++;
        }
        io.print("" + menuSize + ") Coin Return");
        io.print("================================\n");
    }

    public BigDecimal promptToAddFunds(BigDecimal funds, Reservoir in) {
        boolean keepGoing;
        BigDecimal addFunds = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
        io.print("Add funds into the machine\n");
        //BigDecimal quarter = new BigDecimal("0.25");
        do{
            keepGoing = true;
            io.print("Current funds: $" + funds.add(addFunds).toString());
            int selection = io.readInt("Add...\n"
                    + "================\n"
                    + "1) Quarter - .25\n"
                    + "2) Dime - .10\n"
                    + "3) Nickel - .05\n"
                    + "4) Penny - .01\n"
                    + "5) Done\n"
                    + "================\n", 1, 5);
            switch(selection){
                case 1:
                    in.setQuarters(in.getQuarters() + 1);
                    addFunds = addFunds.add(BigDecimal.valueOf(0.25).setScale(2, 
                            RoundingMode.HALF_UP));
                    break;
                case 2:
                    in.setDimes(in.getDimes() + 1);
                    addFunds = addFunds.add(BigDecimal.valueOf(0.10).setScale(2, 
                            RoundingMode.HALF_UP));
                    break;
                case 3:
                    in.setNickels(in.getNickels() + 1);
                    addFunds = addFunds.add(BigDecimal.valueOf(0.05).setScale(2, 
                            RoundingMode.HALF_UP));
                    break;
                case 4:
                    in.setPennies(in.getPennies() + 1);
                    addFunds = addFunds.add(BigDecimal.valueOf(0.01).setScale(2, 
                            RoundingMode.HALF_UP));
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    break;
            }
            
        } while(keepGoing);
        
        
        return addFunds;
    }

    public int displayInventoryAndMakeSelection(List<Item> itemList,
            BigDecimal funds) {
        int menuSize = itemList.size() + 1;
        this.displayInventory(itemList);
        int selection = io.readInt("Select an item (balance: $" 
                + funds.toString() + ")", 1, menuSize);
        if (selection <= menuSize - 1) {
            io.print("==============\n"
                    + itemList.get(selection - 1).getName() + "\n");
        } else {
            io.print("=============\n"
                    + "Coin Return\n");
        }
        return selection;
    }

    public boolean displayChangeDueAndAskToExit(Change change, boolean isReturn) {
        String operation;
        if(isReturn){
            operation = "FUNDS RETURNED!";
        } else{
            operation = "SUCCESSFUL VEND!";
        }
        io.readString("==================================\n"
                + operation + " Please Take your change of $"
                + BigDecimal.valueOf(change.getTotal()) + ": \n"
                + change.getQuarters() + " Quarters\n"
                + change.getDimes() + " Dimes\n"
                + change.getNickels() + " Nickels\n"
                + change.getPennies() + " Pennies\n"
                + "==================================\n"
                + "Press enter to continue.");
        String exit = io.readString("Exit?");
        return exit.equalsIgnoreCase("n");
    }
    
    

    public void displayErrorMessage(String message) {
        io.print(message);
    }

    public void exitMsg() {
        io.print("Goodbye!");
    }

}
