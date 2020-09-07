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

    public void displayApplicationModeBanner(boolean isTraining) {
        if (isTraining) {
            io.print("\n====================== TRAINING MODE ======================\n");
        } else {
            io.print("\n===================== PRODUCTION MODE ======================\n");
        }

    }

    public int displayMenuAndGetSelection() {
        String x = "$";
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
        boolean keepGoing;

        do {
            keepGoing = false;
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
                hasErrors = true;
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
            int operation = io.readInt("1) Continue?\n"
                    + "2) Start Over?", 1, 2);
            if (operation == 2) {
                keepGoing = true;
            }
        } while (keepGoing);
        //Create new Order and set values
        String lowerFirst = firstName.toLowerCase();
        String lowerLast = lastName.toLowerCase();
        String capFirstName = lowerFirst.replace(lowerFirst
                .substring(0, 1), lowerFirst.substring(0, 1).toUpperCase());
        String capLastName = lowerLast.replace(lowerLast
                .substring(0, 1), lowerLast.substring(0, 1).toUpperCase());
        Order newOrder = new Order(nextOrderNum, stateEntry, productEntry);
        newOrder.setFirstName(capFirstName);
        newOrder.setLastName(capLastName);
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
        io.print("\n===ORDER CREATION CANCELLED ===\n");
    }

    public void displayOrderNotCreatedBanner() {
        io.print("\n===ORDER NOT CREATED ===\n");
    }

    public int getOrderNumber() {
        return io.readInt("\nEnter an order number.\n"
                + "(Enter 0 to Exit to the Main Menu)");
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

    public int getDateOperation() {
        return io.readInt("1) Try Again?\n"
                + "2) Exit to Main Menu.", 1, 2);
    }

    public Order displayOrdersByDate(String date, List<Order> orderList) {
        boolean hasErrors;
        int orderNum;
        int listSize = orderList.size();
        int[] orderNumbers;
        Order selected = null;

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
                    selected = order;
//                    return this.displayOrderInfoAndGetOperation(order);
                }
            }

        } else {// X to exit

        }
        return selected;
    }

    public Order editOrderForm(List<State> stateList, List<Product> productList,
            Order order) {

        String tempFirstName = order.getFirstName();
        String tempLastName = order.getLastName();
        String tempStateName = order.getStateInfo()
                .getStateName();
        String tempProductType = order.getProductInfo().getProductType();
        double tempArea = order.getArea();
        String editFirstName = null;
        String editLastName = null;
        String editStateName = null;
        String editProductType = null;
        double editArea = 0;
        boolean keepGoing = true;

        io.print("\n===Edit Order===\n");

        while (keepGoing) {
            io.print("Edit Order by entering data under each field of the "
                    + "retrieved order.\nLeave blank and press enter under each "
                    + "field to leave it as is.\n");

            io.print("Order Number: " + order.getOrderNumber() + "\n"
                    + "===============");

            //Edit First Name
            editFirstName = io.readString("\nFirst name: " + tempFirstName);
            if (editFirstName.isBlank()) {
                editFirstName = tempFirstName;
            }

            //Edit Last Name
            editLastName = io.readString("\nLast Name: " + tempLastName);
            if (editLastName.isBlank()) {
                editLastName = tempLastName;
            }

            //Edit State
            stateList.forEach((state) -> {
                io.print(state.getStateName());
            });
            editStateName = io.readString("State: " + tempStateName);
            if (editStateName.isBlank()) {
                editStateName = tempStateName;
            }

            //Edit Product
            productList.forEach((product) -> {
                io.print(product.getProductType());
            });
            editProductType = io.readString("Product: " + tempProductType);
            if (editProductType.isBlank()) {
                editProductType = tempProductType;
            }

            //Edit Area
            boolean hasErrors;
            String areaString;
            do {
                hasErrors = false;
                areaString = io.readString("Area: " + order.getArea());
                if (areaString.isBlank()) {
                    editArea = tempArea;
                } else {
                    try {
                        editArea = Double.parseDouble(areaString);
                    } catch (NumberFormatException e) {
                        hasErrors = true;
                        io.print("Invalid input. Enter a number value.");
                    }
                }
            } while (hasErrors);
            String done = io.readString("Done editing? (y/n)");
            if (done.equalsIgnoreCase("y")) {
                keepGoing = false;
            }
        }
        order.setFirstName(editFirstName);
        order.setLastName(editLastName);
        order.getStateInfo().setStateName(editStateName.toUpperCase());
        order.getProductInfo().setProductType(editProductType.substring(0, 1).toUpperCase()
                + editProductType.substring(1).toLowerCase());
        order.setArea(editArea);
        return order;
    }

    public int updatedOrderAndSelectOperation(Order updated) {
        displayOrderFrame(updated);
        return io.readInt("\n================================\n"
                + "1) Save & Update Order\n"
                + "2) Edit Order\n"
                + "3) Cancel Changes\n", 1, 3);
    }

    public void orderSuccessfullyUpdatedBanner() {
        io.print("\n===ORDER SUCCESSFULLY UPDATED===\n");
    }

    public void displayErrorMessage(String message) {
        io.print(message);
    }

    public boolean isConfirmedRemove(Order order) {
        displayOrderFrame(order);
        String confirm = io.readString("Confirm remove? (y/n)");
        return confirm.equalsIgnoreCase("y");
    }

    public void orderSuccessfullyRemovedBanner() {
        io.print("\n===ORDER SUCCESSFULLY REMOVED===\n");
    }

    public void displayOrderFrame(Order order) {
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

    public void invalidEntryMessage() {
        io.print("Invalid Entry. Try Again.");
    }

    public void exitMessage(boolean isTraining) {
        if (isTraining) {
            io.print("\nProgress NOT Saved!\n"
                    + "- Training Mode\n"
                    + "\n=== GOOD BYE!!! ===\n");
        } else {
            io.print("Progress Saved!");
            io.print("=== GOOD BYE!!! ===");
        }
    }

}
