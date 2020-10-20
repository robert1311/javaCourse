/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author rober
 */
public class Game {

    private int gameId;
    private int seriesId;
    private int stageNumber;
    private int seriesGameNum;
    private boolean isReady;
    private boolean isNeutralField;
    private int homeTeamId;
    private int awayTeamId;
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime dateTime;
    private boolean isComplete;
    private int homeScore;
    private int awayScore;
    private String winningTeamName;
    private String losingTeamName;

    public Game(int gameId){
        this.gameId = gameId;
    }
    
    public int getGameId() {
        return gameId;
    }

//    public void setGameId(int gameId) {
//        this.gameId = gameId;
//    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }
    
    public int getSeriesGameNum() {
        return seriesGameNum;
    }

    public void setSeriesGameNum(int seriesGameNum) {
        this.seriesGameNum = seriesGameNum;
    }

    public boolean isIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public boolean isIsNeutralField() {
        return isNeutralField;
    }

    public void setIsNeutralField(boolean isNeutralField) {
        this.isNeutralField = isNeutralField;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public String getWinningTeamName() {
        return winningTeamName;
    }

    public void setWinningTeamName(String winningTeamName) {
        this.winningTeamName = winningTeamName;
    }

    public String getLosingTeamName() {
        return losingTeamName;
    }

    public void setLosingTeamName(String losingTeamName) {
        this.losingTeamName = losingTeamName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.gameId;
        hash = 61 * hash + this.seriesId;
        hash = 61 * hash + this.stageNumber;
        hash = 61 * hash + this.seriesGameNum;
        hash = 61 * hash + (this.isReady ? 1 : 0);
        hash = 61 * hash + (this.isNeutralField ? 1 : 0);
        hash = 61 * hash + this.homeTeamId;
        hash = 61 * hash + this.awayTeamId;
        hash = 61 * hash + Objects.hashCode(this.homeTeam);
        hash = 61 * hash + Objects.hashCode(this.awayTeam);
        hash = 61 * hash + Objects.hashCode(this.dateTime);
        hash = 61 * hash + (this.isComplete ? 1 : 0);
        hash = 61 * hash + this.homeScore;
        hash = 61 * hash + this.awayScore;
        hash = 61 * hash + Objects.hashCode(this.winningTeamName);
        hash = 61 * hash + Objects.hashCode(this.losingTeamName);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.seriesId != other.seriesId) {
            return false;
        }
        if (this.stageNumber != other.stageNumber) {
            return false;
        }
        if (this.seriesGameNum != other.seriesGameNum) {
            return false;
        }
        if (this.isReady != other.isReady) {
            return false;
        }
        if (this.isNeutralField != other.isNeutralField) {
            return false;
        }
        if (this.homeTeamId != other.homeTeamId) {
            return false;
        }
        if (this.awayTeamId != other.awayTeamId) {
            return false;
        }
        if (this.isComplete != other.isComplete) {
            return false;
        }
        if (this.homeScore != other.homeScore) {
            return false;
        }
        if (this.awayScore != other.awayScore) {
            return false;
        }
        if (!Objects.equals(this.homeTeam, other.homeTeam)) {
            return false;
        }
        if (!Objects.equals(this.awayTeam, other.awayTeam)) {
            return false;
        }
        if (!Objects.equals(this.winningTeamName, other.winningTeamName)) {
            return false;
        }
        if (!Objects.equals(this.losingTeamName, other.losingTeamName)) {
            return false;
        }
        if (!Objects.equals(this.dateTime, other.dateTime)) {
            return false;
        }
        return true;
    }

    
}
