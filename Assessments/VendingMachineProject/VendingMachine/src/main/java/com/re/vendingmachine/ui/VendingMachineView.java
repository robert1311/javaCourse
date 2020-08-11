/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.ui;

import com.re.vendingmachine.dto.Item;
import java.math.BigDecimal;
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
        int menuSize =itemList.size() + 1;
        int itemCounter = 1;
        io.print("========Vending Machine ========");
        
        for(Item item : itemList){
            io.print("" + itemCounter + ") " + item.getName() + " - " 
                    + "$" + item.getCost());
            itemCounter++;
        }
        io.print("" + menuSize + ") Coin Return");
        io.print("================================");
    }
    
    public int displayInventoryAndMakeSelection(List<Item> itemList){
        int menuSize =itemList.size() + 1;
        this.displayInventory(itemList);
        return io.readInt("Select an item", 1, menuSize);
    }
    
    public boolean askUserToEngage(){
        String response = io.readString("Thirsty");
        return response.equalsIgnoreCase("y");
    }
    
    public void exitMsg(){
        io.print("Goodbye!");
    }
    
    public BigDecimal promptToAddFunds(){
        return io.readBigDecimal("Enter funds into the machine ($0.00)");
    }
}
