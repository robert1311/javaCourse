/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.service;

/**
 *
 * @author rober
 */
public class GameNotCompleteException extends Exception {
    
      public GameNotCompleteException(String message){
        super(message);
    }
    
    public GameNotCompleteException(String message, Throwable cause){
        super(message, cause);
    }    
}
