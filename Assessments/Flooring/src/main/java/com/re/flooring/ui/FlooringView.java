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
        
        io.print("\nAll fields must be filled in before an order can be "
                + "created.\n");
        //Enter non-blank Customer Name
        do{
        customerName = io.readString("Enter customer's name.");
        } while(customerName.isBlank());
        
        //Enter valid State
        do {
            hasErrors = true;
             io.print("\nEnter state code for the customer's "
                    + "order from the list of eligible states.\n"
                    + "========");
            stateList.forEach((state) -> {
                io.print(state.getStateName());
            });
           
            stateEntry = io.readString("");
            for(State state : stateList){
                if(state.getStateName().equalsIgnoreCase(stateEntry
                        .toUpperCase())){
                    hasErrors = false;
                    break;
                }
            }
            if(hasErrors){
                io.print("\n===Invalid Entry===\n");
            }
        } while (hasErrors);
        
        //Enter valid Product type
        do {
            hasErrors = false;
            io.print("Enter product type for the customer's "
                    + "order from the list of eligible products.\n"
                    + "========");
            productList.forEach((product) -> {
                io.print(product.getProductType());
            });
            
            productEntry = io.readString("");
            for(Product product : productList){
                if(product.getProductType().equalsIgnoreCase(productEntry
                        .toUpperCase())){
                    hasErrors = false;
                    break;
                }
            }
            if(hasErrors){
                io.print("\n===Invalid Entry===\n");
            }
        } while (hasErrors);

        //Enter Area to be covered
        area = io.readDouble("\nEnter the area amount to be covered. (in sqFt)\n"
                + "(min 10 SqFt - max 10,000)"
                , 10, 10000);
        
        //Create new Order and set values
        Order newOrder = new Order(customerName, stateEntry, productEntry);
        newOrder.setArea(area);
        return newOrder;
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }
}
