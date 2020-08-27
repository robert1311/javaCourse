/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.service;

import com.re.vendingmachine.dto.Item;
import com.re.vendingmachine.dto.Reservoir;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rober
 */
public class VendingMachineServiceLayerTest {
    
    VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service =ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);

        
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
     * Test of getFullItemList method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetFullItemList() {
        assertEquals(2, service.getFullItemList().size());
    }
    
    /**
     * Test of getAvailableItemsList method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAvailableItemsList() {
        List<Item> items = service.getAvailableItemsList();
        assertEquals(1, items.size());
        
        Item available = items.get(0);
        assertEquals("Cola", service.getInventoryItem(available.getName())
                .getName());
    }

    /**
     * Test of getInventoryItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetInventoryItem() {
        Item fromDao =  service.getInventoryItem("Soda");
        assertEquals("Soda", fromDao.getName());
        Item fromDao2 = service.getInventoryItem("Cola");
        assertEquals("Cola", fromDao2.getName());
        
        List<Item> ofTwo = service.getFullItemList();
        
        assertTrue(ofTwo.contains(fromDao));
        assertTrue(ofTwo.contains(fromDao2));
    }
    
     /**
     * Test of testGetReservoir method, of class VendingMachineServiceLayer.
     */
   @Test
   public void testGetReservoir(){
       Reservoir inFromDao =  service.getSpecReservoir("in");
        assertEquals("in", inFromDao.getReservoirType());
        Reservoir outFromDao =  service.getSpecReservoir("out");
        assertEquals("out", outFromDao.getReservoirType());
   }

    /**
     * Test funds validation of validateFundsAndAvailability method, of class 
     * VendingMachineServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testValidateFunds() throws Exception {
        BigDecimal funds = new BigDecimal("1.00");
        try{
            //first element in list
        service.validateFundsAndAvailability(funds, 1);
        fail("Expected VendingMachineInsufficientFiundsException not thrown.");
        } catch (VendingMachineInsufficientFundsException e){

        }
    }
    
    /**
     * Test availability of validateFundsAndAvailability method, of class 
     * VendingMachineServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testItemAvailability() throws Exception {
        BigDecimal funds = new BigDecimal("1.00");
        try{
        service.validateFundsAndAvailability(funds, 2);
        fail("Expected VendingMachineNoItemInventoryException");
        } catch (VendingMachineNoItemInventoryException e){
            
        }
    }
    
    /**
     * Test of validateFundsAndAvailability method for successful vend, of 
     * class VendingMachineServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testSuccessfulVend() throws Exception {
        BigDecimal funds = new BigDecimal("2.00");
        int iCount = service.getInventoryItem("Cola").getCount();
        service.validateFundsAndAvailability(funds, 1);
        assertEquals(iCount - 1, service.getInventoryItem("Cola").getCount());
    }

//    /**
//     * Test of loadApiInventory method, of class VendingMachineServiceLayer.
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testLoadApiInventory() throws Exception{
//        try{
//            service.loadApiInventory();
//            fail("Expected VendingMachinePersistenceEception was not thrown.");
//        }catch(VendingMachinePersistenceException e){
//            
//        }
//    }
//    
//    /**
//     * Test of saveApiInventory method, of class VendingMachineServiceLayer.
//     * @throws java.lang.Exception
//     */
//    @Test
//    public void testSaveApiInventory() throws Exception {
//        try{
//            service.saveApiInventory();
//            fail("Expected VendingMachinePersistenceEception was not thrown.");
//        }catch(VendingMachinePersistenceException e){
//            
//        }
//    }

   
    
}
