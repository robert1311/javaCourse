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
import com.re.flooring.service.FlooringServiceLayer;
import com.re.flooring.ui.FlooringView;
import com.re.flooring.ui.UserIO;
import com.re.flooring.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author rober
 */
public class FlooringController {

    UserIO io = new UserIOConsoleImpl();
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
        boolean isTraining = false;
        try {
            service.loadEntities();
            isTraining = service.getConfigApplicationMode();
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        /*Menu allows user to create, read, update, delete orders; 
        keep cycling back to menu after each user case until the "quit' option 
        is selected*/
        
        while (keepGoing) {
            view.displayApplicationModeBanner(isTraining);
            selection = view.displayMenuAndGetSelection("#");
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
                    io.print("UPDATE ORDER");
                    break;
                case 5:
                    io.print("REMOVE ORDER");
                    break;
                case 6:
                    keepGoing = false;
                    io.print("QUIT");
                    break;
                default:
                    io.print("INVALID ENTRY");
            }
        }
        try{
        service.saveOrders();
        } catch (FlooringPersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
        io.print("GOODBYE!");
    }

    private void createOrder() {
        List<State> stateList = service.getAllStateInfo();
        List<Product> productList = service.getAllProductInfo();
        boolean isTraining;
        int orderNum;
        try {
            isTraining  = service.getConfigApplicationMode();
            orderNum = service.getConfigNextOrderNumber();
        } catch (FlooringPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            return;
        }
        Order newOrder = view.displayGetNewOrderInfo(stateList, productList,
                orderNum);
        Order finalOrder = service.finalizeOrder(newOrder);
        boolean isConfirmed = view.displayFinalOrderAndCommit(finalOrder);
        if(isConfirmed & !isTraining){
            try{
            service.createNewOrder(finalOrder);
            service.saveOrders();
            view.displayCreateOrderSuccessBanner();
            } catch(FlooringPersistenceException e){
                view.displayErrorMessage(e.getMessage());
            }
        } else if(!isConfirmed){
            view.displayCancelCreateOrderBanner();
        } else if(isTraining){
            view.displayOrderNotCreatedBanner();
        }
    }
    
    private void getOrderByOrderNumber(){
        int orderNum = view.getOrderNumber();
        Order order = service.getOrder(orderNum);
        int operation = view.displayOrderInfoAndGetOperation(order);
        orderOptions(operation);
    }
    
    private void getOrdersByDate(){
        boolean hasErrors;
        String date;
        List<Order> orderList = null;
        do{
            hasErrors = false;
            date = view.getDateInput();
            try{
            orderList = service.getOrdersByDate(date);
            } catch(FlooringInvalidDateException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());;
            }
        } while(hasErrors);
        int operation = view.displayOrdersByDate(date, orderList);
        orderOptions(operation);
    }
    
    private void orderOptions(int operation){
        switch(operation){
            case 1:
                //Edit
                break;
            case 2:
                //Remove
                break;
            case 3:
                break;
            default:
                view.displayErrorMessage("Invalid Selection");
                break;
        }
    }

}
