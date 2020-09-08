/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring;

import com.re.flooring.controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rober
 */
public class App {
    public static void main(String[] args) {
//        UserIO myIO = new UserIOConsoleImpl();
//        FlooringView myView = new FlooringView(myIO);
//        FlooringDao myDao = new FlooringDaoFileImpl();
//        FlooringAuditDao myAudit = new FlooringAuditDaoFileImpl();
//        FlooringConfigurationDao myConfig = 
//                new FlooringConfigurationDaoFileImpl();
//        FlooringServiceLayer myService = 
//                new FlooringServiceLayerImpl(myDao, myAudit, myConfig);
//        FlooringController controller = new FlooringController(myService, 
//                myView);
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    FlooringController controller = 
            ctx.getBean("controller", FlooringController.class);
        controller.run();
    }
}
