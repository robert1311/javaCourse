/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import com.re.bracket.dto.Player;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rober
 */
public class TeamDaoStubImpl implements TeamDao {

    List<Team> teams = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    Team team1;
    Team team2;
    Team team3;
    Team team4;
    Player player1;
    Player player2;
    Player player3;
    Player player4;

    public TeamDaoStubImpl() {
        team1 = new Team(1);
        team1.setCaptainName("JohnDoe123");
        team1.setHasPlayers(true);
        team1.setMaxPlayers(15);
        team1.setRank(1);
        team1.setTeamName("Mavs");
        team1.setTournamentId(1);
        Stat teamStat1 = new Stat();
        teamStat1.setStageNumber(1);
        teamStat1.setIsWinStreak(true);
        team1.setStatInfo(teamStat1);

        teams.add(team1);

        team2 = new Team(2);
        team2.setCaptainName("JoeSmith456");
        team2.setHasPlayers(true);
        team2.setMaxPlayers(15);
        team2.setRank(2);
        team2.setTeamName("Cowboys");
        team2.setTournamentId(1);
        Stat teamStat2 = new Stat();
        teamStat2.setAwayGamesPlayed(0);
        teamStat2.setAwayLosses(0);
        teamStat2.setAwayWins(0);
        teamStat2.setGameLosses(1);
        teamStat2.setGameWins(0);
        teamStat2.setGamesPlayed(1);
        teamStat2.setHomeGamesPlayed(1);
        teamStat2.setHomeLosses(1);
        teamStat2.setHomeWins(0);
        teamStat2.setIsPlayer(false);
        teamStat2.setIsWinStreak(false);
        teamStat2.setSeriesLosses(0);
        teamStat2.setSeriesPlayed(0);
        teamStat2.setSeriesWins(0);
        teamStat2.setStageNumber(0);
        teamStat2.setStreakCount(1);
        teamStat2.setTies(0);
        teamStat2.setTotalPoints(5);
        teamStat2.setWinPercent(0);
        team2.setStatInfo(teamStat2);

        teams.add(team2);

        team3 = new Team(3);
        team3.setCaptainName("SallyMae789");
        team3.setHasPlayers(true);
        team3.setMaxPlayers(15);
        team3.setRank(1);
        team3.setTeamName("Cowboys");
        team3.setTournamentId(2);
        Stat teamStat3 = new Stat();
        team3.setStatInfo(teamStat3);

        teams.add(team3);
        
        team4 = new Team(4);
        team4.setCaptainName("SallyMae789");
        team4.setHasPlayers(true);
        team4.setMaxPlayers(15);
        team4.setRank(1);
        team4.setTeamName("Stars");
        team4.setTournamentId(1);
        Stat teamStat4 = new Stat();
        teamStat4.setAwayGamesPlayed(1);
        teamStat4.setAwayLosses(0);
        teamStat4.setAwayWins(1);
        teamStat4.setGameLosses(0);
        teamStat4.setGameWins(1);
        teamStat4.setGamesPlayed(1);
        teamStat4.setHomeGamesPlayed(0);
        teamStat4.setHomeLosses(0);
        teamStat4.setHomeWins(0);
        teamStat4.setIsPlayer(true);
        teamStat4.setIsWinStreak(true);
        teamStat4.setSeriesLosses(0);
        teamStat4.setSeriesPlayed(0);
        teamStat4.setSeriesWins(0);
        teamStat4.setStageNumber(1);
        teamStat4.setStreakCount(1);
        teamStat4.setTies(0);
        teamStat4.setTotalPoints(10);
        teamStat4.setWinPercent(1.00);
        team4.setStatInfo(teamStat4);

        teams.add(team4);

        player1 = new Player(1);
        player1.setPlayerName("JohnDoe123");
        player1.setIsHuman(true);
        player1.setTeamId(1);
        Stat playerStat1 = new Stat();
        player1.setStatInfo(playerStat1);

        players.add(player1);

        player2 = new Player(2);
        player2.setPlayerName("JohnDoe321");
        player2.setIsHuman(true);
        player2.setTeamId(1);
        Stat playerStat2 = new Stat();
        player2.setStatInfo(playerStat2);

        players.add(player2);

        player3 = new Player(3);
        player3.setPlayerName("JoeSmith456");
        player3.setIsHuman(true);
        player3.setTeamId(2);
        Stat playerStat3 = new Stat();
        player3.setStatInfo(playerStat3);

        players.add(player3);

        player4 = new Player(4);
        player4.setPlayerName("SallyMae789");
        player4.setIsHuman(true);
        player4.setTeamId(3);
        Stat playerStat4 = new Stat();
        player4.setStatInfo(playerStat4);

        players.add(player4);
    }

    @Override
    public Team addTeam(Team team) {
        if (team.equals(team1)) {
            return team1;
        } else if (team.equals(team2)) {
            return team2;
        } else if (team.equals(team3)) {
            return team3;
        } else if (team.equals(team4)) {
            return team4;
        } else{
            return null;
        }
    }

    @Override
    public Team getTeam(int teamId) {
        if (teamId == team1.getTeamId()) {
            return team1;
        } else if(teamId == team2.getTeamId()){
            return team2;
        }  else if(teamId == team3.getTeamId()){
            return team3;
        }  else if(teamId == team4.getTeamId()){
            return team4;
        } else {
            return null;
        }
    }

    @Override
    public List<Team> getAllTeams() {
        return teams;
    }

    @Override
    public Team removeTeam(int teamId) {
        if (teamId == team1.getTeamId()) {
            return team1;
        } else if(teamId == team2.getTeamId()){
            return team2;
        }  else if(teamId == team3.getTeamId()){
            return team3;
        }  else if(teamId == team4.getTeamId()){
            return team4;
        } else {
            return null;
        }
    }

    @Override
    public Player addPlayer(Player player) {
        if (player.equals(player1)) {
            return player1;
        } else if (player.equals(player2)) {
            return player2;
        } else if (player.equals(player3)) {
            return player3;
        } else if (player.equals(player4)) {
            return player4;
        } else{
            return null;
        }
    }

    @Override
    public Player getPlayer(int playerId) {
        if (playerId == player1.getTeamId()) {
            return player1;
        } else if(playerId == player2.getTeamId()){
            return player2;
        }  else if(playerId == player3.getTeamId()){
            return player3;
        }  else if(playerId == player4.getTeamId()){
            return player4;
        } else {
            return null;
        }
    }

    @Override
    public List<Player> getAllPlayers() {
        return players;
    }

    @Override
    public Player removePlayer(int playerId) {
        if (playerId == player1.getTeamId()) {
            return player1;
        } else if(playerId == player2.getTeamId()){
            return player2;
        }  else if(playerId == player3.getTeamId()){
            return player3;
        }  else if(playerId == player4.getTeamId()){
            return player4;
        } else {
            return null;
        }
    }

    @Override
    public void loadTeams() throws TournamentPersistenceException {

    }

    @Override
    public void loadPlayers() throws TournamentPersistenceException {

    }

    @Override
    public void writeTeams() throws TournamentPersistenceException {

    }

    @Override
    public void writePlayers() throws TournamentPersistenceException {

    }

}
