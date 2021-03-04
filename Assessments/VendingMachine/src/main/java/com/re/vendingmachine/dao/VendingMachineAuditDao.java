/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.dao;

/**
 *
 * @author rober
 */
public interface VendingMachineAuditDao {
    /**
     * logs successful transactions with cost and updated counts
     * @param entry
     * @throws com.re.vendingmachine.dao.VendingMachinePersistenceException
     */
    void writeAuditLog(String entry) throws VendingMachinePersistenceException;
}
