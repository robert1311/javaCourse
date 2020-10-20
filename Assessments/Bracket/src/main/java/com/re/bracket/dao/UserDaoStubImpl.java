/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import com.re.bracket.dto.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rober
 */
public class UserDaoStubImpl implements UserDao {
    
    List<User> userList = new ArrayList<>();
    User onlyUser;
    public UserDaoStubImpl(){
        onlyUser = new User("JohnDoe123");
        onlyUser.setIsAdmin(false);
        onlyUser.setNotifications("Ready:Rob3113");
        userList.add(onlyUser);
    }

    @Override
    public User addUser(User user) {
        if (user.equals(onlyUser)) {
            return onlyUser;
        } else {
            return null;
        }
    }

    @Override
    public User getUser(String username) {
        if (username.equals(onlyUser.getUserName())) {
            return onlyUser;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public User removeUser(String username) {
        if (username.equals(onlyUser.getUserName())) {
            return onlyUser;
        } else {
            return null;
        }
    }

    @Override
    public void loadUsers() throws TournamentPersistenceException {

    }

    @Override
    public void writeUsers() throws TournamentPersistenceException {

    }
    
    
}
