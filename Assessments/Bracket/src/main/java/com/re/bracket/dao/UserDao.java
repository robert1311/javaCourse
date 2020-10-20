/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import com.re.bracket.dto.User;
import java.util.List;

/**
 *
 * @author rober
 */
public interface UserDao {

    User addUser(User user);

    User getUser(String username);

    List<User> getAllUsers();

    User removeUser(String username);

    void loadUsers() throws TournamentPersistenceException;

    void writeUsers() throws TournamentPersistenceException;
}
