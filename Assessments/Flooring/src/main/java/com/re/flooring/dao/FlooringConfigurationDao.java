/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dao;

/**
 *
 * @author rober
 */
public interface FlooringConfigurationDao {
    
    /**
     * returns true if application is in Training mode
     * @return
     * @throws FlooringPersistenceException 
     */
    boolean getApplicationMode() throws FlooringPersistenceException;
    
    
    /**
     * returns the order number reserved for the next created Order.
     * @return integer representing the order number
     * @throws FlooringPersistenceException 
     */
    int getNextOrderNumber() throws FlooringPersistenceException;
    
    /**
     * Sets the next order number to be used after an Order has been added to 
     * the system. 
     * @param previousNum
     * @throws FlooringPersistenceException 
     */
    void setOrderNumber(int previousNum) throws FlooringPersistenceException;
}
