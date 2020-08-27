/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dto;

/**
 *
 * @author rober
 */
public class Order {
    private int orderNumber;
    private String customerName;
    private double area;
    private Product productInfo;
    private State stateInfo;
    private Cost costInfo;

    public Order(int orderNumber, String customerName, String productType, 
            String stateName, double area){
        this.orderNumber = orderNumber;
        this.area = area;
        this.customerName = customerName;
        productInfo = new Product(productType);
        stateInfo = new State(stateName);
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

//    public void setOrderNumber(int orderNumber) {//handle in SL
//        this.orderNumber = orderNumber;
//    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Product getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(Product productInfo) {
        this.productInfo = productInfo;
    }
    
    public State getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(State stateInfo) {
        this.stateInfo = stateInfo;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Cost getCostInfo() {
        return costInfo;
    }

    public void setCostInfo(Cost costInfo) {
        this.costInfo = costInfo;
    }
    
    
}
