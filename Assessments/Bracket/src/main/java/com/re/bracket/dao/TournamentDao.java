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
import java.util.List;

/**
 *
 * @author rober
 */
public interface TournamentDao {
    
    Tournament addTournament(Tournament tournament);

    Tournament getTournament(int tournamentId);

    List<Tournament> getAllTournaments();

    Tournament removeTournament(int tournamentId);

    Series addSeries(Series series);

    Series getSeries(int seriesId);

    List<Series> getAllSeries();

    Series removeSeries(int seriesId);

    Game addGame(Game game);

    Game getGame(int gameId);

    List<Game> getAllGames();

    Game removeGame(int gameId);

    void loadTournaments() throws TournamentPersistenceException;

    void loadSeries() throws TournamentPersistenceException;

    void loadGames() throws TournamentPersistenceException;

    void writeTournaments() throws TournamentPersistenceException;

    void writeSeries() throws TournamentPersistenceException;

    void writeGames() throws TournamentPersistenceException;


}
