/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.service;

/**
 *
 * @author rober
 */
public class VendingMachineNoItemInventoryException extends Exception{
    
    public VendingMachineNoItemInventoryException(String message){
        super(message);
    }
    
    public VendingMachineNoItemInventoryException(String message, 
            Throwable cause){
        super(message, cause);
    }
}
