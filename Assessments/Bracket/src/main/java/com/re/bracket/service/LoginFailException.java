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
public class LoginFailException extends Exception {
    
      public LoginFailException(String message){
        super(message);
    }
    
    public LoginFailException(String message, Throwable cause){
        super(message, cause);
    }    
}
