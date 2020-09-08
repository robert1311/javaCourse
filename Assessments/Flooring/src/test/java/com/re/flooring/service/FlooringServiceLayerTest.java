/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.service;

import com.re.flooring.dao.FlooringPersistenceException;
import com.re.flooring.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rober
 */
public class FlooringServiceLayerTest {

    private FlooringServiceLayer service;

    public FlooringServiceLayerTest() {
//        FlooringDao dao = new FlooringDaoStubImpl();
//        FlooringAuditDao audit = new FlooringAuditDaoStubImpl();
//        FlooringConfigurationDao config = new FlooringConfigurationDaoStubImpl();
        ApplicationContext ctx = new 
            ClassPathXmlApplicationContext("applicationContext.xml");
        
            
//        service = new FlooringServiceLayerImpl(dao, audit, config);
        service = ctx.getBean("serviceLayer", FlooringServiceLayer.class);
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
     * Test of getConfigNextOrderNumber method, of class FlooringServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetConfigNextOrderNumber() throws Exception {
        assertEquals(2, service.getConfigNextOrderNumber());
    }

    /**
     * Test of getConfigApplicationMode method, of class FlooringServiceLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetConfigApplicationMode() throws Exception {
        assertFalse(service.getConfigApplicationMode());
    }

    /**
     * Test of getAllStateInfo method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetAllStateInfo() {
        assertEquals(1, service.getAllStateInfo().size());

    }

    /**
     * Test of getAllProductInfo method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetAllProductInfo() {
        assertEquals(1, service.getAllProductInfo().size());
    }

    /**
     * Test of finalizeOrder method, of class FlooringServiceLayer.
     */
    @Test
    public void testFinalizeOrder() {

        Order newOrder;

        newOrder = new Order(99, "Wood", "TX");
        newOrder.setFirstName("John");
        newOrder.setLastName("Doe");
        newOrder.setArea(10);
        newOrder.getStateInfo().setTaxRate(BigDecimal.TEN);
        newOrder.getProductInfo().setMatCostPerSqFt(BigDecimal.TEN);
        newOrder.getProductInfo().setLabCostPersqft(BigDecimal.TEN);

        service.finalizeOrder(newOrder);
        assertEquals(BigDecimal.TEN.setScale(2), newOrder.getProductInfo()
                .getMatCostPerSqFt());
        assertEquals(BigDecimal.TEN.setScale(2), newOrder.getProductInfo()
                .getLabCostPersqft());
        assertEquals(BigDecimal.TEN.setScale(2), newOrder.getStateInfo()
                .getTaxRate());
        assertEquals(BigDecimal.valueOf(100).setScale(2), newOrder.getCostInfo().getMaterialCost());
        assertEquals(BigDecimal.valueOf(100).setScale(2), newOrder.getCostInfo().getLaborCost());
        assertEquals(BigDecimal.valueOf(20).setScale(2), newOrder.getCostInfo().getTax());
        assertEquals(BigDecimal.valueOf(220).setScale(2), newOrder.getCostInfo().getTotalCost());
        assertEquals(LocalDate.now().format(DateTimeFormatter
                .ofPattern("MM/dd/yyyy")), newOrder.getOrderDate()
                .format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));

    }

    /**
     * Test of createNewOrder method, of class FlooringServiceLayer.
     * @throws com.re.flooring.dao.FlooringPersistenceException
     */
    @Test
    public void testCreateNewOrder() throws Exception {
        Order newOrder;
        newOrder = new Order(2, "Wood", "TX");
        newOrder.setFirstName("John");
        newOrder.setLastName("Doe");
        newOrder.setArea(10);
        newOrder.getStateInfo().setTaxRate(BigDecimal.TEN);
        newOrder.getProductInfo().setMatCostPerSqFt(BigDecimal.TEN);
        newOrder.getProductInfo().setLabCostPersqft(BigDecimal.TEN);

        service.finalizeOrder(newOrder);
        
        try{
        service.createNewOrder(newOrder);
        } catch(FlooringPersistenceException e){
            fail("could not save Order.");
        }
        
        int orderNum = newOrder.getOrderNumber();
        
        assertEquals(orderNum + 1, service.getConfigNextOrderNumber() + 1);
    }

    /**
     * Test of getOrder method, of class FlooringServiceLayer
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrder() throws Exception {
        
        assertNotNull(service.getOrder(1));
        try{
            service.getOrder(2);
            fail("Expected FlooringNoSuchOrderException not thrown.");
        } catch(FlooringNoSuchOrderException e){
            return;
        }
        
    }
    
    /**
     * Test of getOrdersByDate method, of class FlooringServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrdersByDate() throws Exception{
        assertEquals(1, service.getOrdersByDate("08/30/2020").size());
        
        try{
            service.getOrdersByDate("01/31/20");
            fail("Expected FlooringInvalidDateException not thrown");
        } catch(FlooringInvalidDateException e){
            
        }
        try{
            service.getOrdersByDate(LocalDate.now().plusWeeks(1)
                    .format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            fail("Expected FlooringInvalidDateException not thrown.");
        } catch(FlooringInvalidDateException e){
            
        }
    }


    /**
     * Test of getAllOrders method, of class FlooringServiceLayer.
     */
    @Test
    public void testGetAllOrders() {
        assertEquals(1, service.getAllOrders().size());
    }

    /**
     * Test of updateOrder method, of class FlooringServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateOrder() throws Exception {
        Order order = service.getOrder(1);
        order.setFirstName("John");
        order.setLastName("Smith");
        Order updated = service.updateOrder(order);

        assertNotNull(updated);
//        assertEquals("LA", updated.getStateInfo().getStateName());
//        assertEquals("Laminate", updated.getProductInfo().getProductType());
    }

    /**
     * Test of removeOrder method, of class FlooringServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOrder() throws Exception{
        Order order = service.removeOrder(1);
        assertNotNull(order);
    }

}
