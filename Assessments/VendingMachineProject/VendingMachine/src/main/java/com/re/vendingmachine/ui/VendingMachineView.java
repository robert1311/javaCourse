/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.ui;

import com.re.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author rober
 */
public class VendingMachineView {
    
    UserIO io;
    
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public void displayInventory(List<Item> itemList){
        
        int itemCounter = 1;
        io.print("========Vending Machine ========");
        
        for(Item item : itemList){
            io.print("" + itemCounter + ") " + item.getName() + " - " 
                    + "$" + item.getCost());
            itemCounter++;
        }
        io.print("================================");
    }
    
    public boolean askUserToEngage(){
        String response = io.readString("Thirsty");
        return response.equalsIgnoreCase("y");
    }
    
    public void exitMsg(){
        io.print("Goodbye!");
    }
}
