/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

/**
 *
 * @author rober
 */
public class TournamentPersistenceException extends Exception {
    
      public TournamentPersistenceException(String message){
        super(message);
    }
    
    public TournamentPersistenceException(String message, Throwable cause){
        super(message, cause);
    }    
}
