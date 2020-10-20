/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author rober
 */
public class FlooringConfigurationDaoFileImpl implements FlooringConfigurationDao {

    @Override
    public boolean getApplicationMode() throws FlooringPersistenceException {

        FileReader reader;
        Properties p;
        try {
            reader = new FileReader("resources/config.properties");

        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Unable to get application status.");
        }
        try {
            p = new Properties();
            p.load(reader);
        } catch (IOException e) {
            throw new FlooringPersistenceException("Unable to get application status.");
        }
        return Boolean.parseBoolean(p.getProperty("isTraining"));
    }

    @Override
    public int getNextOrderNumber() throws FlooringPersistenceException {
        FileReader reader;
        Properties p;
        try {
            reader = new FileReader("resources/config.properties");

        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not retrieve  next order number.");
        }
        try {
            p = new Properties();
            p.load(reader);
        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not retrieve  next order number.");
        }
        return Integer.parseInt(p.getProperty("orderNumber"));
    }

    @Override
    public void setOrderNumber(int previousNum)
            throws FlooringPersistenceException {
        FileInputStream reader;
        Properties prop = new Properties();

        try {
            reader = new FileInputStream("resources/config.properties");
            prop.load(reader);
            reader.close();

            FileOutputStream out = new FileOutputStream("resources/config.properties");
            // set the properties value
            prop.setProperty("orderNumber", "" + (previousNum + 1));

            // save properties to project root folder
            prop.store(out, null);

        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not update order "
                    + "number", e);
        }
    }

}
