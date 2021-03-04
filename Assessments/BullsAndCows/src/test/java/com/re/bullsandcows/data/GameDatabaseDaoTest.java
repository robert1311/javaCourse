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
public class GameDatabaseDaoTest {
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    RoundDao roundDao;
    
    public GameDatabaseDaoTest() {
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
        for(Game game : games){
            gameDao.deleteGame(game.getGameId());
        }
        List<Round> rounds = roundDao.getAllRounds();
        for(Round round : rounds){
            roundDao.deleteRound(round.getRoundId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllGames method, of class GameDatabaseDao.
     */
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setCurrentRound(1);
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDao.addGame(game);
        
        Game game2 = new Game();
        game2.setCurrentRound(2);
        game2.setAnswer("5678");
        game2.setIsFinished(false);//gets defaulted to false by database which is 
        //why it will not be the same object
        gameDao.addGame(game2);
        
        List<Game> games = gameDao.getAllGames();
        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    /**
     * Test of getGame method, of class GameDatabaseDao.
     */
    @Test
    public void testAddGetGame() {
        Game game = new Game();
        game.setCurrentRound(1);
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDao.addGame(game);
        
        Game fromDao = gameDao.getGame(game.getGameId());
        
        assertEquals(game, fromDao);
    }


    /**
     * Test of updateGame method, of class GameDatabaseDao.
     */
    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setCurrentRound(1);
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDao.addGame(game);
        
        Game fromDao = gameDao.getGame(game.getGameId());
        
        assertEquals(game, fromDao);
        
        game.setCurrentRound(2);
        gameDao.updateGame(game);
        
        assertNotEquals(game, fromDao);
        
        fromDao = gameDao.getGame(game.getGameId());
        
        assertEquals(game, fromDao);
        
    }

    /**
     * Test of deleteGame method, of class GameDatabaseDao.
     */
    @Test
    public void testDeleteGame() {
        Game game = new Game();
        game.setCurrentRound(2);
        game.setAnswer("1234");
        game.setIsFinished(false);
        gameDao.addGame(game);
        
        Round round = new Round();
        round.setGuess("2467");
        round.setTimeOfGuess(LocalDateTime.now());
        round.setRoundNumeber(1);
        round.setResult("e:0:p:2");
        round.setGame(game);
        roundDao.addRound(round);
        
        gameDao.deleteGame(game.getGameId());
        
        Game fromDao = gameDao.getGame(game.getGameId());
        assertNull(fromDao);
        
    }
    
}
