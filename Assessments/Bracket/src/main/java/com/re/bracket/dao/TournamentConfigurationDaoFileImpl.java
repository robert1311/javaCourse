/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author rober
 */
public class TournamentConfigurationDaoFileImpl implements TournamentConfigurationDao {
    
    private final String CONFIG_FILE = "resources/config.properties";

    @Override
    public int getNextTournamentId() throws TournamentPersistenceException {
        return Integer.parseInt(readConfig().getProperty("tournamentId"));
    }

    @Override
    public void settNextTournamentId(int currentNumber) throws TournamentPersistenceException {
        writeConfig("tournamentId", currentNumber);
    }

    @Override
    public int getNextSeriesId() throws TournamentPersistenceException {
        return Integer.parseInt(readConfig().getProperty("seriesId"));
    }

    @Override
    public void setNextSeriesId(int currentNumber) throws TournamentPersistenceException {
        writeConfig("seriesId", currentNumber);
    }

    @Override
    public int getNextGameId() throws TournamentPersistenceException {
        return Integer.parseInt(readConfig().getProperty("gameId"));
    }

    @Override
    public void setNextGameId(int currentNumber) throws TournamentPersistenceException {
        writeConfig("gameId", currentNumber);
    }

    @Override
    public int getNextTeamId() throws TournamentPersistenceException {
        return Integer.parseInt(readConfig().getProperty("teamId"));
    }

    @Override
    public void setNextTeamId(int currentNumber) throws TournamentPersistenceException {
        writeConfig("teamId", currentNumber);
    }

    @Override
    public int getNextPlayerId() throws TournamentPersistenceException {
        return Integer.parseInt(readConfig().getProperty("playerId"));
    }

    @Override
    public void setNextPlayerId(int currentNumber) throws TournamentPersistenceException {
        writeConfig("playerId", currentNumber);
    }

//    @Override
//    public int getNextStatId() throws TournamentPersistenceException {
//        return Integer.parseInt(readConfig().getProperty("statId"));
//    }
//
//    @Override
//    public void setNextStatId(int currentNumber) throws TournamentPersistenceException {
//        writeConfig("statId", currentNumber);
//    }

    private Properties readConfig() throws TournamentPersistenceException {
        FileReader reader;
        Properties p;
        try {
            reader = new FileReader(CONFIG_FILE);

        } catch (FileNotFoundException e) {
            throw new TournamentPersistenceException("Unable to get application status.");
        }
        try {
            p = new Properties();
            p.load(reader);
        } catch (IOException e) {
            throw new TournamentPersistenceException("Unable to get application status.");
        }
        return p;
    }
    
    private void writeConfig(String property, int currentNumber) throws TournamentPersistenceException {
        FileInputStream reader;
        Properties prop = new Properties();

        try {
            reader = new FileInputStream(CONFIG_FILE);
            prop.load(reader);
            reader.close();

            FileOutputStream out = new FileOutputStream(CONFIG_FILE);
            // set the properties value
            prop.setProperty(property, "" + (currentNumber + 1));

            // save properties to project root folder
            prop.store(out, null);

        } catch (IOException e) {
            throw new TournamentPersistenceException("Could not update order "
                    + "number", e);
        }
    }
    
}
