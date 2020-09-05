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
public class FlooringInvalidDateException extends Exception {

    public FlooringInvalidDateException(String message) {
        super(message);
    }
    
    public FlooringInvalidDateException(String message, Throwable cause){
        super(message, cause);
    }
}
