/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import com.re.bracket.dto.Game;
import com.re.bracket.dto.Participant;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Tournament;
import com.re.bracket.dto.Tournament;
import com.re.bracket.dto.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author rober
 */
public class TournamentDaoFileImpl implements TournamentDao {

    private Map<Integer, Tournament> tournamentMap = new HashMap<>();
    private Map<Integer, Participant> participantMap = new HashMap<>();
    private Map<Integer, Series> seriesMap = new HashMap<>();
    private Map<Integer, Game> gameMap = new HashMap<>();
    private final String DEL = "::";
    private final String TOURNAMENT_FILE = "tournaments.txt";
//    private final String PARTICIPANT_FILE = "participants.txt";
    private final String SERIES_FILE = "series.txt";
    private final String GAME_FILE = "games.txt";

    @Override
    public Tournament addTournament(Tournament tournament) {
        return tournamentMap.put(tournament.getTournamentId(), tournament);
    }

    @Override
    public Tournament getTournament(int tournamentId) {
        return tournamentMap.get(tournamentId);
    }

    @Override
    public List<Tournament> getAllTournaments() {
        List<Tournament> tournamentList = new ArrayList<>();
        for (Tournament tournament : tournamentMap.values()) {
            tournamentList.add(tournament);
        }
        return tournamentList;
    }

    @Override
    public Tournament removeTournament(int tournamentId) {

        List<Series> seriesList = this.getAllSeries()
                .stream()
                .filter(s -> s.getTournamentId() == tournamentId)
                .collect(Collectors.toList());
        List<Game> gameList = this.getAllGames();

        for (Series series : seriesList) {
            gameList
                    .stream()
                    .filter(g -> g.getSeriesId() == series.getSeriesId())
                    .forEach(g -> this.removeGame(g.getGameId()));
            this.removeSeries(series.getSeriesId());
        }

        return tournamentMap.remove(tournamentId);
    }

    @Override
    public Series addSeries(Series series) {
        return seriesMap.put(series.getSeriesId(), series);
    }

    @Override
    public Series getSeries(int seriesId) {
        return seriesMap.get(seriesId);
    }

    @Override
    public List<Series> getAllSeries() {
        List<Series> seriesList = new ArrayList<>();
        for (Series series : seriesMap.values()) {
            seriesList.add(series);
        }
        return seriesList;
    }

    @Override
    public Series removeSeries(int seriesId) {
        return seriesMap.remove(seriesId);
    }

    @Override
    public Game addGame(Game game) {
        return gameMap.put(game.getGameId(), game);
    }

    @Override
    public Game getGame(int gameId) {
        return gameMap.get(gameId);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<>();
        for (Game game : gameMap.values()) {
            gameList.add(game);
        }
        return gameList;
    }

    @Override
    public Game removeGame(int gameId) {
        return gameMap.remove(gameId);
    }

    private Tournament unmarshallTournament(String tournamentFromFile) {
        String[] tournamentTokens = tournamentFromFile.split(DEL);

        int tournamentId = Integer.parseInt(tournamentTokens[0]);
        String tournamentName = tournamentTokens[1];
        String username = tournamentTokens[2];
        int stageType = Integer.parseInt(tournamentTokens[3]);
        String s1Format = tournamentTokens[4];
        String s2Format = tournamentTokens[5];
        boolean isSignUp = Boolean.parseBoolean(tournamentTokens[6]);
        boolean isSecondStage = Boolean.parseBoolean(tournamentTokens[7]);
        int numOfParticipants = Integer.parseInt(tournamentTokens[8]);
        int actualNumOfParticipants = Integer.parseInt(tournamentTokens[9]);
        int seedsToAdvance = Integer.parseInt(tournamentTokens[10]);
        int numOfCycles = Integer.parseInt(tournamentTokens[11]);
        int s1NumOfRounds = Integer.parseInt(tournamentTokens[12]);
        int s2NumOfRounds = Integer.parseInt(tournamentTokens[13]);
        int currentRound = Integer.parseInt(tournamentTokens[14]);
        LocalDateTime ldt = LocalDateTime.parse(tournamentTokens[15],
                DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));

        Tournament unmarshalledTournament = new Tournament(tournamentId);
        unmarshalledTournament.setUsername(username);
        unmarshalledTournament.setCurrentRound(currentRound);
        unmarshalledTournament.setIsSecondStage(isSecondStage);
        unmarshalledTournament.setIsSignUp(isSignUp);
        unmarshalledTournament.setMaxNumOfParticipants(numOfParticipants);
        unmarshalledTournament.setS1Format(s1Format);
        unmarshalledTournament.setS2Format(s2Format);
        unmarshalledTournament.setS1NumOfRounds(s1NumOfRounds);
        unmarshalledTournament.setS2NumOfRounds(s2NumOfRounds);
        unmarshalledTournament.setSeedsToAdvance(seedsToAdvance);
        unmarshalledTournament.setStageType(stageType);
        unmarshalledTournament.setTournamentName(tournamentName);
        unmarshalledTournament.setActualNumOfParticipants(actualNumOfParticipants);
        unmarshalledTournament.setNumOfCycles(numOfCycles);
        unmarshalledTournament.setStartDate(ldt);

        return unmarshalledTournament;
    }

    public Series unmarshallSeries(String seriesFromFile) {
        String[] seriesTokens = seriesFromFile.split(DEL);
        int seriesId = Integer.parseInt(seriesTokens[0]);
        int tournamentId = Integer.parseInt(seriesTokens[1]);
        int seriesNumber = Integer.parseInt(seriesTokens[2]);
        int roundNumber = Integer.parseInt(seriesTokens[3]);
        int bestOfNumGames = Integer.parseInt(seriesTokens[4]);
        int numGamesPlayed = Integer.parseInt(seriesTokens[5]);
        boolean isReady = Boolean.parseBoolean(seriesTokens[6]);
        boolean isComplete = Boolean.parseBoolean(seriesTokens[7]);
        String team1Name = seriesTokens[8];
        String team2Name = seriesTokens[9];
        int team1Id = Integer.parseInt(seriesTokens[10]);
        int team2Id = Integer.parseInt(seriesTokens[11]);
        int team1Wins = Integer.parseInt(seriesTokens[12]);
        int team2Wins = Integer.parseInt(seriesTokens[13]);
        String seriesWinnerName = seriesTokens[14];
        String seriesLoserName = seriesTokens[15];
        LocalDateTime dateTime = LocalDateTime.parse(seriesTokens[16],
                DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));

        Series unmarshalledSeries = new Series(seriesId);
        unmarshalledSeries.setBestOfNumGames(bestOfNumGames);
        unmarshalledSeries.setDateTime(dateTime);
        unmarshalledSeries.setIsComplete(isComplete);
        unmarshalledSeries.setIsReady(isReady);
        unmarshalledSeries.setNumGamesPlayed(numGamesPlayed);
        unmarshalledSeries.setRoundNumber(roundNumber);
        unmarshalledSeries.setSeriesLoserName(seriesLoserName);
        unmarshalledSeries.setSeriesWinnerName(seriesWinnerName);
        unmarshalledSeries.setTeam1Name(team1Name);
        unmarshalledSeries.setTeam2Name(team2Name);
        unmarshalledSeries.setTournamentId(tournamentId);
        unmarshalledSeries.setSeriesNumber(seriesNumber);
        unmarshalledSeries.setTeam1Wins(team1Wins);
        unmarshalledSeries.setTeam2Wins(team2Wins);
        unmarshalledSeries.setTeam1Id(team1Id);
        unmarshalledSeries.setTeam2Id(team2Id);

        return unmarshalledSeries;
    }

    private Game unmarshallGame(String gameFromFile) {
        String[] gameTokens = gameFromFile.split(DEL);

        int gameId = Integer.parseInt(gameTokens[0]);
        int seriesId = Integer.parseInt(gameTokens[1]);
        int seriesGameNum = Integer.parseInt(gameTokens[2]);
        int stageNumber = Integer.parseInt(gameTokens[3]);
        boolean isReady = Boolean.parseBoolean(gameTokens[4]);
        boolean isNeutralField = Boolean.parseBoolean(gameTokens[5]);
        int homeTeamId = Integer.parseInt(gameTokens[6]);
        int awayTeamId = Integer.parseInt(gameTokens[7]);
        String homeTeam = gameTokens[8];
        String awayTeam = gameTokens[9];
        LocalDateTime dateTime = LocalDateTime.parse(gameTokens[10],
                DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
        boolean isComplete = Boolean.parseBoolean(gameTokens[11]);
        int homeScore = Integer.parseInt(gameTokens[12]);
        int awayScore = Integer.parseInt(gameTokens[13]);
        String winningTeamName = gameTokens[14];
        String losingTeamName = gameTokens[15];

        Game unmarshalledGame = new Game(gameId);
        unmarshalledGame.setAwayScore(awayScore);
        unmarshalledGame.setAwayTeam(awayTeam);
        unmarshalledGame.setAwayTeamId(awayTeamId);
        unmarshalledGame.setDateTime(dateTime);
        unmarshalledGame.setHomeScore(homeScore);
        unmarshalledGame.setHomeTeam(homeTeam);
        unmarshalledGame.setHomeTeamId(homeTeamId);
        unmarshalledGame.setIsComplete(isComplete);
        unmarshalledGame.setIsNeutralField(isNeutralField);
        unmarshalledGame.setIsReady(isReady);
        unmarshalledGame.setLosingTeamName(losingTeamName);
        unmarshalledGame.setSeriesGameNum(seriesGameNum);
        unmarshalledGame.setSeriesId(seriesId);
        unmarshalledGame.setStageNumber(stageNumber);
        unmarshalledGame.setWinningTeamName(winningTeamName);

        return unmarshalledGame;
    }

    private String marshallTournament(Tournament aTournament) {
        String tournamentAsText = aTournament.getTournamentId() + DEL;
        tournamentAsText += aTournament.getTournamentName() + DEL;
        tournamentAsText += aTournament.getUsername() + DEL;
        tournamentAsText += aTournament.getStageType() + DEL;
        tournamentAsText += aTournament.getS1Format() + DEL;
        tournamentAsText += aTournament.getS2Format() + DEL;
        tournamentAsText += aTournament.isIsSignUp() + DEL;
        tournamentAsText += aTournament.isIsSecondStage() + DEL;
        tournamentAsText += aTournament.getMaxNumOfParticipants() + DEL;
        tournamentAsText += aTournament.getActualNumOfParticipants() + DEL;
        tournamentAsText += aTournament.getSeedsToAdvance() + DEL;
        tournamentAsText += aTournament.getNumOfCycles() + DEL;
        tournamentAsText += aTournament.getS1NumOfRounds() + DEL;
        tournamentAsText += aTournament.getS2NumOfRounds() + DEL;
        tournamentAsText += aTournament.getCurrentRound() + DEL;
        tournamentAsText += aTournament.getStartDate().format(DateTimeFormatter
                .ofPattern("MM/dd/yyyy HH:mm"));

        return tournamentAsText;
    }

    public String marshallSeries(Series aSeries) {
        String seriesAsText = aSeries.getSeriesId() + DEL;
        seriesAsText += aSeries.getTournamentId() + DEL;
        seriesAsText += aSeries.getSeriesNumber() + DEL;
        seriesAsText += aSeries.getRoundNumber() + DEL;
        seriesAsText += aSeries.getBestOfNumGames() + DEL;
        seriesAsText += aSeries.getNumGamesPlayed() + DEL;
        seriesAsText += aSeries.isIsReady() + DEL;
        seriesAsText += aSeries.isIsComplete() + DEL;
        seriesAsText += aSeries.getTeam1Name() + DEL;
        seriesAsText += aSeries.getTeam2Name() + DEL;
        seriesAsText += aSeries.getTeam1Id() + DEL;
        seriesAsText += aSeries.getTeam2Id() + DEL;
        seriesAsText += aSeries.getTeam1Wins() + DEL;
        seriesAsText += aSeries.getTeam2Wins() + DEL;
        seriesAsText += aSeries.getSeriesWinnerName() + DEL;
        seriesAsText += aSeries.getSeriesLoserName() + DEL;
        seriesAsText += aSeries.getDateTime().format(DateTimeFormatter
                .ofPattern("MM/dd/yyyy HH:mm"));

        return seriesAsText;
    }

    public String marshallGame(Game aGame) {
        String gameAsText = aGame.getGameId() + DEL;
        gameAsText += aGame.getSeriesId() + DEL;
        gameAsText += aGame.getSeriesGameNum() + DEL;
        gameAsText += aGame.getStageNumber() + DEL;
        gameAsText += aGame.isIsReady() + DEL;
        gameAsText += aGame.isIsNeutralField() + DEL;
        gameAsText += aGame.getHomeTeamId() + DEL;
        gameAsText += aGame.getAwayTeamId() + DEL;
        gameAsText += aGame.getHomeTeam() + DEL;
        gameAsText += aGame.getAwayTeam() + DEL;
        gameAsText += aGame.getDateTime().format(DateTimeFormatter
                .ofPattern("MM/dd/yyyy HH:mm")) + DEL;
        gameAsText += aGame.isIsComplete() + DEL;
        gameAsText += aGame.getHomeScore() + DEL;
        gameAsText += aGame.getAwayScore() + DEL;
        gameAsText += aGame.getWinningTeamName() + DEL;
        gameAsText += aGame.getLosingTeamName();
        
        return gameAsText;
    }

    @Override
    public void loadTournaments() throws TournamentPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(TOURNAMENT_FILE)));
        } catch (IOException e) {
            throw new TournamentPersistenceException("Tournaments could not be loaded.", e);
        }
        Tournament currentTournament;
        String currentLine;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTournament = unmarshallTournament(currentLine);
            tournamentMap.put(currentTournament.getTournamentId(), currentTournament);
        }
        sc.close();
    }

    @Override
    public void loadSeries() throws TournamentPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(SERIES_FILE)));
        } catch (IOException e) {
            throw new TournamentPersistenceException("Series could not be loaded.", e);
        }
        Series currentSeries;
        String currentLine;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentSeries = unmarshallSeries(currentLine);
            seriesMap.put(currentSeries.getSeriesId(), currentSeries);
        }
        sc.close();
    }

    @Override
    public void loadGames() throws TournamentPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(GAME_FILE)));
        } catch (IOException e) {
            throw new TournamentPersistenceException("Games could not be loaded.", e);
        }
        Game currentGame;
        String currentLine;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentGame = unmarshallGame(currentLine);
            gameMap.put(currentGame.getGameId(), currentGame);
        }
        sc.close();
    }

    @Override
    public void writeTournaments() throws TournamentPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(TOURNAMENT_FILE));
        } catch (IOException e) {
            throw new TournamentPersistenceException("Tournaments could not be saved.", e);
        }
        String marshalledTournament;
        for (Tournament currentTournament : tournamentMap.values()) {
            marshalledTournament = marshallTournament(currentTournament);
            out.println(marshalledTournament);
            out.flush();
        }
        out.close();
    }

    @Override
    public void writeSeries() throws TournamentPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(SERIES_FILE));
        } catch (IOException e) {
            throw new TournamentPersistenceException("Series could not be saved.", e);
        }
        String marshalledSeries;
        for (Series currentSeries : seriesMap.values()) {
            marshalledSeries = marshallSeries(currentSeries);
            out.println(marshalledSeries);
            out.flush();
        }
        out.close();
    }

    @Override
    public void writeGames() throws TournamentPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(GAME_FILE));
        } catch (IOException e) {
            throw new TournamentPersistenceException("Games could not be saved.", e);
        }
        String marshalledGame;
        for (Game currentGame : gameMap.values()) {
            marshalledGame = marshallGame(currentGame);
            out.println(marshalledGame);
            out.flush();
        }
        out.close();
    }

}
