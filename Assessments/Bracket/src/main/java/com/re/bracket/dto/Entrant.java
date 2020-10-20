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
public class Entrant {

    private int teamId;
    private int playerId;

    public Entrant(int teamId, int playerId){
        this.teamId = teamId;
        this.playerId = playerId;
    }
    
    public int getTeamId() {
        return teamId;
    }

//    public void setTeamId(int teamId) {
//        this.teamId = teamId;
//    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.teamId;
        hash = 79 * hash + this.playerId;
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
        final Entrant other = (Entrant) obj;
        if (this.teamId != other.teamId) {
            return false;
        }
        if (this.playerId != other.playerId) {
            return false;
        }
        return true;
    }
    
    
}
