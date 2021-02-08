/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.dvdlibrary.dao;

import com.re.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author Dev10
 */
public interface DvdLibraryDao {
    
    /**
     * Adds the given Dvd to the library and associates it with the given 
     * title. If there is already a dvd associated with the given 
     * title it will return that dvd object, otherwise it will 
     * return null.
     * 
     * @param title id with which dvd is to be associated
     * @param dvd dvd to be added to the roster
     * @return the Dvd object previously associated with the given  
     * title if it exists, null otherwise
     * @throws DvdLibraryDaoException
     */
    Dvd addDvd(String title, Dvd dvd ) throws DvdLibraryDaoException;
    
    /**
     * Removes from the library the dvd associated with the given title. 
     * Returns the dvd object that is being removed or null if 
     * there is no dvd associated with the given title
     * 
     * @param title id of dvd to be removed
     * @return Dvd object that was removed or null if no dvd 
     * was associated with the given title
     * @throws DvdLibraryDaoException
     */
    Dvd removeDvd(String title) throws DvdLibraryDaoException; 
    
    Dvd editDvd(String title) throws DvdLibraryDaoException;
    
    /**
     * Returns a List of all Dvds on the roster. 
     * 
     * @return Dvd List containing all dvds on the roster.
     * @throws DvdLibraryDaoException
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;
    
    /**
     * Returns the dvd object associated with the given title.
     * Returns null if no such dvd exists
     * 
     * @param title ID of the dvd to retrieve
     * @return the Dvd object associated with the given title,  
     * null if no such dvd exists
     * @throws DvdLibraryDaoException
     */
    Dvd displayDvdInfo(String title) throws DvdLibraryDaoException;
    
    Dvd searchDvd(String title) throws DvdLibraryDaoException;
    
    void loadLibrary() throws DvdLibraryDaoException;
    
    void writeLibrary() throws DvdLibraryDaoException;
    
}
