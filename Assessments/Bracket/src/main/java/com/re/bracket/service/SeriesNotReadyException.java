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
public class SeriesNotReadyException extends Exception {
    
      public SeriesNotReadyException(String message){
        super(message);
    }
    
    public SeriesNotReadyException(String message, Throwable cause){
        super(message, cause);
    }    
}
