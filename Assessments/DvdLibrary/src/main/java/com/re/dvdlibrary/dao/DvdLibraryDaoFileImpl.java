/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.dvdlibrary.dao;

import com.re.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Dev10
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao{
    //setting constants
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, Dvd> dvds = new HashMap<>();
    
    private Dvd unmarshallDvd(String dvdAsText){
   
    String[] dvdTokens = dvdAsText.split(DELIMITER);

    // Given the pattern above, the title is in index 0 of the array.
    String title = dvdTokens[0];

    // Which we can then use to create a new Dvd object to satisfy
    // the requirements of the Student constructor.
    Dvd dvdFromFile = new Dvd(title);

    // However, there are 3 remaining tokens that need to be set into the
    // new student object. Do this manually by using the appropriate setters.

    // Index 1 - Release Date
    dvdFromFile.setDate(dvdTokens[1]);

    // Index 2 - MPAA Rating
    dvdFromFile.setRating(dvdTokens[2]);

    // Index 3 - Director's name
    dvdFromFile.setDirName(dvdTokens[3]);
    
    // Index 4 - Studio 
    dvdFromFile.setStudio(dvdTokens[4]);
    
    //Index 5 - User Rating/Note
    dvdFromFile.setNote(dvdTokens[5]);
    
    // We have now created a dvd! Return it!
    return dvdFromFile;
}
    //////////LoadROster////////
    public void loadLibrary() throws DvdLibraryDaoException {
    Scanner scanner;

    try {
        // Create Scanner for reading the file
        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(LIBRARY_FILE)));
    } catch (FileNotFoundException e) {
        throw new DvdLibraryDaoException(
                "-_- Could not load library data into memory.", e);
    }
    // currentLine holds the most recent line read from the file
    String currentLine;
    // currentDvd holds the most recent student unmarshalled
    Dvd currentDvd;
    // Go through Library_FILE line by line, decoding each line into a 
    // Dvd object by calling the unmarshallDvd method.
    // Process while we have more lines in the file
    while (scanner.hasNextLine()) {
        // get the next line in the file
        currentLine = scanner.nextLine();
        // unmarshall the line into a Dvd
        currentDvd = unmarshallDvd(currentLine);

        // We are going to use the title as the map key for our student object.
        // Put currentStudent into the map using student id as the key
        dvds.put(currentDvd.getTitle(), currentDvd);
    }
    // close scanner
    scanner.close();
}

    private String marshallDvd(Dvd aDvd){
    // We need to turn a Dvd object into a line of text for our file.
    // For example, we need an in memory object to end up like this:
    // 4321::Charles::Babbage::Java-September1842

    // It's not a complicated process. Just get out each property,
    // and concatenate with our DELIMITER as a kind of spacer. 

    // Start with the title, since that's supposed to be first.
    String dvdAsText = aDvd.getTitle() + DELIMITER;

    // add the rest of the properties in the correct order:

    // Release Date
    dvdAsText += aDvd.getDate() + DELIMITER;

    // MPAA rating
    dvdAsText += aDvd.getRating() + DELIMITER;
    
    //Director;s Name
    dvdAsText += aDvd.getDirName() + DELIMITER;
    
    //Studio
    dvdAsText += aDvd.getStudio() + DELIMITER;

    // Note - don't forget to skip the DELIMITER here.
    dvdAsText += aDvd.getNote();

    // We have now turned a student to text! Return it!
    return dvdAsText;
}
    
    /**
 * Writes all Dvds in the roster out to a LIBRARY_FILE.  See loadLibrary
 * for file format.
 * 
 * @throws DvdLibraryDaoException if an error occurs writing to the file
 */
public void writeLibrary() throws DvdLibraryDaoException {
    // NOTE FOR APPRENTICES: We are not handling the IOException - but
    // we are translating it to an application specific exception and 
    // then simple throwing it (i.e. 'reporting' it) to the code that
    // called us.  It is the responsibility of the calling code to 
    // handle any errors that occur.
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(LIBRARY_FILE));
    } catch (IOException e) {
        throw new DvdLibraryDaoException(
                "Could not save DVD data.", e);
    }

    // Write out the Dvd objects to the roster file.
    // NOTE TO THE APPRENTICES: We could just grab the dvd map,
    // get the Collection of Students and iterate over them but we've
    // already created a method that gets a List of Dvds so
    // we'll reuse it.
    String dvdAsText;
    List<Dvd> dvdList = this.getAllDvds();
    for (Dvd currentDvd : dvdList) {
        // turn a Dvd into a String
        dvdAsText = marshallDvd(currentDvd);
        // write the Dvd object to the file
        out.println(dvdAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
}
    
    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException{
        //loadLibrary();
        Dvd newDvd = dvds.put(title, dvd);
        //writeLibrary();
        return newDvd;
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException{
        //loadLibrary();
        Dvd removedDvd = dvds.remove(title);
       // writeLibrary();
        return removedDvd;
    }

    @Override
    public Dvd editDvd(String title) throws DvdLibraryDaoException{
        //loadLibrary();
        // use remove and Add Dao methods to edit
        String trueKey = "";
        Set<String> keys = dvds.keySet();
            for (String k : keys){
                if (k.equalsIgnoreCase(title)){
                    trueKey = k;
                }
            }
          return dvds.get(trueKey);
        //return dvds.get(title);
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException{
        //loadLibrary();
        return new ArrayList<Dvd>(dvds.values());//gets dvd objects from HashMap
    }

    @Override
    public Dvd displayDvdInfo(String title) throws DvdLibraryDaoException{
        //loadLibrary();
        return dvds.get(title);
        
    }

    @Override
    public Dvd searchDvd(String title) throws DvdLibraryDaoException{
        //loadLibrary();
        Set<String> keys = dvds.keySet();
            for (String k : keys){
                if (k.equalsIgnoreCase(title)){
                    title = k;
                }
            }
        return dvds.get(title);
    }
    
}
