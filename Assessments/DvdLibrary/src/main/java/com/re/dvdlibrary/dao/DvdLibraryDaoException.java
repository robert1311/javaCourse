/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.dvdlibrary.dao;

/**
 *
 * @author Dev10
 */
public class DvdLibraryDaoException extends Exception{
    public DvdLibraryDaoException(String message){
        super(message);
    }
    
    public DvdLibraryDaoException(String message, Throwable cause){
        super(message, cause);
    }
}
