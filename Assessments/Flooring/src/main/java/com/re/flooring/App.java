/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring;

import com.re.flooring.controller.FlooringController;
import com.re.flooring.dao.FlooringAuditDao;
import com.re.flooring.dao.FlooringAuditDaoFileImpl;
import com.re.flooring.dao.FlooringConfigurationDao;
import com.re.flooring.dao.FlooringConfigurationDaoFileImpl;
import com.re.flooring.dao.FlooringDao;
import com.re.flooring.dao.FlooringDaoFileImpl;
import com.re.flooring.service.FlooringServiceLayer;
import com.re.flooring.service.FlooringServiceLayerImpl;
import com.re.flooring.ui.FlooringView;
import com.re.flooring.ui.UserIO;
import com.re.flooring.ui.UserIOConsoleImpl;

/**
 *
 * @author rober
 */
public class App {
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        FlooringView myView = new FlooringView(myIO);
        FlooringDao myDao = new FlooringDaoFileImpl();
        FlooringAuditDao myAudit = new FlooringAuditDaoFileImpl();
        FlooringConfigurationDao myConfig = 
                new FlooringConfigurationDaoFileImpl();
        FlooringServiceLayer myService = 
                new FlooringServiceLayerImpl(myDao, myAudit, myConfig);
        FlooringController controller = new FlooringController(myService, 
                myView);
        controller.run();
    }
}
