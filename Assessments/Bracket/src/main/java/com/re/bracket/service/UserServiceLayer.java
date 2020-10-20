/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.service;

import com.re.bracket.dao.TournamentPersistenceException;
import com.re.bracket.dto.Tournament;
import com.re.bracket.dto.User;

/**
 *
 * @author rober
 */
public interface UserServiceLayer {

    public User createNewUser(String newUserName)
            throws InvalidUserNameException, TournamentPersistenceException;


    public User getLoggedInUser(String username) throws LoginFailException;

    public String addNotificationsToUser(Tournament tournament,
            String message);

    public String clearNotifications(String username);
    
    public void loadUsers() throws TournamentPersistenceException;
    
    public void saveUsers() throws TournamentPersistenceException;
}
