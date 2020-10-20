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
public class Tournament {

    private int tournamentId;
    private String TournamentName;
    private String username;
    boolean isSignUp;
    private int stageType;
    private String s1Format;
    private String s2Format;
    private boolean isSecondStage;
    private int maxNumOfParticipants;
    private int actualNumOfParticipants;
    private int seedsToAdvance;
    private int numOfCycles;
    private int s1NumOfRounds;
    private int s2NumOfRounds;
    private int currentRound;
    private LocalDateTime startDate;

    public Tournament(int tournamentId){
        this.tournamentId = tournamentId;
    }
    
    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return TournamentName;
    }

    public void setTournamentName(String TournamentName) {
        this.TournamentName = TournamentName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   

    public boolean isIsSignUp() {
        return isSignUp;
    }

    public void setIsSignUp(boolean isSignUp) {
        this.isSignUp = isSignUp;
    }

    public int getStageType() {
        return stageType;
    }

    public void setStageType(int stageType) {
        this.stageType = stageType;
    }

    public String getS1Format() {
        return s1Format;
    }

    public void setS1Format(String s1Format) {
        this.s1Format = s1Format;
    }

    public String getS2Format() {
        return s2Format;
    }

    public void setS2Format(String s2Format) {
        this.s2Format = s2Format;
    }

    public boolean isIsSecondStage() {
        return isSecondStage;
    }

    public void setIsSecondStage(boolean isSecondStage) {
        this.isSecondStage = isSecondStage;
    }

    public int getMaxNumOfParticipants() {
        return maxNumOfParticipants;
    }

    public int getActualNumOfParticipants() {
        return actualNumOfParticipants;
    }

    public void setActualNumOfParticipants(int actualNumOfParticipants) {
        this.actualNumOfParticipants = actualNumOfParticipants;
    }
    
    public void setMaxNumOfParticipants(int maxNumOfParticipants) {
        this.maxNumOfParticipants = maxNumOfParticipants;
    }

    public int getSeedsToAdvance() {
        return seedsToAdvance;
    }

    public void setSeedsToAdvance(int seedsToAdvance) {
        this.seedsToAdvance = seedsToAdvance;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    public void setNumOfCycles(int numOfCycles) {
        this.numOfCycles = numOfCycles;
    }

    public int getS1NumOfRounds() {
        return s1NumOfRounds;
    }

    public void setS1NumOfRounds(int s1NumOfRounds) {
        this.s1NumOfRounds = s1NumOfRounds;
    }

    public int getS2NumOfRounds() {
        return s2NumOfRounds;
    }

    public void setS2NumOfRounds(int s2NumOfRounds) {
        this.s2NumOfRounds = s2NumOfRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.tournamentId;
        hash = 37 * hash + Objects.hashCode(this.TournamentName);
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + (this.isSignUp ? 1 : 0);
        hash = 37 * hash + this.stageType;
        hash = 37 * hash + Objects.hashCode(this.s1Format);
        hash = 37 * hash + Objects.hashCode(this.s2Format);
        hash = 37 * hash + (this.isSecondStage ? 1 : 0);
        hash = 37 * hash + this.maxNumOfParticipants;
        hash = 37 * hash + this.actualNumOfParticipants;
        hash = 37 * hash + this.seedsToAdvance;
        hash = 37 * hash + this.numOfCycles;
        hash = 37 * hash + this.s1NumOfRounds;
        hash = 37 * hash + this.s2NumOfRounds;
        hash = 37 * hash + this.currentRound;
        hash = 37 * hash + Objects.hashCode(this.startDate);
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
        final Tournament other = (Tournament) obj;
        if (this.tournamentId != other.tournamentId) {
            return false;
        }
        if (this.isSignUp != other.isSignUp) {
            return false;
        }
        if (this.stageType != other.stageType) {
            return false;
        }
        if (this.isSecondStage != other.isSecondStage) {
            return false;
        }
        if (this.maxNumOfParticipants != other.maxNumOfParticipants) {
            return false;
        }
        if (this.actualNumOfParticipants != other.actualNumOfParticipants) {
            return false;
        }
        if (this.seedsToAdvance != other.seedsToAdvance) {
            return false;
        }
        if (this.numOfCycles != other.numOfCycles) {
            return false;
        }
        if (this.s1NumOfRounds != other.s1NumOfRounds) {
            return false;
        }
        if (this.s2NumOfRounds != other.s2NumOfRounds) {
            return false;
        }
        if (this.currentRound != other.currentRound) {
            return false;
        }
        if (!Objects.equals(this.TournamentName, other.TournamentName)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.s1Format, other.s1Format)) {
            return false;
        }
        if (!Objects.equals(this.s2Format, other.s2Format)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        return true;
    }

    
}
