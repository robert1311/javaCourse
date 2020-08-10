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
    public static final String DELIMITER = "::";
    public static final String INVENTORY_FILE = "inventory.txt";
    
    @Override
    public Item addItem(Item item) {
        return itemMap.put(item.getName(), item);
    }
    
    @Override
    public Item getItem(String name) {
        return itemMap.get(name);
    }

    @Override
    public List<Item> getFullItemList() {
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
        itemAsText += String.valueOf(item.getCount());
        
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


    
    
}
