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
public class NoSuchPlayerException extends Exception{
     
    public NoSuchPlayerException(String message){
        super(message);
    }
    
    public NoSuchPlayerException(String message, Throwable cause){
        super(message, cause);
    }
}
