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
import com.re.vendingmachine.dto.Reservoir;
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
            VendingMachineAuditDao auditDao) {
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
    public Reservoir getSpecReservoir(String type) {
        return dao.getReservoir(type);
    }

    private Reservoir updateReservoir(Reservoir reservoir) {
        return dao.putReservoir(reservoir);

    }

    @Override
    public Change validateFundsAndAvailability(BigDecimal funds, int selection)
            throws VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException,
            VendingMachinePersistenceException {

        List<Item> fullInventory = this.getFullItemList();
        int pennyChange;
        Change change;
        String operation;
        Item selected;
        String itemName;
        String itemPrice;
        String itemCount;
        BigDecimal lOO = new BigDecimal("100");

        if (selection <= fullInventory.size()) {
            List<Item> availableItems = this.getAvailableItemsList();
            int index = selection - 1;
            selected = dao.getItem(fullInventory.get(index).getName());

            checkAvailability(availableItems, selected);

            validateFunds(selected, funds);

            selected.setCount(selected.getCount() - 1);

            BigDecimal bigChange = funds.subtract(selected.getCost());
            pennyChange = bigChange.multiply(lOO).intValue();
            operation = "Successful Vend";
            itemName = selected.getName();
            itemPrice = selected.getCost().toString();
            itemCount = String.valueOf(selected.getCount());
        } else {//Coin Return
            pennyChange = funds.multiply(lOO).intValue();
            operation = "Coin Return";
            itemName = "No Selection";
            itemPrice = "N/A";
            itemCount = "N/A";
        }
        Reservoir out = this.getSpecReservoir("Out");
        change = calculateChange(pennyChange, out);
        updateReservoir(out);
        this.saveApiReservoir();

        //write audit log
        auditDao.writeAuditLog(operation + " : " + itemName + " : $" + itemPrice
                + " : Count-" + itemCount + " : Change Return - "
                + "Quarters[" + change.getQuarters() + "],"
                + "Dimes[" + change.getDimes() + "],"
                + "Nickels[" + change.getNickels() + "],"
                + "Pennies[" + change.getPennies() + "]");

        return change;
    }

    private void checkAvailability(List<Item> instockItems, Item selected)
            throws VendingMachineNoItemInventoryException {
        if (!instockItems.contains(selected)) {
            throw new VendingMachineNoItemInventoryException("SOLD OUT! Please"
                    + "make another selection.");
        }

    }

    private void validateFunds(Item selected, BigDecimal funds) throws
            VendingMachineInsufficientFundsException {
        BigDecimal difference = selected.getCost().subtract(funds);
        if (funds.compareTo(selected.getCost()) == -1) {
            throw new VendingMachineInsufficientFundsException("Insufficient "
                    + "funds. Please add $" + difference + " for the slected item.");
        }
    }

    private Change calculateChange(int pennyChange, Reservoir out) {
        int quartersOut;
        int dimesOut;
        int nickelsOut;
        int penniesOut;

        Change change = new Change();
        Reservoir in = this.getSpecReservoir("In");

        quartersOut = change.calculateCoinReturn(Coin.QUARTERS, pennyChange);
        if (out.getQuarters() <= quartersOut) {
            out.setQuarters(out.getQuarters() + in.getQuarters());
            in.setQuarters(0);
        }
        out.setQuarters(out.getQuarters() - quartersOut);

        dimesOut = change.calculateCoinReturn(Coin.DIMES, pennyChange);
        if (out.getDimes() <= dimesOut) {
            out.setDimes(out.getDimes() + in.getDimes());
            in.setDimes(0);
        }
            out.setDimes(out.getDimes() - dimesOut);
        
        nickelsOut = change.calculateCoinReturn(Coin.NICKELS, pennyChange);
        if (out.getNickels() <= nickelsOut) {
            out.setNickels(out.getNickels() + in.getNickels());
            in.setNickels(0);
        }
            out.setNickels(out.getNickels() - nickelsOut);
        
        penniesOut = change.calculateCoinReturn(Coin.PENNIES, pennyChange);
        if (out.getPennies() <= penniesOut) {
            out.setPennies(out.getPennies() + in.getPennies());
            in.setPennies(0);
        }
            out.setPennies(out.getPennies() - penniesOut);

        return change;
    }

    @Override
    public void loadApiInventory() throws VendingMachinePersistenceException {
        dao.loadInventory();
    }

    @Override
    public void saveApiInventory() throws VendingMachinePersistenceException {
        dao.writeInventory();
    }

    @Override
    public void loadApiReservoir() throws VendingMachinePersistenceException {
        dao.loadReservoir();
    }

    @Override
    public void saveApiReservoir() throws VendingMachinePersistenceException {
        dao.writeReservoir();
    }

}
