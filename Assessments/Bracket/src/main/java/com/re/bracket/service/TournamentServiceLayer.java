/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.service;

import com.re.bracket.dao.NoSuchGameException;
import com.re.bracket.dao.NoSuchSeriesException;
import com.re.bracket.dao.NoSuchTournamentException;
import com.re.bracket.dao.TournamentPersistenceException;
import com.re.bracket.dto.Game;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Team;
import com.re.bracket.dto.Tournament;
import java.util.List;

/**
 *
 * @author rober
 */
public interface TournamentServiceLayer {
    public int getNextTournamentId() throws TournamentPersistenceException;
    
    public void setNextTournamentId() throws TournamentPersistenceException;
    
    public Tournament saveTournament(Tournament tournament);
    
    public List<Series> finalizeSchedule( Tournament tournament, List<Team> teamList) 
            throws InvalidDateException, TournamentPersistenceException, 
            TournamentNotCompleteException;

    public List<Series> getScheduleByTournament(Tournament tournament);

    public Tournament startTournament(Tournament tournament) 
            throws TournamentNotReadyException;

    public List<Tournament> getAllTournaments();

    public List<Tournament> getAllTournamentsByUsername(String username);

    public Tournament getTournament(int tournamentId)
            throws NoSuchTournamentException;

//    public Tournament buildBracket(Tournament tournament,
//            List<Team> allTeams);

//    public List<Series> getScheduleByTournament(int tournamentId);
    public Game addGameToSeries(Series series)  throws TournamentPersistenceException;

    public Series getSeries(int seriesId) throws NoSuchSeriesException;
    
    public Game getGame(int gameId) throws NoSuchGameException ;

    public List<Game> getGamesbySeriesId(int seriesId);

    public Game updateGame(Game game) throws PlayersNotCheckedInException;
    
    public Game removeGame(int gameId);

    public Series removeSeries(int seriesId) throws NoSuchSeriesException;

    public Series updateSeries(Series series) throws SeriesNotReadyException,
            InvalidResultsException;

//    public Tournament updateTournament(Tournament tournament);

    public Tournament deleteTournament(int tournamentId)
            throws NoSuchTournamentException;

    public void loadTournamentEntities() throws TournamentPersistenceException;

    public void saveTournamentEntities() throws TournamentPersistenceException;
}
