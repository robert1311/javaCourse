/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dao;

import com.re.bracket.dto.Entrant;
import com.re.bracket.dto.Player;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
import java.util.List;

/**
 *
 * @author rober
 */
public interface TeamDao {

    Team addTeam(Team team);

    Team getTeam(int teamId);

    List<Team> getAllTeams();

    Team removeTeam(int teamId);

    Player addPlayer(Player player);

    Player getPlayer(int playerId);

    List<Player> getAllPlayers();

    Player removePlayer(int playerId);

//    Entrant addEntrant(Entrant entrant);
//
//    Entrant getEntrant(int playerId);
//
//    List<Entrant> getAllEntrants();
//
//    Entrant removeEntrant(int playerId);
//
//    Stat addStat(Stat stat);
//
//    Stat getStat(int statId);
//
//    List<Stat> getAllStats();
//
//    Stat removeStat(int statId);

    void loadTeams() throws TournamentPersistenceException;

    void loadPlayers() throws TournamentPersistenceException;

//    void loadEntrants() ;
//
//    void loadStats();

    void writeTeams() throws TournamentPersistenceException;

    void writePlayers() throws TournamentPersistenceException;

//    void writeEntrants();
//
//    void writeStats();
}
