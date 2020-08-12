/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.service;

import com.re.vendingmachine.dao.VendingMachineAuditDao;
import com.re.vendingmachine.dao.VendingMachineDao;
import com.re.vendingmachine.dao.VendingMachinePersistenceException;
import com.re.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rober
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, 
            VendingMachineAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Item> getFullItemList() {
        return dao.getAllItems();
    }

     @Override
    public List<Item> getAvailableItemsList() {
        return dao.getAllItems()
                .stream()
                .filter(i -> i.getCount() > 0)
                .collect(Collectors.toList());
    }
    
    @Override
    public Item getInventoryItem(String name) {
        return dao.getItem(name);
    }

    @Override
    public boolean validateFundsAndAvailability(BigDecimal funds, int selection) 
            throws VendingMachineInsufficientFundsException, 
            VendingMachineNoItemInventoryException, 
            VendingMachinePersistenceException {
        
        List<Item> fullInventory = this.getFullItemList();
        List<Item> availableItems = this.getAvailableItemsList();
        int index = selection -1;
        Item selected = dao.getItem(fullInventory.get(index).getName());
        
        checkAvailability(availableItems, selected);
        
        validateFunds(selected, funds);
        
        selected.setCount(selected.getCount() -1);
        //calculate change
        //write audit log
        return true;
    }
    
    private void checkAvailability(List<Item> instockItems, Item selected) 
            throws VendingMachineNoItemInventoryException{
        if(!instockItems.contains(selected)){
            throw new VendingMachineNoItemInventoryException("SOLD OUT! Please"
                    + "make another selection.");
        }
       
    }
    
    private void validateFunds(Item selected, BigDecimal funds) throws 
            VendingMachineInsufficientFundsException {
        if(funds.compareTo(selected.getCost()) == -1){
            throw new VendingMachineInsufficientFundsException("Insufficient "
                    + "funds. Please add more $ for the slected item.");
        }
    }

    @Override
    public void loadApiInventory() throws VendingMachinePersistenceException {
        dao.loadInventory();
    }

    @Override
    public void saveApiInventory() throws VendingMachinePersistenceException {
        dao.writeInventory();
    }

   
    
}
