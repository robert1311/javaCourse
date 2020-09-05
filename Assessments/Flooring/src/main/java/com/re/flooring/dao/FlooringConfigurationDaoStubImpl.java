/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dao;

/**
 *
 * @author rober
 */
public class FlooringConfigurationDaoStubImpl implements
        FlooringConfigurationDao {

    @Override
    public boolean getApplicationMode() throws FlooringPersistenceException {

        return false;
    }

    @Override
    public int getNextOrderNumber() throws FlooringPersistenceException {
        return 2;
    }

    @Override
    public void setOrderNumber(int previousNum) throws FlooringPersistenceException {

    }

}
