/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine;

import com.re.vendingmachine.controller.VendingMachineController;
import com.re.vendingmachine.dao.VendingMachineDao;
import com.re.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.re.vendingmachine.ui.UserIO;
import com.re.vendingmachine.ui.UserIOConsoleImpl;
import com.re.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author rober
 */
public class App {

    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineController controller
                = new VendingMachineController(myView, myDao);
        controller.run();
    }
}
