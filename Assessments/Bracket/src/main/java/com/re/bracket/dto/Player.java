/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dto;

import java.util.Objects;

/**
 *
 * @author rober
 */
public class Player {

    private int playerId;
    private int teamId;
    private boolean isHuman;
    private String playerName;
    private Stat statInfo;
    private Stat s2StatInfo;
    
    
    public Player(int playerId){
        this.playerId = playerId;
        
    }

    public int getPlayerId(){
        return playerId;
    }

//    public void setPlayerId(int playerId) {
//        this.playerId = playerId;
//    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isIsHuman() {
        return isHuman;
    }

    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.playerId;
        hash = 67 * hash + this.teamId;
        hash = 67 * hash + (this.isHuman ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.playerName);
        hash = 67 * hash + Objects.hashCode(this.statInfo);
        hash = 67 * hash + Objects.hashCode(this.s2StatInfo);
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
        final Player other = (Player) obj;
        if (this.playerId != other.playerId) {
            return false;
        }
        if (this.teamId != other.teamId) {
            return false;
        }
        if (this.isHuman != other.isHuman) {
            return false;
        }
        if (!Objects.equals(this.playerName, other.playerName)) {
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
