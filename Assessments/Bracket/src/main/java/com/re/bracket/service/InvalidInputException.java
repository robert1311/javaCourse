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
public class InvalidInputException extends Exception {
    
      public InvalidInputException(String message){
        super(message);
    }
    
    public InvalidInputException(String message, Throwable cause){
        super(message, cause);
    }    
}
