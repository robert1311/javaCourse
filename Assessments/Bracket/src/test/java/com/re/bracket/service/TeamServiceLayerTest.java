/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.service;

import com.re.bracket.dao.NoSuchPlayerException;
import com.re.bracket.dao.NoSuchSeriesException;
import com.re.bracket.dao.NoSuchStatException;
import com.re.bracket.dao.NoSuchTeamException;
import com.re.bracket.dao.TeamAuditDao;
import com.re.bracket.dao.TeamAuditDaoStubImpl;
import com.re.bracket.dao.TeamDao;
import com.re.bracket.dao.TeamDaoStubImpl;
import com.re.bracket.dao.TournamentConfigurationDao;
import com.re.bracket.dao.TournamentConfigurationDaoFileImpl;
import com.re.bracket.dto.Game;
import com.re.bracket.dto.Player;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
import com.re.bracket.dto.Tournament;
import java.time.LocalDateTime;
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
public class TeamServiceLayerTest {

    TeamServiceLayer service;

    public TeamServiceLayerTest() {
//        TeamDao tmDao = new TeamDaoStubImpl();
//        TeamAuditDao tmAudit = new TeamAuditDaoStubImpl();
//        TournamentConfigurationDao config = new TournamentConfigurationDaoFileImpl();
        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");

//        service = new TeamServiceLayerImpl(tmDao, tmAudit, config);
        service = ctx.getBean("tmService", TeamServiceLayer.class);
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
     * Test of ValidateNewTeamName method, of class TeamServiceLayer.
     */
    @Test
    public void testValidateNewTeamName() throws Exception {
        Tournament tournament = new Tournament(1);
        tournament.setActualNumOfParticipants(8);
        tournament.setMaxNumOfParticipants(8);

        try {
            service.validateNewTeamName(tournament, "Mavs");
            fail("Expected EventFullException not thrown.");
        } catch (EventFullException e) {

        }

        tournament.setActualNumOfParticipants(7);

        try {
            service.validateNewTeamName(tournament, "Mavs");
            fail("Expected TeamNameTakenException not thrown.");
        } catch (TeamNameTakenException e) {

        }
    }

    /**
     * Test of validatePlayerInfo method, of class TeamServiceLayer.
     */
    @Test
    public void testGetValidatePlayerInfo() throws Exception {
        Team team = null;
        try {
            team = service.getTeam(5);
            fail("Expected NoSuchTeamException not thrown.");
        } catch (NoSuchTeamException e) {

        }

        try {
            team = service.getTeam(1);
            service.validatePlayerInfo("JohnDoe123", team);
            fail("Expected PlayerNameTakenException not thrown.");
        } catch (PlayerNameTakenException e) {

        }
    }

//    /**
//     * Test of getTeam method, of class TeamServiceLayer.
//     */
//    @Test
//    public void testGetTeam() throws Exception {
//        
//    }
    /**
     * Test of getPlayer method, of class TeamServiceLayer.
     */
    @Test
    public void testGetPlayer() {
        try {
            service.getPlayer(5);
            fail("Expected NoSuchPlayerException not thrown.");
        } catch (NoSuchPlayerException e) {

        }
    }

    /**
     * Test of getTeamsByTournamentId method, of class TeamServiceLayer.
     */
    @Test
    public void testGetTeamsByTournamentId() {
        assertEquals(3, service.getTeamsByTournamentId(1).size());
    }

    /**
     * Test of getAllPlayers method, of class TeamServiceLayer.
     */
    @Test
    public void testGetAllPlayers() {
        assertEquals(4, service.getAllPlayers().size());
    }

    /**
     * Test of getPlayersByTeamId method, of class TeamServiceLayer.
     */
    @Test
    public void testGetPlayersByTeamId() {
        assertEquals(2, service.getPlayersByTeamId(1).size());
        assertEquals(1, service.getPlayersByTeamId(2).size());
    }

    /**
     * Test of updateTeamStats method, of class TeamServiceLayer.
     */
    @Test
    public void testUpdateTeamStats() throws Exception {
        Team game1HomeTeam = service.getTeam(1);
        Team game1AwayTeam = service.getTeam(2);
        Team game2HomeTeam = service.getTeam(4);

        Game played = new Game(1);//First Mavs Game

        played.setAwayScore(-13);
        played.setAwayTeam(game1AwayTeam.getTeamName());
        played.setAwayTeamId(game1AwayTeam.getTeamId());
        played.setDateTime(LocalDateTime.now().minusDays(2));
        played.setHomeScore(17);
        played.setHomeTeam(game1HomeTeam.getTeamName());
        played.setHomeTeamId(1);
        played.setIsComplete(true);
        played.setIsNeutralField(false);
        played.setIsReady(true);
        played.setLosingTeamName("Cowboys");
        played.setSeriesGameNum(1);
        played.setSeriesId(1);
        played.setStageNumber(1);
        played.setWinningTeamName("Mavs");
        assertEquals(2, played.getAwayTeamId());
        assertEquals(1, played.getHomeTeamId());

        service.updateTeamStats(game1HomeTeam, played);
        assertEquals(1, game1HomeTeam.getStatInfo().getGameWins());
        assertEquals(0, game1HomeTeam.getStatInfo().getGameLosses());
        assertEquals(0, game1HomeTeam.getStatInfo().getTies());
        assertEquals(1, game1HomeTeam.getStatInfo().getHomeWins());
        assertEquals(0, game1HomeTeam.getStatInfo().getHomeLosses());
        assertEquals(0, game1HomeTeam.getStatInfo().getAwayWins());
        assertEquals(0, game1HomeTeam.getStatInfo().getAwayLosses());
        assertEquals(0, game1HomeTeam.getStatInfo().getAwayGamesPlayed());
        assertEquals(1, game1HomeTeam.getStatInfo().getHomeGamesPlayed());
        assertEquals(1, game1HomeTeam.getStatInfo().getGamesPlayed());
        assertEquals(1.00, game1HomeTeam.getStatInfo().getWinPercent());
        assertTrue(game1HomeTeam.getStatInfo().isIsWinStreak());
        assertEquals(1, game1HomeTeam.getStatInfo().getStreakCount());
        assertEquals(17, game1HomeTeam.getStatInfo().getTotalPoints());
        assertEquals(17.0, game1HomeTeam.getStatInfo().getPtsPerGame());
        assertEquals(1, game1HomeTeam.getRank());

        service.updateTeamStats(game1AwayTeam, played);
        assertEquals(0, game1AwayTeam.getStatInfo().getGameWins());
        assertEquals(2, game1AwayTeam.getStatInfo().getGameLosses());
        assertEquals(0, game1AwayTeam.getStatInfo().getTies());
        assertEquals(0, game1AwayTeam.getStatInfo().getHomeWins());
        assertEquals(1, game1AwayTeam.getStatInfo().getHomeLosses());
        assertEquals(0, game1AwayTeam.getStatInfo().getAwayWins());
        assertEquals(1, game1AwayTeam.getStatInfo().getAwayLosses());
        assertEquals(1, game1AwayTeam.getStatInfo().getAwayGamesPlayed());
        assertEquals(1, game1AwayTeam.getStatInfo().getHomeGamesPlayed());
        assertEquals(2, game1AwayTeam.getStatInfo().getGamesPlayed());
        assertEquals(0.00, game1AwayTeam.getStatInfo().getWinPercent());
        assertFalse(game1AwayTeam.getStatInfo().isIsWinStreak());
        assertEquals(2, game1AwayTeam.getStatInfo().getStreakCount());
        assertEquals(-8, game1AwayTeam.getStatInfo().getTotalPoints());
        assertEquals(-4, game1AwayTeam.getStatInfo().getPtsPerGame());
        assertEquals(2, game1AwayTeam.getRank());

        ////2nd Mavs Game
        Team game2AwayTeam = game1HomeTeam;
        Game played2 = new Game(2);

        played2.setAwayScore(8);
        played2.setAwayTeam(game2AwayTeam.getTeamName());
        played2.setAwayTeamId(game2AwayTeam.getTeamId());
        played2.setDateTime(LocalDateTime.now().minusDays(2));
        played2.setHomeScore(7);
        played2.setHomeTeam(game2HomeTeam.getTeamName());
        played2.setHomeTeamId(game2HomeTeam.getTeamId());
        played2.setIsComplete(true);
        played2.setIsNeutralField(false);
        played2.setIsReady(true);
        played2.setLosingTeamName("Stars");
        played2.setSeriesGameNum(1);
        played2.setSeriesId(2);
        played2.setStageNumber(1);
        played2.setWinningTeamName("Mavs");
        assertEquals(1, played2.getAwayTeamId());
        assertEquals(4, played2.getHomeTeamId());

        service.updateTeamStats(game2HomeTeam, played2);//stars 1-0(A)
        assertEquals(1, game2HomeTeam.getStatInfo().getGameWins());
        assertEquals(1, game2HomeTeam.getStatInfo().getGameLosses());
        assertEquals(0, game2HomeTeam.getStatInfo().getTies());
        assertEquals(0, game2HomeTeam.getStatInfo().getHomeWins());
        assertEquals(1, game2HomeTeam.getStatInfo().getHomeLosses());
        assertEquals(1, game2HomeTeam.getStatInfo().getAwayWins());
        assertEquals(0, game2HomeTeam.getStatInfo().getAwayLosses());
        assertEquals(1, game2HomeTeam.getStatInfo().getAwayGamesPlayed());
        assertEquals(1, game2HomeTeam.getStatInfo().getHomeGamesPlayed());
        assertEquals(2, game2HomeTeam.getStatInfo().getGamesPlayed());
        assertEquals(0.50, game2HomeTeam.getStatInfo().getWinPercent());
        assertFalse(game2HomeTeam.getStatInfo().isIsWinStreak());
        assertEquals(1, game2HomeTeam.getStatInfo().getStreakCount());
        assertEquals(17, game2HomeTeam.getStatInfo().getTotalPoints());
        assertEquals(8.5, game2HomeTeam.getStatInfo().getPtsPerGame());
//        assertEquals(2, game2HomeTeam.getRank());

        service.updateTeamStats(game2AwayTeam, played2);//Mavs 1-0(H)
        assertEquals(2, game2AwayTeam.getStatInfo().getGameWins());
        assertEquals(0, game2AwayTeam.getStatInfo().getGameLosses());
        assertEquals(0, game2AwayTeam.getStatInfo().getTies());
        assertEquals(1, game2AwayTeam.getStatInfo().getHomeWins());
        assertEquals(0, game2AwayTeam.getStatInfo().getHomeLosses());
        assertEquals(1, game2AwayTeam.getStatInfo().getAwayWins());
        assertEquals(0, game2AwayTeam.getStatInfo().getAwayLosses());
        assertEquals(1, game2AwayTeam.getStatInfo().getAwayGamesPlayed());
        assertEquals(1, game2AwayTeam.getStatInfo().getHomeGamesPlayed());
        assertEquals(1.00, game2AwayTeam.getStatInfo().getWinPercent());
        assertTrue(game2AwayTeam.getStatInfo().isIsWinStreak());
        assertEquals(2, game2AwayTeam.getStatInfo().getStreakCount());
        assertEquals(25, game2AwayTeam.getStatInfo().getTotalPoints());
        assertEquals(12.5, game2AwayTeam.getStatInfo().getPtsPerGame());

    }

    @Test
    public void testUpdateTeamStatsSeries() throws Exception {
        Team team1 = service.getTeam(1);
        Stat stage2Stat1 = new Stat();
        stage2Stat1.setAwayGamesPlayed(2);
        stage2Stat1.setAwayLosses(1);
        stage2Stat1.setAwayWins(1);
        stage2Stat1.setGameLosses(2);
        stage2Stat1.setGameWins(2);
        stage2Stat1.setGamesPlayed(4);
        stage2Stat1.setHomeGamesPlayed(2);
        stage2Stat1.setHomeLosses(1);
        stage2Stat1.setHomeWins(2);
        stage2Stat1.setIsPlayer(false);
        stage2Stat1.setIsWinStreak(false);
        stage2Stat1.setSeriesLosses(0);
        stage2Stat1.setSeriesPlayed(0);
        stage2Stat1.setSeriesWins(0);
        stage2Stat1.setStageNumber(2);
        stage2Stat1.setStreakCount(1);
        stage2Stat1.setTies(0);
        stage2Stat1.setTotalPoints(2);
        stage2Stat1.setWinPercent(0.50);
        team1.setS2StatInfo(stage2Stat1);

        Team team2 = service.getTeam(4);
        Stat stage2Stat2 = new Stat();
        stage2Stat2.setAwayGamesPlayed(2);
        stage2Stat2.setAwayLosses(2);
        stage2Stat2.setAwayWins(1);
        stage2Stat2.setGameLosses(2);
        stage2Stat2.setGameWins(2);
        stage2Stat2.setGamesPlayed(4);
        stage2Stat2.setHomeGamesPlayed(2);
        stage2Stat2.setHomeLosses(1);
        stage2Stat2.setHomeWins(1);
        stage2Stat2.setIsPlayer(false);
        stage2Stat2.setIsWinStreak(true);
        stage2Stat2.setSeriesLosses(0);
        stage2Stat2.setSeriesPlayed(0);
        stage2Stat2.setSeriesWins(0);
        stage2Stat2.setStageNumber(2);
        stage2Stat2.setStreakCount(1);
        stage2Stat2.setTies(0);
        stage2Stat2.setTotalPoints(0);
        stage2Stat2.setWinPercent(0.50);
        team2.setS2StatInfo(stage2Stat2);

        Series newSeries1 = new Series(1);
        newSeries1.setBestOfNumGames(7);
        newSeries1.setDateTime(LocalDateTime.now().minusDays(1));
        newSeries1.setNumGamesPlayed(4);
        newSeries1.setIsReady(true);
        newSeries1.setIsComplete(false);
        newSeries1.setTeam1Name(team1.getTeamName());
        newSeries1.setTeam2Name(team2.getTeamName());

        Tournament tournament = new Tournament(100);
        tournament.setIsSecondStage(false);

        service.updateTeamStats(team1, newSeries1, tournament);

        assertEquals(0, team1.getS2StatInfo().getSeriesLosses());
        assertEquals(0, team1.getS2StatInfo().getSeriesPlayed());
        assertEquals(0, team1.getS2StatInfo().getSeriesWins());

        assertEquals(0, team2.getS2StatInfo().getSeriesLosses());
        assertEquals(0, team2.getS2StatInfo().getSeriesPlayed());
        assertEquals(0, team2.getS2StatInfo().getSeriesWins());

        newSeries1.setNumGamesPlayed(5);

        //This will be done by TournamentSL
        newSeries1.setIsComplete(true);
        newSeries1.setIsReady(true);
        newSeries1.setNumGamesPlayed(6);
        newSeries1.setRoundNumber(1);
        newSeries1.setSeriesLoserName("Stars");
        newSeries1.setSeriesWinnerName("Mavs");
        newSeries1.setTeam1Name(team1.getTeamName());
        newSeries1.setTeam2Name(team2.getTeamName());
        newSeries1.setTournamentId(1);

        tournament.setIsSecondStage(true);

        team1 = service.updateTeamStats(team1, newSeries1, tournament);
        team2 = service.updateTeamStats(team2, newSeries1, tournament);

        assertEquals(0, team1.getS2StatInfo().getSeriesLosses());
        assertEquals(1, team1.getS2StatInfo().getSeriesPlayed());
        assertEquals(1, team1.getS2StatInfo().getSeriesWins());

        assertEquals(1, team2.getS2StatInfo().getSeriesLosses());
        assertEquals(1, team2.getS2StatInfo().getSeriesPlayed());
        assertEquals(0, team2.getS2StatInfo().getSeriesWins());
    }

    /**
     * Test of userJoinTournament method, of class TeamServiceLayer.
     */
    @Test
    public void testUserJoinTournament() throws Exception {
    }

    /**
     * Test of removePlayer method, of class TeamServiceLayer.
     */
    @Test
    public void testRemovePlayer() throws Exception {

        assertNotNull(service.removePlayer(3));
        try {
            service.removePlayer(5);
            fail("Expected NoSuchPlayerException not thrown.");
        } catch (NoSuchPlayerException e) {

        }
    }

    /**
     * Test of removeTeamFromTournament method, of class TeamServiceLayer.
     */
    @Test
    public void testRemoveTeamFromTournament() throws Exception {
        Tournament tournament = new Tournament(1);
        tournament.setActualNumOfParticipants(3);

        assertEquals(2, service.removeTeamFromTournament(tournament, 2)
                .getActualNumOfParticipants() - 1);
        try {
            service.removeTeamFromTournament(tournament, 5);
            fail("Expected NoSuchTeamException not thrown.");
        } catch (NoSuchTeamException e) {

        }
    }

//    /**
//     * Test of updatePlayerStatus method, of class TeamServiceLayer.
//     */
//    @Test
//    public void testUpdatePlayerStatus() {
//        
//    }
//
//    /**
//     * Test of updateTeamStatus method, of class TeamServiceLayer.
//     */
//    @Test
//    public void testUpdateTeamStatus() {
//    }
//
//    /**
//     * Test of getStatById method, of class TeamServiceLayer.
//     */
//    @Test
//    public void testGetStatById() throws Exception {
//    }
    /**
     * Test of getTeamStandings method, of class TeamServiceLayer.
     */
    @Test
    public void testGetTeamStandings() {
        List<Team> unsortedTeamList = new ArrayList<>();

        Team team1 = new Team(11);
        Stat team1Stat = new Stat();
        team1.setStatInfo(team1Stat);
        team1.getStatInfo().setWinPercent(0.70);
        team1.getStatInfo().setPtsPerGame(3);
        team1.getStatInfo().setTotalPoints(15);
        unsortedTeamList.add(team1);

        Team team2 = new Team(12);
        Stat team2Stat = new Stat();
        team2.setStatInfo(team2Stat);
        team2.getStatInfo().setWinPercent(0.44);
        team2.getStatInfo().setPtsPerGame(1);
        team2.getStatInfo().setTotalPoints(2);
        unsortedTeamList.add(team2);

        Team team3 = new Team(13);
        Stat team3Stat = new Stat();
        team3.setStatInfo(team3Stat);
        team3.getStatInfo().setWinPercent(0.70);
        team3.getStatInfo().setPtsPerGame(5);
        team3.getStatInfo().setTotalPoints(10);
        unsortedTeamList.add(team3);

        Team team4 = new Team(14);
        Stat team4Stat = new Stat();
        team4.setStatInfo(team4Stat);
        team4.getStatInfo().setWinPercent(0.91);
        team4.getStatInfo().setPtsPerGame(4);
        team4.getStatInfo().setTotalPoints(9);
        unsortedTeamList.add(team4);

        Team team5 = new Team(15);
        Stat team5Stat = new Stat();
        team5.setStatInfo(team5Stat);
        team5.getStatInfo().setWinPercent(0.44);
        team5.getStatInfo().setPtsPerGame(1);
        team5.getStatInfo().setTotalPoints(4);
        unsortedTeamList.add(team5);

        Tournament tournament = new Tournament(1);
        tournament.setIsSecondStage(false);

        service.getTeamStandings(unsortedTeamList, tournament);

        assertEquals(1, team4.getRank());
        assertEquals(2, team3.getRank());
        assertEquals(3, team1.getRank());
        assertEquals(4, team5.getRank());
        assertEquals(5, team2.getRank());
    }

    /**
     * Test of getPlayerStandings method, of class TeamServiceLayer.
     */
    @Test
    public void testGetPlayerStandings() {
    }

}
