/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.dao;

import com.re.vendingmachine.dto.Item;
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
    public List<Item> getFullItemList();
    
    /**
     * removes the item from inventory given its name
     * @param item
     * @return 
     */
    public Item removeItem(Item item);

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
}
