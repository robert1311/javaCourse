/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.controller;

import com.re.flooring.dao.FlooringPersistenceException;
import com.re.flooring.dto.Order;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import com.re.flooring.service.FlooringInvalidDateException;
import com.re.flooring.service.FlooringNoSuchOrderException;
import com.re.flooring.service.FlooringServiceLayer;
import com.re.flooring.ui.FlooringView;
import java.util.List;

/**
 *
 * @author rober
 */
public class FlooringController {

    FlooringServiceLayer service;
    FlooringView view;

    public FlooringController(FlooringServiceLayer service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    /*runs a program used to manage flooring orders through CRUD operations*/
    public void run() {
        boolean keepGoing = true;
        int selection;
        boolean isTraining = applicationMode();

        /*Menu allows user to create, read, update, delete orders; 
        keep cycling back to menu after each user case until the Exit option 
        is selected*/
        while (keepGoing) {
            selection = menuFrameAndGetSelection(isTraining);
            switch (selection) {
                case 1:
                    createOrder();
                    break;
                case 2:
                    getOrderByOrderNumber();
                    break;
                case 3:
                    getOrdersByDate();
                    break;
                case 4:
                    updateOrder();
                    break;
                case 5:
                    removeOrder();
                    break;
                case 6:
                    saveWork();
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    invalidEntry();
                    break;
            }
        }
        exit(isTraining);
    }

    private void createOrder() {
        List<State> stateList = service.getAllStateInfo();
        List<Product> productList = service.getAllProductInfo();
        boolean isTraining;
        int orderNum;
        try {
            isTraining = service.getConfigApplicationMode();
            orderNum = service.getConfigNextOrderNumber();
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            return;
        }
        Order newOrder = view.displayGetNewOrderInfo(stateList, productList,
                orderNum);
        Order finalOrder = service.finalizeOrder(newOrder);
        boolean isConfirmed = view.displayFinalOrderAndCommit(finalOrder);
        if (isConfirmed & !isTraining) {
            try {
                service.createNewOrder(finalOrder);
                boolean isSaving = view.promptToSaveWork();
                if (isSaving) {
                    service.saveOrders();
                    view.displayCreateOrderSuccessBanner();
                } else {
                    view.createdNotSavedBanner();
                }
            } catch (FlooringPersistenceException e) {
                view.displayErrorMessage(e.getMessage());
            }
        } else if (!isConfirmed) {
            view.displayCancelCreateOrderBanner();
        } else if (isTraining) {
            view.displayApplicationModeBanner(isTraining);
            view.displayOrderNotCreatedBanner();
        }
    }

    private void getOrderByOrderNumber() {
        Order order = getOrderCore();
        if (order == null) {

        } else {
            int operation = view.displayOrderInfoAndGetOperation(order);
            orderOptions(operation, order);
        }
    }

    private void getOrdersByDate() {
        boolean hasErrors;
        String date;
        List<Order> orderList = null;
        Order selected = null;
        int dateOperation;
        do {
            hasErrors = false;
            date = view.getDateInput();
            try {
                orderList = service.getOrdersByDate(date);
                selected = view.displayOrdersByDate(date, orderList);
            } catch (FlooringInvalidDateException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
                dateOperation = view.getDateOperation();
                if (dateOperation == 2) {
                    hasErrors = false;
                }
            }
        } while (hasErrors);

        if (selected != null) {
            int operation = view.displayOrderInfoAndGetOperation(selected);
            orderOptions(operation, selected);
        } else {

        }
    }

    private void updateOrder() {//load orders again after cancelled update
        Order initial = getOrderCore();
        if (initial == null) {

        } else {
            view.displayOrderFrame(initial);
            updateOrderCore(initial);
        }
    }

    public void removeOrder() {
        Order order = getOrderCore();
        if (order == null) {

        } else {
            removeOrderCore(order);
        }
    }

    private void saveWork() {
        try {
            service.saveOrders();
            view.workSavedBanner();
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private boolean applicationMode() {
        boolean isTraining = false;
        try {
            service.loadEntities();
            isTraining = service.getConfigApplicationMode();
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        return isTraining;
    }

    private int menuFrameAndGetSelection(boolean isTraining) {
        view.displayApplicationModeBanner(isTraining);
        return view.displayMenuAndGetSelection();
    }

    private void invalidEntry() {
        view.invalidEntryMessage();
    }

    private void exit(boolean isTraining) {
        if (!isTraining) {
            try {
                service.saveOrders();
            } catch (FlooringPersistenceException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
        view.exitMessage(isTraining);
    }

    ///////////////////////////Class Only Methods//////////////////////////////
    private void orderOptions(int operation, Order order) {
        switch (operation) {
            case 1:
                updateOrderCore(order);
                break;
            case 2:
                removeOrderCore(order);
                break;
            case 3:
                break;
            default:
                view.displayErrorMessage("Invalid Selection");
                break;
        }
    }

    private Order getOrderCore() {
        boolean hasErrors;
        Order order = null;
        do {
            hasErrors = false;
            int orderNum = view.getOrderNumber();
            if (orderNum == 0) {//Exit

            } else {//get Order
                try {
                    order = service.getOrder(orderNum);
                } catch (FlooringNoSuchOrderException e) {
                    hasErrors = true;
                    view.displayErrorMessage(e.getMessage());
                }
            }
        } while (hasErrors);

        return order;
    }

    private Order updateOrderCore(Order order) {
//        Order initial = order;
        Order edited;
        Order finalEdit;
        boolean keepEditing;
        boolean isTraining;
        List<State> stateList = service.getAllStateInfo();
        List<Product> productList = service.getAllProductInfo();

        try {
            isTraining = service.getConfigApplicationMode();
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            return order;
        }

        do {
            keepEditing = false;
            edited = view.editOrderForm(stateList, productList, order);
            finalEdit = service.finalizeOrder(edited);
            int operation = view.updatedOrderAndSelectOperation(edited);
            switch (operation) {
                case 1://update
                    try {
                        service.updateOrder(finalEdit);
                        if (isTraining) {
                            view.displayApplicationModeBanner(isTraining);
                        }
                        view.orderSuccessfullyUpdatedBanner();
                    } catch (FlooringPersistenceException e) {
                        view.displayErrorMessage(e.getMessage());
                    }
                    keepEditing = false;
                    break;
                case 2://keepEditing
                    keepEditing = true;
                    try {
                        service.loadEntities();
                        order = service.getOrder(order.getOrderNumber());
                    } catch (FlooringPersistenceException | FlooringNoSuchOrderException e) {
                        view.displayErrorMessage(e.getMessage());
                    }
                    break;
                case 3://Exit
                    keepEditing = false;
                    try {
                        service.loadEntities();
                    } catch (FlooringPersistenceException e) {
                        view.displayErrorMessage(e.getMessage());
                    }
            }
        } while (keepEditing);
        return finalEdit;
    }

    private Order removeOrderCore(Order order) {
        int orderNum = order.getOrderNumber();
        boolean isConfirmed = view.isConfirmedRemove(order);
        boolean isTraining;
        try {
            isTraining = service.getConfigApplicationMode();
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            return order;
        }
        if (isConfirmed) {//Production Mode
            try {
                service.removeOrder(orderNum);
                if (isTraining) {
                    view.displayApplicationModeBanner(isTraining);
                }
                view.orderSuccessfullyRemovedBanner();
            } catch (FlooringPersistenceException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
        return order;
    }

}
