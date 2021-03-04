/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.dao;

import com.re.vendingmachine.dto.Item;
import com.re.vendingmachine.dto.Reservoir;
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
    Reservoir inReservoir;
    Reservoir outReservoir;
    List<Item> itemList = new ArrayList<>();
    List<Reservoir> resList = new ArrayList<>();

    public VendingMachineDaoStubImpl() {
        pricyItem = new Item("Cola");
        BigDecimal cost = new BigDecimal("2.00");
        pricyItem.setCost(cost);
        pricyItem.setCount(10);

        itemList.add(pricyItem);
        
        noItem = new Item("Soda");
        BigDecimal cost2 = new BigDecimal("1.00");
        noItem.setCost(cost2);
        noItem.setCount(0);

        itemList.add(noItem);
        
        inReservoir = new Reservoir("in");
        inReservoir.setQuarters(0);
        inReservoir.setDimes(0);
        inReservoir.setNickels(0);
        inReservoir.setPennies(0);
        
        resList.add(inReservoir);
        
        outReservoir = new Reservoir("out");
        outReservoir.setQuarters(10);
        outReservoir.setDimes(10);
        outReservoir.setNickels(10);
        outReservoir.setPennies(10);
        
        resList.add(outReservoir);
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
                    new FileReader("")));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
    }

    @Override
    public void writeInventory() throws VendingMachinePersistenceException {
        PrintWriter out;
        try{
        out = new PrintWriter(new FileWriter(""));
        } catch(IOException e){
            throw new VendingMachinePersistenceException(
                    "Could not save inventory. Try again later.", e);
        }
    }

    @Override
    public Reservoir getReservoir(String type) {
        if(type.equalsIgnoreCase("In")){
            return inReservoir;
        } else if(type.equalsIgnoreCase("Out")){
            return outReservoir;
        } else{
            return null;
        }
    }
    
    @Override
    public Reservoir putReservoir(Reservoir type){
        if(type.getReservoirType().equalsIgnoreCase("In")){
            return inReservoir;
        } else if(type.getReservoirType().equalsIgnoreCase("Out")){
            return outReservoir;
        } else{
            return null;
        }
    }

    @Override
    public void loadReservoir() throws VendingMachinePersistenceException {
Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader("")));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Error: Reservoir "
                    + "could not be loaded.", e);
        }    }

    @Override
    public void writeReservoir() throws VendingMachinePersistenceException {
        PrintWriter out;
        try{
        out = new PrintWriter(new FileWriter(""));
        } catch(IOException e){
            throw new VendingMachinePersistenceException(
                    "Could not save reservoir. Try again later.", e);
        }
    }
}
