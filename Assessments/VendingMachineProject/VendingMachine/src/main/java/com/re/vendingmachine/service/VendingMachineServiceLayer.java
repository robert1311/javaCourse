/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.service;

import com.re.vendingmachine.dao.VendingMachinePersistenceException;
import com.re.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rober
 */
public interface VendingMachineServiceLayer {
    
    /**
     * Expects to receive a list of all items from DAO in order to pass through
     * this method
     * @return full list of items of the Vending Machine 
     */
    List<Item> getFullItemList();
    
    /**
     * Expects to receive an Item from the DAO given the item's name
     * @param name
     * @return item specified by the name
     */
    Item getInventoryItem(String name);
    
    /**
     * Checks if funds entered by the user is sufficient enough to purchase 
     * the item.Then checks if the item is in stock.If both conditions are met
 the method return true else, returns false.Also writes to an audit log 
 file for inventory history tracking.
     * @param funds that the user entered as input.
     * @param selection the integer corresponding to the item selected.
     * @return Boolean depending if funds are sufficient and item is available 
     * for purchase.
     * @throws VendingMachineInsufficientFundsException if cost of item is more 
     * than funds entered.
     * @throws VendingMachineNoItemInventoryException if item selected is 
     * unavailable for purchase (sold out).
     * @throws VendingMachinePersistenceException if Audit Log cannot be 
     * persisted on successful vends.
     */
    boolean validateFundsAndAvailability(BigDecimal funds, int selection) 
            throws VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException,
            VendingMachinePersistenceException;
    
    /**
     * Loads the items in inventory from external storage to internal memory
     * of the program.
     * @throws VendingMachinePersistenceException if inventory cannot be loaded
     * into the program.
     */
    void loadFromInventory() throws VendingMachinePersistenceException;
    
    /**
     * Saves inventory's current in-memory state of the program to the external 
     * storage
     * @throws VendingMachinePersistenceException if inventory cannot be saved.
     */
    void saveToInventory() throws VendingMachinePersistenceException;
}
