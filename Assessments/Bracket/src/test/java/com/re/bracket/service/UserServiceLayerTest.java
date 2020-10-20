/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.service;

import com.re.bracket.dao.UserAuditDao;
import com.re.bracket.dao.UserAuditDaoStubImpl;
import com.re.bracket.dao.UserDao;
import com.re.bracket.dao.UserDaoStubImpl;
import com.re.bracket.dto.Tournament;
import com.re.bracket.dto.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author rober
 */
public class UserServiceLayerTest {

    private UserServiceLayer service;

    public UserServiceLayerTest() {
//        UserDao uDao = new UserDaoStubImpl();
//        UserAuditDao uAudit = new UserAuditDaoStubImpl();
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");

//        service = new UserServiceLayerImpl(uDao, uAudit);
        service = ctx.getBean("uService", UserServiceLayer.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createNewUser method, of class UserServiceLayer.
     */
    @Test
    public void testCreateNewUser() throws Exception {

        try {
            service.createNewUser("JohnDoe123");
            fail("Expected InvslidUserNameException not thrown.");
        } catch (InvalidUserNameException e) {

        }
    }

    /**
     * Test of getLoggedInUser method, of class UserServiceLayer.
     */
    @Test
    public void testGetLoggedInUser() {
        try {
            service.getLoggedInUser("Johndoe13");
            fail("Expected LoginFailException not thrown.");
        } catch (LoginFailException e) {

        }
    }

    /**
     * Test of addNotificationsToUser method, of class UserServiceLayer.
     */
    @Test
    public void testAddNotificationsToUser() throws Exception {
        Tournament newTournament = new Tournament(1);
        newTournament.setUsername("JohnDoe123");
        User onlyUser = service.getLoggedInUser("JohnDoe123");
        String onlyUserNotes = onlyUser.getNotifications();

        assertEquals(onlyUserNotes + " (1)JoeSmith456", service.addNotificationsToUser(newTournament, "JoeSmith456"));
    }

    /**
     * Test of ClearNotifications method, of class UserServiceLayer.
     */
    @Test
    public void testClearNotifications() throws Exception {

        service.clearNotifications("JohnDoe123");
        User onlyUser = service.getLoggedInUser("JohnDoe123");
        String onlyUserNotes = onlyUser.getNotifications();
        assertEquals("Ready:", onlyUserNotes);

    }

}
