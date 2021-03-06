/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.dao;

import com.re.vendingmachine.dto.Item;
import com.re.vendingmachine.dto.Reservoir;
import java.util.List;

/**
 *
 * @author rober
 */
public interface VendingMachineDao {

    /**
     * adds item to the inventory
     * @param item
     * @return Item that was added
     */
    public Item addItem(Item item);
    
    /**
     * returns the Item from in-memory storage given the item's name
     * @param name
     * @return 
     */
    public Item getItem(String name);
    
    /**
     * returns a list of all items; available or not
     * @return the full inventory list 
     */
    public List<Item> getAllItems();
    
    /**
     * removes the item from inventory given its name
     * @param item
     * @return 
     */
    public Item removeItem(Item item);
    
    /**
     * returns the coin reservoir needed for the appropriate vending machine
     * operation.
     * @param type of reservoir to be returned.
     * @return specified reservoir
     */
    public Reservoir getReservoir(String type);
    
    public Reservoir putReservoir(Reservoir type);

    /**
     * loads inventory from external storage to in-memory storage
     * @throws com.re.vendingmachine.dao.VendingMachinePersistenceException
     */
    public void loadInventory() throws VendingMachinePersistenceException;
    
    /**
     * writes to the external storage from in-memory storage 
     * @throws com.re.vendingmachine.dao.VendingMachinePersistenceException
     */
    public void writeInventory() throws VendingMachinePersistenceException;
    
    public void loadReservoir() throws VendingMachinePersistenceException;
    
    public void writeReservoir() throws VendingMachinePersistenceException;
}
