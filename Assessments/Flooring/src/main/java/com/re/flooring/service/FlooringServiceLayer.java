/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.service;

import com.re.flooring.dao.FlooringPersistenceException;
import com.re.flooring.dto.Order;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import java.util.List;

/**
 *
 * @author rober
 */
public interface FlooringServiceLayer {

    /**
     * Returns the next auto-generated order number for any Order to be added to
     * the system.
     *
     * @return an integer representing the next available order number
     * @throws FlooringPersistenceException
     */
    int getConfigNextOrderNumber() throws FlooringPersistenceException;

    /**
     * Retrieves the Boolean value representing the status of whether or not the
     * system is in Training or Production mode.
     *
     * @return true if application is in Training Mode; else false;
     * @throws FlooringPersistenceException
     */
    boolean getConfigApplicationMode() throws FlooringPersistenceException;

    /**
     * Returns all State objects in the system.
     *
     * @return List of State objects
     */
    List<State> getAllStateInfo();

    /**
     * Returns all Product objects in the system.
     *
     * @return List of Product objects
     */
    List<Product> getAllProductInfo();

    /**
     * Takes a newly constructed Order, gives it an auto-generated unique order
     * number, and calculates the costs of the Order.
     *
     * @param newOrder
     * @return a complete Order with no null fields
     */
    Order finalizeOrder(Order newOrder);

    /**
     * Adds a complete Order to the system and logs the confirmed Order with a
     * timestamp and all the order info.Does not persist if application is in
     * training mode.
     * @param confirmedOrder
     * @return the Order to be added to the system
     * @throws com.re.flooring.dao.FlooringPersistenceException
     */
    Order createNewOrder(Order confirmedOrder) throws FlooringPersistenceException;

    /**
     * Returns a filtered list of Orders whose order date matches with
     * orderDate.
     *
     * @param orderDate
     * @return List of Orders with matching orderDate
     * @throws FlooringInvalidDateException
     */
    List<Order> getOrdersByDate(String orderDate) throws FlooringInvalidDateException;

    /**
     * Gets all orders from the system and returns them to the calling method.
     *
     * @return List of all Orders
     */
    List<Order> getAllOrders();

    /**
     * Retrieves the Order based of the given order number
     *
     * @param orderNumber
     * @return the Order with the matching order number
     */
    Order getOrder(int orderNumber);

    /**
     * Updates an existing Order in the system.Order number cannot be changed.
     *
     * @param updated
     * @return updated Order.
     * @throws com.re.flooring.dao.FlooringPersistenceException
     */
    Order updateOrder(Order updated) throws FlooringPersistenceException;

    /**
     * Removes an Order from the system with the given order number.
     *
     * @param orderNumber
     * @return Order to be removed.
     * @throws com.re.flooring.dao.FlooringPersistenceException
     */
    Order removeOrder(int orderNumber) throws FlooringPersistenceException;
    
    /**
     * Loads State info, Product Info, and orders.
     * @throws FlooringPersistenceException 
     */
    void loadEntities() throws FlooringPersistenceException;
    
    /**
     * Saves Orders to the system
     * @throws FlooringPersistenceException 
     */
    void saveOrders() throws FlooringPersistenceException;
}
