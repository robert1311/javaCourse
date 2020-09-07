/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.service;

import com.re.flooring.dao.FlooringAuditDao;
import com.re.flooring.dao.FlooringConfigurationDao;
import com.re.flooring.dao.FlooringDao;
import com.re.flooring.dao.FlooringPersistenceException;
import com.re.flooring.dto.Cost;
import com.re.flooring.dto.Order;
import com.re.flooring.dto.Product;
import com.re.flooring.dto.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rober
 */
public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    FlooringDao dao;
    FlooringAuditDao audit;
    FlooringConfigurationDao config;

    public FlooringServiceLayerImpl(FlooringDao dao, FlooringAuditDao audit,
            FlooringConfigurationDao config) {
        this.dao = dao;
        this.audit = audit;
        this.config = config;
    }

    @Override
    public int getConfigNextOrderNumber() throws FlooringPersistenceException {
        return config.getNextOrderNumber();

    }

    @Override
    public boolean getConfigApplicationMode()
            throws FlooringPersistenceException {
        return config.getApplicationMode();
    }

    @Override
    public List<State> getAllStateInfo() {
        return dao.getAllStates();
    }

    @Override
    public List<Product> getAllProductInfo() {
        return dao.getAllProducts();
    }

    @Override
    public Order finalizeOrder(Order newOrder) {

        BigDecimal taxRate = dao.getState(newOrder.getStateInfo()
                .getStateName()).getTaxRate();
        BigDecimal matCostSqFt = dao.getProduct(newOrder
                .getProductInfo().getProductType()).getMatCostPerSqFt();
        BigDecimal labCostSqFt = dao.getProduct(newOrder.getProductInfo()
                .getProductType()).getLabCostPersqft();

        newOrder.getStateInfo().setTaxRate(taxRate);
        newOrder.getProductInfo().setMatCostPerSqFt(matCostSqFt);
        newOrder.getProductInfo().setLabCostPersqft(labCostSqFt);

        calculateCosts(newOrder);

        //Create timestamp
        newOrder.setOrderDate(LocalDate.now());

        return newOrder;
    }

    @Override
    public Order createNewOrder(Order order)
            throws FlooringPersistenceException {
        dao.addOrder(order);
        audit.writeAuditLog("CREATED ORDER: " + order.getOrderDate() + "("
                + order.getOrderNumber() + ") - Name:"
                + order.getFirstName() + " " + order.getLastName() + " - State:"
                + order.getStateInfo().getStateName() + " - TaxRate:"
                + order.getStateInfo().getTaxRate() + "% - Product:"
                + order.getProductInfo().getProductType() + " - MatCost/SqFt:$"
                + order.getProductInfo().getMatCostPerSqFt() + " - LabCost/SqFt$"
                + order.getProductInfo().getLabCostPersqft() + " - Area:"
                + order.getArea() + "SqFt - MatCost:$"
                + order.getCostInfo().getMaterialCost() + " - LabCost:$"
                + order.getCostInfo().getLaborCost() + " - Tax:$"
                + order.getCostInfo().getTax() + " - Total:$"
                + order.getCostInfo().getTotalCost());
        config.setOrderNumber(order.getOrderNumber());
        return order;

    }

    @Override
    public List<Order> getOrdersByDate(String orderDate)
            throws FlooringInvalidDateException {
        LocalDate ld;
        try {
            ld = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        } catch (DateTimeParseException e) {
            throw new FlooringInvalidDateException("ERROR. Enter "
                    + "a valid date.");
        }
        if (ld.isAfter(LocalDate.now())) {
            throw new FlooringInvalidDateException("ERROR. Future order dates "
                    + "not allowed. Enter today's date or a past date.");
        }
        List<Order> orderList = dao.getAllOrder();
        return orderList.stream()
                .filter(o -> o.getOrderDate().equals(ld))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllOrders() {
        return dao.getAllOrder();
    }

    @Override
    public Order getOrder(int orderNumber) throws FlooringNoSuchOrderException {
        
        Order order;
        
        order = dao.getOrder(orderNumber);
        if(order == null){
            throw new FlooringNoSuchOrderException("Order does not exist for "
                    + "the given Order Number. Try Again.");
        }
        return order;
    }

    @Override
    public Order updateOrder(Order updated) throws FlooringPersistenceException {

        boolean isTraining = config.getApplicationMode();

        calculateCosts(updated);
        dao.addOrder(updated);
        if (!isTraining) {//Production Mode
            audit.writeAuditLog("UPDATED ORDER: " + updated.getOrderDate() + "("
                    + updated.getOrderNumber() + ") - Name:"
                    + updated.getFirstName() + " " + updated.getLastName() + " - State:"
                    + updated.getStateInfo().getStateName() + " - TaxRate:"
                    + updated.getStateInfo().getTaxRate() + "% - Product:"
                    + updated.getProductInfo().getProductType() + " - MatCost/SqFt:$"
                    + updated.getProductInfo().getMatCostPerSqFt() + " - LabCost/SqFt$"
                    + updated.getProductInfo().getLabCostPersqft() + " - Area:"
                    + updated.getArea() + "SqFt - MatCost:$"
                    + updated.getCostInfo().getMaterialCost() + " - LabCost:$"
                    + updated.getCostInfo().getLaborCost() + " - Tax:$"
                    + updated.getCostInfo().getTax() + " - Total:$"
                    + updated.getCostInfo().getTotalCost());
        }
        return updated;
    }

    @Override
    public Order removeOrder(int orderNumber) throws FlooringPersistenceException {

        boolean isTraining = config.getApplicationMode();
        Order order = dao.removeOrder(orderNumber);
        if (!isTraining) {
            audit.writeAuditLog("REMOVED ORDER: " + order.getOrderDate() + "("
                    + order.getOrderNumber() + ") - Name:"
                    + order.getFirstName() + " " + order.getLastName() + " - State:"
                    + order.getStateInfo().getStateName() + " - TaxRate:"
                    + order.getStateInfo().getTaxRate() + "% - Product:"
                    + order.getProductInfo().getProductType() + " - MatCost/SqFt:$"
                    + order.getProductInfo().getMatCostPerSqFt() + " - LabCost/SqFt$"
                    + order.getProductInfo().getLabCostPersqft() + " - Area:"
                    + order.getArea() + "SqFt - MatCost:$"
                    + order.getCostInfo().getMaterialCost() + " - LabCost:$"
                    + order.getCostInfo().getLaborCost() + " - Tax:$"
                    + order.getCostInfo().getTax() + " - Total:$"
                    + order.getCostInfo().getTotalCost());
        }
        return order;
    }

    @Override
    public void loadEntities() throws FlooringPersistenceException {
        dao.loadOrders();
        dao.loadProducts();
        dao.loadStates();
    }

    private Order calculateCosts(Order newOrder) {
        //Declare variablesfor calculations
        BigDecimal taxRate = newOrder.getStateInfo().getTaxRate();
        BigDecimal matCostSqFt = newOrder.getProductInfo().getMatCostPerSqFt();
        BigDecimal labCostSqFt = newOrder.getProductInfo().getLabCostPersqft();
        double area = newOrder.getArea();

        //Calculate costs
        BigDecimal laborCost = labCostSqFt.multiply(BigDecimal.valueOf(area));
        BigDecimal materialCost = matCostSqFt.multiply(BigDecimal
                .valueOf(area));
        BigDecimal subTotal = laborCost.add(materialCost);
        BigDecimal taxes = subTotal.multiply(taxRate.divide(BigDecimal
                .valueOf(100)));
        BigDecimal total = subTotal.add(taxes);

        //Instantiate a Cost object and set it to the new Order's Cost object
        Cost cost = new Cost();
        cost.setMaterialCost(materialCost);
        cost.setLaborCost(laborCost);
        cost.setTax(taxes);
        cost.setTotalCost(total);
        newOrder.setCostInfo(cost);
        return newOrder;
    }

    @Override
    public void saveOrders() throws FlooringPersistenceException {
        dao.writeOrders();
    }
}
