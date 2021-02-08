/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.dvdlibrary.controller;

import com.re.dvdlibrary.dao.DvdLibraryDao;
import com.re.dvdlibrary.dao.DvdLibraryDaoException;
import com.re.dvdlibrary.dto.Dvd;
import com.re.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author Dev10
 */
public class DvdLibraryController {
    public DvdLibraryController(DvdLibraryDao dao,DvdLibraryView view){
        this.dao = dao;
        this.view = view;
    }
       
        
        DvdLibraryView view;// 
        //in order to run view method to return user selection*/
        DvdLibraryDao dao;//
        
        //DvdLibraryDaoFileImpl file;
        public void run() {
            
            boolean keepGoing = true; //ensures Menu is displayed in while loop
            /////Remember to clean up comments!!!/////
            int menuSelection = 0;
            try { 
                dao.loadLibrary();
               //file.loadLibrary();
            while (keepGoing){
                
                menuSelection = getMenuSelection();
                
                switch (menuSelection){/*user selection used for switch 
                        statement*/
                    case 1:
                        addDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvdInfo();
                        break;
                    case 4:
                        listDvds();
                        break;
                    case 5:
                        displayDvdInfo();
                        break;  
                    case 6:
                        searchDvd();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownMessage();
                        
                }//end of switch
                
            }//end of while loop
            dao.writeLibrary();
            exitMessage();
        } catch (DvdLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
            }
        }
        /*Create method outside of run method to call view class and its method 
        to return user's selection as our menuselection variable in the 
        while loop*/
        private int getMenuSelection(){//created method that returns what the 
                //view method returns (users choice)
            return view.printMenuAndGetSelection();
        }
        //Add Dvd method
        private void addDvd() throws DvdLibraryDaoException {
            view.displayAddDvdBanner();
            Dvd newDvd = view.getNewDvdInfo();//calls method to enter DVD info
            dao.addDvd(newDvd.getTitle(), newDvd);/*puts dvd object with
            associated title into dvds HashMap*/
            view.displayAddSuccessBanner();
        }
        
        //List All DVDs in Library method
        private void listDvds() throws DvdLibraryDaoException{
            view.DisplayAllBanner();
            List<Dvd> DvdList = dao.getAllDvds();
            view.DispayDvdList(DvdList);
        }
        
        //Display DVD information
        private void displayDvdInfo() throws DvdLibraryDaoException{
            view.displayDisplayDvdBanner();
            String title = view.getDvdTitleChoice();
            Dvd dvd = dao.displayDvdInfo(title);
            view.displayDvdInfo(dvd);
        }
        
        //method to remove DVD process
        private void removeDvd() throws DvdLibraryDaoException{
            view.displayRemoveDvdBanner();
            String title = view.getDvdTitleChoice();
            dao.removeDvd(title);
            view.displayRemoveDvdSuccessBanner();
        }
        
        //method to start edit DVD process
        private void editDvdInfo() throws DvdLibraryDaoException{
            
            view.displayEditDvdBanner();
           
            String userTitle = view.getDvdTitleChoice();
            //String oTitle = dao.editDvd(title);
            Dvd currentDvd = dao.editDvd(userTitle);
            view.displayDvdInfo(currentDvd);
            view.DisplayEditDvdInfo(currentDvd);
            view.displayEditSuccessBanner();
        }
        
        //method to start Search process
        private void searchDvd() throws DvdLibraryDaoException{
            view.displaySearchBanner();
            String title = view.getDvdTitleChoice();
            Dvd dvd = dao.searchDvd(title);
            view.searchDvdTitle(dvd);
        }
        
        
        
        //Uknown command message
        private void unknownMessage(){
            view.displayUnknownCommandBanner();
        }
        
        //exit message method
        private void exitMessage(){
            view.displayExitBanner();
        }
        
      
}
