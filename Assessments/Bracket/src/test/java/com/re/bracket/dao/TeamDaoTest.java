/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import com.re.bracket.dto.Entrant;
import com.re.bracket.dto.Player;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
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
public class TeamDaoTest {
    
    TeamDao dao = new TeamDaoFileImpl();
    
    public TeamDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
//        List<Entrant> entrantList = dao.getAllEntrants();
//        for(Entrant entrant : entrantList){
//            dao.removeEntrant(entrant.getPlayerId());
//        }
        
        List<Player> playerList = dao.getAllPlayers();
        for(Player player : playerList){
            dao.removePlayer(player.getPlayerId());
        }
        
        List<Team> teamList = dao.getAllTeams();
        for(Team team : teamList){
            dao.removeTeam(team.getTeamId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addTeam method, of class TeamDao.
     */
    @Test
    public void testAddGetTeam() {
        Team newTeam = new Team(1);
        newTeam.setCaptainName("JohnDoe123");
        newTeam.setHasPlayers(true);
        newTeam.setMaxPlayers(8);
        newTeam.setRank(1);
        newTeam.setTeamName("Team1");
        
        Stat newStat = new Stat();
        newStat.setAwayGamesPlayed(0);
        newStat.setAwayLosses(0);
        newStat.setAwayWins(0);
        newStat.getGameLosses();
        newStat.setGameWins(0);
        newStat.setGamesPlayed(0);
        newStat.getHomeGamesPlayed();
        newStat.setHomeLosses(0);
        newStat.setHomeWins(0);
        newStat.setIsPlayer(true);
        newStat.setIsWinStreak(true);
        
        newStat.setNegPoints(0);
        newStat.setPosPoints(0);
        newStat.setSeriesLosses(0);
        newStat.setSeriesPlayed(0);
        newStat.setSeriesWins(0);
        newStat.setStageNumber(0);
        newStat.setStreakCount(0);
        newStat.setTies(0);
        newStat.setTotalPoints(0);
        newStat.setWinPercent(0);
        
        newTeam.setStatInfo(newStat);
        
        dao.addTeam(newTeam);
        
        Team fromDao = dao.getTeam(newTeam.getTeamId());
        
        assertEquals(newTeam, fromDao);
         
    }

    /**
     * Test of getAllTeams method, of class TeamDao.
     */
    @Test
    public void testGetAllTeams() {
        Team newTeam1 = new Team(1);
        newTeam1.setCaptainName("JohnDoe123");
        newTeam1.setHasPlayers(true);
        newTeam1.setMaxPlayers(8);
        newTeam1.setRank(1);
        newTeam1.setTeamName("Team1");
        
        Stat newStat1 = new Stat();
        newStat1.setAwayGamesPlayed(0);
        newStat1.setAwayLosses(0);
        newStat1.setAwayWins(0);
        newStat1.getGameLosses();
        newStat1.setGameWins(0);
        newStat1.setGamesPlayed(0);
        newStat1.getHomeGamesPlayed();
        newStat1.setHomeLosses(0);
        newStat1.setHomeWins(0);
        newStat1.setIsPlayer(true);
        newStat1.setIsWinStreak(true);
        newStat1.setNegPoints(0);
        newStat1.setPosPoints(0);
        newStat1.setSeriesLosses(0);
        newStat1.setSeriesPlayed(0);
        newStat1.setSeriesWins(0);
        newStat1.setStageNumber(0);
        newStat1.setStreakCount(0);
        newStat1.setTies(0);
        newStat1.setTotalPoints(0);
        newStat1.setWinPercent(0);
        newTeam1.setStatInfo(newStat1);
        
        dao.addTeam(newTeam1);
        
        assertEquals(1, dao.getAllTeams().size());
        
        Team newTeam2 = new Team(2);
        newTeam2.setCaptainName("JoeSmith456");
        newTeam2.setHasPlayers(true);
        newTeam2.setMaxPlayers(8);
        newTeam2.setRank(1);
        newTeam2.setTeamName("Team2");
        
        Stat newStat2 = new Stat();
        newStat2.setAwayGamesPlayed(0);
        newStat2.setAwayLosses(0);
        newStat2.setAwayWins(0);
        newStat2.getGameLosses();
        newStat2.setGameWins(0);
        newStat2.setGamesPlayed(0);
        newStat2.getHomeGamesPlayed();
        newStat2.setHomeLosses(0);
        newStat2.setHomeWins(0);
        newStat2.setIsPlayer(true);
        newStat2.setIsWinStreak(true);
        newStat2.setNegPoints(0);
        newStat2.setPosPoints(0);
        newStat2.setSeriesLosses(0);
        newStat2.setSeriesPlayed(0);
        newStat2.setSeriesWins(0);
        newStat2.setStageNumber(0);
        newStat2.setStreakCount(0);
        newStat2.setTies(0);
        newStat2.setTotalPoints(0);
        newStat2.setWinPercent(0);
        newTeam2.setStatInfo(newStat2);
        
        dao.addTeam(newTeam2);
        
        assertEquals(2, dao.getAllTeams().size());
        
    }

    /**
     * Test of removeTeam method, of class TeamDao.
     */
    @Test
    public void testRemoveTeam() {
        Team newTeam1 = new Team(1);
        newTeam1.setCaptainName("JohnDoe123");
        newTeam1.setHasPlayers(true);
        newTeam1.setMaxPlayers(8);
        newTeam1.setRank(1);
        newTeam1.setTeamName("Team1");
        
        Stat newStat1 = new Stat();
        newStat1.setAwayGamesPlayed(0);
        newStat1.setAwayLosses(0);
        newStat1.setAwayWins(0);
        newStat1.getGameLosses();
        newStat1.setGameWins(0);
        newStat1.setGamesPlayed(0);
        newStat1.getHomeGamesPlayed();
        newStat1.setHomeLosses(0);
        newStat1.setHomeWins(0);
        newStat1.setIsPlayer(true);
        newStat1.setIsWinStreak(true);
        newStat1.setNegPoints(0);
        newStat1.setPosPoints(0);
        newStat1.setSeriesLosses(0);
        newStat1.setSeriesPlayed(0);
        newStat1.setSeriesWins(0);
        newStat1.setStageNumber(0);
        newStat1.setStreakCount(0);
        newStat1.setTies(0);
        newStat1.setTotalPoints(0);
        newStat1.setWinPercent(0);
        newTeam1.setStatInfo(newStat1);
        
        //create team1
        dao.addTeam(newTeam1);
        
        Team newTeam2 = new Team(2);
        newTeam2.setCaptainName("JoeSmith456");
        newTeam2.setHasPlayers(true);
        newTeam2.setMaxPlayers(8);
        newTeam2.setRank(1);
        newTeam2.setTeamName("Team2");
        
        Stat newStat2 = new Stat();
        newStat2.setAwayGamesPlayed(0);
        newStat2.setAwayLosses(0);
        newStat2.setAwayWins(0);
        newStat2.getGameLosses();
        newStat2.setGameWins(0);
        newStat2.setGamesPlayed(0);
        newStat2.getHomeGamesPlayed();
        newStat2.setHomeLosses(0);
        newStat2.setHomeWins(0);
        newStat2.setIsPlayer(true);
        newStat2.setIsWinStreak(true);
        newStat2.setNegPoints(0);
        newStat2.setPosPoints(0);
        newStat2.setSeriesLosses(0);
        newStat2.setSeriesPlayed(0);
        newStat2.setSeriesWins(0);
        newStat2.setStageNumber(0);
        newStat2.setStreakCount(0);
        newStat2.setTies(0);
        newStat2.setTotalPoints(0);
        newStat2.setWinPercent(0);
        newTeam2.setStatInfo(newStat2);
        
        //create team2
        dao.addTeam(newTeam2);
        
        //remove Stat2 then Team2
        dao.removeTeam(newTeam2.getTeamId());
        
        assertEquals(1, dao.getAllTeams().size());
        assertNull(dao.getTeam(newTeam2.getTeamId()));
        
        //remove Stat1 then Team1
        dao.removeTeam(newTeam1.getTeamId());
        
        assertEquals(0, dao.getAllTeams().size());
        assertNull(dao.getTeam(newTeam1.getTeamId()));
    }

    /**
     * Test of addPlayer method, of class TeamDao.
     */
    @Test
    public void testAddgetPlayer() {
        Player newPlayer = new Player(1);
        newPlayer.setPlayerName("player1");
        newPlayer.setIsHuman(true);
        Stat newStat1 = new Stat();
        newStat1.setAwayGamesPlayed(0);
        newStat1.setAwayLosses(0);
        newStat1.setAwayWins(0);
        newStat1.getGameLosses();
        newStat1.setGameWins(0);
        newStat1.setGamesPlayed(0);
        newStat1.getHomeGamesPlayed();
        newStat1.setHomeLosses(0);
        newStat1.setHomeWins(0);
        newStat1.setIsPlayer(true);
        newStat1.setIsWinStreak(true);
        newStat1.setNegPoints(0);
        newStat1.setPosPoints(0);
        newStat1.setSeriesLosses(0);
        newStat1.setSeriesPlayed(0);
        newStat1.setSeriesWins(0);
        newStat1.setStageNumber(0);
        newStat1.setStreakCount(0);
        newStat1.setTies(0);
        newStat1.setTotalPoints(0);
        newStat1.setWinPercent(0);
        newPlayer.setStatInfo(newStat1);
        
        dao.addPlayer(newPlayer);
        
        Player fromDao = dao.getPlayer(newPlayer.getPlayerId());
        
        assertEquals(newPlayer, fromDao);
    }

    /**
     * Test of getAllPlayers method, of class TeamDao.
     */
    @Test
    public void testGetAllPlayers() {
        
        Player newPlayer1 = new Player(1);
        newPlayer1.setPlayerName("player1");
        newPlayer1.setIsHuman(true);
        Stat newStat1 = new Stat();
        newStat1.setAwayGamesPlayed(0);
        newStat1.setAwayLosses(0);
        newStat1.setAwayWins(0);
        newStat1.getGameLosses();
        newStat1.setGameWins(0);
        newStat1.setGamesPlayed(0);
        newStat1.getHomeGamesPlayed();
        newStat1.setHomeLosses(0);
        newStat1.setHomeWins(0);
        newStat1.setIsPlayer(true);
        newStat1.setIsWinStreak(true);
        newStat1.setNegPoints(0);
        newStat1.setPosPoints(0);
        newStat1.setSeriesLosses(0);
        newStat1.setSeriesPlayed(0);
        newStat1.setSeriesWins(0);
        newStat1.setStageNumber(0);
        newStat1.setStreakCount(0);
        newStat1.setTies(0);
        newStat1.setTotalPoints(0);
        newStat1.setWinPercent(0);
        newPlayer1.setStatInfo(newStat1);
        
        dao.addPlayer(newPlayer1);
        assertEquals(1, dao.getAllPlayers().size());
        
        Player newPlayer2 = new Player(2);
        newPlayer2.setPlayerName("player2");
        newPlayer2.setIsHuman(true);
        Stat newStat2 = new Stat();
        newStat2.setAwayGamesPlayed(0);
        newStat2.setAwayLosses(0);
        newStat2.setAwayWins(0);
        newStat2.getGameLosses();
        newStat2.setGameWins(0);
        newStat2.setGamesPlayed(0);
        newStat2.getHomeGamesPlayed();
        newStat2.setHomeLosses(0);
        newStat2.setHomeWins(0);
        newStat2.setIsPlayer(true);
        newStat2.setIsWinStreak(true);
        newStat2.setNegPoints(0);
        newStat2.setPosPoints(0);
        newStat2.setSeriesLosses(0);
        newStat2.setSeriesPlayed(0);
        newStat2.setSeriesWins(0);
        newStat2.setStageNumber(0);
        newStat2.setStreakCount(0);
        newStat2.setTies(0);
        newStat2.setTotalPoints(0);
        newStat2.setWinPercent(0);
        newPlayer1.setStatInfo(newStat2);
        
        dao.addPlayer(newPlayer2);
        
        assertEquals(2, dao.getAllPlayers().size());
    }

    /**
     * Test of removePlayer method, of class TeamDao.
     */
    @Test
    public void testRemovePlayer() {
        Player newPlayer1 = new Player(1);
        newPlayer1.setPlayerName("player1");
        newPlayer1.setIsHuman(true);
        Stat newStat1 = new Stat();
        newStat1.setAwayGamesPlayed(0);
        newStat1.setAwayLosses(0);
        newStat1.setAwayWins(0);
        newStat1.getGameLosses();
        newStat1.setGameWins(0);
        newStat1.setGamesPlayed(0);
        newStat1.getHomeGamesPlayed();
        newStat1.setHomeLosses(0);
        newStat1.setHomeWins(0);
        newStat1.setIsPlayer(true);
        newStat1.setIsWinStreak(true);
        newStat1.setNegPoints(0);
        newStat1.setPosPoints(0);
        newStat1.setSeriesLosses(0);
        newStat1.setSeriesPlayed(0);
        newStat1.setSeriesWins(0);
        newStat1.setStageNumber(0);
        newStat1.setStreakCount(0);
        newStat1.setTies(0);
        newStat1.setTotalPoints(0);
        newStat1.setWinPercent(0);
        newPlayer1.setStatInfo(newStat1);
        
        dao.addPlayer(newPlayer1);
        
        Player newPlayer2 = new Player(2);
        newPlayer2.setPlayerName("player2");
        newPlayer2.setIsHuman(true);
        Stat newStat2 = new Stat();
        newStat2.setAwayGamesPlayed(0);
        newStat2.setAwayLosses(0);
        newStat2.setAwayWins(0);
        newStat2.getGameLosses();
        newStat2.setGameWins(0);
        newStat2.setGamesPlayed(0);
        newStat2.getHomeGamesPlayed();
        newStat2.setHomeLosses(0);
        newStat2.setHomeWins(0);
        newStat2.setIsPlayer(true);
        newStat2.setIsWinStreak(true);
        newStat2.setNegPoints(0);
        newStat2.setPosPoints(0);
        newStat2.setSeriesLosses(0);
        newStat2.setSeriesPlayed(0);
        newStat2.setSeriesWins(0);
        newStat2.setStageNumber(0);
        newStat2.setStreakCount(0);
        newStat2.setTies(0);
        newStat2.setTotalPoints(0);
        newStat2.setWinPercent(0);
        newPlayer1.setStatInfo(newStat2);
        
        dao.addPlayer(newPlayer1);
        
        dao.removePlayer(newPlayer2.getPlayerId());
        assertEquals(1, dao.getAllPlayers().size());
        assertNull(dao.getPlayer(newPlayer2.getPlayerId()));
        
        dao.removePlayer(newPlayer1.getPlayerId());
        assertEquals(0, dao.getAllPlayers().size());
        assertNull(dao.getPlayer(newPlayer1.getPlayerId()));
        
    }

//    /**
//     * Test of getEntrantByPlayerId method, of class TeamDao.
//     */
//    @Test
//    public void testAddGetEntrant() {
//        Team newTeam = new Team(1);
//        newTeam.setCaptainName("JohnDoe123");
//        newTeam.setHasPlayers(true);
//        newTeam.setMaxPlayers(8);
//        newTeam.setRank(1);
//        newTeam.setTeamName("Team1");
//        
//        Stat newStat1 = new Stat();
//        newStat1.setAwayGamesPlayed(0);
//        newStat1.setAwayLosses(0);
//        newStat1.setAwayWins(0);
//        newStat1.getGameLosses();
//        newStat1.setGameWins(0);
//        newStat1.setGamesPlayed(0);
//        newStat1.getHomeGamesPlayed();
//        newStat1.setHomeLosses(0);
//        newStat1.setHomeWins(0);
//        newStat1.setIsPlayer(true);
//        newStat1.setIsWinStreak(true);
//        newStat1.setLosses(0);
//        newStat1.setNegPoints(0);
//        newStat1.setPosPoints(0);
//        newStat1.setSeriesLosses(0);
//        newStat1.setSeriesPlayed(0);
//        newStat1.setSeriesWins(0);
//        newStat1.setStageNumber(0);
//        newStat1.setStreakCount(0);
//        newStat1.setTies(0);
//        newStat1.setTotalPoints(0);
//        newStat1.setWinPercent(0);
//        
//        newTeam.setStatInfo(newStat1);
//        
//        Player newPlayer1 = new Player(1);
//        newPlayer1.setPlayerName("player1");
//        newPlayer1.setIsHuman(true);
//        Stat newStat2 = new Stat();
//        newStat2.setAwayGamesPlayed(0);
//        newStat2.setAwayLosses(0);
//        newStat2.setAwayWins(0);
//        newStat2.getGameLosses();
//        newStat2.setGameWins(0);
//        newStat2.setGamesPlayed(0);
//        newStat2.getHomeGamesPlayed();
//        newStat2.setHomeLosses(0);
//        newStat2.setHomeWins(0);
//        newStat2.setIsPlayer(true);
//        newStat2.setIsWinStreak(true);
//        newStat2.setLosses(0);
//        newStat2.setNegPoints(0);
//        newStat2.setPosPoints(0);
//        newStat2.setSeriesLosses(0);
//        newStat2.setSeriesPlayed(0);
//        newStat2.setSeriesWins(0);
//        newStat2.setStageNumber(0);
//        newStat2.setStreakCount(0);
//        newStat2.setTies(0);
//        newStat2.setTotalPoints(0);
//        newStat2.setWinPercent(0);
//        newPlayer1.setStatInfo(newStat2);
//        
//        dao.addPlayer(newPlayer1);
//        
//        dao.addTeam(newTeam);
//        Entrant newEntrant = new Entrant(newTeam.getTeamId(), newPlayer1.getPlayerId());
//        dao.addEntrant(newEntrant);
//        
//        Entrant fromDao = dao.getEntrant(newPlayer1.getPlayerId());
//        assertEquals(newEntrant, fromDao);
//        assertEquals(newTeam.getTeamId(), fromDao.getTeamId());
//        
//    }
//
//    /**
//     * Test of getAllEntrants method, of class TeamDao.
//     */
//    @Test
//    public void testGetAllEntrants() {
//        Team newTeam1 = new Team(1);
//        newTeam1.setCaptainName("JohnDoe123");
//        newTeam1.setHasPlayers(true);
//        newTeam1.setMaxPlayers(8);
//        newTeam1.setRank(1);
//        newTeam1.setTeamName("Team1");
//        
//        Stat newStat1 = new Stat();
//        newStat1.setAwayGamesPlayed(0);
//        newStat1.setAwayLosses(0);
//        newStat1.setAwayWins(0);
//        newStat1.getGameLosses();
//        newStat1.setGameWins(0);
//        newStat1.setGamesPlayed(0);
//        newStat1.getHomeGamesPlayed();
//        newStat1.setHomeLosses(0);
//        newStat1.setHomeWins(0);
//        newStat1.setIsPlayer(true);
//        newStat1.setIsWinStreak(true);
//        newStat1.setLosses(0);
//        newStat1.setNegPoints(0);
//        newStat1.setPosPoints(0);
//        newStat1.setSeriesLosses(0);
//        newStat1.setSeriesPlayed(0);
//        newStat1.setSeriesWins(0);
//        newStat1.setStageNumber(0);
//        newStat1.setStreakCount(0);
//        newStat1.setTies(0);
//        newStat1.setTotalPoints(0);
//        newStat1.setWinPercent(0);
//        
//        newTeam1.setStatInfo(newStat1);
//        
//        Player newPlayer1 = new Player(1);
//        newPlayer1.setPlayerName("player1");
//        newPlayer1.setIsHuman(true);
//        Stat newStat2 = new Stat();
//        newStat2.setAwayGamesPlayed(0);
//        newStat2.setAwayLosses(0);
//        newStat2.setAwayWins(0);
//        newStat2.getGameLosses();
//        newStat2.setGameWins(0);
//        newStat2.setGamesPlayed(0);
//        newStat2.getHomeGamesPlayed();
//        newStat2.setHomeLosses(0);
//        newStat2.setHomeWins(0);
//        newStat2.setIsPlayer(true);
//        newStat2.setIsWinStreak(true);
//        newStat2.setLosses(0);
//        newStat2.setNegPoints(0);
//        newStat2.setPosPoints(0);
//        newStat2.setSeriesLosses(0);
//        newStat2.setSeriesPlayed(0);
//        newStat2.setSeriesWins(0);
//        newStat2.setStageNumber(0);
//        newStat2.setStreakCount(0);
//        newStat2.setTies(0);
//        newStat2.setTotalPoints(0);
//        newStat2.setWinPercent(0);
//        newPlayer1.setStatInfo(newStat2);
//        
//        dao.addPlayer(newPlayer1);
//        
//        dao.addTeam(newTeam1);
//        Entrant newEntrant = new Entrant(newTeam1.getTeamId(), newPlayer1.getPlayerId());
//        dao.addEntrant(newEntrant);
//        assertEquals(1, dao.getAllEntrants().size());
//        
//        Team newTeam2 = new Team(2);
//        newTeam2.setCaptainName("JoeSmith456");
//        newTeam2.setHasPlayers(true);
//        newTeam2.setMaxPlayers(8);
//        newTeam2.setRank(1);
//        newTeam2.setTeamName("Team2");
//        
//        Stat newStat3 = new Stat(3);
//        newStat3.setAwayGamesPlayed(0);
//        newStat3.setAwayLosses(0);
//        newStat3.setAwayWins(0);
//        newStat3.getGameLosses();
//        newStat3.setGameWins(0);
//        newStat3.setGamesPlayed(0);
//        newStat3.getHomeGamesPlayed();
//        newStat3.setHomeLosses(0);
//        newStat3.setHomeWins(0);
//        newStat3.setIsPlayer(true);
//        newStat3.setIsWinStreak(true);
//        newStat3.setLosses(0);
//        newStat3.setNegPoints(0);
//        newStat3.setPosPoints(0);
//        newStat3.setSeriesLosses(0);
//        newStat3.setSeriesPlayed(0);
//        newStat3.setSeriesWins(0);
//        newStat3.setStageNumber(0);
//        newStat3.setStreakCount(0);
//        newStat3.setTies(0);
//        newStat3.setTotalPoints(0);
//        newStat3.setWinPercent(0);
//        
//        newTeam2.setStatInfo(newStat3);
//        
//        Player newPlayer2 = new Player(2);
//        newPlayer2.setPlayerName("player1");
//        newPlayer2.setIsHuman(true);
//        Stat newStat4 = new Stat(4);
//        newStat4.setAwayGamesPlayed(0);
//        newStat4.setAwayLosses(0);
//        newStat4.setAwayWins(0);
//        newStat4.getGameLosses();
//        newStat4.setGameWins(0);
//        newStat4.setGamesPlayed(0);
//        newStat4.getHomeGamesPlayed();
//        newStat4.setHomeLosses(0);
//        newStat4.setHomeWins(0);
//        newStat4.setIsPlayer(true);
//        newStat4.setIsWinStreak(true);
//        newStat4.setLosses(0);
//        newStat4.setNegPoints(0);
//        newStat4.setPosPoints(0);
//        newStat4.setSeriesLosses(0);
//        newStat4.setSeriesPlayed(0);
//        newStat4.setSeriesWins(0);
//        newStat4.setStageNumber(0);
//        newStat4.setStreakCount(0);
//        newStat4.setTies(0);
//        newStat4.setTotalPoints(0);
//        newStat4.setWinPercent(0);
//        newPlayer2.setStatInfo(newStat4);
//        
//        dao.addPlayer(newPlayer2);
//        
//        dao.addTeam(newTeam2);
//        Entrant newEntrant2 = new Entrant(newTeam2.getTeamId(), newPlayer2.getPlayerId());
//        dao.addEntrant(newEntrant2);
//        
//        assertEquals(2, dao.getAllEntrants().size());
//        
//    }
//
//    /**
//     * Test of removeEntrant method, of class TeamDao.
//     */
//    @Test
//    public void testRemoveEntrant() {
//        Team newTeam1 = new Team(1);
//        newTeam1.setCaptainName("JohnDoe123");
//        newTeam1.setHasPlayers(true);
//        newTeam1.setMaxPlayers(8);
//        newTeam1.setRank(1);
//        newTeam1.setTeamName("Team1");
//        
//        Stat newStat1 = new Stat();
//        newStat1.setAwayGamesPlayed(0);
//        newStat1.setAwayLosses(0);
//        newStat1.setAwayWins(0);
//        newStat1.getGameLosses();
//        newStat1.setGameWins(0);
//        newStat1.setGamesPlayed(0);
//        newStat1.getHomeGamesPlayed();
//        newStat1.setHomeLosses(0);
//        newStat1.setHomeWins(0);
//        newStat1.setIsPlayer(true);
//        newStat1.setIsWinStreak(true);
//        newStat1.setLosses(0);
//        newStat1.setNegPoints(0);
//        newStat1.setPosPoints(0);
//        newStat1.setSeriesLosses(0);
//        newStat1.setSeriesPlayed(0);
//        newStat1.setSeriesWins(0);
//        newStat1.setStageNumber(0);
//        newStat1.setStreakCount(0);
//        newStat1.setTies(0);
//        newStat1.setTotalPoints(0);
//        newStat1.setWinPercent(0);
//        
//        newTeam1.setStatInfo(newStat1);
//        
//        Player newPlayer1 = new Player(1);
//        newPlayer1.setPlayerName("player1");
//        newPlayer1.setIsHuman(true);
//        Stat newStat2 = new Stat();
//        newStat2.setAwayGamesPlayed(0);
//        newStat2.setAwayLosses(0);
//        newStat2.setAwayWins(0);
//        newStat2.getGameLosses();
//        newStat2.setGameWins(0);
//        newStat2.setGamesPlayed(0);
//        newStat2.getHomeGamesPlayed();
//        newStat2.setHomeLosses(0);
//        newStat2.setHomeWins(0);
//        newStat2.setIsPlayer(true);
//        newStat2.setIsWinStreak(true);
//        newStat2.setLosses(0);
//        newStat2.setNegPoints(0);
//        newStat2.setPosPoints(0);
//        newStat2.setSeriesLosses(0);
//        newStat2.setSeriesPlayed(0);
//        newStat2.setSeriesWins(0);
//        newStat2.setStageNumber(0);
//        newStat2.setStreakCount(0);
//        newStat2.setTies(0);
//        newStat2.setTotalPoints(0);
//        newStat2.setWinPercent(0);
//        newPlayer1.setStatInfo(newStat2);
//        
//        dao.addPlayer(newPlayer1);
//        
//        dao.addTeam(newTeam1);
//        Entrant newEntrant1 = new Entrant(newTeam1.getTeamId(), newPlayer1.getPlayerId());
//        dao.addEntrant(newEntrant1);
//        
//        Team newTeam2 = new Team(2);
//        newTeam2.setCaptainName("JoeSmith456");
//        newTeam2.setHasPlayers(true);
//        newTeam2.setMaxPlayers(8);
//        newTeam2.setRank(1);
//        newTeam2.setTeamName("Team2");
//        
//        Stat newStat3 = new Stat(3);
//        newStat3.setAwayGamesPlayed(0);
//        newStat3.setAwayLosses(0);
//        newStat3.setAwayWins(0);
//        newStat3.getGameLosses();
//        newStat3.setGameWins(0);
//        newStat3.setGamesPlayed(0);
//        newStat3.getHomeGamesPlayed();
//        newStat3.setHomeLosses(0);
//        newStat3.setHomeWins(0);
//        newStat3.setIsPlayer(true);
//        newStat3.setIsWinStreak(true);
//        newStat3.setLosses(0);
//        newStat3.setNegPoints(0);
//        newStat3.setPosPoints(0);
//        newStat3.setSeriesLosses(0);
//        newStat3.setSeriesPlayed(0);
//        newStat3.setSeriesWins(0);
//        newStat3.setStageNumber(0);
//        newStat3.setStreakCount(0);
//        newStat3.setTies(0);
//        newStat3.setTotalPoints(0);
//        newStat3.setWinPercent(0);
//        
//        newTeam2.setStatInfo(newStat3);
//        
//        Player newPlayer2 = new Player(2);
//        newPlayer2.setPlayerName("player1");
//        newPlayer2.setIsHuman(true);
//        Stat newStat4 = new Stat(4);
//        newStat4.setAwayGamesPlayed(0);
//        newStat4.setAwayLosses(0);
//        newStat4.setAwayWins(0);
//        newStat4.getGameLosses();
//        newStat4.setGameWins(0);
//        newStat4.setGamesPlayed(0);
//        newStat4.getHomeGamesPlayed();
//        newStat4.setHomeLosses(0);
//        newStat4.setHomeWins(0);
//        newStat4.setIsPlayer(true);
//        newStat4.setIsWinStreak(true);
//        newStat4.setLosses(0);
//        newStat4.setNegPoints(0);
//        newStat4.setPosPoints(0);
//        newStat4.setSeriesLosses(0);
//        newStat4.setSeriesPlayed(0);
//        newStat4.setSeriesWins(0);
//        newStat4.setStageNumber(0);
//        newStat4.setStreakCount(0);
//        newStat4.setTies(0);
//        newStat4.setTotalPoints(0);
//        newStat4.setWinPercent(0);
//        newPlayer2.setStatInfo(newStat4);
//        
//        dao.addPlayer(newPlayer2);
//        
//        dao.addTeam(newTeam2);
//        Entrant newEntrant2 = new Entrant(newTeam2.getTeamId(), newPlayer2.getPlayerId());
//        dao.addEntrant(newEntrant2);
//        
//        dao.removeEntrant(newEntrant2.getPlayerId());
//        assertEquals(1, dao.getAllEntrants().size());
//        assertNull(dao.getEntrant(newEntrant2.getPlayerId()));
//        
//        dao.removeEntrant(newEntrant1.getPlayerId());
//        assertEquals(0, dao.getAllEntrants().size());
//        assertNull(dao.getEntrant(newEntrant1.getPlayerId()));
//    }
//
//    /**
//     * Test of addStat method, of class TeamDao.
//     */
//    @Test
//    public void testAddGetStat() {
//        Stat newStat = new Stat();
//        newStat.setAwayGamesPlayed(0);
//        newStat.setAwayLosses(0);
//        newStat.setAwayWins(0);
//        newStat.getGameLosses();
//        newStat.setGameWins(0);
//        newStat.setGamesPlayed(0);
//        newStat.getHomeGamesPlayed();
//        newStat.setHomeLosses(0);
//        newStat.setHomeWins(0);
//        newStat.setIsPlayer(true);
//        newStat.setIsWinStreak(true);
//        
//        newStat.setNegPoints(0);
//        newStat.setPosPoints(0);
//        newStat.setSeriesLosses(0);
//        newStat.setSeriesPlayed(0);
//        newStat.setSeriesWins(0);
//        newStat.setStageNumber(0);
//        newStat.setStreakCount(0);
//        newStat.setTies(0);
//        newStat.setTotalPoints(0);
//        newStat.setWinPercent(0);
//        
//        dao.addStat(newStat);
//        
//        Stat fromDao = dao.getStat(newStat.getStatId());
//        
//        assertEquals(newStat, fromDao);
//    }
//
//    /**
//     * Test of getAllStats method, of class TeamDao.
//     */
//    @Test
//    public void testGetAllStats() {
//        
//        Stat newStat1 = new Stat();
//        newStat1.setAwayGamesPlayed(0);
//        newStat1.setAwayLosses(0);
//        newStat1.setAwayWins(0);
//        newStat1.getGameLosses();
//        newStat1.setGameWins(0);
//        newStat1.setGamesPlayed(0);
//        newStat1.getHomeGamesPlayed();
//        newStat1.setHomeLosses(0);
//        newStat1.setHomeWins(0);
//        newStat1.setIsPlayer(true);
//        newStat1.setIsWinStreak(true);
//        newStat1.setLosses(0);
//        newStat1.setNegPoints(0);
//        newStat1.setPosPoints(0);
//        newStat1.setSeriesLosses(0);
//        newStat1.setSeriesPlayed(0);
//        newStat1.setSeriesWins(0);
//        newStat1.setStageNumber(0);
//        newStat1.setStreakCount(0);
//        newStat1.setTies(0);
//        newStat1.setTotalPoints(0);
//        newStat1.setWinPercent(0);
//        
//        dao.addStat(newStat1);
//        assertEquals(1, dao.getAllStats().size());
//        
//        Stat newStat2 = new Stat();
//        newStat2.setAwayGamesPlayed(0);
//        newStat2.setAwayLosses(0);
//        newStat2.setAwayWins(0);
//        newStat2.getGameLosses();
//        newStat2.setGameWins(0);
//        newStat2.setGamesPlayed(0);
//        newStat2.getHomeGamesPlayed();
//        newStat2.setHomeLosses(0);
//        newStat2.setHomeWins(0);
//        newStat2.setIsPlayer(true);
//        newStat2.setIsWinStreak(true);
//        newStat2.setLosses(0);
//        newStat2.setNegPoints(0);
//        newStat2.setPosPoints(0);
//        newStat2.setSeriesLosses(0);
//        newStat2.setSeriesPlayed(0);
//        newStat2.setSeriesWins(0);
//        newStat2.setStageNumber(0);
//        newStat2.setStreakCount(0);
//        newStat2.setTies(0);
//        newStat2.setTotalPoints(0);
//        newStat2.setWinPercent(0);
//        
//        dao.addStat(newStat2);
//        assertEquals(2, dao.getAllStats().size());
//    }
//
//    /**
//     * Test of removeStat method, of class TeamDao.
//     */
//    @Test
//    public void testRemoveStat() {
//    
//        Stat newStat1 = new Stat();
//        newStat1.setAwayGamesPlayed(0);
//        newStat1.setAwayLosses(0);
//        newStat1.setAwayWins(0);
//        newStat1.getGameLosses();
//        newStat1.setGameWins(0);
//        newStat1.setGamesPlayed(0);
//        newStat1.getHomeGamesPlayed();
//        newStat1.setHomeLosses(0);
//        newStat1.setHomeWins(0);
//        newStat1.setIsPlayer(true);
//        newStat1.setIsWinStreak(true);
//        newStat1.setLosses(0);
//        newStat1.setNegPoints(0);
//        newStat1.setPosPoints(0);
//        newStat1.setSeriesLosses(0);
//        newStat1.setSeriesPlayed(0);
//        newStat1.setSeriesWins(0);
//        newStat1.setStageNumber(0);
//        newStat1.setStreakCount(0);
//        newStat1.setTies(0);
//        newStat1.setTotalPoints(0);
//        newStat1.setWinPercent(0);
//        
//        dao.addStat(newStat1);
//        
//        Stat newStat2 = new Stat();
//        newStat2.setAwayGamesPlayed(0);
//        newStat2.setAwayLosses(0);
//        newStat2.setAwayWins(0);
//        newStat2.getGameLosses();
//        newStat2.setGameWins(0);
//        newStat2.setGamesPlayed(0);
//        newStat2.getHomeGamesPlayed();
//        newStat2.setHomeLosses(0);
//        newStat2.setHomeWins(0);
//        newStat2.setIsPlayer(true);
//        newStat2.setIsWinStreak(true);
//        newStat2.setLosses(0);
//        newStat2.setNegPoints(0);
//        newStat2.setPosPoints(0);
//        newStat2.setSeriesLosses(0);
//        newStat2.setSeriesPlayed(0);
//        newStat2.setSeriesWins(0);
//        newStat2.setStageNumber(0);
//        newStat2.setStreakCount(0);
//        newStat2.setTies(0);
//        newStat2.setTotalPoints(0);
//        newStat2.setWinPercent(0);
//        
//        dao.addStat(newStat2);
//        
//        dao.removeStat(newStat2.getStatId());
//        assertEquals(1, dao.getAllStats().size());
//        assertNull(dao.getStat(newStat2.getStatId()));
//        
//        dao.removeStat(newStat1.getStatId());
//        assertEquals(0, dao.getAllStats().size());
//        assertNull(dao.getStat(newStat1.getStatId()));
//    }

    /**
     * Test of loadTeams method, of class TeamDao.
     */
    @Test
    public void testLoadTeams() {
    }

    /**
     * Test of loadPlayers method, of class TeamDao.
     */
    @Test
    public void testLoadPlayers() {
    }

    /**
     * Test of loadEntrants method, of class TeamDao.
     */
    @Test
    public void testLoadEntrants() {
    }

    /**
     * Test of loadStats method, of class TeamDao.
     */
    @Test
    public void testLoadStats() {
    }

    /**
     * Test of writeTeams method, of class TeamDao.
     */
    @Test
    public void testWriteTeams() {
    }

    /**
     * Test of writePlayers method, of class TeamDao.
     */
    @Test
    public void testWritePlayers() {
    }

    /**
     * Test of writeEntrants method, of class TeamDao.
     */
    @Test
    public void testWriteEntrants() {
    }

    /**
     * Test of writeStats method, of class TeamDao.
     */
    @Test
    public void testWriteStats() {
    }

}
