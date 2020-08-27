/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.service;

import com.re.vendingmachine.dao.VendingMachinePersistenceException;
import com.re.vendingmachine.dto.Item;
import com.re.vendingmachine.dto.Reservoir;
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
     * takes the full inventory list and returns a list with only those that
     * are in stock and available for purchase.
     * @return filtered list of only available items.
     */
    List<Item> getAvailableItemsList();
    
    /**
     * Expects to receive an Item from the DAO given the item's name
     * @param name
     * @return item specified by the name
     */
    Item getInventoryItem(String name);
    
    Reservoir getSpecReservoir(String reservoirType);
    
    /**
     * Updates the counts for each coin in the specified reservoir
     * @param reservoir
     * @return the specified updated reservoir
     */
    Reservoir updateReservoir(Reservoir reservoir);
    
    
    /**
     * Checks if funds entered by the user is sufficient for the purchase the 
     * item that they selected.Then checks if the item is in stock.If both 
 conditions are met the method return true else, returns false. Also 
 writes to an audit log or inventory history tracking.
     * @param funds that the user entered as input.
     * @param selection the integer corresponding to the item selected.
     * @return Change due to the user.
     * @throws VendingMachineInsufficientFundsException if cost of item is more 
     * than funds entered.
     * @throws VendingMachineNoItemInventoryException if item selected is 
     * unavailable for purchase (sold out).
     * @throws VendingMachinePersistenceException if Audit Log cannot be 
     * persisted on successful vends.
     */
    Change validateFundsAndAvailability(BigDecimal funds, int selection) 
            throws VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException,
            VendingMachinePersistenceException;
    
    /**
     * Loads the items in inventory from external storage to internal memory
     * of the program.
     * @throws VendingMachinePersistenceException if inventory cannot be loaded
     * into the program.
     */
    void loadApiInventory() throws VendingMachinePersistenceException;
    
    /**
     * Saves inventory's current in-memory state of the program to the external 
     * storage
     * @throws VendingMachinePersistenceException if inventory cannot be saved.
     */
    void saveApiInventory() throws VendingMachinePersistenceException;
    
    void loadApiReservoir() throws VendingMachinePersistenceException;
    
    void saveApiReservoir() throws VendingMachinePersistenceException;
}
