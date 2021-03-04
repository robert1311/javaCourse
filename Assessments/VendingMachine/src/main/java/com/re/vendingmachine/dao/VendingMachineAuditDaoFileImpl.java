/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author rober
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao{
    
    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditLog(String entry) throws 
            VendingMachinePersistenceException{
        
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch(IOException e){
            throw new VendingMachinePersistenceException("Error: Audit Log "
                    + "could npt be written to", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
        
    }
}
