/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rober
 */
public class FlooringConfigurationDaoTest {
    
    FlooringConfigurationDao dao = new FlooringConfigurationDaoFileImpl();
    
    public FlooringConfigurationDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getApplicationMode method, of class FlooringConfigurationDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetApplicationMode() throws Exception {
        assertFalse(dao.getApplicationMode());
    }

    /**
     * Test of getNextOrderNumber method, of class FlooringConfigurationDao.
     */
    @Test
    public void testGetNextOrderNumber() throws Exception {
        assertEquals(1,dao.getNextOrderNumber());
    }

    /**
     * Test of setOrderNumber method, of class FlooringConfigurationDao.
     */
    @Test
    public void testSetOrderNumber() throws Exception {
        int previousNum = dao.getNextOrderNumber() - 1;
        dao.setOrderNumber(previousNum);
        int currentNum = previousNum + 1;
        assertEquals(currentNum, dao.getNextOrderNumber());
    }

    
    
}
