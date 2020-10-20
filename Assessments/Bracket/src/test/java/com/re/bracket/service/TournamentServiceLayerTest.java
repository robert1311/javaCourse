/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.service;

import com.re.bracket.dao.NoSuchSeriesException;
import com.re.bracket.dao.NoSuchTournamentException;
import com.re.bracket.dao.TournamentAuditDao;
import com.re.bracket.dao.TournamentAuditDaoStubImpl;
import com.re.bracket.dao.TournamentConfigurationDao;
import com.re.bracket.dao.TournamentConfigurationDaoStubImpl;
import com.re.bracket.dao.TournamentDao;
import com.re.bracket.dao.TournamentDaoStubImpl;
import com.re.bracket.dao.TournamentPersistenceException;
import com.re.bracket.dto.Game;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
import com.re.bracket.dto.Tournament;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
public class TournamentServiceLayerTest {

    private TournamentServiceLayer service;

    public TournamentServiceLayerTest() {
//        TournamentDao tDao = new TournamentDaoStubImpl();
//        TournamentAuditDao tAudit = new TournamentAuditDaoStubImpl();
//        TournamentConfigurationDao config = new TournamentConfigurationDaoStubImpl();
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");

//        service = new TournamentServiceLayerImpl(tDao, tAudit, config);
        service = ctx.getBean("tService", TournamentServiceLayer.class);
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
     * Test of finalizeSchedule method, of class TournamentServiceLayer.
     */
    @Test
    public void testFinalizeSchedule() throws Exception {
        Tournament newTournament = new Tournament(20);
        newTournament.setS1Format("Round-Robin");
        newTournament.setS2Format("Single-Elimination");
        newTournament.setNumOfCycles(4);
        newTournament.setSeedsToAdvance(8);
        newTournament.setStageType(2);
        newTournament.setTournamentName("newTournament");
        newTournament.setUsername("JohnDoe123");

        List<Team> teams = new ArrayList<>();
        Team team1 = new Team(1);
        team1.setTeamName("Team1");
        team1.setRank(1);
        Stat stat1 = new Stat();
        stat1.setIsPlayer(false);
        team1.setStatInfo(stat1);
        teams.add(team1);

        Team team2 = new Team(2);
        team2.setTeamName("Team2");
        team2.setRank(2);
        Stat stat2 = new Stat();
        stat2.setIsPlayer(false);
        team2.setStatInfo(stat2);

        Team team3 = new Team(3);
        team3.setTeamName("Team3");
        teams.add(team2);
        team3.setRank(3);
        Stat stat3 = new Stat();
        stat3.setIsPlayer(false);
        team3.setStatInfo(stat3);
        teams.add(team3);

        Team team4 = new Team(4);
        team4.setTeamName("Team4");
        team4.setRank(4);
        Stat stat4 = new Stat();
        stat4.setIsPlayer(false);
        team4.setStatInfo(stat4);
        teams.add(team4);

        Team team5 = new Team(5);
        team5.setTeamName("Team5");
        team5.setRank(5);
        Stat stat5 = new Stat();
        stat5.setIsPlayer(false);
        team5.setStatInfo(stat5);
        teams.add(team5);

        Team team6 = new Team(6);
        team6.setTeamName("Team6");
        team6.setRank(6);
        Stat stat6 = new Stat();
        stat6.setIsPlayer(false);
        team6.setStatInfo(stat6);
        teams.add(team6);

        Team team7 = new Team(7);
        team7.setTeamName("Team7");
        team7.setRank(7);
        Stat stat7 = new Stat();
        stat7.setIsPlayer(false);
        team7.setStatInfo(stat7);
        teams.add(team7);

        Team team8 = new Team(8);
        team8.setTeamName("Team8");
        team8.setRank(8);
        Stat stat8 = new Stat();
        stat8.setIsPlayer(false);
        team8.setStatInfo(stat8);
        teams.add(team8);

        Team team9 = new Team(9);
        team9.setTeamName("Team9");
        team9.setRank(9);
        Stat stat9 = new Stat();
        stat9.setIsPlayer(false);
        team9.setStatInfo(stat9);
        teams.add(team9);

        LocalDateTime ldt = LocalDateTime.now();
        newTournament.setStartDate(ldt);
        assertEquals(180, service.finalizeSchedule(
                newTournament, teams).size());
        assertEquals(36, newTournament.getS1NumOfRounds());
        teams.removeAll(teams);

        try {
            ldt = LocalDateTime.now().minusDays(1);
            newTournament.setStartDate(ldt);
            service.finalizeSchedule(newTournament, teams);
            fail("Ecpected InvalidDateException not thrown.");
        } catch (InvalidDateException e) {

        }
    }

    /**
     * Test of startTournament method, of class TournamentServiceLayer.
     */
    @Test
    public void testStartTournament() throws Exception {
        Tournament notStarted = service.getTournament(2);
        try {
            service.startTournament(notStarted);
            fail("Expected TournamentNotReadyException not thrown.");
        } catch (TournamentNotReadyException e) {

        }
        notStarted.setStartDate(LocalDateTime.now());
        service.startTournament(notStarted);
        assertEquals(1, notStarted.getCurrentRound());
        assertFalse(notStarted.isIsSignUp());
    }

    /**
     * Test of getAllTournamentsByUsername method, of class
     * TournamentServiceLayer.
     */
    @Test
    public void testGetAllTournamentsByUsername() {
        List<Tournament> allTournaments = service.getAllTournaments();
        assertEquals(4, allTournaments.size());

        assertEquals(2, service.getAllTournamentsByUsername("JohnDoe123").size());
    }

    /**
     * Test of getTournament method, of class TournamentServiceLayer.
     */
    @Test
    public void testGetTournament() throws Exception {
        assertNotNull(service.getTournament(1));
        assertNull(service.getTournament(12));
    }

    /**
     * Test of buildBracket method, of class TournamentServiceLayer.
     */
    @Test
    public void testBuildBracket() {
    }

    /**
     * Test of getScheduleByTournament method, of class TournamentServiceLayer.
     */
    @Test
    public void testGetScheduleByTournament() {
    }

    @Test
    public void testAddGameToSeries() throws Exception {
        Series series = service.getSeries(5);
        Game newGame = service.addGameToSeries(series);
        List<Game> games = service.getGamesbySeriesId(5);
        assertEquals(newGame.getSeriesGameNum(), games.size() + 1);
    }

    /**
     * Test of getGamesbySeriesId method, of class TournamentServiceLayer.
     */
    @Test
    public void testGetGamesbySeriesId() throws Exception {
        Series series = service.getSeries(3);
        assertNotNull(series);
        List<Game> seriesGames = service
                .getGamesbySeriesId(series.getSeriesId());
        assertEquals(2, seriesGames.size());
    }

    /**
     * Test of updateGame method, of class TournamentServiceLayer.
     */
    @Test
    public void testUpdateGame() throws Exception {
        Game game = service.getGame(1);
        game.setHomeScore(10);
        game.setAwayScore(-5);
        game.setWinningTeamName("Team1");
        game.setLosingTeamName("Team2");
        service.updateGame(game);

        assertTrue(game.isIsComplete());

        game = service.getGame(4);
        try {
            service.updateGame(game);
            fail("Expected PlayersNotCheckedInException not thrown.");
        } catch (PlayersNotCheckedInException e) {

        }

    }

    /**
     * Test of updateSeries method, of class TournamentServiceLayer.
     */
    @Test
    public void testUpdateSeries() throws Exception {
        Series series = service.getSeries(3);
        try {
            service.updateSeries(series);
            fail("Expected SeriesNotReadyException not thrown.");
        } catch (SeriesNotReadyException e) {

        }

        Tournament tournament = service.getTournament(series.getTournamentId());
        tournament.setCurrentRound(1);
        service.updateSeries(series);
        assertEquals(2, series.getNumGamesPlayed());
        assertEquals("Team4", series.getSeriesWinnerName());
        assertEquals("Team5", series.getSeriesLoserName());
    }

    /**
     * Test of deleteTournament method, of class TournamentServiceLayer.
     */
    @Test
    public void testDeleteTournament() throws Exception {
        assertNotNull(service.deleteTournament(1));

    }

    /**
     * Test of loadEntities method, of class TournamentServiceLayer.
     */
    @Test
    public void testLoadEntities() throws Exception {
    }

}
