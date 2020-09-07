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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
    private final String DELIMITER = "::";

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

    private Product unmarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(DELIMITER);
        String productType = productTokens[0];
        BigDecimal matCostSqFt = new BigDecimal(productTokens[1]);
        BigDecimal labCostsqFt = new BigDecimal(productTokens[2]);

        Product unmarshalledProduct = new Product(productType);
        unmarshalledProduct.setMatCostPerSqFt(matCostSqFt);
        unmarshalledProduct.setLabCostPersqft(labCostsqFt);
        return unmarshalledProduct;

    }

    private State unmarshallState(String stateAsText) {
        String[] stateTokens = stateAsText.split(DELIMITER);
        String stateName = stateTokens[0];
        BigDecimal taxRate = new BigDecimal(stateTokens[1]);
        
        State unmarshalledState = new State(stateName);
        unmarshalledState.setTaxRate(taxRate);
        
        return unmarshalledState;
    }

    private Order unmarshallOrder(String orderAsText) {
        String[] orderTokens = orderAsText.split(DELIMITER);
        int orderNumber = Integer.parseInt(orderTokens[0]);
        String firstName = orderTokens[1];
        String lastName = orderTokens[2];
        String stateName = orderTokens[3];
        BigDecimal taxRate = new BigDecimal(orderTokens[4])
                .setScale(2, RoundingMode.HALF_UP);
        String productType = orderTokens[5];
        BigDecimal matCostSqFt = new BigDecimal(orderTokens[6]);
        BigDecimal labCostsqFt = new BigDecimal(orderTokens[7]);
        double area = Double.parseDouble(orderTokens[8]);
        BigDecimal matCost = new BigDecimal(orderTokens[8]);
        BigDecimal labCost = new BigDecimal(orderTokens[10]);
        BigDecimal tax = new BigDecimal(orderTokens[11]);
        BigDecimal total = new BigDecimal(orderTokens[12]);
        LocalDate ld = LocalDate.parse(orderTokens[13],
                DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        
        Order unmarshalledOrder = new Order(orderNumber, productType, 
                stateName);
        unmarshalledOrder.setFirstName(firstName);
        unmarshalledOrder.setLastName(lastName);
        unmarshalledOrder.getStateInfo().setTaxRate(taxRate);
        unmarshalledOrder.getProductInfo().setMatCostPerSqFt(matCostSqFt);
        unmarshalledOrder.getProductInfo().setLabCostPersqft(labCostsqFt);
        unmarshalledOrder.setArea(area);
        Cost cost = new Cost();
        unmarshalledOrder.setCostInfo(cost);
        unmarshalledOrder.getCostInfo().setMaterialCost(matCost);
        unmarshalledOrder.getCostInfo().setLaborCost(labCost);
        unmarshalledOrder.getCostInfo().setTax(tax);
        unmarshalledOrder.getCostInfo().setTotalCost(total);
        unmarshalledOrder.setOrderDate(ld);
        
        return unmarshalledOrder;
    }

    @Override
    public void loadOrders() throws FlooringPersistenceException {
        Scanner sc;
        try{
            sc = new Scanner(new BufferedReader(new FileReader(ORDERS_FILE)));
        } catch(IOException e){
            throw new FlooringPersistenceException("Orders could not be loaded.", e);
        }
        Order currentOrder;
        String currentLine;
        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentOrder = unmarshallOrder(currentLine);
            orderMap.put(currentOrder.getOrderNumber(), currentOrder);
        }
        sc.close();
    }

    @Override
    public void loadProducts() throws FlooringPersistenceException {
        Scanner sc;
        try{
            sc = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        } catch(IOException e){
            throw new FlooringPersistenceException("Orders could not be loaded.", e);
        }
        Product currentProduct;
        String currentLine;
        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            productMap.put(currentProduct.getProductType(), currentProduct);
        }
        sc.close();
        
    }

    @Override
    public void loadStates() throws FlooringPersistenceException {
        Scanner sc;
        try{
            sc = new Scanner(new BufferedReader(new FileReader(STATES_FILE)));
        } catch(FileNotFoundException e){
            throw new FlooringPersistenceException("Orders could not be loaded.", e);
        }
        State currentState;
        String currentLine;
        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentState = unmarshallState(currentLine);
            stateMap.put(currentState.getStateName(), currentState);
        }
        sc.close();
    }

    private String marshallOrder(Order anOrder) {
        String orderAsText = "" + anOrder.getOrderNumber() + DELIMITER;
                orderAsText += anOrder.getFirstName() 
                        + DELIMITER;
                orderAsText += anOrder.getLastName() 
                        + DELIMITER;
                orderAsText += anOrder.getStateInfo().getStateName() 
                        + DELIMITER;
                orderAsText += anOrder.getStateInfo().getTaxRate() 
                        + DELIMITER;
                orderAsText += anOrder.getProductInfo().getProductType() 
                        + DELIMITER;
                orderAsText += anOrder.getProductInfo().getMatCostPerSqFt() 
                        + DELIMITER;
                orderAsText += anOrder.getProductInfo().getLabCostPersqft() 
                        + DELIMITER;
                orderAsText += anOrder.getArea() 
                        + DELIMITER;
                orderAsText += anOrder.getCostInfo().getMaterialCost() 
                        + DELIMITER;
                orderAsText += anOrder.getCostInfo().getLaborCost() 
                        + DELIMITER;
                orderAsText += anOrder.getCostInfo().getTax() 
                        + DELIMITER;
                orderAsText += anOrder.getCostInfo().getTotalCost()
                        + DELIMITER;
                orderAsText += anOrder.getOrderDate().format(DateTimeFormatter
                        .ofPattern("MM/dd/yyyy"));
                return orderAsText;
        
    }
    
    private String marshallProduct(Product aProduct){
        String productAsText = aProduct.getProductType() + DELIMITER;
        productAsText += aProduct.getMatCostPerSqFt() + DELIMITER;
        productAsText += aProduct.getLabCostPersqft();
        
        return productAsText;
    }
    
    private String marshallState(State aState){
        String stateAsText = aState.getStateName() + DELIMITER;
        stateAsText += aState.getTaxRate();
        
        return stateAsText;
    }
    
    @Override
    public void writeOrders() throws FlooringPersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(ORDERS_FILE));
        } catch (IOException e){
            throw new FlooringPersistenceException("Orders could not be saved.", e);
        }
        String marshalledOrder;
        for(Order currentOrder : orderMap.values()){
            marshalledOrder = marshallOrder(currentOrder);
            out.println(marshalledOrder);
            out.flush();
        }
        out.close();
    }

    @Override
    public void writeProducts() throws FlooringPersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(ORDERS_FILE));
        } catch (IOException e){
            throw new FlooringPersistenceException("Orders could not be saved.", e);
        }
        String marshalledProduct;
        for(Product currentProduct : productMap.values()){
            marshalledProduct = marshallProduct(currentProduct);
            out.println(marshalledProduct);
            out.flush();
        }
        out.close();
    }

    @Override
    public void writeStates() throws FlooringPersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(ORDERS_FILE));
        } catch (IOException e){
            throw new FlooringPersistenceException("Orders could not be saved.", e);
        }
        String marshalledState;
        for(State currentState : stateMap.values()){
            marshalledState = marshallState(currentState);
            out.println(marshalledState);
            out.flush();
        }
        out.close();
    }

}
