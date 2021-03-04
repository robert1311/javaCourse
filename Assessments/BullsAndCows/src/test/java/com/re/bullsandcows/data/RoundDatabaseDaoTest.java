/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bullsandcows.data;

import com.re.bullsandcows.TestApplicationConfiguration;
import com.re.bullsandcows.entity.Game;
import com.re.bullsandcows.entity.Round;
import java.time.LocalDateTime;
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
public class RoundDatabaseDaoTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public RoundDatabaseDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGame(game.getGameId());
        }
        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds) {
            roundDao.deleteRound(round.getRoundId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllRounds method, of class RoundDatabaseDao.
     */
    @Test
    public void testGetAllRounds() {
        Game game = new Game();
        game.setCurrentRound(2);
        game.setAnswer("1234");
        game.setIsFinished(false);
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGuess("2467");
        round.setTimeOfGuess(LocalDateTime.now().withNano(0));
        round.setRoundNumeber(1);
        round.setResult("e:0:p:2");
        round.setGame(game);
        round = roundDao.addRound(round);

//        Game game2 = new Game();
//        game2.setCurrentRound(5);
//        game2.setAnswer("5678");
//        game2.setIsFinished(false);
//        game2 = gameDao.addGame(game2);

        Round round2 = new Round();
        round2.setGuess("5679");
        round2.setTimeOfGuess(LocalDateTime.now().withNano(0));
        round2.setRoundNumeber(4);
        round2.setResult("e:3:p:0");
        round2.setGame(game);
        round2 = roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRounds();

        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round2));
    }

    /**
     * Test of getRound method, of class RoundDatabaseDao.
     */
    @Test
    public void testAddGetRound() {
        Game game = new Game();
        game.setCurrentRound(2);
        game.setAnswer("1234");
        game.setIsFinished(false);
        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGuess("2467");
        round.setTimeOfGuess(LocalDateTime.now().withNano(0));
        round.setRoundNumeber(1);
        round.setResult("e:0:p:2");
        round.setGame(game);
        round = roundDao.addRound(round);

        Round fromDao = roundDao.getRound(round.getRoundId());

        assertEquals(round, fromDao);
    }

    /**
     * Test of updateRound method, of class RoundDatabaseDao.
     */
    @Test
    public void testUpdateRound() {
//        Game game = new Game();
//        game.setCurrentRound(2);
//        game.setAnswer("1234");
//        game.setIsFinished(false);
//        gameDao.addGame(game);
//        
//        Round round = new Round();
//        round.setGuess("2467");
//        round.setTimeOfGuess(LocalDateTime.now());
//        round.setRoundNumeber(1);
//        round.setResult("e:0:p:2");
//        round.setGame(game);
//        roundDao.addRound(round);
//        
//        Round fromDao = roundDao.getRound(round.getRoundId());
//        
//        assertEquals(round, fromDao);

    }

    /**
     * Test of deleteRound method, of class RoundDatabaseDao.
     */
    @Test
    public void testDeleteRound() {
        Game game = new Game();
        game.setCurrentRound(2);
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDao.addGame(game);

        Round round = new Round();
        round.setGuess("2467");
        round.setTimeOfGuess(LocalDateTime.now().withNano(0));
        round.setRoundNumeber(1);
        round.setResult("e:0:p:2");
        round.setGame(game);
        roundDao.addRound(round);

        roundDao.deleteRound(round.getRoundId());

        assertNull(roundDao.getRound(round.getRoundId()));
    }

    /**
     * Test of getRoundsForGame method, of class RoundDatabaseDao.
     */
    @Test
    public void testGetRoundsForGame() {
        Game game = new Game();
        game.setCurrentRound(1);
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDao.addGame(game);

        Game game2 = new Game();
        game2.setCurrentRound(1);
        game2.setAnswer("5678");
        game2.setIsFinished(false);
        gameDao.addGame(game2);

        Round round = new Round();
        round.setGuess("2467");
        round.setTimeOfGuess(LocalDateTime.now().withNano(0));
        round.setRoundNumeber(1);
        round.setResult("e:0:p:2");
        round.setGame(game);
        roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGuess("2765");
        round2.setTimeOfGuess(LocalDateTime.now().withNano(0));
        round2.setRoundNumeber(1);
        round2.setResult("e:2:p:1");
        round2.setGame(game2);
        roundDao.addRound(round2);

        Round round3 = new Round();
        round3.setGuess("1234");
        round3.setTimeOfGuess(LocalDateTime.now().withNano(0));
        round3.setRoundNumeber(1);
        round3.setResult("e:4:p:0");
        round3.setGame(game);
        roundDao.addRound(round3);
        
        List<Round> roundsForGames = roundDao.getRoundsForGame(game);
        
        assertEquals(2, roundsForGames.size());
        assertTrue(roundsForGames.contains(round));
        assertTrue(roundsForGames.contains(round3));
        assertFalse(roundsForGames.contains(round2));
    }
}
