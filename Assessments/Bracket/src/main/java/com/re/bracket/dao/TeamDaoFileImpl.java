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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author rober
 */
public class TeamDaoFileImpl implements TeamDao {

    private Map<Integer, Team> teamMap = new HashMap<>();
    private Map<Integer, Player> playerMap = new HashMap<>();
    
    private final String DEL = "::";
    private final String TEAM_FILE = "teams.txt";
    private final String PLAYER_FILE = "players.txt";

    @Override
    public Team addTeam(Team team) {
        return teamMap.put(team.getTeamId(), team);
    }

    @Override
    public Team getTeam(int teamId) {
        return teamMap.get(teamId);
    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> teamList = new ArrayList<>();
        for (Team team : teamMap.values()) {
            teamList.add(team);
        }
        return teamList;
    }

    @Override
    public Team removeTeam(int teamId) {
        return teamMap.remove(teamId);
    }

    @Override
    public Player addPlayer(Player player) {
        return playerMap.put(player.getPlayerId(), player);
    }

    @Override
    public Player getPlayer(int playerId) {
        return playerMap.get(playerId);
    }

    @Override
    public List<Player> getAllPlayers() {
        List<Player> playerList = new ArrayList<>();
        for (Player player : playerMap.values()) {
            playerList.add(player);
        }
        return playerList;
    }

    @Override
    public Player removePlayer(int playerId) {
        return playerMap.remove(playerId);
    }


    private Player unmarshallPlayer(String playerAsText) {
        String[] playerTokens = playerAsText.split(DEL);

        int playerId = Integer.parseInt(playerTokens[0]);
        int teamId = Integer.parseInt(playerTokens[1]);
        boolean isHuman = Boolean.parseBoolean(playerTokens[2]);
        String playername = playerTokens[3];
        
        boolean isPlayer = Boolean.parseBoolean(playerTokens[4]);
        int stageNumber = Integer.parseInt(playerTokens[5]);
        int homeGamesPlayed = Integer.parseInt(playerTokens[6]);
        int awayGamesPlayed = Integer.parseInt(playerTokens[7]);
        int gamesPlayed = Integer.parseInt(playerTokens[8]);
        int seriesPlayed = Integer.parseInt(playerTokens[9]);
        int homeWins = Integer.parseInt(playerTokens[10]);
        int homeLosses = Integer.parseInt(playerTokens[11]);
        int awayWins = Integer.parseInt(playerTokens[12]);
        int awayLosses = Integer.parseInt(playerTokens[13]);
        int gameWins = Integer.parseInt(playerTokens[14]);
        int gameLosses = Integer.parseInt(playerTokens[15]);
        int ties = Integer.parseInt(playerTokens[16]);
        double winPercent = Double.parseDouble(playerTokens[17]);
        int posPoints = Integer.parseInt(playerTokens[18]);
        int negPoints = Integer.parseInt(playerTokens[19]);
        int totalPoints = Integer.parseInt(playerTokens[20]);
        double ptsPerGame = Double.parseDouble(playerTokens[21]);
        int seriesWins = Integer.parseInt(playerTokens[22]);
        int seriesLosses = Integer.parseInt(playerTokens[23]);
        boolean isWinStreak = Boolean.parseBoolean(playerTokens[24]);
        int streakCount = Integer.parseInt(playerTokens[25]);
         Stat stat2Info = new Stat();
        boolean isPlayer2 = Boolean.parseBoolean(playerTokens[26]);
        int stageNumber2 = Integer.parseInt(playerTokens[27]);
        int homeGamesPlayed2 = Integer.parseInt(playerTokens[28]);
        int awayGamesPlayed2 = Integer.parseInt(playerTokens[29]);
        int gamesPlayed2 = Integer.parseInt(playerTokens[30]);
        int seriesPlayed2 = Integer.parseInt(playerTokens[31]);
        int homeWins2 = Integer.parseInt(playerTokens[32]);
        int homeLosses2 = Integer.parseInt(playerTokens[33]);
        int awayWins2 = Integer.parseInt(playerTokens[34]);
        int awayLosses2 = Integer.parseInt(playerTokens[35]);
        int gameWins2 = Integer.parseInt(playerTokens[36]);
        int gameLosses2 = Integer.parseInt(playerTokens[37]);
        int ties2 = Integer.parseInt(playerTokens[38]);
        double winPercent2 = Double.parseDouble(playerTokens[39]);
        int posPoints2 = Integer.parseInt(playerTokens[40]);
        int negPoints2 = Integer.parseInt(playerTokens[41]);
        int totalPoints2 = Integer.parseInt(playerTokens[42]);
        double ptsPerGame2 = Double.parseDouble(playerTokens[43]);
        int seriesWins2 = Integer.parseInt(playerTokens[44]);
        int seriesLosses2 = Integer.parseInt(playerTokens[45]);
        boolean isWinStreak2 = Boolean.parseBoolean(playerTokens[46]);
        int streakCount2 = Integer.parseInt(playerTokens[47]);
       

        Stat playerStat = new Stat();
        Player unmarshalledPlayer = new Player(playerId);
        unmarshalledPlayer.setIsHuman(isHuman);
        unmarshalledPlayer.setPlayerName(playername);
        unmarshalledPlayer.setStatInfo(playerStat);
        unmarshalledPlayer.setS2StatInfo(stat2Info);
        unmarshalledPlayer.setTeamId(teamId);
        unmarshalledPlayer.getStatInfo().setAwayGamesPlayed(awayGamesPlayed);
        unmarshalledPlayer.getStatInfo().setAwayGamesPlayed(awayGamesPlayed);
        unmarshalledPlayer.getStatInfo().setAwayLosses(awayLosses);
        unmarshalledPlayer.getStatInfo().setAwayWins(awayWins);
        unmarshalledPlayer.getStatInfo().setGameLosses(gameLosses);
        unmarshalledPlayer.getStatInfo().setGameWins(gameWins);
        unmarshalledPlayer.getStatInfo().setGamesPlayed(gamesPlayed);
        unmarshalledPlayer.getStatInfo().setHomeGamesPlayed(homeGamesPlayed);
        unmarshalledPlayer.getStatInfo().setHomeLosses(homeLosses);
        unmarshalledPlayer.getStatInfo().setHomeWins(homeWins);
        unmarshalledPlayer.getStatInfo().setIsPlayer(isPlayer);
        unmarshalledPlayer.getStatInfo().setIsWinStreak(isWinStreak);
        unmarshalledPlayer.getStatInfo().setNegPoints(negPoints);
        unmarshalledPlayer.getStatInfo().setPosPoints(posPoints);
        unmarshalledPlayer.getStatInfo().setPtsPerGame(ptsPerGame);
        unmarshalledPlayer.getStatInfo().setSeriesLosses(seriesLosses);
        unmarshalledPlayer.getStatInfo().setSeriesPlayed(seriesPlayed);
        unmarshalledPlayer.getStatInfo().setSeriesWins(seriesWins);
        unmarshalledPlayer.getStatInfo().setStageNumber(stageNumber);
        unmarshalledPlayer.getStatInfo().setStreakCount(streakCount);
        unmarshalledPlayer.getStatInfo().setTies(ties);
        unmarshalledPlayer.getStatInfo().setTotalPoints(totalPoints);
        unmarshalledPlayer.getStatInfo().setWinPercent(winPercent);
        unmarshalledPlayer.getS2StatInfo().setAwayGamesPlayed(awayGamesPlayed);
        
        unmarshalledPlayer.getS2StatInfo().setAwayGamesPlayed(awayGamesPlayed);
        unmarshalledPlayer.getS2StatInfo().setAwayGamesPlayed(awayGamesPlayed);
        unmarshalledPlayer.getS2StatInfo().setAwayLosses(awayLosses);
        unmarshalledPlayer.getS2StatInfo().setAwayWins(awayWins);
        unmarshalledPlayer.getS2StatInfo().setGameLosses(gameLosses);
        unmarshalledPlayer.getS2StatInfo().setGameWins(gameWins);
        unmarshalledPlayer.getS2StatInfo().setGamesPlayed(gamesPlayed);
        unmarshalledPlayer.getS2StatInfo().setHomeGamesPlayed(homeGamesPlayed);
        unmarshalledPlayer.getS2StatInfo().setHomeLosses(homeLosses);
        unmarshalledPlayer.getS2StatInfo().setHomeWins(homeWins);
        unmarshalledPlayer.getS2StatInfo().setIsPlayer(isPlayer);
        unmarshalledPlayer.getS2StatInfo().setIsWinStreak(isWinStreak);
        unmarshalledPlayer.getS2StatInfo().setNegPoints(negPoints);
        unmarshalledPlayer.getS2StatInfo().setPosPoints(posPoints);
        unmarshalledPlayer.getS2StatInfo().setPtsPerGame(ptsPerGame);
        unmarshalledPlayer.getS2StatInfo().setSeriesLosses(seriesLosses);
        unmarshalledPlayer.getS2StatInfo().setSeriesPlayed(seriesPlayed);
        unmarshalledPlayer.getS2StatInfo().setSeriesWins(seriesWins);
        unmarshalledPlayer.getS2StatInfo().setStageNumber(stageNumber);
        unmarshalledPlayer.getS2StatInfo().setStreakCount(streakCount);
        unmarshalledPlayer.getS2StatInfo().setTies(ties);
        unmarshalledPlayer.getS2StatInfo().setTotalPoints(totalPoints);
        unmarshalledPlayer.getS2StatInfo().setWinPercent(winPercent);

        return unmarshalledPlayer;
    }

    private Team unmarshallTeam(String teamFromFile) {
        String[] teamTokens = teamFromFile.split(DEL);
        int teamId = Integer.parseInt(teamTokens[0]);
        int tournamentId = Integer.parseInt(teamTokens[1]);
        String teamName = teamTokens[2];
        boolean hasPlayers = Boolean.parseBoolean(teamTokens[3]);
        String captainName = teamTokens[4];
        int maxPlayers = Integer.parseInt(teamTokens[5]);
        int numOfPlayers = Integer.parseInt(teamTokens[6]);
        int rank = Integer.parseInt(teamTokens[7]);
        Stat statInfo = new Stat();
        boolean isPlayer = Boolean.parseBoolean(teamTokens[8]);
        int stageNumber = Integer.parseInt(teamTokens[9]);
        int homeGamesPlayed = Integer.parseInt(teamTokens[10]);
        int awayGamesPlayed = Integer.parseInt(teamTokens[11]);
        int gamesPlayed = Integer.parseInt(teamTokens[12]);
        int seriesPlayed = Integer.parseInt(teamTokens[13]);
        int homeWins = Integer.parseInt(teamTokens[14]);
        int homeLosses = Integer.parseInt(teamTokens[15]);
        int awayWins = Integer.parseInt(teamTokens[16]);
        int awayLosses = Integer.parseInt(teamTokens[17]);
        int gameWins = Integer.parseInt(teamTokens[18]);
        int gameLosses = Integer.parseInt(teamTokens[19]);
        int ties = Integer.parseInt(teamTokens[20]);
        double winPercent = Double.parseDouble(teamTokens[21]);
        int posPoints = Integer.parseInt(teamTokens[22]);
        int negPoints = Integer.parseInt(teamTokens[23]);
        int totalPoints = Integer.parseInt(teamTokens[24]);
        double ptsPerGame = Double.parseDouble(teamTokens[25]);
        int seriesWins = Integer.parseInt(teamTokens[26]);
        int seriesLosses = Integer.parseInt(teamTokens[27]);
        boolean isWinStreak = Boolean.parseBoolean(teamTokens[28]);
        int streakCount = Integer.parseInt(teamTokens[29]);
        Stat stat2Info = new Stat();
        boolean isPlayer2 = Boolean.parseBoolean(teamTokens[30]);
        int stageNumber2 = Integer.parseInt(teamTokens[31]);
        int homeGamesPlayed2 = Integer.parseInt(teamTokens[32]);
        int awayGamesPlayed2 = Integer.parseInt(teamTokens[33]);
        int gamesPlayed2 = Integer.parseInt(teamTokens[34]);
        int seriesPlayed2 = Integer.parseInt(teamTokens[35]);
        int homeWins2 = Integer.parseInt(teamTokens[36]);
        int homeLosses2 = Integer.parseInt(teamTokens[37]);
        int awayWins2 = Integer.parseInt(teamTokens[38]);
        int awayLosses2 = Integer.parseInt(teamTokens[39]);
        int gameWins2 = Integer.parseInt(teamTokens[40]);
        int gameLosses2 = Integer.parseInt(teamTokens[41]);
        int ties2 = Integer.parseInt(teamTokens[42]);
        double winPercent2 = Double.parseDouble(teamTokens[43]);
        int posPoints2 = Integer.parseInt(teamTokens[44]);
        int negPoints2 = Integer.parseInt(teamTokens[45]);
        int totalPoints2 = Integer.parseInt(teamTokens[46]);
        double ptsPerGame2 = Double.parseDouble(teamTokens[47]);
        int seriesWins2 = Integer.parseInt(teamTokens[48]);
        int seriesLosses2 = Integer.parseInt(teamTokens[49]);
        boolean isWinStreak2 = Boolean.parseBoolean(teamTokens[50]);
        int streakCount2 = Integer.parseInt(teamTokens[51]);
        Team  unmarshalledTeam = new Team(teamId);
        unmarshalledTeam.setTournamentId(tournamentId);
        unmarshalledTeam.setCaptainName(captainName);
        unmarshalledTeam.setHasPlayers(hasPlayers);
        unmarshalledTeam.setMaxPlayers(maxPlayers);
        unmarshalledTeam.setNumOfPlayers(numOfPlayers);
        unmarshalledTeam.setRank(rank);
        unmarshalledTeam.setStatInfo(statInfo);
        unmarshalledTeam.setS2StatInfo(stat2Info);
        unmarshalledTeam.setTeamName(teamName);
        unmarshalledTeam.getStatInfo().setAwayGamesPlayed(awayGamesPlayed);
        unmarshalledTeam.getStatInfo().setAwayLosses(awayLosses);
        unmarshalledTeam.getStatInfo().setAwayWins(awayWins);
        unmarshalledTeam.getStatInfo().setGameLosses(gameLosses);
        unmarshalledTeam.getStatInfo().setGameWins(gameWins);
        unmarshalledTeam.getStatInfo().setGamesPlayed(gamesPlayed);
        unmarshalledTeam.getStatInfo().setHomeGamesPlayed(homeGamesPlayed);
        unmarshalledTeam.getStatInfo().setHomeLosses(homeLosses);
        unmarshalledTeam.getStatInfo().setHomeWins(homeWins);
        unmarshalledTeam.getStatInfo().setIsPlayer(isPlayer);
        unmarshalledTeam.getStatInfo().setIsWinStreak(isWinStreak);
        unmarshalledTeam.getStatInfo().setNegPoints(negPoints);
        unmarshalledTeam.getStatInfo().setPosPoints(posPoints);
        unmarshalledTeam.getStatInfo().setPtsPerGame(ptsPerGame);
        unmarshalledTeam.getStatInfo().setSeriesLosses(seriesLosses);
        unmarshalledTeam.getStatInfo().setSeriesPlayed(seriesPlayed);
        unmarshalledTeam.getStatInfo().setSeriesWins(seriesWins);
        unmarshalledTeam.getStatInfo().setStageNumber(stageNumber);
        unmarshalledTeam.getStatInfo().setStreakCount(streakCount);
        unmarshalledTeam.getStatInfo().setTies(ties);
        unmarshalledTeam.getStatInfo().setTotalPoints(totalPoints);
        unmarshalledTeam.getStatInfo().setWinPercent(winPercent);
        unmarshalledTeam.getS2StatInfo().setAwayGamesPlayed(awayGamesPlayed2);
        unmarshalledTeam.getS2StatInfo().setAwayLosses(awayLosses2);
        unmarshalledTeam.getS2StatInfo().setAwayWins(awayWins2);
        unmarshalledTeam.getS2StatInfo().setGameLosses(gameLosses2);
        unmarshalledTeam.getS2StatInfo().setGameWins(gameWins2);
        unmarshalledTeam.getS2StatInfo().setGamesPlayed(gamesPlayed2);
        unmarshalledTeam.getS2StatInfo().setHomeGamesPlayed(homeGamesPlayed2);
        unmarshalledTeam.getS2StatInfo().setHomeLosses(homeLosses2);
        unmarshalledTeam.getS2StatInfo().setHomeWins(homeWins2);
        unmarshalledTeam.getS2StatInfo().setIsPlayer(isPlayer2);
        unmarshalledTeam.getS2StatInfo().setIsWinStreak(isWinStreak2);
        unmarshalledTeam.getS2StatInfo().setNegPoints(negPoints2);
        unmarshalledTeam.getS2StatInfo().setPosPoints(posPoints2);
        unmarshalledTeam.getS2StatInfo().setPtsPerGame(ptsPerGame2);
        unmarshalledTeam.getS2StatInfo().setSeriesLosses(seriesLosses2);
        unmarshalledTeam.getS2StatInfo().setSeriesPlayed(seriesPlayed2);
        unmarshalledTeam.getS2StatInfo().setSeriesWins(seriesWins2);
        unmarshalledTeam.getS2StatInfo().setStageNumber(stageNumber2);
        unmarshalledTeam.getS2StatInfo().setStreakCount(streakCount2);
        unmarshalledTeam.getS2StatInfo().setTies(ties2);
        unmarshalledTeam.getS2StatInfo().setTotalPoints(totalPoints2);
        unmarshalledTeam.getS2StatInfo().setWinPercent(winPercent2);
        return unmarshalledTeam;
    }

    @Override
    public void loadTeams() throws TournamentPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(TEAM_FILE)));
        } catch (FileNotFoundException e) {
            throw new TournamentPersistenceException("Teams could not be loaded.", e);
        }
        Team currentTeam;
        String currentLine;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTeam = unmarshallTeam(currentLine);
            teamMap.put(currentTeam.getTeamId(), currentTeam);
        }
        sc.close();
    }

    @Override
    public void loadPlayers() throws TournamentPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(PLAYER_FILE)));
        } catch (FileNotFoundException e) {
            throw new TournamentPersistenceException("Players could not be loaded.", e);
        }
        Player currentPlayer;
        String currentLine;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentPlayer = unmarshallPlayer(currentLine);
            playerMap.put(currentPlayer.getPlayerId(), currentPlayer);
        }
        sc.close();
    }
    
    private String marshallPlayer(Player aPlayer){
        String playerAsText = aPlayer.getPlayerId() + DEL;
        playerAsText += aPlayer.getTeamId() + DEL;
        playerAsText += aPlayer.isIsHuman() + DEL;
        playerAsText += aPlayer.getPlayerName() + DEL;
        playerAsText += aPlayer.getStatInfo().isIsPlayer() + DEL;
        playerAsText += aPlayer.getStatInfo().getStageNumber() + DEL;
        playerAsText += aPlayer.getStatInfo().getHomeGamesPlayed() + DEL;
        playerAsText += aPlayer.getStatInfo().getAwayGamesPlayed() + DEL;
        playerAsText += aPlayer.getStatInfo().getGamesPlayed() + DEL;
        playerAsText += aPlayer.getStatInfo().getSeriesPlayed() + DEL;
        playerAsText += aPlayer.getStatInfo().getHomeWins() + DEL;
        playerAsText += aPlayer.getStatInfo().getHomeLosses() + DEL;
        playerAsText += aPlayer.getStatInfo().getAwayWins() + DEL;
        playerAsText += aPlayer.getStatInfo().getAwayLosses() + DEL;
        playerAsText += aPlayer.getStatInfo().getGameWins() + DEL;
        playerAsText += aPlayer.getStatInfo().getGameLosses() + DEL;
        playerAsText += aPlayer.getStatInfo().getTies() + DEL;
        playerAsText += aPlayer.getStatInfo().getWinPercent() + DEL;
        playerAsText += aPlayer.getStatInfo().getPosPoints() + DEL;
        playerAsText += aPlayer.getStatInfo().getNegPoints() + DEL;
        playerAsText += aPlayer.getStatInfo().getTotalPoints() + DEL;
        playerAsText += aPlayer.getStatInfo().getPtsPerGame() + DEL;
        playerAsText += aPlayer.getStatInfo().getSeriesWins() + DEL;
        playerAsText += aPlayer.getStatInfo().getSeriesLosses() + DEL;
        playerAsText += aPlayer.getStatInfo().isIsWinStreak() + DEL;
        playerAsText += aPlayer.getStatInfo().getStreakCount() + DEL;
        playerAsText += aPlayer.getS2StatInfo().isIsPlayer() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getStageNumber() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getHomeGamesPlayed() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getAwayGamesPlayed() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getGamesPlayed() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getSeriesPlayed() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getHomeWins() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getHomeLosses() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getAwayWins() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getAwayLosses() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getGameWins() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getGameLosses() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getTies() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getWinPercent() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getPosPoints() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getNegPoints() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getTotalPoints() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getPtsPerGame() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getSeriesWins() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getSeriesLosses() + DEL;
        playerAsText += aPlayer.getS2StatInfo().isIsWinStreak() + DEL;
        playerAsText += aPlayer.getS2StatInfo().getStreakCount();

        return playerAsText;
    }
    
    private String marshallTeam(Team aTeam){
        String teamAsText = aTeam.getTeamId() + DEL;
        teamAsText += aTeam.getTournamentId() + DEL;
        teamAsText += aTeam.getTeamName() + DEL;
        teamAsText += aTeam.isHasPlayers() + DEL;
        teamAsText += aTeam.getCaptainName() + DEL;
        teamAsText += aTeam.getMaxPlayers() + DEL;
        teamAsText += aTeam.getNumOfPlayers() + DEL;
        teamAsText += aTeam.getRank() + DEL;
        teamAsText += aTeam.getStatInfo().isIsPlayer() + DEL;
        teamAsText += aTeam.getStatInfo().getStageNumber() + DEL;
        teamAsText += aTeam.getStatInfo().getHomeGamesPlayed() + DEL;
        teamAsText += aTeam.getStatInfo().getAwayGamesPlayed() + DEL;
        teamAsText += aTeam.getStatInfo().getGamesPlayed() + DEL;
        teamAsText += aTeam.getStatInfo().getSeriesPlayed() + DEL;
        teamAsText += aTeam.getStatInfo().getHomeWins() + DEL;
        teamAsText += aTeam.getStatInfo().getHomeLosses() + DEL;
        teamAsText += aTeam.getStatInfo().getAwayWins() + DEL;
        teamAsText += aTeam.getStatInfo().getAwayLosses() + DEL;
        teamAsText += aTeam.getStatInfo().getGameWins() + DEL;
        teamAsText += aTeam.getStatInfo().getGameLosses() + DEL;
        teamAsText += aTeam.getStatInfo().getTies() + DEL;
        teamAsText += aTeam.getStatInfo().getWinPercent() + DEL;
        teamAsText += aTeam.getStatInfo().getPosPoints() + DEL;
        teamAsText += aTeam.getStatInfo().getNegPoints() + DEL;
        teamAsText += aTeam.getStatInfo().getTotalPoints() + DEL;
        teamAsText += aTeam.getStatInfo().getPtsPerGame() + DEL;
        teamAsText += aTeam.getStatInfo().getSeriesWins() + DEL;
        teamAsText += aTeam.getStatInfo().getSeriesLosses() + DEL;
        teamAsText += aTeam.getStatInfo().isIsWinStreak() + DEL;
        teamAsText += aTeam.getStatInfo().getStreakCount() + DEL;
        teamAsText += aTeam.getS2StatInfo().isIsPlayer() + DEL;
        teamAsText += aTeam.getS2StatInfo().getStageNumber() + DEL;
        teamAsText += aTeam.getS2StatInfo().getHomeGamesPlayed() + DEL;
        teamAsText += aTeam.getS2StatInfo().getAwayGamesPlayed() + DEL;
        teamAsText += aTeam.getS2StatInfo().getGamesPlayed() + DEL;
        teamAsText += aTeam.getS2StatInfo().getSeriesPlayed() + DEL;
        teamAsText += aTeam.getS2StatInfo().getHomeWins() + DEL;
        teamAsText += aTeam.getS2StatInfo().getHomeLosses() + DEL;
        teamAsText += aTeam.getS2StatInfo().getAwayWins() + DEL;
        teamAsText += aTeam.getS2StatInfo().getAwayLosses() + DEL;
        teamAsText += aTeam.getS2StatInfo().getGameWins() + DEL;
        teamAsText += aTeam.getS2StatInfo().getGameLosses() + DEL;
        teamAsText += aTeam.getS2StatInfo().getTies() + DEL;
        teamAsText += aTeam.getS2StatInfo().getWinPercent() + DEL;
        teamAsText += aTeam.getS2StatInfo().getPosPoints() + DEL;
        teamAsText += aTeam.getS2StatInfo().getNegPoints() + DEL;
        teamAsText += aTeam.getS2StatInfo().getTotalPoints() + DEL;
        teamAsText += aTeam.getS2StatInfo().getPtsPerGame() + DEL;
        teamAsText += aTeam.getS2StatInfo().getSeriesWins() + DEL;
        teamAsText += aTeam.getS2StatInfo().getSeriesLosses() + DEL;
        teamAsText += aTeam.getS2StatInfo().isIsWinStreak() + DEL;
        teamAsText += aTeam.getS2StatInfo().getStreakCount();
        
        return teamAsText;
    }
    
    @Override
    public void writeTeams() throws TournamentPersistenceException{
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(TEAM_FILE));
        } catch (IOException e){
            throw new TournamentPersistenceException("Teams could not be saved.", e);
        }
        String marshalledTeam;
        for(Team currentTeam : teamMap.values()){
            marshalledTeam = marshallTeam(currentTeam);
            out.println(marshalledTeam);
            out.flush();
        }
        out.close();
    }

    @Override
    public void writePlayers() throws TournamentPersistenceException{
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(PLAYER_FILE));
        } catch (IOException e){
            throw new TournamentPersistenceException("Players could not be saved.", e);
        }
        String marshalledPlayer;
        for(Player currentPlayer : playerMap.values()){
            marshalledPlayer = marshallPlayer(currentPlayer);
            out.println(marshalledPlayer);
            out.flush();
        }
        out.close();
    }

//    @Override
//    public void loadEntrants() throws TournamentPersistenceException {
//        Scanner sc;
//        try {
//            sc = new Scanner(new BufferedReader(new FileReader(ENTRANT_FILE)));
//        } catch (FileNotFoundException e) {
//            throw new TournamentPersistenceException("Entrants could not be loaded.", e);
//        }
//        Entrant currentEntrant;
//        String currentLine;
//        while (sc.hasNextLine()) {
//            currentLine = sc.nextLine();
//            currentEntrant = unmarshallEntrant(currentLine);
//            entrantMap.put(currentEntrant.getPlayerId(), currentEntrant);
//        }
//        sc.close();
//    }

//    @Override
//    public void loadStats() throws TournamentPersistenceException {
//        Scanner sc;
//        try {
//            sc = new Scanner(new BufferedReader(new FileReader(STAT_FILE)));
//        } catch (FileNotFoundException e) {
//            throw new TournamentPersistenceException("Stats could not be loaded.", e);
//        }
//        Stat currentStat;
//        String currentLine;
//        while (sc.hasNextLine()) {
//            currentLine = sc.nextLine();
//            currentStat = unmarshallStat(currentLine);
//            statMap.put(currentStat.getStatId(), currentStat);
//        }
//        sc.close();
//    }
//
//    public String marshallPlayer(Player aPlayer) {
//
//    }

    

//    @Override
//    public void writeEntrants() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void writeStats() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
