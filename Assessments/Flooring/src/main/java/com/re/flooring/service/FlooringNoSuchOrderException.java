/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.service;

/**
 *
 * @author rober
 */
public class FlooringNoSuchOrderException extends Exception {
    
    public FlooringNoSuchOrderException(String message){
        super(message);
    }
    
    public FlooringNoSuchOrderException(String message, Throwable cause){
        super(message, cause);
    }
}
