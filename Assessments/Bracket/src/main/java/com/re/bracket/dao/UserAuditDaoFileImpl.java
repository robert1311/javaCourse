/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author rober
 */
public class UserAuditDaoFileImpl implements UserAuditDao {

    public static final String AUDIT_FILE = "userAudit.txt";
    
    @Override
    public void writeAudit(String entry) throws TournamentPersistenceException {
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch(IOException e){
            throw new TournamentPersistenceException("Could not add to audit");
        }
        out.println(entry + " - TimeOfSave: " + LocalDateTime.now());
        out.flush();
    }

}
