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
public class NoSuchTournamentException extends Exception {
    
      public NoSuchTournamentException(String message){
        super(message);
    }
    
    public NoSuchTournamentException(String message, Throwable cause){
        super(message, cause);
    }
}
