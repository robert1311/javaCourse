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
public class TournamentConfigurationDaoStubImpl implements TournamentConfigurationDao {

    int nextSeriesId = 10;
    
    @Override
    public int getNextTournamentId() throws TournamentPersistenceException {
        return 2;
    }

    @Override
    public void settNextTournamentId(int currentNumber) throws TournamentPersistenceException {

    }

    @Override
    public int getNextSeriesId() throws TournamentPersistenceException {
        return nextSeriesId;
    }

    @Override
    public void setNextSeriesId(int currentNumber) throws TournamentPersistenceException {
        nextSeriesId = nextSeriesId +1;
    }

    @Override
    public int getNextGameId() throws TournamentPersistenceException {
        return 10;
    }

    @Override
    public void setNextGameId(int currentNumber) throws TournamentPersistenceException {
        
    }

    @Override
    public int getNextTeamId() throws TournamentPersistenceException {
        return 2;
    }

    @Override
    public void setNextTeamId(int currentNumber) throws TournamentPersistenceException {

    }

    @Override
    public int getNextPlayerId() throws TournamentPersistenceException {
        return 2;
    }

    @Override
    public void setNextPlayerId(int currentNumber) throws TournamentPersistenceException {

    }
    
}
