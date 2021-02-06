/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dto;

/**
 *
 * @author rober
 */
public class Stat {

private boolean isPlayer;
private int stageNumber;
private int homeGamesPlayed;
private int awayGamesPlayed;
private int gamesPlayed;
private int seriesPlayed;
private int homeWins;
private int homeLosses;
private int awayWins;
private int awayLosses;
private int gameWins;
private int gameLosses;
private int ties;
private double winPercent;
private int posPoints;
private int negPoints;
private int TotalPoints;
private int pntDifferential;
double ptsPerGame;
private int seriesWins;
private int seriesLosses;
private boolean isWinStreak;
private int streakCount;


    public Stat(){
        
    }

    public boolean isIsPlayer() {
        return isPlayer;
    }

    public void setIsPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public int getHomeGamesPlayed() {
        return homeGamesPlayed;
    }

    public void setHomeGamesPlayed(int homeGamesPlayed) {
        this.homeGamesPlayed = homeGamesPlayed;
    }

    public int getAwayGamesPlayed() {
        return awayGamesPlayed;
    }

    public void setAwayGamesPlayed(int awayGamesPlayed) {
        this.awayGamesPlayed = awayGamesPlayed;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getSeriesPlayed() {
        return seriesPlayed;
    }

    public void setSeriesPlayed(int seriesPlayed) {
        this.seriesPlayed = seriesPlayed;
    }

    public int getHomeWins() {
        return homeWins;
    }

    public void setHomeWins(int homeWins) {
        this.homeWins = homeWins;
    }

    public int getHomeLosses() {
        return homeLosses;
    }

    public void setHomeLosses(int homeLosses) {
        this.homeLosses = homeLosses;
    }

    public int getAwayWins() {
        return awayWins;
    }

    public void setAwayWins(int awayWins) {
        this.awayWins = awayWins;
    }

    public int getAwayLosses() {
        return awayLosses;
    }

    public void setAwayLosses(int awayLosses) {
        this.awayLosses = awayLosses;
    }

    public int getGameWins() {
        return gameWins;
    }

    public void setGameWins(int gameWins) {
        this.gameWins = gameWins;
    }

    public int getGameLosses() {
        return gameLosses;
    }

    public void setGameLosses(int gameLosses) {
        this.gameLosses = gameLosses;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public double getWinPercent() {
        return winPercent;
    }

    public void setWinPercent(double winPercent) {
        this.winPercent = winPercent;
    }

    public int getPosPoints() {
        return posPoints;
    }

    public void setPosPoints(int posPoints) {
        this.posPoints = posPoints;
    }

    public int getNegPoints() {
        return negPoints;
    }

    public void setNegPoints(int negPoints) {
        this.negPoints = negPoints;
    }

    public int getTotalPoints() {
        return TotalPoints;
    }

    public void setTotalPoints(int TotalPoints) {
        this.TotalPoints = TotalPoints;
    }

    public int getPntDifferential() {
        return pntDifferential;
    }

    public void setPntDifferential(int pntDifferential) {
        this.pntDifferential = pntDifferential;
    }

    
    
    public double getPtsPerGame() {
        return ptsPerGame;
    }

    public void setPtsPerGame(double ptsPerGame) {
        this.ptsPerGame = ptsPerGame;
    }

    public int getSeriesWins() {
        return seriesWins;
    }

    public void setSeriesWins(int seriesWins) {
        this.seriesWins = seriesWins;
    }

    public int getSeriesLosses() {
        return seriesLosses;
    }

    public void setSeriesLosses(int seriesLosses) {
        this.seriesLosses = seriesLosses;
    }

    public boolean isIsWinStreak() {
        return isWinStreak;
    }

    public void setIsWinStreak(boolean isWinStreak) {
        this.isWinStreak = isWinStreak;
    }

    public int getStreakCount() {
        return streakCount;
    }

    public void setStreakCount(int streakCount) {
        this.streakCount = streakCount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.isPlayer ? 1 : 0);
        hash = 67 * hash + this.stageNumber;
        hash = 67 * hash + this.homeGamesPlayed;
        hash = 67 * hash + this.awayGamesPlayed;
        hash = 67 * hash + this.gamesPlayed;
        hash = 67 * hash + this.seriesPlayed;
        hash = 67 * hash + this.homeWins;
        hash = 67 * hash + this.homeLosses;
        hash = 67 * hash + this.awayWins;
        hash = 67 * hash + this.awayLosses;
        hash = 67 * hash + this.gameWins;
        hash = 67 * hash + this.gameLosses;
        hash = 67 * hash + this.ties;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.winPercent) ^ (Double.doubleToLongBits(this.winPercent) >>> 32));
        hash = 67 * hash + this.posPoints;
        hash = 67 * hash + this.negPoints;
        hash = 67 * hash + this.TotalPoints;
        hash = 67 * hash + this.pntDifferential;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.ptsPerGame) ^ (Double.doubleToLongBits(this.ptsPerGame) >>> 32));
        hash = 67 * hash + this.seriesWins;
        hash = 67 * hash + this.seriesLosses;
        hash = 67 * hash + (this.isWinStreak ? 1 : 0);
        hash = 67 * hash + this.streakCount;
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
        final Stat other = (Stat) obj;
        if (this.isPlayer != other.isPlayer) {
            return false;
        }
        if (this.stageNumber != other.stageNumber) {
            return false;
        }
        if (this.homeGamesPlayed != other.homeGamesPlayed) {
            return false;
        }
        if (this.awayGamesPlayed != other.awayGamesPlayed) {
            return false;
        }
        if (this.gamesPlayed != other.gamesPlayed) {
            return false;
        }
        if (this.seriesPlayed != other.seriesPlayed) {
            return false;
        }
        if (this.homeWins != other.homeWins) {
            return false;
        }
        if (this.homeLosses != other.homeLosses) {
            return false;
        }
        if (this.awayWins != other.awayWins) {
            return false;
        }
        if (this.awayLosses != other.awayLosses) {
            return false;
        }
        if (this.gameWins != other.gameWins) {
            return false;
        }
        if (this.gameLosses != other.gameLosses) {
            return false;
        }
        if (this.ties != other.ties) {
            return false;
        }
        if (Double.doubleToLongBits(this.winPercent) != Double.doubleToLongBits(other.winPercent)) {
            return false;
        }
        if (this.posPoints != other.posPoints) {
            return false;
        }
        if (this.negPoints != other.negPoints) {
            return false;
        }
        if (this.TotalPoints != other.TotalPoints) {
            return false;
        }
        if (this.pntDifferential != other.pntDifferential) {
            return false;
        }
        if (Double.doubleToLongBits(this.ptsPerGame) != Double.doubleToLongBits(other.ptsPerGame)) {
            return false;
        }
        if (this.seriesWins != other.seriesWins) {
            return false;
        }
        if (this.seriesLosses != other.seriesLosses) {
            return false;
        }
        if (this.isWinStreak != other.isWinStreak) {
            return false;
        }
        if (this.streakCount != other.streakCount) {
            return false;
        }
        return true;
    }

    

    
    

   
    
}
