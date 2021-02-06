/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;
import com.re.bracket.dto.User;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author rober
 */
public class UserDaoTest {

    UserDao dao = new UserDaoFileImpl();

    public UserDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<User> userList = dao.getAllUsers();
        for (User user : userList) {
            dao.removeUser(user.getUserName());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class UserDao.
     */
    @Test
    public void testAddGetUser() {
        User newUser = new User("JohnDoe123");
        newUser.setIsAdmin(false);
        newUser.setNotifications("Team Blue is Ready for Game 1!");
        dao.addUser(newUser);

        User fromDao = dao.getUser(newUser.getUserName());
        assertEquals(newUser, fromDao);
    }

    /**
     * Test of getAllUsers method, of class UserDao.
     */
    @Test
    public void testGetAllUsers() {
        User newUser1 = new User("JohnDoe123");
        newUser1.setIsAdmin(false);
        newUser1.setNotifications("Team Blue is Ready for Game 1!");
        dao.addUser(newUser1);

        assertEquals(1, dao.getAllUsers().size());

        User newUser2 = new User("JoeSmith456");
        newUser2.setIsAdmin(true);
        newUser2.setNotifications("Team Red is Ready for Game 1!");
        dao.addUser(newUser2);

        assertEquals(2, dao.getAllUsers().size());
    }

    /**
     * Test of removeUser method, of class UserDao.
     */
    @Test
    public void testRemoveUser() {

        User newUser1 = new User("JohnDoe123");
        newUser1.setIsAdmin(false);
        newUser1.setNotifications("Team Blue is Ready for Game 1!");
        dao.addUser(newUser1);

        User newUser2 = new User("JoeSmith456");
        newUser2.setIsAdmin(true);
        newUser2.setNotifications("Team Red is Ready for Game 1!");
        dao.addUser(newUser2);

        dao.removeUser(newUser2.getUserName());

        assertEquals(1, dao.getAllUsers().size());
        assertNull(dao.getUser(newUser2.getUserName()));

        dao.removeUser(newUser1.getUserName());

        assertEquals(0, dao.getAllUsers().size());
        assertNull(dao.getUser(newUser1.getUserName()));

    }

    /**
     * Test of loadUsers method, of class UserDao.
     */
    @Test
    public void testLoadUsers() {
    }

    /**
     * Test of writeUsers method, of class UserDao.
     */
    @Test
    public void testWriteUsers() {
    }

}
