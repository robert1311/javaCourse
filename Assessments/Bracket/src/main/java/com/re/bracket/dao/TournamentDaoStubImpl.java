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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rober
 */
public class TournamentDaoStubImpl implements TournamentDao {

    List<Tournament> tournaments = new ArrayList<>();
    List<Series> seriesList = new ArrayList<>();
    List<Game> games = new ArrayList<>();
    Tournament tournament1;
    Tournament tournament2;
    Tournament tournament3;
    Tournament tournament4;
    Series series1;
    Series series2;
    Series series3;
    Series series4;
    Series series5;
    Game game1;
    Game game2;
    Game game3;
    Game game4;

    public TournamentDaoStubImpl() {
        tournament1 = new Tournament(1);//2-stage-type-stage1 - RR - R3
        tournament1.setCurrentRound(3);
        tournament1.setIsSecondStage(false);
        tournament1.setIsSignUp(true);
        tournament1.setMaxNumOfParticipants(15);
        tournament1.setS1Format("Round-Robin");
        tournament1.setS1NumOfRounds(14);
        tournament1.setS2Format("Single-Elimination");
        tournament1.setS2NumOfRounds(4);
        tournament1.setSeedsToAdvance(8);
        tournament1.setStageType(2);
        tournament1.setTournamentName("Stub1");
        tournament1.setUsername("JohnDoe123");

        tournaments.add(tournament1);
        
        tournament2 = new Tournament(2);//not started; 1 stage-type - SE
        tournament2.setCurrentRound(0);
        tournament2.setIsSecondStage(false);
        tournament2.setIsSignUp(true);
        tournament2.setMaxNumOfParticipants(15);
        tournament2.setS1Format("Single-Elimination");
        tournament2.setS2Format("n/a");
        tournament2.setS2NumOfRounds(0);
        tournament2.setSeedsToAdvance(0);
        tournament2.setStageType(1);
        tournament2.setTournamentName("Stub2");
        tournament2.setUsername("JohnDoe123");
        tournament2.setStartDate(LocalDateTime.now().plusDays(2));

        tournaments.add(tournament2);
        
        tournament3 = new Tournament(3);// 2 stage-type; stage1 RR complete
        tournament3.setCurrentRound(11);
        tournament3.setIsSecondStage(false);
        tournament3.setIsSignUp(true);
        tournament3.setMaxNumOfParticipants(15);
        tournament3.setS1Format("Round-Robin");
        tournament3.setS1NumOfRounds(10);
        tournament3.setS2Format("Single-Elimination");
        tournament3.setSeedsToAdvance(8);
        tournament3.setStageType(2);
        tournament3.setTournamentName("Stub3");
        tournament3.setUsername("JoeSmith456");

        tournaments.add(tournament3);
        
        tournament4 = new Tournament(4);//2-stage-type; S2- SE in progress
        tournament4.setCurrentRound(2);
        tournament4.setIsSecondStage(true);
        tournament4.setIsSignUp(true);
        tournament4.setMaxNumOfParticipants(15);
        tournament4.setS1Format("Round-Robin");
        tournament4.setS1NumOfRounds(14);
        tournament4.setS2Format("Single-Elimination");
        tournament4.setS2NumOfRounds(4);
        tournament4.setSeedsToAdvance(8);
        tournament4.setStageType(2);
        tournament4.setTournamentName("Stub4");
        tournament4.setUsername("SallyMae789");

        tournaments.add(tournament4);

        series1 = new Series(1);
        series1.setBestOfNumGames(3);
        series1.setDateTime(LocalDateTime.now());
        series1.setIsComplete(false);
        series1.setIsReady(true);
        series1.setNumGamesPlayed(0);
        series1.setSeriesLoserName("");
        series1.setSeriesWinnerName("");
        series1.setTeam1Name("Team1");
        series1.setTeam2Name("Team2");
        series1.setTournamentId(2);

        seriesList.add(series1);
        
        series2 = new Series(2);
        series2.setBestOfNumGames(7);
        series2.setDateTime(LocalDateTime.now());
        series2.setIsComplete(true);
        series2.setIsReady(true);
        series2.setNumGamesPlayed(4);
        series2.setSeriesLoserName("Team2");
        series2.setSeriesWinnerName("Team1");
        series2.setTeam1Name("Team1");
        series2.setTeam2Name("Team8");
        series2.setTournamentId(4);

        seriesList.add(series2);
        
        series3 = new Series(3);
        series3.setRoundNumber(1);
        series3.setBestOfNumGames(3);
        series3.setDateTime(LocalDateTime.now().minusDays(2));
        series3.setIsComplete(false);
        series3.setIsReady(true);
        series3.setNumGamesPlayed(2);
        series3.setSeriesLoserName("");
        series3.setSeriesWinnerName("");
        series3.setTeam1Name("Team4");
        series3.setTeam2Name("Team5");
        series3.setTournamentId(2);

        seriesList.add(series3);
        
        series4 = new Series(4);
        series4.setBestOfNumGames(7);
        series4.setDateTime(LocalDateTime.now());
        series4.setIsComplete(false);
        series4.setIsReady(true);
        series4.setNumGamesPlayed(6);
        series4.setSeriesLoserName("");
        series4.setSeriesWinnerName("");
        series4.setTeam1Name("Team2");
        series4.setTeam2Name("Team7");
        series4.setTournamentId(2);

        seriesList.add(series4);
        
        series5 = new Series(5);
        series5.setBestOfNumGames(7);
        series5.setDateTime(LocalDateTime.now());
        series5.setIsComplete(true);
        series5.setIsReady(true);
        series5.setNumGamesPlayed(0);
        series5.setSeriesLoserName("Team3");
        series5.setSeriesWinnerName("Team6");
        series5.setTeam1Name("Team3");
        series5.setTeam2Name("Team6");
        series5.setTournamentId(2);
        

        seriesList.add(series5);

        game1 = new Game(1);//unstarted game
        game1.setAwayTeam("Team2");
        game1.setAwayTeamId(2);
        game1.setDateTime(LocalDateTime.now().minusDays(2));
        game1.setHomeTeam("Team1");
        game1.setHomeTeamId(1);
        game1.setIsComplete(false);
        game1.setIsNeutralField(false);
        game1.setIsReady(true);
        game1.setLosingTeamName("");
        game1.setSeriesGameNum(1);
        game1.setSeriesId(1);
        game1.setWinningTeamName("");

        games.add(game1);
        
        game2 = new Game(2);//series3 4-5
        game2.setAwayScore(10);
        game2.setAwayTeam("Team5");
        game2.setAwayTeamId(5);
        game2.setDateTime(LocalDateTime.now().minusDays(2));
        game2.setHomeScore(20);
        game2.setHomeTeam("Team4");
        game2.setHomeTeamId(4);
        game2.setIsComplete(true);
        game2.setIsNeutralField(false);
        game2.setIsReady(true);
        game2.setLosingTeamName("Team5");
        game2.setSeriesGameNum(1);
        game2.setSeriesId(3);
        game2.setWinningTeamName("Team4");

        games.add(game2);
        
        game3 = new Game(3);
        game3.setAwayScore(10);
        game3.setAwayTeam("Team4");
        game3.setAwayTeamId(4);
        game3.setDateTime(LocalDateTime.now().minusDays(2));
        game3.setHomeScore(5);
        game3.setHomeTeam("Team5");
        game3.setHomeTeamId(5);
        game3.setIsComplete(true);
        game3.setIsNeutralField(false);
        game3.setIsReady(true);
        game3.setLosingTeamName("Team5");
        game3.setSeriesGameNum(2);
        game3.setSeriesId(3);
        game3.setWinningTeamName("Team4");

        games.add(game3);
        
        game4 = new Game(4);
        game4.setAwayScore(0);
        game4.setAwayTeam("Team7");
        game4.setAwayTeamId(7);
        game4.setDateTime(LocalDateTime.now().minusDays(2));
        game4.setHomeScore(20);
        game4.setHomeTeam("Team2");
        game4.setHomeTeamId(2);
        game4.setIsComplete(false);
        game4.setIsNeutralField(false);
        game4.setIsReady(false);
        game4.setLosingTeamName("");
        game4.setSeriesGameNum(7);
        game4.setSeriesId(4);
        game4.setWinningTeamName("");

        games.add(game4);
        
    }

    @Override
    public Tournament addTournament(Tournament tournament) {
        if (tournament.equals(tournament1)) {
            return tournament1;
        } else if (tournament.equals(tournament2)) {
            return tournament2;
        } else if (tournament.equals(tournament3)) {
            return tournament3;
        } else if (tournament.equals(tournament4)) {
            return tournament4;
        } else {
            return null;
        }
    }

    @Override
    public Tournament getTournament(int tournamentId) {
        if (tournamentId == tournament1.getTournamentId()) {
            return tournament1;
        } else if (tournamentId == tournament2.getTournamentId()) {
            return tournament2;
        } else if (tournamentId == tournament3.getTournamentId()) {
            return tournament3;
        } else if (tournamentId == tournament4.getTournamentId()) {
            return tournament4;
        } else {
            return null;
        }
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return tournaments;
    }

    @Override
    public Tournament removeTournament(int tournamentId) {
        if (tournamentId == tournament1.getTournamentId()) {
            return tournament1;
        } else if (tournamentId == tournament2.getTournamentId()) {
            return tournament2;
        } else if (tournamentId == tournament3.getTournamentId()) {
            return tournament3;
        } else if (tournamentId == tournament4.getTournamentId()) {
            return tournament4;
        } else {
            return null;
        }
    }

    @Override
    public Series addSeries(Series series) {
        if (series.equals(series1)) {
            return series1;
        } else if (series.equals(series2)) {
            return series2;
        } else if (series.equals(series3)) {
            return series3;
        } else if (series.equals(series4)) {
            return series4;
        } else {
            return null;
        }
    }

    @Override
    public Series getSeries(int seriesId) {
        if (seriesId == series1.getSeriesId()) {
            return series1;
        } else if (seriesId == series2.getSeriesId()) {
            return series2;
        } else if (seriesId == series3.getSeriesId()) {
            return series3;
        } else if (seriesId == series4.getSeriesId()) {
            return series4;
        } else if (seriesId == series5.getSeriesId()) {
            return series5;
        } else {
            return null;
        }
    }

    @Override
    public List<Series> getAllSeries() {
        return seriesList;
    }

    @Override
    public Series removeSeries(int seriesId) {
        if (seriesId == series1.getSeriesId()) {
            return series1;
        } else if (seriesId == series2.getSeriesId()) {
            return series2;
        } else if (seriesId == series3.getSeriesId()) {
            return series3;
        } else if (seriesId == series4.getSeriesId()) {
            return series4;
        } else if (seriesId == series5.getSeriesId()) {
            return series5;
        } else {
            return null;
        }
    }

    @Override
    public Game addGame(Game game) {
        if (game.equals(game1)) {
            return game1;
        } else if (game.equals(game2)) {
            return game2;
        } else if (game.equals(game3)) {
            return game3;
        } else if (game.equals(game4)) {
            return game4;
        } else {
            return null;
        }
    }

    @Override
    public Game getGame(int gameId) {
        if (gameId == game1.getGameId()) {
            return game1;
        } else if (gameId == game2.getGameId()) {
            return game2;
        } else if (gameId == game3.getGameId()) {
            return game3;
        } else if (gameId == game4.getGameId()) {
            return game4;
        } else {
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        return games;
    }

    @Override
    public Game removeGame(int gameId) {
        if (gameId == game1.getGameId()) {
            return game1;
        } else if (gameId == game2.getGameId()) {
            return game2;
        } else if (gameId == game3.getGameId()) {
            return game3;
        } else if (gameId == game4.getGameId()) {
            return game4;
        } else {
            return null;
        }
    }

    @Override
    public void loadTournaments() throws TournamentPersistenceException {

    }

    @Override
    public void loadSeries() throws TournamentPersistenceException {

    }

    @Override
    public void loadGames() throws TournamentPersistenceException {

    }

    @Override
    public void writeTournaments() throws TournamentPersistenceException {

    }

    @Override
    public void writeSeries() throws TournamentPersistenceException {

    }

    @Override
    public void writeGames() throws TournamentPersistenceException {

    }

}
