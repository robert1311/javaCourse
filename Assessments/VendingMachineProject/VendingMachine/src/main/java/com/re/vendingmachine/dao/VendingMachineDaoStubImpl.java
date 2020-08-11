/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.dao;

import com.re.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rober
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    Item pricyItem;
    Item noItem;
    List<Item> itemList = new ArrayList<>();

    public VendingMachineDaoStubImpl() {
        pricyItem = new Item("Cola");
        BigDecimal cost = new BigDecimal("2.00");
        pricyItem.setCost(cost);
        pricyItem.setCount(1);

        itemList.add(pricyItem);
        
        noItem = new Item("Soda");
        BigDecimal cost2 = new BigDecimal("1.00");
        noItem.setCost(cost2);
        noItem.setCount(0);

        itemList.add(noItem);
    }

    @Override
    public Item addItem(Item item) {
        if(item.equals(pricyItem)){
            return pricyItem;
        } else if(item.equals(noItem)){
            return noItem;
        } else {
            return null;
        }
    }

    @Override
    public Item getItem(String name) {
        if(name.equalsIgnoreCase(pricyItem.getName())){
            return pricyItem;
        } else if(name.equalsIgnoreCase(noItem.getName())){
            return noItem;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() {
        return itemList;
    }

    @Override
    public Item removeItem(Item item) {
        if(item.equals(pricyItem)){
            return pricyItem;
        } else if(item.equals(noItem)){
            return noItem;
        } else {
            return null;
        }
    }

    @Override
    public void loadInventory() throws VendingMachinePersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader("inventry.txt")));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
    }

    @Override
    public void writeInventory() throws VendingMachinePersistenceException {
        PrintWriter out;
        try{
        out = new PrintWriter(new FileWriter("inventry.txt"));
        } catch(IOException e){
            throw new VendingMachinePersistenceException(
                    "Could not save inventory. Try again later.", e);
        }
    }
}
