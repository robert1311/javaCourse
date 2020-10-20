/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import com.re.bracket.dto.Game;
import com.re.bracket.dto.Participant;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
import com.re.bracket.dto.Tournament;
import com.re.bracket.dto.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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
public class TournamentDaoTest {

    TournamentDao dao = new TournamentDaoFileImpl();

    public TournamentDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Tournament> tournamentList = dao.getAllTournaments();
        List<Series> seriesList = dao.getAllSeries();
        List<Game> gameList = dao.getAllGames();

        for (Game game : gameList) {
            dao.removeGame(game.getGameId());
        }

        for (Series series : seriesList) {
            dao.removeSeries(series.getSeriesId());
        }

        for (Tournament tournament : tournamentList) {
            dao.removeTournament(tournament.getTournamentId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addTournament method, of class TournamentDao.
     */
    @Test
    public void testAddGetTournament() {
        User newUser1 = new User("JohnDoe123");
        newUser1.setIsAdmin(true);
        newUser1.setNotifications("Team1 Ready!");

        Tournament newTournament1 = new Tournament(1);
        newTournament1.setTournamentId(1);
        newTournament1.setTournamentName("Beta");
        newTournament1.setUsername(newUser1.getUserName());
        newTournament1.setCurrentRound(0);
        newTournament1.setS1Format("Round Robin");
        newTournament1.setIsSecondStage(true);
        newTournament1.setIsSignUp(true);
        newTournament1.setMaxNumOfParticipants(4);
        newTournament1.setS1NumOfRounds(4);
        newTournament1.setS2NumOfRounds(4);
        newTournament1.setSeedsToAdvance(4);
        newTournament1.setStageType(1);

        dao.addTournament(newTournament1);

        Tournament fromDao = dao.getTournament(newTournament1.getTournamentId());
        assertEquals(newTournament1, fromDao);

    }

    /**
     * Test of getAllTournaments method, of class TournamentDao.
     */
    @Test
    public void testGetAllTournaments() {

        User newUser1 = new User("JohnDoe123");
        newUser1.setIsAdmin(false);
        newUser1.setNotifications("Team1 Ready!");

        Tournament newTournament1 = new Tournament(1);
        newTournament1.setTournamentId(1);
        newTournament1.setTournamentName("Beta");
        newTournament1.setUsername(newUser1.getUserName());
        newTournament1.setCurrentRound(0);
        newTournament1.setS1Format("Round Robin");
        newTournament1.setIsSecondStage(false);
        newTournament1.setIsSignUp(true);
        newTournament1.setMaxNumOfParticipants(4);
        newTournament1.setS1NumOfRounds(4);
        newTournament1.setS2NumOfRounds(4);
        newTournament1.setSeedsToAdvance(4);
        newTournament1.setStageType(1);

        dao.addTournament(newTournament1);

        assertEquals(1, dao.getAllTournaments().size());

        User newUser2 = new User("JoeSmith456");
        newUser2.setIsAdmin(false);
        newUser2.setNotifications("Team4 Ready!");

        Tournament newTournament2 = new Tournament(2);
        newTournament2.setTournamentId(2);
        newTournament2.setTournamentName("Gamma");
        newTournament1.setUsername(newUser1.getUserName());
        newTournament2.setCurrentRound(1);
        newTournament2.setS1Format("Single Elimination");
        newTournament2.setIsSecondStage(false);
        newTournament2.setIsSignUp(false);
        newTournament2.setMaxNumOfParticipants(8);
        newTournament2.setS1NumOfRounds(8);
        newTournament2.setS2NumOfRounds(4);
        newTournament2.setSeedsToAdvance(8);
        newTournament2.setStageType(2);

        dao.addTournament(newTournament2);

        assertEquals(2, dao.getAllTournaments().size());

    }

    /**
     * Test of removeTournament method, of class TournamentDao.
     */
    @Test
    public void testRemoveTournament() {

        User newUser1 = new User("JohnDoe123");
        newUser1.setIsAdmin(false);
        newUser1.setNotifications("Team1 Ready!");
        Tournament newTournament1 = new Tournament(1);
        newTournament1.setTournamentName("Beta");
        newTournament1.setCurrentRound(0);
        newTournament1.setS1Format("Round Robin");
        newTournament1.setIsSecondStage(false);
        newTournament1.setIsSignUp(true);
        newTournament1.setMaxNumOfParticipants(4);
        newTournament1.setS1NumOfRounds(4);
        newTournament1.setS2NumOfRounds(4);
        newTournament1.setSeedsToAdvance(4);
        newTournament1.setStageType(1);
        dao.addTournament(newTournament1);

        User newUser2 = new User("JoeSmith456");
        newUser2.setIsAdmin(false);
        newUser2.setNotifications("Team4 Ready!");
        Tournament newTournament2 = new Tournament(2);
        newTournament2.setTournamentName("Gamma");
        newTournament2.setCurrentRound(1);
        newTournament2.setS1Format("Single Elimination");
        newTournament2.setIsSecondStage(false);
        newTournament2.setIsSignUp(false);
        newTournament2.setMaxNumOfParticipants(8);
        newTournament2.setS1NumOfRounds(8);
        newTournament2.setS2NumOfRounds(4);
        newTournament2.setSeedsToAdvance(8);
        newTournament2.setStageType(2);
        dao.addTournament(newTournament2);
        
        Series newSeries1 = new Series(1);
        newSeries1.setBestOfNumGames(7);
        newSeries1.setDateTime(LocalDateTime.now().plusWeeks(1));
        newSeries1.setIsComplete(false);
        newSeries1.setIsReady(false);
        newSeries1.setNumGamesPlayed(0);
        newSeries1.setRoundNumber(1);
        newSeries1.setSeriesLoserName("");
        newSeries1.setSeriesWinnerName("");
        newSeries1.setTeam1Name("Team1");
        newSeries1.setTeam2Name("Team2");
        newSeries1.setTournamentId(1);
        dao.addSeries(newSeries1);

        Series newSeries2 = new Series(2);
        newSeries2.setBestOfNumGames(7);
        newSeries2.setDateTime(LocalDateTime.now());
        newSeries2.setIsComplete(false);
        newSeries2.setIsReady(true);
        newSeries2.setNumGamesPlayed(2);
        newSeries2.setRoundNumber(2);
        newSeries2.setSeriesLoserName("");
        newSeries2.setSeriesWinnerName("");
        newSeries2.setTeam1Name("Team1");
        newSeries2.setTeam2Name("Team2");
        newSeries2.setTournamentId(1);
        dao.addSeries(newSeries2);
        
        Series newSeries3 = new Series(3);
        newSeries3.setBestOfNumGames(7);
        newSeries3.setDateTime(LocalDateTime.now().plusWeeks(1));
        newSeries3.setIsComplete(false);
        newSeries3.setIsReady(false);
        newSeries3.setNumGamesPlayed(0);
        newSeries3.setRoundNumber(1);
        newSeries3.setSeriesLoserName("");
        newSeries3.setSeriesWinnerName("");
        newSeries3.setTeam1Name("Team1");
        newSeries3.setTeam2Name("Team2");
        newSeries3.setTournamentId(2);
        dao.addSeries(newSeries3);

        Series newSeries4 = new Series(4);
        newSeries4.setBestOfNumGames(7);
        newSeries4.setDateTime(LocalDateTime.now());
        newSeries4.setIsComplete(false);
        newSeries4.setIsReady(true);
        newSeries4.setNumGamesPlayed(2);
        newSeries4.setRoundNumber(2);
        newSeries4.setSeriesLoserName("");
        newSeries4.setSeriesWinnerName("");
        newSeries4.setTeam1Name("Team1");
        newSeries4.setTeam2Name("Team2");
        newSeries4.setTournamentId(2);
        dao.addSeries(newSeries4);
        
        Game newGame1 = new Game(1);
        newGame1.setAwayScore(10);
        newGame1.setAwayTeam("Team1");
        newGame1.setAwayTeamId(1);
        newGame1.setDateTime(LocalDateTime.now().minusDays(1));
        newGame1.setHomeScore(20);
        newGame1.setHomeTeam("Team2");
        newGame1.setHomeTeamId(2);
        newGame1.setIsComplete(true);
        newGame1.setIsNeutralField(false);
        newGame1.setIsReady(true);
        newGame1.setLosingTeamName("Team1");
        newGame1.setSeriesGameNum(1);
        newGame1.setSeriesId(1);
        newGame1.setWinningTeamName("Team2");
        dao.addGame(newGame1);

        Game newGame2 = new Game(2);
        newGame2.setAwayScore(10);
        newGame2.setAwayTeam("Team1");
        newGame2.setAwayTeamId(1);
        newGame2.setDateTime(LocalDateTime.now().minusDays(1));
        newGame2.setHomeScore(20);
        newGame2.setHomeTeam("Team2");
        newGame2.setHomeTeamId(2);
        newGame2.setIsComplete(true);
        newGame2.setIsNeutralField(false);
        newGame2.setIsReady(true);
        newGame2.setLosingTeamName("Team1");
        newGame2.setSeriesGameNum(2);
        newGame2.setSeriesId(1);
        newGame2.setWinningTeamName("Team2");
        dao.addGame(newGame2);

        Game newGame3 = new Game(3);
        newGame3.setAwayScore(10);
        newGame3.setAwayTeam("Team1");
        newGame3.setAwayTeamId(1);
        newGame3.setDateTime(LocalDateTime.now().minusDays(1));
        newGame3.setHomeScore(20);
        newGame3.setHomeTeam("Team2");
        newGame3.setHomeTeamId(2);
        newGame3.setIsComplete(true);
        newGame3.setIsNeutralField(false);
        newGame3.setIsReady(true);
        newGame3.setLosingTeamName("Team1");
        newGame3.setSeriesGameNum(1);
        newGame3.setSeriesId(2);
        newGame3.setWinningTeamName("Team2");
        dao.addGame(newGame3);

        Game newGame4 = new Game(4);
        newGame4.setAwayScore(10);
        newGame4.setAwayTeam("Team1");
        newGame4.setAwayTeamId(1);
        newGame4.setDateTime(LocalDateTime.now().minusDays(1));
        newGame4.setHomeScore(20);
        newGame4.setHomeTeam("Team2");
        newGame4.setHomeTeamId(2);
        newGame4.setIsComplete(true);
        newGame4.setIsNeutralField(false);
        newGame4.setIsReady(true);
        newGame4.setLosingTeamName("Team1");
        newGame4.setSeriesGameNum(2);
        newGame4.setSeriesId(2);
        newGame4.setWinningTeamName("Team2");
        dao.addGame(newGame4);

        Game newGame5 = new Game(5);
        newGame5.setAwayScore(10);
        newGame5.setAwayTeam("Team1");
        newGame5.setAwayTeamId(1);
        newGame5.setDateTime(LocalDateTime.now().minusDays(1));
        newGame5.setHomeScore(20);
        newGame5.setHomeTeam("Team2");
        newGame5.setHomeTeamId(2);
        newGame5.setIsComplete(true);
        newGame5.setIsNeutralField(false);
        newGame5.setIsReady(true);
        newGame5.setLosingTeamName("Team1");
        newGame5.setSeriesGameNum(1);
        newGame5.setSeriesId(3);
        newGame5.setWinningTeamName("Team2");
        dao.addGame(newGame5);

        Game newGame6 = new Game(6);
        newGame6.setAwayScore(10);
        newGame6.setAwayTeam("Team1");
        newGame6.setAwayTeamId(1);
        newGame6.setDateTime(LocalDateTime.now().minusDays(1));
        newGame6.setHomeScore(20);
        newGame6.setHomeTeam("Team2");
        newGame6.setHomeTeamId(2);
        newGame6.setIsComplete(true);
        newGame6.setIsNeutralField(false);
        newGame6.setIsReady(true);
        newGame6.setLosingTeamName("Team1");
        newGame6.setSeriesGameNum(2);
        newGame6.setSeriesId(3);
        newGame6.setWinningTeamName("Team2");
        dao.addGame(newGame6);

        Game newGame7 = new Game(7);
        newGame7.setAwayScore(10);
        newGame7.setAwayTeam("Team1");
        newGame7.setAwayTeamId(1);
        newGame7.setDateTime(LocalDateTime.now().minusDays(1));
        newGame7.setHomeScore(20);
        newGame7.setHomeTeam("Team2");
        newGame7.setHomeTeamId(2);
        newGame7.setIsComplete(true);
        newGame7.setIsNeutralField(false);
        newGame7.setIsReady(true);
        newGame7.setLosingTeamName("Team1");
        newGame7.setSeriesGameNum(1);
        newGame7.setSeriesId(4);
        newGame7.setWinningTeamName("Team2");
        dao.addGame(newGame7);

        Game newGame8 = new Game(8);
        newGame8.setAwayScore(10);
        newGame8.setAwayTeam("Team1");
        newGame8.setAwayTeamId(1);
        newGame8.setDateTime(LocalDateTime.now().minusDays(1));
        newGame8.setHomeScore(20);
        newGame8.setHomeTeam("Team2");
        newGame8.setHomeTeamId(2);
        newGame8.setIsComplete(true);
        newGame8.setIsNeutralField(false);
        newGame8.setIsReady(true);
        newGame8.setLosingTeamName("Team1");
        newGame8.setSeriesGameNum(2);
        newGame8.setSeriesId(4);
        newGame8.setWinningTeamName("Team2");
        dao.addGame(newGame8);

        List<Game> gameList1;
        List<Game> gameList2;
        List<Series> seriesList;
        seriesList = dao.getAllSeries()
                .stream()
                .filter(s -> s.getTournamentId() == 1)
                .collect(Collectors.toList());
        gameList1 = dao.getAllGames()
                .stream()
                .filter(g -> g.getSeriesId() == 1)
                .collect(Collectors.toList());
                
        gameList2 = dao.getAllGames()
                .stream()
                .filter(g -> g.getSeriesId() == 2)
                .collect(Collectors.toList());
        
        assertEquals(2, seriesList.size());
        assertEquals(2, gameList1.size());
        assertEquals(2, gameList2.size());
        dao.removeTournament(newTournament1.getTournamentId());
        assertEquals(1, dao.getAllTournaments().size());
        assertNull(dao.getTournament(newTournament1.getTournamentId()));

        seriesList = dao.getAllSeries()
                .stream()
                .filter(s -> s.getTournamentId() == 1)
                .collect(Collectors.toList());
        gameList1 = dao.getAllGames()
                .stream()
                .filter(g -> g.getSeriesId() == 1)
                .collect(Collectors.toList());
                
        gameList2 = dao.getAllGames()
                .stream()
                .filter(g -> g.getSeriesId() == 2)
                .collect(Collectors.toList());
        
        assertEquals(0, seriesList.size());
        assertEquals(0, gameList1.size());
        assertEquals(0, gameList2.size());
        
        dao.removeTournament(newTournament2.getTournamentId());
        assertEquals(0, dao.getAllTournaments().size());
        assertNull(dao.getTournament(newTournament1.getTournamentId()));
    }

    /**
     * Test of addSeries method, of class TournamentDao.
     */
    @Test
    public void testAddGetSeries() {
        Series newSeries1 = new Series(1);
        newSeries1.setBestOfNumGames(7);
        newSeries1.setDateTime(LocalDateTime.now().plusWeeks(1));
        newSeries1.setIsComplete(false);
        newSeries1.setIsReady(false);
        newSeries1.setNumGamesPlayed(0);
        newSeries1.setRoundNumber(1);
        newSeries1.setSeriesLoserName("");
        newSeries1.setSeriesWinnerName("");
        newSeries1.setTeam1Name("Team1");
        newSeries1.setTeam2Name("Team2");
        newSeries1.setTournamentId(1);
        dao.addSeries(newSeries1);

        Series fromDao = dao.getSeries(newSeries1.getSeriesId());
        assertEquals(newSeries1, fromDao);
    }

    /**
     * Test of getAllSeries method, of class TournamentDao.
     */
    @Test
    public void testGetAllSeries() {
        Series newSeries1 = new Series(1);
        newSeries1.setBestOfNumGames(7);
        newSeries1.setDateTime(LocalDateTime.now().plusWeeks(1));
        newSeries1.setIsComplete(false);
        newSeries1.setIsReady(false);
        newSeries1.setNumGamesPlayed(0);
        newSeries1.setRoundNumber(1);
        newSeries1.setSeriesLoserName("");
        newSeries1.setSeriesWinnerName("");
        newSeries1.setTeam1Name("Team1");
        newSeries1.setTeam2Name("Team2");
        newSeries1.setTournamentId(1);
        dao.addSeries(newSeries1);

        assertEquals(1, dao.getAllSeries().size());

        Series newSeries2 = new Series(2);
        newSeries2.setBestOfNumGames(7);
        newSeries2.setDateTime(LocalDateTime.now());
        newSeries2.setIsComplete(false);
        newSeries2.setIsReady(true);
        newSeries2.setNumGamesPlayed(2);
        newSeries2.setRoundNumber(2);
        newSeries2.setSeriesLoserName("");
        newSeries2.setSeriesWinnerName("");
        newSeries2.setTeam1Name("Team1");
        newSeries2.setTeam2Name("Team2");
        newSeries2.setTournamentId(2);
        dao.addSeries(newSeries2);

        assertEquals(2, dao.getAllSeries().size());
    }

    /**
     * Test of removeSeries method, of class TournamentDao.
     */
    @Test
    public void testRemoveSeries() {
        Series newSeries1 = new Series(1);
        newSeries1.setBestOfNumGames(7);
        newSeries1.setDateTime(LocalDateTime.now().plusWeeks(1));
        newSeries1.setIsComplete(false);
        newSeries1.setIsReady(false);
        newSeries1.setNumGamesPlayed(0);
        newSeries1.setRoundNumber(1);
        newSeries1.setSeriesLoserName("");
        newSeries1.setSeriesWinnerName("");
        newSeries1.setTeam1Name("Team1");
        newSeries1.setTeam2Name("Team2");
        newSeries1.setTournamentId(1);
        dao.addSeries(newSeries1);

        Series newSeries2 = new Series(2);
        newSeries2.setBestOfNumGames(7);
        newSeries2.setDateTime(LocalDateTime.now());
        newSeries2.setIsComplete(false);
        newSeries2.setIsReady(true);
        newSeries2.setNumGamesPlayed(2);
        newSeries2.setRoundNumber(2);
        newSeries2.setSeriesLoserName("");
        newSeries2.setSeriesWinnerName("");
        newSeries2.setTeam1Name("Team1");
        newSeries2.setTeam2Name("Team2");
        newSeries2.setTournamentId(2);
        dao.addSeries(newSeries2);
        
        dao.removeSeries(newSeries2.getSeriesId());
        assertEquals(1, dao.getAllSeries().size());
        assertNull(dao.getSeries(newSeries2.getSeriesId()));

        dao.removeSeries(newSeries1.getSeriesId());
        assertEquals(0, dao.getAllSeries().size());
        assertNull(dao.getSeries(newSeries1.getSeriesId()));
    }


    /**
     * Test of addGame method, of class TournamentDao.
     */
    @Test
    public void testAddGetGame() {
        Game newGame1 = new Game(1);
        newGame1.setAwayScore(10);
        newGame1.setAwayTeam("Team1");
        newGame1.setAwayTeamId(1);
        newGame1.setDateTime(LocalDateTime.now().minusDays(1));
        newGame1.setHomeScore(20);
        newGame1.setHomeTeam("Team2");
        newGame1.setHomeTeamId(2);
        newGame1.setIsComplete(true);
        newGame1.setIsNeutralField(false);
        newGame1.setIsReady(true);
        newGame1.setLosingTeamName("Team1");
        newGame1.setSeriesGameNum(1);
        newGame1.setSeriesId(1);
        newGame1.setWinningTeamName("Team2");
        dao.addGame(newGame1);

        Game fromDao = dao.getGame(newGame1.getGameId());
        assertEquals(newGame1, fromDao);

    }

    /**
     * Test of getAllGames method, of class TournamentDao.
     */
    @Test
    public void testGetAllGames() {
        Game newGame1 = new Game(1);
        newGame1.setAwayScore(10);
        newGame1.setAwayTeam("Team1");
        newGame1.setAwayTeamId(1);
        newGame1.setDateTime(LocalDateTime.now().minusDays(1));
        newGame1.setHomeScore(20);
        newGame1.setHomeTeam("Team2");
        newGame1.setHomeTeamId(2);
        newGame1.setIsComplete(true);
        newGame1.setIsNeutralField(false);
        newGame1.setIsReady(true);
        newGame1.setLosingTeamName("Team1");
        newGame1.setSeriesGameNum(1);
        newGame1.setSeriesId(1);
        newGame1.setWinningTeamName("Team2");
        dao.addGame(newGame1);

        assertEquals(1, dao.getAllGames().size());

        Game newGame2 = new Game(2);
        newGame2.setAwayScore(10);
        newGame2.setAwayTeam("Team1");
        newGame2.setAwayTeamId(1);
        newGame2.setDateTime(LocalDateTime.now().minusDays(1));
        newGame2.setHomeScore(20);
        newGame2.setHomeTeam("Team2");
        newGame2.setHomeTeamId(2);
        newGame2.setIsComplete(true);
        newGame2.setIsNeutralField(false);
        newGame2.setIsReady(true);
        newGame2.setLosingTeamName("Team1");
        newGame2.setSeriesGameNum(2);
        newGame2.setSeriesId(1);
        newGame2.setWinningTeamName("Team2");
        dao.addGame(newGame2);

        assertEquals(2, dao.getAllGames().size());
    }

    /**
     * Test of removeGame method, of class TournamentDao.
     */
    @Test
    public void testRemoveGame() {
        Game newGame1 = new Game(1);
        newGame1.setAwayScore(10);
        newGame1.setAwayTeam("Team1");
        newGame1.setAwayTeamId(1);
        newGame1.setDateTime(LocalDateTime.now().minusDays(1));
        newGame1.setHomeScore(20);
        newGame1.setHomeTeam("Team2");
        newGame1.setHomeTeamId(2);
        newGame1.setIsComplete(true);
        newGame1.setIsNeutralField(false);
        newGame1.setIsReady(true);
        newGame1.setLosingTeamName("Team1");
        newGame1.setSeriesGameNum(1);
        newGame1.setSeriesId(1);
        newGame1.setWinningTeamName("Team2");
        dao.addGame(newGame1);

        Game newGame2 = new Game(2);
        newGame2.setAwayScore(10);
        newGame2.setAwayTeam("Team1");
        newGame2.setAwayTeamId(1);
        newGame2.setDateTime(LocalDateTime.now().minusDays(1));
        newGame2.setHomeScore(20);
        newGame2.setHomeTeam("Team2");
        newGame2.setHomeTeamId(2);
        newGame2.setIsComplete(true);
        newGame2.setIsNeutralField(false);
        newGame2.setIsReady(true);
        newGame2.setLosingTeamName("Team1");
        newGame2.setSeriesGameNum(2);
        newGame2.setSeriesId(1);
        newGame2.setWinningTeamName("Team2");
        dao.addGame(newGame2);

        dao.removeGame(newGame2.getGameId());
        assertEquals(1, dao.getAllGames().size());
        assertNull(dao.getGame(newGame2.getGameId()));

        dao.removeGame(newGame1.getGameId());
        assertEquals(0, dao.getAllGames().size());
        assertNull(dao.getGame(newGame1.getGameId()));
    }

    /**
     * Test of loadTournaments method, of class TournamentDao.
     */
    @Test
    public void testLoadTournaments() {
    }

    /**
     * Test of loadMatchUps method, of class TournamentDao.
     */
    @Test
    public void testLoadMatchUps() {
    }

    /**
     * Test of loadParticipants method, of class TournamentDao.
     */
    @Test
    public void testLoadParticipants() {
    }

    /**
     * Test of loadSeries method, of class TournamentDao.
     */
    @Test
    public void testLoadSeries() {
    }

    /**
     * Test of loadGame method, of class TournamentDao.
     */
    @Test
    public void testLoadGame() {
    }

    /**
     * Test of loadType method, of class TournamentDao.
     */
    @Test
    public void testLoadType() {
    }

    /**
     * Test of loadS1Format method, of class TournamentDao.
     */
    @Test
    public void testLoadS1Format() {
    }

    /**
     * Test of writeTournaments method, of class TournamentDao.
     */
    @Test
    public void testWriteTournaments() {
    }

    /**
     * Test of writeMatchUps method, of class TournamentDao.
     */
    @Test
    public void testWriteMatchUps() {
    }

    /**
     * Test of writeParticipants method, of class TournamentDao.
     */
    @Test
    public void testWriteParticipants() {
    }

    /**
     * Test of writeSeries method, of class TournamentDao.
     */
    @Test
    public void testWriteSeries() {
    }

    /**
     * Test of writeGame method, of class TournamentDao.
     */
    @Test
    public void testWriteGame() {
    }

    /**
     * Test of writeType method, of class TournamentDao.
     */
    @Test
    public void testWriteType() {
    }

    /**
     * Test of writeS1Format method, of class TournamentDao.
     */
    @Test
    public void testWriteS1Format() {
    }

}
