/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.dvdlibrary.ui;

import com.re.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author Dev10
 */
public class DvdLibraryView {
    public DvdLibraryView(UserIO io){
        this.io = io;
    }
    
    UserIO io;// = new UserIOConsoleImpl();
    
    public int printMenuAndGetSelection(){
    
                io.print("Main Menu");
                io.print("1. Add DVD");
                io.print("2. Remove DVD");
                io.print("3. Edit information in existing DVD");
                io.print("4. List DVDs in collection");
                io.print("5. Display information for DVD");
                io.print("6. Search for DVD by title");
                io.print("7. Exit");
                
                return io.readInt( "Please select from the above "
                        + "choices", 1, 7);/* Will prompt for selection and get
                        read in by io object*/
    }
    //Method for adding new Dvd info
    public Dvd getNewDvdInfo(){
        String title = io.readString("Enter title");
        String date = io.readString("Enter release date");
        String rating = io.readString("Enter MPAA rating");
        String dirName = io.readString("Enter director's Name");
        String studio = io.readString("Enter studio name");
        String note = io.readString("Enter User rating or note");
        
        Dvd currentDvd = new Dvd(title);
        
        currentDvd.setDate(date);
        currentDvd.setRating(rating);
        currentDvd.setDirName(dirName);
        currentDvd.setStudio(studio);
        currentDvd.setNote(note);
        
        return currentDvd;
    } 
    public void displayAddDvdBanner(){
        io.print("=== Add DVD ===");
    }  
    public void displayAddSuccessBanner(){
        io.readString("DVD sucessfully added. Please hit enter to continue");
    }
    
    //Create Method for listing All DVDs in ibrary
    public void DispayDvdList(List<Dvd> DvdList){
        for(Dvd currentDvd : DvdList){
            io.print(currentDvd.getTitle() + ": " + currentDvd.getDate()
            + " | " + currentDvd.getRating() + " | " 
            + currentDvd.getDirName() + " | " + currentDvd.getStudio() 
            + " | " + currentDvd.getNote());    
        }
    }
    public void DisplayAllBanner(){
        io.print("===Display All Dvds ===");
    }    
    
    //Methods to search DVD and display info
    public void displayDisplayDvdBanner(){
        io.print("===Display DVD===");
    }
    public String getDvdTitleChoice(){//determines specific DVD to be displayed 
        return io.readString("Please enter DVD title");
         
    }
    public void displayDvdInfo(Dvd dvd){//Maybe combine search, display, and edit
        if (dvd != null){
            io.print(dvd.getTitle());
            io.print(dvd.getDate());
            io.print(dvd.getRating());
            io.print(dvd.getDirName());
            io.print(dvd.getStudio());
            io.print(dvd.getNote());
        } else {
            io.print("No such Dvd exists in your library");
        }
        io.readString("Please hit enter to continue");
    }
    
    //Methods for removing DVD
    public void displayRemoveDvdBanner(){
        io.print("===Remove DVD===");
    }
    public void displayRemoveDvdSuccessBanner(){
        io.readString("Dvd successfully removed. Pleae hit enter to continue");
    }
    
    //Methods for user to search for DVD
    public void searchDvdTitle(Dvd dvd){
        if (dvd != null){
            io.print("Yes! The DVD " + dvd.getTitle() + " is in your "
                    + "library");
        } else {
            io.print("No such Dvd exists in your library");
        }
        io.readString("Please hit enter to continue");
    }
    public void displaySearchBanner(){
        io.print("===Search===");
    }
    
    //methods for user to edit DVD information
    public void DisplayEditDvdInfo(Dvd dvd){
        Dvd editedDvd = dvd;
        String answer = io.readString("Would you like to edit any information (y/n?");
         if (dvd != null & answer.equals("y")){ 
               
            io.print(dvd.getTitle());
            String eTitle = io.readString("Edit Title (y/n)?");
            if (eTitle.equals("y")){
            String newTitle = io.readString("What's the new Title");
            dvd.setTitle(newTitle);
            }
             
            io.print(dvd.getDate());
            String eDate = io.readString("Edit Release Date (y/n)?");
            if (eDate.equals("y")){
            String newDate = io.readString("What's the new release date?");
            dvd.setDate(newDate);
            }
           
            io.print(dvd.getRating());
            String eRating = io.readString("Edit MPAA rating (y/n)?");
            if (eRating.equals("y")){
            String newRating = io.readString("What's the new rating?");
            dvd.setRating(newRating);
            }
           
            io.print(dvd.getDirName());
            String eDirName = io.readString("Edit director's name (y/n)?");
            if (eDate.equals("y")){
            String newDirName = io.readString("Whats the  new director's name");
            dvd.setDirName(newDirName);
            }
           
            io.print(dvd.getStudio());
            String eStudio = io.readString("Edit Studio name? (y/n)");
            if (eStudio.equals("y")){
            String newStudio = io.readString("What's the  new Studio name?");
            dvd.setStudio(newStudio);
            }
            
            io.print(dvd.getNote());
            String eNote = io.readString("Edit User rating or note? (y/n)");
            if (eNote.equals("y")){
            String newNote = io.readString("What changes do you want to make to "
                    + "your notes");
            dvd.setNote(newNote);
            }
        } else {
            io.print("No Changes will be made to your library");
            
        }
        io.readString("Please hit enter to continue");
    }
    public void displayEditDvdBanner(){
        io.print("===Edit DVD===");
    }
    public void displayEditSuccessBanner(){
        io.print("DVD information successfully edited. Please hit enter to "
                + "continue");
    }
    
     //methods for Exit and Uknown Command
    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }
    public void displayUnknownCommandBanner(){
        io.print("Unkown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
}
}
