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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author rober
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{

    Map<String, Item> itemMap = new HashMap<>();
    Map<String, Reservoir> reservoirMap = new HashMap<>();
    public static final String DELIMITER = "::";
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String RESERVOIR_FILE = "reservoir.txt";
    
    
    @Override
    public Item addItem(Item item) {
        return itemMap.put(item.getName(), item);
    }
    
    @Override
    public Item getItem(String name) {
        return itemMap.get(name);
    }
    
    @Override
    public Reservoir getReservoir(String type){
        return reservoirMap.get(type);
    }
    
    @Override
    public Reservoir putReservoir(Reservoir type){
        return reservoirMap.put(type.getReservoirType(), type);
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        for(Item item : itemMap.values()){
            items.add(item);
        }
        return items;
    }

    @Override
    public Item removeItem(Item item) {
        return itemMap.remove(item.getName());
    }
    
    private Item unmarshallItem(String itemAsText){
        String[] itemTokens = itemAsText.split(DELIMITER);
        
        String name = itemTokens[0];
        Item unmarshalledItem = new Item(name);
        BigDecimal cost = new BigDecimal(itemTokens[1])
                .setScale(2, RoundingMode.HALF_UP);
        unmarshalledItem.setCost(cost);
        unmarshalledItem.setCount(Integer.parseInt(itemTokens[2]));
        
        return unmarshalledItem;
    }
    
    @Override
    public void loadInventory() throws VendingMachinePersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
        
        Item currentItem;
        String currentLine;
        
        while(sc.hasNext()){
            currentLine = sc.nextLine();
            currentItem = unmarshallItem(currentLine);
            itemMap.put(currentItem.getName(), currentItem);
        }
        sc.close();
    }

   
    private String marshallItem(Item item){
        String itemAsText = item.getName() + DELIMITER;
        itemAsText += item.getCost().toString() + DELIMITER;
        itemAsText += item.getCount();
        
        return itemAsText;
    }
    
    @Override
    public void writeInventory() throws VendingMachinePersistenceException {
        PrintWriter out;
        try{
        out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch(IOException e){
            throw new VendingMachinePersistenceException(
                    "Could not save inventory. Try again later.", e);
        }
        String marshalledItem;
        for(Item currentItem : itemMap.values()){
            marshalledItem = marshallItem(currentItem);
            out.println(marshalledItem);
            out.flush();
        }
        out.close();
    }

    private Reservoir unmarshallReservoir(String reservoirAsText){
        String[] resTokens = reservoirAsText.split(DELIMITER);
        
        String type = resTokens[0];
        Reservoir unmarshalledRes = new Reservoir(type);
        unmarshalledRes.setQuarters(Integer.parseInt(resTokens[1]));
        unmarshalledRes.setDimes(Integer.parseInt(resTokens[2]));
        unmarshalledRes.setNickels(Integer.parseInt(resTokens[3]));
        unmarshalledRes.setPennies(Integer.parseInt(resTokens[4]));
        
        
        
        return unmarshalledRes;
    }
    
    @Override
    public void loadReservoir() throws VendingMachinePersistenceException{
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader(RESERVOIR_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
        
        Reservoir currentReservoir;
        String currentLine;
        
        while(sc.hasNext()){
            currentLine = sc.nextLine();
            currentReservoir = unmarshallReservoir(currentLine);
            reservoirMap.put(currentReservoir.getReservoirType(), currentReservoir);
        }
        sc.close();
    }
    
    public String marshallReservoir(Reservoir reservoir){
        String reservoirAsText = reservoir.getReservoirType() + DELIMITER;
        reservoirAsText += reservoir.getQuarters() + DELIMITER;
        reservoirAsText += reservoir.getDimes() + DELIMITER;
        reservoirAsText += reservoir.getNickels() + DELIMITER;
        reservoirAsText += reservoir.getPennies();
        
        return reservoirAsText;
    }
    
    @Override
    public void writeReservoir() throws VendingMachinePersistenceException{
        PrintWriter out;
        try{
        out = new PrintWriter(new FileWriter(RESERVOIR_FILE));
        } catch(IOException e){
            throw new VendingMachinePersistenceException(
                    "Could not save reservoir. Try again later.", e);
        }
        String marshalledReservoir;
        for(Reservoir currentReservoir : reservoirMap.values()){
            marshalledReservoir = marshallReservoir(currentReservoir);
            out.println(marshalledReservoir);
            out.flush();
        }
        out.close();
    }
    
}
