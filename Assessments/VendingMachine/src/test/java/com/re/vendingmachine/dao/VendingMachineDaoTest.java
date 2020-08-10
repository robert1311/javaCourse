/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.dao;

import com.re.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
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
public class VendingMachineDaoTest {

    VendingMachineDao dao = new VendingMachineDaoFileImpl();

    public VendingMachineDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Item> itemList = dao.getFullItemList();
        for (Item item : itemList) {
            dao.removeItem(item);
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class VendingMachineDao.
     */
    @Test
    public void testAddGetItem() {
        Item item1 = new Item("Coke");
        BigDecimal cost1 = new BigDecimal("1.00");
        item1.setCost(cost1);
        item1.setCount(1);

        dao.addItem(item1);

        Item fromDao = dao.getItem(item1.getName());

        assertEquals(item1, fromDao);
    }

    /**
     * Test of getFullItemList method, of class VendingMachineDao.
     */
    @Test
    public void testGetFullItemList() {
        Item item1 = new Item("Coke");
        BigDecimal cost1 = new BigDecimal("1.00");
        item1.setCost(cost1);
        item1.setCount(1);

        dao.addItem(item1);

        Item item2 = new Item("Pepsi");
        BigDecimal cost2 = new BigDecimal("1.25");
        item2.setCost(cost2);
        item2.setCount(2);

        dao.addItem(item2);

        assertEquals(2, dao.getFullItemList().size());
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveItem() {
        Item item1 = new Item("Coke");
        BigDecimal cost1 = new BigDecimal("1.00");
        item1.setCost(cost1);
        item1.setCount(1);

        dao.addItem(item1);

        Item item2 = new Item("Pepsi");
        BigDecimal cost2 = new BigDecimal("1.25");
        item2.setCost(cost2);
        item2.setCount(2);

        dao.addItem(item2);

        dao.removeItem(item1);
        assertEquals(1, dao.getFullItemList().size());
        assertNull(dao.getItem(item1.getName()));

        dao.removeItem(item2);
        assertEquals(0, dao.getFullItemList().size());
        assertNull(dao.getItem(item2.getName()));
    }

    /**
     * Test of loadInventory method, of class VendingMachineDao.
     *
     * @throws com.re.vendingmachine.dao.VendingMachinePersistenceException
     */
    @Test
    public void testLoadInventory() throws VendingMachinePersistenceException {
        try {
            dao.loadInventory();
            List<Item> itemList = dao.getFullItemList();
            for (Item item : itemList) {
                dao.removeItem(item);
            }
        } catch (VendingMachinePersistenceException e) {
            List<Item> itemList = dao.getFullItemList();
            for (Item item : itemList) {
                dao.removeItem(item);
            }
            fail("Expected VendingMachinePersistenceException was not thrown.");
        }
    }

    /**
     * Test of writeInventory method, of class VendingMachineDao.
     *
     * @throws com.re.vendingmachine.dao.VendingMachinePersistenceException
     */
    @Test
    public void testWriteInventory() throws VendingMachinePersistenceException {
        try {
            dao.loadInventory();
            dao.writeInventory();
            List<Item> itemList = dao.getFullItemList();
            for (Item item : itemList) {
                dao.removeItem(item);
            }
        } catch (VendingMachinePersistenceException e) {
            List<Item> itemList = dao.getFullItemList();
            for (Item item : itemList) {
                dao.removeItem(item);
            }
                fail("Expected VendingMachinePersistenceException was not thrown.");
            }
        }
    }
