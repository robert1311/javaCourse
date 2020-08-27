/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dao;

import com.re.flooring.dto.Order;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import java.util.List;

/**
 *
 * @author rober
 */
public interface FlooringDao {
    /**
     * adds the given Order to the system.
     *
     * @param order
     * @return the Order to be added.
     */
    Order addOrder(Order order);

    /**
     * retrieves an Order from the system given the order number.
     *
     * @param orderNumber
     * @return the Order to be retrieved; can be null.
     */
    Order getOrder(int orderNumber);

    /**
     * retrieves all the Orders in the system.
     * @return List of all Orders saved in the system.
     */
    List<Order> getAllOrder();

    /**
     * removes the given Order from the system.
     * @param orderNumber
     * @return Order to be removed; can be null
     */
    Order removeOrder(int orderNumber);

    /**
     * adds the given product to the system.
     * @param product
     * @return Product to be added.
     */
    Product addProduct(Product product);

    /**
     * retrieves the Product from the system given the product type.
     * @param productType
     * @return the Product to be retrieved.
     */
    Product getProduct(String productType);

    /**
     * retrieves all the Products in the system.
     * @return List of all Products in the system.
     */
    List<Product> getAllProducts();

    /**
     * removes the given Product from the system.
     * @param productType
     * @return Product to be removed
     */
    Product removeProduct(String productType);

    /**
     * adds the given State to the system.
     * @param state
     * @return State to be added.
     */
    State addState(State state);

    /**
     * retrieves a State from the system given the state name
     * @param stateName
     * @return State to be retrieved.
     */
    State getState(String stateName);

    /**
     * retrieves all states in the system.
     * @return List of all states in the system.
     */
    List<State> getAllStates();

    /**
     * removes the State given the stateName
     * @param stateName
     * @return State to be removed.
     */
    State removeState(String stateName);

    /**
     * loads all Orders into the system from external storage.
     * @throws FlooringPersistenceException
     */
    void loadOrders() throws FlooringPersistenceException;

    /**
     * loads all Products into the system from external storage.
     * @throws FlooringPersistenceException
     */
    void loadProducts() throws FlooringPersistenceException;

    /**
     * loads all States into the system from external storage.
     * @throws FlooringPersistenceException
     */
    void loadStates() throws FlooringPersistenceException;

    /**
     * writes or saves Orders from the system to external storage.
     * @throws FlooringPersistenceException
     */
    void writeOrders() throws FlooringPersistenceException;

    /**
     * writes or saves Products from the system to external storage.
     * @throws FlooringPersistenceException
     */
    void writeProducts() throws FlooringPersistenceException;

    /**
     * writes or saves States from the system to external storage.
     * @throws FlooringPersistenceException
     */
    void writeStates() throws FlooringPersistenceException;
}
