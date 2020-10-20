/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dto;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rober
 */
public class Team {

    private int teamId;
    private int tournamentId;
    private String teamName;
    private boolean hasPlayers;
    private String captainName;
    private int maxPlayers;
    private int numOfPlayers;
    private Stat statInfo;
    private Stat s2StatInfo;
    private int rank;

    public Team(int teamId){
        this.teamId = teamId;
        
    }

    public int getTeamId() {
        return teamId;
    }

//    public void setTeamId(int teamId) {
//        this.teamId = teamId;
//    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }
    
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public boolean isHasPlayers() {
        return hasPlayers;
    }

    public void setHasPlayers(boolean hasPlayers) {
        this.hasPlayers = hasPlayers;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    
    
    public Stat getStatInfo() {
        return statInfo;
    }

    public void setStatInfo(Stat statInfo) {
        this.statInfo = statInfo;
    }

    public Stat getS2StatInfo() {
        return s2StatInfo;
    }

    public void setS2StatInfo(Stat s2StatInfo) {
        this.s2StatInfo = s2StatInfo;
    }
    
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.teamId;
        hash = 17 * hash + this.tournamentId;
        hash = 17 * hash + Objects.hashCode(this.teamName);
        hash = 17 * hash + (this.hasPlayers ? 1 : 0);
        hash = 17 * hash + Objects.hashCode(this.captainName);
        hash = 17 * hash + this.maxPlayers;
        hash = 17 * hash + this.numOfPlayers;
        hash = 17 * hash + Objects.hashCode(this.statInfo);
        hash = 17 * hash + Objects.hashCode(this.s2StatInfo);
        hash = 17 * hash + this.rank;
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
        final Team other = (Team) obj;
        if (this.teamId != other.teamId) {
            return false;
        }
        if (this.tournamentId != other.tournamentId) {
            return false;
        }
        if (this.hasPlayers != other.hasPlayers) {
            return false;
        }
        if (this.maxPlayers != other.maxPlayers) {
            return false;
        }
        if (this.numOfPlayers != other.numOfPlayers) {
            return false;
        }
        if (this.rank != other.rank) {
            return false;
        }
        if (!Objects.equals(this.teamName, other.teamName)) {
            return false;
        }
        if (!Objects.equals(this.captainName, other.captainName)) {
            return false;
        }
        if (!Objects.equals(this.statInfo, other.statInfo)) {
            return false;
        }
        if (!Objects.equals(this.s2StatInfo, other.s2StatInfo)) {
            return false;
        }
        return true;
    }
    
   
    
}
