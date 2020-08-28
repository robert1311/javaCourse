/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dto;

import java.util.Objects;

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
            String stateName){
        this.orderNumber = orderNumber;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.orderNumber;
        hash = 79 * hash + Objects.hashCode(this.customerName);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.area) ^ (Double.doubleToLongBits(this.area) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.productInfo);
        hash = 79 * hash + Objects.hashCode(this.stateInfo);
        hash = 79 * hash + Objects.hashCode(this.costInfo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (Double.doubleToLongBits(this.area) != Double.doubleToLongBits(other.area)) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.productInfo, other.productInfo)) {
            return false;
        }
        if (!Objects.equals(this.stateInfo, other.stateInfo)) {
            return false;
        }
        if (!Objects.equals(this.costInfo, other.costInfo)) {
            return false;
        }
        return true;
    }
    
    
}
