/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dao;

import com.re.flooring.dto.Cost;
import com.re.flooring.dto.Order;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rober
 */
public class FlooringDaoStubImpl implements FlooringDao {

    List<Order> orders = new ArrayList<>();
    List<State> states = new ArrayList<>();
    List<Product> products = new ArrayList<>();
    Order onlyOrder;
    State onlyState;
    Product onlyProduct;

    public FlooringDaoStubImpl() {
        onlyState = new State("TX");
        onlyState.setTaxRate(BigDecimal.TEN);
        states.add(onlyState);
//        onlyOrder.setStateInfo(onlyState);
        
        onlyProduct = new Product("Wood");
        onlyProduct.setMatCostPerSqFt(BigDecimal.TEN);
        onlyProduct.setLabCostPersqft(BigDecimal.TEN);
        products.add(onlyProduct);
//        onlyOrder.setProductInfo(onlyProduct);
        
        onlyOrder = new Order(1, "Wood", "TX");
        onlyOrder.setFirstName("Rob");
        onlyOrder.setLastName("Esq");
        onlyOrder.setArea(5.00);
        onlyOrder.setStateInfo(onlyState);
        onlyOrder.setProductInfo(onlyProduct);
//        onlyOrder.getProductInfo().setLabCostPersqft(BigDecimal.TEN);
//        onlyOrder.getProductInfo().setMatCostPerSqFt(BigDecimal.TEN);
//        onlyOrder.getStateInfo().setTaxRate(BigDecimal.TEN);
        Cost cost = new Cost();
        onlyOrder.setCostInfo(cost);
        onlyOrder.getCostInfo().setMaterialCost(BigDecimal.valueOf(50));
        onlyOrder.getCostInfo().setLaborCost(BigDecimal.valueOf(50));
        onlyOrder.getCostInfo().setTax(BigDecimal.TEN);
        onlyOrder.getCostInfo().setTotalCost(BigDecimal.valueOf(110));
        onlyOrder.setOrderDate(LocalDate.parse("08/30/2020", 
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        orders.add(onlyOrder);
        
        
    }

    @Override
    public Order addOrder(Order order) {
        if (order.equals(onlyOrder)) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order getOrder(int orderNumber) {
        if (orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrder() {
        return orders;
    }

    @Override
    public Order removeOrder(int orderNumber) {
        if (orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Product addProduct(Product product) {
        if (product.equals(onlyProduct)) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public Product getProduct(String productType) {
        if (productType.equalsIgnoreCase(onlyProduct.getProductType())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product removeProduct(String productType) {
        if (productType.equalsIgnoreCase(onlyProduct.getProductType())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public State addState(State state) {
        if (state.equals(onlyState)) {
            return onlyState;
        } else {
            return null;
        }
    }

    @Override
    public State getState(String stateName) {
        if (stateName.equalsIgnoreCase(onlyState.getStateName())) {
            return onlyState;
        } else {
            return null;
        }
    }

    @Override
    public List<State> getAllStates() {
        return states;
    }

    @Override
    public State removeState(String stateName) {
        if (stateName.equalsIgnoreCase(onlyState.getStateName())) {
            return onlyState;
        } else {
            return null;
        }
    }

    @Override
    public void loadOrders() throws FlooringPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader("")));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
    }

    @Override
    public void loadProducts() throws FlooringPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader("")));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
    }

    @Override
    public void loadStates() throws FlooringPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader("")));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
    }

    @Override
    public void writeOrders() throws FlooringPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader("")));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
    }

    @Override
    public void writeProducts() throws FlooringPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader("")));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
    }

    @Override
    public void writeStates() throws FlooringPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(
                    new FileReader("")));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Error: Inventory "
                    + "could not be loaded.", e);
        }
    }

}
