/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.ui;

import com.re.flooring.dto.Order;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rober
 */
public class FlooringView {

    UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public void displayApplicationModeBanner(boolean isTraining) {
        if (isTraining) {
            io.print("\n====================== TRAINING MODE ======================");
        } else {
            io.print("\n====================== PRODUCTION MODE ======================");
        }

    }

    public int displayMenuAndGetSelection(String x) {
        io.print("\n" + x + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " " + x + " " + x + " " + x + " " + x + " " + x
                + " " + x + " ");
        int selection = io.readInt("" + x + " Choose an operation from the following.\n"
                + x + "\n"
                + x + " 1) Create Order\n"
                + x + " 2) Get Order By Order Number\n"
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
            List<Product> productList, int nextOrderNum) {
        boolean hasErrors;
        String firstName;
        String lastName;
        String stateEntry;
        String productEntry;
        double area;

        io.print("\nAll fields must be filled in before an order can be "
                + "created.\n");
        //Enter non-blank Customer Name
        do {
            firstName = io.readString("\nEnter customer's First Name.");
        } while (firstName.isBlank());
        do {
            lastName = io.readString("\nEnter customer's Last Name.");
        } while (lastName.isBlank());

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
            for (State state : stateList) {
                if (state.getStateName().equalsIgnoreCase(stateEntry)) {
                    stateEntry = stateEntry.toUpperCase();
                    hasErrors = false;
                    break;
                }
            }
            if (hasErrors) {
                io.print("\n===Invalid Entry===\n");
            }
        } while (hasErrors);

        //Enter valid Product type
        do {
            hasErrors = false;
            io.print("\nEnter product type for the customer's "
                    + "order from the list of eligible products.\n"
                    + "========");
            productList.forEach((product) -> {
                io.print(product.getProductType());
            });

            productEntry = io.readString("");
            for (Product product : productList) {
                if (product.getProductType().equalsIgnoreCase(productEntry)) {
                    productEntry = productEntry.substring(0, 1).toUpperCase()
                            + productEntry.substring(1).toLowerCase();

                    hasErrors = false;
                    break;
                }
            }
            if (hasErrors) {
                io.print("\n===Invalid Entry===\n");
            }
        } while (hasErrors);

        //Enter Area to be covered
        area = io.readDouble("\nEnter the area amount to be covered. (in sqFt)\n"
                + "(min 10 SqFt - max 10,000)",
                10, 10000);

        //Create new Order and set values
        Order newOrder = new Order(nextOrderNum, stateEntry, productEntry);
        newOrder.setFirstName(firstName);
        newOrder.setLastName(lastName);
        newOrder.setArea(area);
        newOrder.getStateInfo().setStateName(stateEntry);
        newOrder.getProductInfo().setProductType(productEntry);
        return newOrder;
    }

    public boolean displayFinalOrderAndCommit(Order finalOrder) {
        io.print("\nReview Order. Press 'S' to save and finalize order; \n"
                + "Press 'C' to cancel.\n");
        displayOrderFrame(finalOrder);
        return io.readString("").equalsIgnoreCase("s");

    }

    public void displayCreateOrderSuccessBanner() {
        io.print("\n===ORDER SUCCESSFULLY CREATED===\n");
    }

    public void displayCancelCreateOrderBanner() {
        io.print("\n===ORDER CREATION CANCELLED===");
    }

    public void displayOrderNotCreatedBanner() {
        io.print("\n===ORDER NOT CREATED===\n");
    }

    public int getOrderNumber() {
        return io.readInt("\nEnter an order number.");
    }

    public int displayOrderInfoAndGetOperation(Order order) {
        displayOrderFrame(order);
        return io.readInt("What would you like to do?\n"
                + "1) Edit\n"
                + "2) Remove\n"
                + "3) Exit to Main Menu.", 1, 3);
    }

    public String getDateInput() {
        return io.readString("Enter a date (in the format of MM/dd/yyyy)");
    }

    public int displayOrdersByDate(String date, List<Order> orderList) {
        boolean hasErrors;
        int orderNum;
        int listSize = orderList.size();
        int[] orderNumbers;

        io.print("Orders for " + date
                + "\n================================\n");
        orderList.stream()
                .forEach(o -> io.print(o.getOrderNumber() + ": "
                + o.getFirstName() + " " + o.getLastName()));
        String option = io.readString("Press 'V' to view an order from the list"
                + " or 'X' to exit to main menu.");
        if (option.equalsIgnoreCase("v")) {
            orderNumbers = orderList.stream()
                    .mapToInt(o -> o.getOrderNumber())
                    .toArray();

            do {
                hasErrors = true;
                orderNum = io.readInt("Enter Order Number to view that Order");
                for (int i = 0; i < listSize; i++) {
                    if (orderNum == orderNumbers[i]) {
                        hasErrors = false;
                        break;
                    }
                }
            } while (hasErrors);
            for (Order order : orderList) {
                if (order.getOrderNumber() == orderNum) {
                    return this.displayOrderInfoAndGetOperation(order);
                }
            }

        } else {

        }
        return 0;
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }

    private void displayOrderFrame(Order order) {
        io.print("\n================================\n"
                + "Name: " + order.getFirstName() + " "
                + order.getLastName() + "\n"
                + "Order Number: " + order.getOrderNumber() + "\n"
                + "State: " + order.getStateInfo().getStateName() + "\n"
                + "Tax Rate: " + order.getStateInfo().getTaxRate() + "%\n"
                + "Product Type: " + order.getProductInfo()
                        .getProductType() + "\n"
                + "Material Cost/sqFt: $" + order.getProductInfo()
                        .getMatCostPerSqFt() + "\n"
                + "Labor Cost/sqFt: $" + order.getProductInfo()
                        .getLabCostPersqft() + "\n"
                + "Area (sqFt): " + order.getArea() + "\n"
                + "Material Cost: $" + order.getCostInfo()
                        .getMaterialCost() + "\n"
                + "Labor Cost: $" + order.getCostInfo()
                        .getLaborCost() + "\n"
                + "Tax: $" + order.getCostInfo().getTax() + " \n"
                + "---------------------------------\n"
                + "Total: $" + order.getCostInfo().getTotalCost() + "\n");
    }
}
