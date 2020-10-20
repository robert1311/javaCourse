/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.service;

import com.re.bracket.dao.TournamentPersistenceException;
import com.re.bracket.dao.UserAuditDao;
import com.re.bracket.dao.UserDao;
import com.re.bracket.dto.Tournament;
import com.re.bracket.dto.User;
import java.util.List;

/**
 *
 * @author rober
 */
public class UserServiceLayerImpl implements UserServiceLayer {

    UserDao uDao;
    UserAuditDao uAudit;

    public UserServiceLayerImpl(UserDao uDao, UserAuditDao uAudit) {
        this.uDao = uDao;
        this.uAudit = uAudit;
    }

    @Override
    public User createNewUser(String newUserName) throws InvalidUserNameException,
            TournamentPersistenceException {
        List<User> allUsers = uDao.getAllUsers();
        for (User user : allUsers) {
            if (user.getUserName().equalsIgnoreCase(newUserName)) {
                throw new InvalidUserNameException("Username taken. Please try again.");
            }
        }
        User newUser = new User(newUserName);
        newUser.setIsAdmin(false);
        newUser.setNotifications("Ready:");
        uDao.addUser(newUser);
        uAudit.writeAudit("USER ADDED: " + newUser.getUserName()
                + " - " + newUser.isIsAdmin());
        return newUser;
    }

    @Override
    public User getLoggedInUser(String username) throws LoginFailException {
        List<User> allUsers = uDao.getAllUsers();
        for (User user : allUsers) {
            if (user.getUserName().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new LoginFailException("Incorrect Login. Please try "
                + "again.");

    }

    @Override
    public String addNotificationsToUser(Tournament tournament, String message) {
        String noteeUsername = tournament.getUsername();
        User noteeUser = uDao.getUser(noteeUsername);
        String currentNotes = noteeUser.getNotifications();
        noteeUser.setNotifications(currentNotes + " (" + tournament.getTournamentId()
                + ")" + message);
        return noteeUser.getNotifications();
    }

    @Override
    public String clearNotifications(String username) {
        User currentUser = uDao.getUser(username);
        currentUser.setNotifications("Ready:");
        return currentUser.getNotifications();
    }

    @Override
    public void loadUsers() throws TournamentPersistenceException {
        uDao.loadUsers();
    }

    @Override
    public void saveUsers() throws TournamentPersistenceException {
        uDao.writeUsers();
    }

}
