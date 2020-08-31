/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.service;

import com.re.flooring.dao.FlooringPersistenceException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author rober
 */
public class FlooringConfig {
    InputStream inputStream;
    Properties prop = new Properties();

    public boolean getApplicationMode() throws FlooringPersistenceException {
        boolean isTraining;
        try {
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader()
                    .getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FlooringPersistenceException("Unable to find application "
                    + "status");
            }
            isTraining = Boolean.parseBoolean(prop
                    .getProperty("isTraining"));
            inputStream.close();
        } catch (IOException e) {
            isTraining = false;
            throw new FlooringPersistenceException("Unable to find application "
                    + "status", e);
        } 
        
        return isTraining;
    }

    public int getnextOrderNumber() throws FlooringPersistenceException {
        int orderNumber;
        try {

            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader()
                    .getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FlooringPersistenceException("Unable to find application "
                    + "status");
            }
            orderNumber = Integer.parseInt(prop.getProperty("orderNumber"));
            inputStream.close();
            } catch (IOException e) {
            throw new FlooringPersistenceException("");
            }
        return orderNumber;
    }
    
    public void setOrderNumber(int previousNum) throws FlooringPersistenceException {

        try (OutputStream output = new FileOutputStream("config.properties")) {
            
            // set the properties value
            prop.setProperty("orderNumber", "" + (previousNum +1));

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not update order "
                    + "number", e);
        }
    }

}
