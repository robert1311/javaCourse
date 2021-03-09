/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.service;

import com.re.bullsandcows.TestApplicationConfiguration;
import com.re.bullsandcows.data.GameDao;
import com.re.bullsandcows.data.RoundDao;
import com.re.bullsandcows.entity.Game;
import com.re.bullsandcows.entity.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author rober
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class BullsAndCowsServiceLayerImplTest {

    @Autowired
    BullsAndCowsServiceLayer service;

    public BullsAndCowsServiceLayerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateGetGame() {
        Game game = service.createGame();

        Game fromDao = service.findGameById(100);

        assertEquals(1, game.getCurrentRound());
        assertNotEquals(game, fromDao);
    }
//
//    @Test
//    public void testGetAllGames() {
//        Game game = service.createGame();
//        Game game2 = service.createGame();
//
//        List<Game> games = service.getAllGames();
//        assertEquals(2, games.size());
//    }
//
//    @Test
//    public void testmakeGuessesGetRoundsForGame() throws Exception {
//
//        try {
//            Round round6 = service.makeGuess(100, "2h39");
//            fail("InvalidGuessException not thrown");
//        } catch (InvalidGuessException e) {
//
//        }
//
//        try {
//            Round round7 = service.makeGuess(100, "294");
//            fail("InvalidGuessException not thrown");
//        } catch (InvalidGuessException e) {
//
//        }
//
//        Round round = null;
//        try {
//            round = service.makeGuess(99, "1234");
//            fail("GameFinishedException not thrown");
//        } catch (GameFinishedException e) {
//
//        }
//
//        Round round2 = service.makeGuess(100, "6395");
//        assertEquals("e:2:p:2", round2.getResult());
//        assertEquals(3, round2.getGame().getCurrentRound());
//        assertFalse(round2.getGame().isIsFinished());
//
////        Round round3 = service.makeGuess(round.getGame().getGameId(), "3215");
////        assertEquals(2, round3.getRoundNumeber());
////        assertEquals("e:3:p:0", round3.getResult());
////        assertEquals(3, round.getGame().getCurrentRound());
////        assertFalse(round.getGame().isIsFinished());
//        Round round4 = service.makeGuess(100, "9365");
//        assertEquals(2, round4.getRoundNumeber());
//        assertEquals("e:4:p:0", round4.getResult());
//        assertEquals(3, round4.getGame().getCurrentRound());
//        assertTrue(round4.getGame().isIsFinished());
//
//        List<Round> rounds = service.getAllRoundsForGame(99);
//        assertEquals(2, rounds.size());
//        
//    }

}
