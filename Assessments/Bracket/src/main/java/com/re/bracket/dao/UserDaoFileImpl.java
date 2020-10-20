/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import com.re.bracket.dto.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author rober
 */
public class UserDaoFileImpl implements UserDao {

    private Map<String, User> userMap = new HashMap<>() ;
    private final String DEL = "::";
    private final String USER_FILE = "users.txt";

    @Override
    public User addUser(User user) {
        return userMap.put(user.getUserName(), user);
    }

    @Override
    public User getUser(String username) {
        return userMap.get(username);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        for(User user : userMap.values()){
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User removeUser(String username) {
        return userMap.remove(username);
    }
    
    private User unmarshallUser(String userFromFile){
        String[] userTokens = userFromFile.split(DEL);
        
        User unmarshalledUser = new User(userTokens[0]);
        unmarshalledUser.setIsAdmin(Boolean.parseBoolean(userTokens[1]));
        unmarshalledUser.setNotifications(userTokens[2]);
        
        return unmarshalledUser;
    }
    
    private String marshallUser(User aUser){
        String userAsText = aUser.getUserName() + DEL;
        userAsText += aUser.isIsAdmin() + DEL;
        userAsText += aUser.getNotifications();
        return userAsText;
    }

    @Override
    public void loadUsers() throws TournamentPersistenceException {
        Scanner sc;
        try{
            sc = new Scanner(new BufferedReader(new FileReader(USER_FILE)));
        } catch(IOException e){
            throw new TournamentPersistenceException("Users could not be loaded.", e);
        }
        User currentUser;
        String currentLine;
        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentUser = unmarshallUser(currentLine);
            userMap.put(currentUser.getUserName(), currentUser);
        }
        sc.close();
    }

    @Override
    public void writeUsers() throws TournamentPersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(USER_FILE));
        } catch (IOException e){
            throw new TournamentPersistenceException("Users could not be saved.", e);
        }
        String marshalledUser;
        for(User currentUser : userMap.values()){
            marshalledUser = marshallUser(currentUser);
            out.println(marshalledUser);
            out.flush();
        }
        out.close();
    }

}
