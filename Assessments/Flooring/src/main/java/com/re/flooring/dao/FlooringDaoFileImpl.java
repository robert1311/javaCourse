/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dao;

import com.re.flooring.dto.Order;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rober
 */
public class FlooringDaoFileImpl implements FlooringDao {

    Map<Integer, Order> orderMap = new HashMap<>();
    Map<String, Product> productMap = new HashMap<>();
    Map<String, State> stateMap = new HashMap<>();
    
    private final String ORDERS_FILE = "orders.txt";
    private final String PRODUCTS_FILE = "products.txt";
    private final String STATES_FILE = "states.txt";
    
    @Override
    public Order addOrder(Order order) {
        return orderMap.put(order.getOrderNumber(), order);
    }

    @Override
    public Order getOrder(int orderNumber) {
        return orderMap.get(orderNumber);
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orderList = new ArrayList<>();
        orderMap.values().forEach((currentOrder) -> {
            orderList.add(currentOrder);
        });
        return orderList;
    }

    @Override
    public Order removeOrder(int orderNumber) {
        return orderMap.remove(orderNumber);
    }

    @Override
    public Product addProduct(Product product) {
        return productMap.put(product.getProductType(), product);
    }

    @Override
    public Product getProduct(String productType) {
        return productMap.get(productType);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productMap.values().forEach((currentProduct) -> {
            productList.add(currentProduct);
        });
        return productList;
    }

    @Override
    public Product removeProduct(String productType) {
        return productMap.remove(productType);
    }

    @Override
    public State addState(State state) {
        return stateMap.put(state.getStateName(), state);
    }

    @Override
    public State getState(String stateName) {
        return stateMap.get(stateName);
    }

    @Override
    public List<State> getAllStates() {
        List<State> stateList = new ArrayList<>();
        stateMap.values().forEach((currentState) -> {
            stateList.add(currentState);
        });
        return stateList;
    }

    @Override
    public State removeState(String stateName) {
        return stateMap.remove(stateName);
    }

    @Override
    public void loadOrders() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadProducts() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadStates() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeOrders() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeProducts() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeStates() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
