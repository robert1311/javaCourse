/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author rober
 */
public class Order {
    private int orderNumber;
    private String firstName;
    private String lastName;
    private double area;
    private Product productInfo;
    private State stateInfo;
    private Cost costInfo;
    private LocalDate orderDate;

   

    public Order(int orderNumber, String productType, String stateName){
        this.orderNumber = orderNumber;
        productInfo = new Product(productType);
        stateInfo = new State(stateName);
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

//    public void setOrderNumber(int orderNumber) {//handle in SL
//        this.orderNumber = orderNumber;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

     public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.orderNumber;
        hash = 17 * hash + Objects.hashCode(this.firstName);
        hash = 17 * hash + Objects.hashCode(this.lastName);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.area) ^ (Double.doubleToLongBits(this.area) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.productInfo);
        hash = 17 * hash + Objects.hashCode(this.stateInfo);
        hash = 17 * hash + Objects.hashCode(this.costInfo);
        hash = 17 * hash + Objects.hashCode(this.orderDate);
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
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
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
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        return true;
    }

   
}
