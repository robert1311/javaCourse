/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dao;

import com.re.flooring.dto.Order;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class FlooringDaoTest {

    FlooringDao dao = new FlooringDaoFileImpl();

    public FlooringDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Product> products = dao.getAllProducts();
        for (Product product : products) {
            dao.removeProduct(product.getProductType());
        }
        List<State> states = dao.getAllStates();
        for (State state : states) {
            dao.removeProduct(state.getStateName());
        }
        List<Order> orders = dao.getAllOrder();
        for (Order order : orders) {
            dao.removeState(order.getStateInfo().getStateName());
            dao.removeProduct(order.getProductInfo().getProductType());
            dao.removeOrder(order.getOrderNumber());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addProduct method, of class FlooringDao.
     */
    @Test
    public void testAddGetProduct() {
        Product newProduct = new Product("p1");
        newProduct.setLabCostPersqft(BigDecimal.TEN);
        newProduct.setMatCostPerSqFt(BigDecimal.TEN);
        dao.addProduct(newProduct);

        Product fromDao = dao.getProduct(newProduct.getProductType());
        assertEquals(newProduct, fromDao);
    }

    /**
     * Test of getAllProducts method, of class FlooringDao.
     */
    @Test
    public void testGetAllProducts() {
        Product newProduct = new Product("p1");
        newProduct.setLabCostPersqft(BigDecimal.TEN);
        newProduct.setMatCostPerSqFt(BigDecimal.TEN);
        dao.addProduct(newProduct);
        assertEquals(1, dao.getAllProducts().size());

        Product newProduct2 = new Product("p12");
        newProduct2.setLabCostPersqft(BigDecimal.TEN);
        newProduct2.setMatCostPerSqFt(BigDecimal.TEN);
        dao.addProduct(newProduct2);
        assertEquals(2, dao.getAllProducts().size());
    }

    /**
     * Test of removeProduct method, of class FlooringDao.
     */
    @Test
    public void testRemoveProduct() {
        Product newProduct = new Product("p1");
        newProduct.setLabCostPersqft(BigDecimal.TEN);
        newProduct.setMatCostPerSqFt(BigDecimal.TEN);
        dao.addProduct(newProduct);

        Product newProduct2 = new Product("p2");
        newProduct2.setLabCostPersqft(BigDecimal.TEN);
        newProduct2.setMatCostPerSqFt(BigDecimal.TEN);
        dao.addProduct(newProduct2);

        dao.removeProduct(newProduct.getProductType());
        assertEquals(1, dao.getAllProducts().size());
        assertNull(dao.getProduct(newProduct.getProductType()));

        dao.removeProduct(newProduct2.getProductType());
        assertEquals(0, dao.getAllProducts().size());
        assertNull(dao.getProduct(newProduct2.getProductType()));

    }

    /**
     * Test of addState method, of class FlooringDao.
     */
    @Test
    public void testAddGetState() {
        State newState = new State("s1");
        newState.setTaxRate(BigDecimal.TEN);
    }

    /**
     * Test of getAllStates method, of class FlooringDao.
     */
    @Test
    public void testGetAllStates() {
        State newState = new State("s1");
        newState.setTaxRate(BigDecimal.TEN);
        dao.addState(newState);
        assertEquals(1, dao.getAllStates().size());

        State newState2 = new State("s2");
        newState2.setTaxRate(BigDecimal.TEN);
        dao.addState(newState2);
        assertEquals(2, dao.getAllStates().size());
    }

    /**
     * Test of removeState method, of class FlooringDao.
     */
    @Test
    public void testRemoveState() {
        State newState = new State("p1");
        newState.setTaxRate(BigDecimal.TEN);
        dao.addState(newState);

        State newState2 = new State("p2");
        newState2.setTaxRate(BigDecimal.TEN);
        dao.addState(newState2);

        dao.removeState(newState.getStateName());
        assertNull(dao.getState(newState.getStateName()));
        assertEquals(1, dao.getAllStates().size());

        dao.removeState(newState2.getStateName());
        assertNull(dao.getState(newState2.getStateName()));
        assertEquals(0, dao.getAllStates().size());
    }

    /**
     * Test of addOrder method, of class FlooringDao.
     */
    @Test
    public void testAddGetOrder() {
        Order newOrder = new Order(1, "Rob", "Wood", "Tx");
        newOrder.setArea(5.00);
        newOrder.getProductInfo().setLabCostPersqft(BigDecimal.TEN);
        newOrder.getProductInfo().setMatCostPerSqFt(BigDecimal.TEN);
        newOrder.getStateInfo().setTaxRate(BigDecimal.TEN);
        newOrder.setOrderDate(LocalDate.now());
        dao.addOrder(newOrder);

        Order fromDao = dao.getOrder(1);

        assertEquals(newOrder, fromDao);
    }

    /**
     * Test of getAllOrder method, of class FlooringDao.
     */
    @Test
    public void testGetAllOrder() {
        Order newOrder = new Order(1, "Rob", "Wood", "TX");
        newOrder.setArea(5.00);
        newOrder.getProductInfo().setLabCostPersqft(BigDecimal.TEN);
        newOrder.getProductInfo().setMatCostPerSqFt(BigDecimal.TEN);
        newOrder.getStateInfo().setTaxRate(BigDecimal.TEN);
        newOrder.setOrderDate(LocalDate.now());
        dao.addOrder(newOrder);

        assertEquals(1, dao.getAllOrder().size());

        Order newOrder2 = new Order(2, "John", "laminate", "LA");
        newOrder2.setArea(10.00);
        newOrder2.getProductInfo().setLabCostPersqft(BigDecimal.TEN);
        newOrder2.getProductInfo().setMatCostPerSqFt(BigDecimal.TEN);
        newOrder2.getStateInfo().setTaxRate(BigDecimal.TEN);
        newOrder.setOrderDate(LocalDate.now());
        dao.addOrder(newOrder2);

        assertEquals(2, dao.getAllOrder().size());
    }

    /**
     * Test of removeOrder method, of class FlooringDao.
     */
    @Test
    public void testRemoveOrder() {
        Order newOrder = new Order(1, "Rob", "Wood", "TX");
        newOrder.setArea(5.00);
        newOrder.getProductInfo().setLabCostPersqft(BigDecimal.TEN);
        newOrder.getProductInfo().setMatCostPerSqFt(BigDecimal.TEN);
        newOrder.getStateInfo().setTaxRate(BigDecimal.TEN);
        newOrder.setOrderDate(LocalDate.now());
        dao.addOrder(newOrder);

        Order newOrder2 = new Order(2, "John", "laminate", "LA");
        newOrder2.setArea(10.00);
        newOrder2.getProductInfo().setLabCostPersqft(BigDecimal.TEN);
        newOrder2.getProductInfo().setMatCostPerSqFt(BigDecimal.TEN);
        newOrder2.getStateInfo().setTaxRate(BigDecimal.TEN);
        newOrder.setOrderDate(LocalDate.now());
        dao.addOrder(newOrder2);

        dao.removeState(newOrder2.getStateInfo().getStateName());
        dao.removeProduct(newOrder2.getProductInfo().getProductType());
        dao.removeOrder(newOrder2.getOrderNumber());

        assertNull(dao.getOrder(newOrder2.getOrderNumber()));
        assertEquals(1, dao.getAllOrder().size());

        dao.removeState(newOrder.getStateInfo().getStateName());
        dao.removeProduct(newOrder.getProductInfo().getProductType());
        dao.removeOrder(newOrder.getOrderNumber());

        assertNull(dao.getOrder(newOrder.getOrderNumber()));
        assertEquals(0, dao.getAllOrder().size());
    }

    /**
     * Test of loadOrders method, of class FlooringDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadOrders() throws Exception {
    }

    /**
     * Test of loadProducts method, of class FlooringDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadProducts() throws Exception {
    }

    /**
     * Test of loadStates method, of class FlooringDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadStates() throws Exception {
    }

    /**
     * Test of writeOrders method, of class FlooringDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testWriteOrders() throws Exception {
    }

    /**
     * Test of writeProducts method, of class FlooringDao.
     *
     * @throws java.lang.Exception
     */
    @Test

    public void testWriteProducts() throws Exception {
    }

    /**
     * Test of writeStates method, of class FlooringDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testWriteStates() throws Exception {
    }

}
