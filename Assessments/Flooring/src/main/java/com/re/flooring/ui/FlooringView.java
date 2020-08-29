/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.ui;

import com.re.flooring.dto.Order;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import java.util.List;

/**
 *
 * @author rober
 */
public class FlooringView {

    UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int displayMenuAndGetSelection(String x) {
        io.print(x + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " ");
        int selection = io.readInt("" + x + " Choose an operation from the following.\n"
                + x + "\n"
                + x + " 1) Create Order\n"
                + x + " 2) Get Order\n"
                + x + " 3) Get Orders By Date\n"
                + x + " 4) Update Order\n"
                + x + " 5) Remove Order\n"
                + x + " 6) Save & Quit\n"
                + x + "\n"
                + x + " " + x + " " + x + " " + x + " " + x + " " + x + " "
                + x + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " ", 1, 6);
        return selection;
    }

    public Order displayGetNewOrderInfo(List<State> stateList,
            List<Product> productList) {
        boolean hasErrors;
        String customerName;
        String stateEntry;
        String productEntry;
        double area;

        io.print("All fields must be filled in before an order can be "
                + "created.");
        do{
        customerName = io.readString("Enter customer's name.");
        } while(customerName.isBlank());
        do {
            hasErrors = true;
            stateEntry = io.readString("Enter state code for the customer's "
                    + "order from the list of eligible states.");
            stateList.forEach((state) -> {
                io.print(state.getStateName());
            });
            for(State state : stateList){
                if(state.getStateName().equalsIgnoreCase(stateEntry
                        .toUpperCase())){
                    hasErrors = false;
                    break;
                }
            }
            if(hasErrors){
                io.print("===Invalid Entry===");
            }
        } while (hasErrors);

        
        do {
            hasErrors = false;
            productEntry = io.readString("Enter product type for the customer's "
                    + "order from the list of eligible products.");
            productList.forEach((product) -> {
                io.print(product.getProductType());
            });
            for(Product product : productList){
                if(product.getProductType().equalsIgnoreCase(stateEntry
                        .toUpperCase())){
                    hasErrors = false;
                    break;
                }
            }
            if(hasErrors){
                io.print("===Invalid Entry===");
            }
        } while (hasErrors);

        area = io.readDouble("Enter the area amount to be covered. (in sqFt)"
                , 0, 10000);
        
        Order newOrder = new Order(customerName, stateEntry, productEntry);
        return newOrder;
    }

}
