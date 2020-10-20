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
public class Participant {

    private int tournamentId;
    private int teamId;

    public Participant(int tournamentId, int teamId){
        this.tournamentId = tournamentId;
        this.teamId = teamId;
    }
    
    public int getTournamentId() {
        return tournamentId;
    }

//    public void setTournamentId(int tournamentId) {
//        this.tournamentId = tournamentId;
//    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.tournamentId;
        hash = 89 * hash + this.teamId;
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
        final Participant other = (Participant) obj;
        if (this.tournamentId != other.tournamentId) {
            return false;
        }
        if (this.teamId != other.teamId) {
            return false;
        }
        return true;
    }
    
    
}
