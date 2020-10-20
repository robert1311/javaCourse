/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

/**
 *
 * @author rober
 */
public interface TournamentConfigurationDao {

    int getNextTournamentId()throws TournamentPersistenceException;
    
    void settNextTournamentId(int currentNumber) throws TournamentPersistenceException;

    int getNextSeriesId() throws TournamentPersistenceException;
    
    void setNextSeriesId(int currentNumber) throws TournamentPersistenceException;

    int getNextGameId() throws TournamentPersistenceException;

    void setNextGameId(int currentNumber) throws TournamentPersistenceException;
    
    int getNextTeamId() throws TournamentPersistenceException;
    
    void setNextTeamId(int currentNumber) throws TournamentPersistenceException;

    int getNextPlayerId() throws TournamentPersistenceException;
    
    void setNextPlayerId(int currentNumber) throws TournamentPersistenceException;

//    int getNextStatId() throws TournamentPersistenceException;
//
//    void setNextStatId(int currentNumber) throws TournamentPersistenceException;
}
