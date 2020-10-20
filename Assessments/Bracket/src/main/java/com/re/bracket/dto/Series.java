/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rober
 */
public class Series {

    private int seriesId;
    private int tournamentId;
    private int seriesNumber;//to replace seriesIds and make user selecting ID easier?
    private int roundNumber;
    private int bestOfNumGames;
    private int numGamesPlayed;
    private boolean isReady;
    private boolean isComplete;
    private String team1Name;
    private String team2Name;
    private int team1Id;
    private int team2Id;
    private int team1Wins;
    private int team2Wins;
    private String seriesWinnerName;
    private String seriesLoserName;
    private LocalDateTime dateTime;

    public Series(int seriesId){
        this.seriesId = seriesId;
    }
    
    public int getSeriesId() {
        return seriesId;
    }

//    public void setSeriesId(int seriesId) {
//        this.seriesId = seriesId;
//    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }
    
    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public int getBestOfNumGames() {
        return bestOfNumGames;
    }

    public void setBestOfNumGames(int bestOfNumGames) {
        this.bestOfNumGames = bestOfNumGames;
    }

    public int getNumGamesPlayed() {
        return numGamesPlayed;
    }

    public void setNumGamesPlayed(int numGamesPlayed) {
        this.numGamesPlayed = numGamesPlayed;
    }

    public boolean isIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public int getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(int team1Id) {
        this.team1Id = team1Id;
    }

    public int getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(int team2Id) {
        this.team2Id = team2Id;
    }
    
    

    public int getTeam1Wins() {
        return team1Wins;
    }

    public void setTeam1Wins(int team1Wins) {
        this.team1Wins = team1Wins;
    }

    public int getTeam2Wins() {
        return team2Wins;
    }

    public void setTeam2Wins(int team2Wins) {
        this.team2Wins = team2Wins;
    }

    
    
    public String getSeriesWinnerName() {
        return seriesWinnerName;
    }

    public void setSeriesWinnerName(String seriesWinnerName) {
        this.seriesWinnerName = seriesWinnerName;
    }

    public String getSeriesLoserName() {
        return seriesLoserName;
    }

    public void setSeriesLoserName(String seriesLoserName) {
        this.seriesLoserName = seriesLoserName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.seriesId;
        hash = 83 * hash + this.tournamentId;
        hash = 83 * hash + this.seriesNumber;
        hash = 83 * hash + this.roundNumber;
        hash = 83 * hash + this.bestOfNumGames;
        hash = 83 * hash + this.numGamesPlayed;
        hash = 83 * hash + (this.isReady ? 1 : 0);
        hash = 83 * hash + (this.isComplete ? 1 : 0);
        hash = 83 * hash + Objects.hashCode(this.team1Name);
        hash = 83 * hash + Objects.hashCode(this.team2Name);
        hash = 83 * hash + this.team1Id;
        hash = 83 * hash + this.team2Id;
        hash = 83 * hash + this.team1Wins;
        hash = 83 * hash + this.team2Wins;
        hash = 83 * hash + Objects.hashCode(this.seriesWinnerName);
        hash = 83 * hash + Objects.hashCode(this.seriesLoserName);
        hash = 83 * hash + Objects.hashCode(this.dateTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Series other = (Series) obj;
        if (this.seriesId != other.seriesId) {
            return false;
        }
        if (this.tournamentId != other.tournamentId) {
            return false;
        }
        if (this.seriesNumber != other.seriesNumber) {
            return false;
        }
        if (this.roundNumber != other.roundNumber) {
            return false;
        }
        if (this.bestOfNumGames != other.bestOfNumGames) {
            return false;
        }
        if (this.numGamesPlayed != other.numGamesPlayed) {
            return false;
        }
        if (this.isReady != other.isReady) {
            return false;
        }
        if (this.isComplete != other.isComplete) {
            return false;
        }
        if (this.team1Id != other.team1Id) {
            return false;
        }
        if (this.team2Id != other.team2Id) {
            return false;
        }
        if (this.team1Wins != other.team1Wins) {
            return false;
        }
        if (this.team2Wins != other.team2Wins) {
            return false;
        }
        if (!Objects.equals(this.team1Name, other.team1Name)) {
            return false;
        }
        if (!Objects.equals(this.team2Name, other.team2Name)) {
            return false;
        }
        if (!Objects.equals(this.seriesWinnerName, other.seriesWinnerName)) {
            return false;
        }
        if (!Objects.equals(this.seriesLoserName, other.seriesLoserName)) {
            return false;
        }
        if (!Objects.equals(this.dateTime, other.dateTime)) {
            return false;
        }
        return true;
    }

    
}
