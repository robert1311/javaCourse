/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.dvdlibrary;

import com.re.dvdlibrary.controller.DvdLibraryController;
import com.re.dvdlibrary.dao.DvdLibraryDao;
import com.re.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.re.dvdlibrary.ui.DvdLibraryView;
import com.re.dvdlibrary.ui.UserIO;
import com.re.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author Dev10
 */
public class App {
     public static void main(String[] args) {/* instantiate Controller*/
        UserIO myIo = new UserIOConsoleImpl();//Depeency Injection
        DvdLibraryView myView = new DvdLibraryView(myIo); 
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(myDao,myView);
        controller.run();//call run method */
     }
}
