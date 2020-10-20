/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.service;

import com.re.bracket.dao.NoSuchPlayerException;
import com.re.bracket.dao.NoSuchSeriesException;
import com.re.bracket.dao.NoSuchStatException;
import com.re.bracket.dao.NoSuchTeamException;
import com.re.bracket.dao.TournamentPersistenceException;
import com.re.bracket.dto.Game;
import com.re.bracket.dto.Player;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
import com.re.bracket.dto.Tournament;
import java.util.List;

/**
 *
 * @author rober
 */
public interface TeamServiceLayer {

    public Team validateNewTeamName(Tournament tournament,
            String teamName) throws TeamNameTakenException,
            TournamentPersistenceException, EventFullException;

    public Player validatePlayerInfo(String playerName, Team team) throws PlayerNameTakenException, 
            TournamentPersistenceException;

    public Team getTeam(int teamId) throws NoSuchTeamException;

    public Player getPlayer(int playerId) throws NoSuchPlayerException;

    public List<Team> getTeamsByTournamentId(int TournamentId);

    public List<Player> getAllPlayers();

    public List<Player> getPlayersByTeamId(int teamId);

    public Team updateTeamStats(Team team, Game played);
    
    public Team updateTeamStats(Team team, Series series, Tournament tournament);
    
    public Player updatePlayerStats(Player player, Game game);
    
    public Player updatePlayerStats(Player player, Series series, Tournament tournament);

    public Tournament userJoinTournament(Tournament tournament,
            String username) throws EventFullException;

    public Team addTeamToTournament(Tournament tournament, String teamName) 
            throws TournamentPersistenceException;

    public Player addPlayerToTeam(Team team, String playerName) 
            throws TournamentPersistenceException;

    public Player removePlayer(int playerId) throws NoSuchPlayerException;

    public Tournament removeTeamFromTournament(Tournament tournament, int teamId) 
            throws NoSuchTeamException;

    public List<Team> getTeamStandings(List<Team> teamList,
            Tournament tournament);

//    public List<Stat> getPlayerStandings(List<Team> teamList,
//            Tournament tournament);
    
    public void loadTeamEntities() throws TournamentPersistenceException;
    
    public void saveTeamEntities() throws TournamentPersistenceException;

}
