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
import com.re.bracket.dao.TeamAuditDao;
import com.re.bracket.dao.TeamDao;
import com.re.bracket.dao.TournamentConfigurationDao;
import com.re.bracket.dao.TournamentPersistenceException;
import com.re.bracket.dto.Game;
import com.re.bracket.dto.Player;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
import com.re.bracket.dto.Tournament;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rober
 */
public class TeamServiceLayerImpl implements TeamServiceLayer {

    private TeamDao tmDao;
    private TeamAuditDao tmAudit;
    private TournamentConfigurationDao config;

    public TeamServiceLayerImpl(TeamDao tmDao, TeamAuditDao tmAudit, TournamentConfigurationDao config) {
        this.tmDao = tmDao;
        this.tmAudit = tmAudit;
        this.config = config;
    }

    @Override
    public Team validateNewTeamName(Tournament tournament, String teamName)
            throws TeamNameTakenException, TournamentPersistenceException,
            EventFullException {
        if (tournament.getActualNumOfParticipants() >= tournament
                .getMaxNumOfParticipants()) {
            throw new EventFullException("Max number of teams has "
                    + "already been reached. Only admin can remove teams or "
                    + "start the tournament.");
        }
        List<Team> currentTeams = tmDao.getAllTeams()
                .stream()
                .filter(t -> t.getTournamentId() == tournament.getTournamentId())
                .collect(Collectors.toList());
        for (Team team : currentTeams) {
            if (teamName.equalsIgnoreCase(team.getTeamName())) {
                throw new TeamNameTakenException("Team name for this Tournament already exists. Please try again.");
            }
        }
        return this.addTeamToTournament(tournament, teamName);
    }

    @Override
    public Player validatePlayerInfo(
            String playerNameInput, Team team) throws PlayerNameTakenException,
            TournamentPersistenceException {

        List<Player> currentPlayers;
        currentPlayers = this.getPlayersByTeamId(team.getTeamId());
        for (Player player : currentPlayers) {
            if (player.getPlayerName().equalsIgnoreCase(playerNameInput)) {
                throw new PlayerNameTakenException("Player name has "
                        + "already been taken on this team. "
                        + "Please try again.");
            }
        }

        return this.addPlayerToTeam(team, playerNameInput);
    }

    @Override
    public Team getTeam(int teamId) throws NoSuchTeamException {

        Team team = tmDao.getTeam(teamId);
        if (team == null) {
            throw new NoSuchTeamException("No Such team exists.");
        } else {
            return team;
        }

    }

    @Override
    public Player getPlayer(int playerId) throws NoSuchPlayerException {
        Player player = tmDao.getPlayer(playerId);
        if (player == null) {
            throw new NoSuchPlayerException("No such player exists");
        } else {
            return tmDao.getPlayer(playerId);
        }
    }

    @Override
    public List<Team> getTeamsByTournamentId(int tournamentId) {
        return tmDao.getAllTeams()
                .stream()
                .filter(t -> t.getTournamentId() == tournamentId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getAllPlayers() {
        return tmDao.getAllPlayers();
    }

    @Override
    public List<Player> getPlayersByTeamId(int teamId) {
        return tmDao.getAllPlayers()
                .stream()
                .filter(p -> p.getTeamId() == teamId)
                .collect(Collectors.toList());

    }

    @Override
    public Team updateTeamStats(Team team, Game played) {
        String winningTeamName = played.getWinningTeamName();
        String losingTeamName = played.getLosingTeamName();
        int homeTeamId = played.getHomeTeamId();
        int awayTeamId = played.getAwayTeamId();
        int homeScore = played.getHomeScore();
        int awayScore = played.getAwayScore();
        int stageNumber = played.getStageNumber();

        int awayGamesPlayed = team.getStatInfo().getAwayGamesPlayed();
        int awayLosses = team.getStatInfo().getAwayLosses();
        int awayWins = team.getStatInfo().getAwayWins();
        int gameLosses = team.getStatInfo().getGameLosses();
        int gameWins = team.getStatInfo().getGameWins();
        int homeGamesPlayed = team.getStatInfo().getHomeGamesPlayed();
        int homeLosses = team.getStatInfo().getHomeLosses();
        int homeWins = team.getStatInfo().getHomeWins();
        int gamesPlayed = team.getStatInfo().getGamesPlayed();
        boolean isWinStreak = team.getStatInfo().isIsWinStreak();
        int seriesLosses = team.getStatInfo().getSeriesLosses();
        int seriesPlayed = team.getStatInfo().getSeriesPlayed();
        int seriesWins = team.getStatInfo().getSeriesWins();
        int streakCount = team.getStatInfo().getStreakCount();
//        int ties = team.getStatInfo().getTies();
        int totalPoints = team.getStatInfo().getTotalPoints();
        int homePoints = played.getHomeScore();
        int awayPoints = played.getAwayScore();
        
        
        if (played.isIsComplete()) {

            //////////////////////////Win //////////////////////////////
            if (winningTeamName.equalsIgnoreCase(team.getTeamName())) {//win
                if (team.getTeamId() == homeTeamId) {//at Home
                    if (stageNumber == 1) {//Stage 1
                        team.getStatInfo().setGameWins(gameWins + 1);
                        team.getStatInfo().setHomeGamesPlayed(homeGamesPlayed + 1);
                        team.getStatInfo().setHomeWins(homeWins + 1);
                        team.getStatInfo().setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            team.getStatInfo().setStreakCount(streakCount + 1);
                        } else {//on losing streak
                            team.getStatInfo().setIsWinStreak(true);
                            team.getStatInfo().setStreakCount(1);
                        }

                        team.getStatInfo().setWinPercent((double) team.getStatInfo()
                                .getGameWins() / team.getStatInfo().getGamesPlayed());
                        team.getStatInfo().setTotalPoints(totalPoints + homePoints);
                        team.getStatInfo().setPtsPerGame((double) team.getStatInfo()
                                .getTotalPoints() / team.getStatInfo()
                                        .getGamesPlayed());
                    } else {//Stage2
                        team.getS2StatInfo().setGameWins(gameWins + 1);
                        team.getS2StatInfo().setHomeGamesPlayed(homeGamesPlayed + 1);
                        team.getS2StatInfo().setHomeWins(homeWins + 1);
                        team.getStatInfo().setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            team.getS2StatInfo().setStreakCount(streakCount + 1);
                        } else {//on losing streak
                            team.getS2StatInfo().setIsWinStreak(true);
                            team.getS2StatInfo().setStreakCount(1);
                        }
                        team.getS2StatInfo().setWinPercent((double) gameWins / gamesPlayed);
                        team.getS2StatInfo().setTotalPoints(totalPoints + homePoints);
                        team.getS2StatInfo().setPtsPerGame((double) team.getS2StatInfo()
                                .getTotalPoints() / team.getS2StatInfo()
                                        .getGamesPlayed());
                    }
                } else {//At Away
                    if (stageNumber == 1) {//Stage 1
                        team.getStatInfo().setGameWins(gameWins + 1);
                        team.getStatInfo().setAwayGamesPlayed(awayGamesPlayed + 1);
                        team.getStatInfo().setAwayWins(awayWins + 1);
                        team.getStatInfo().setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            team.getStatInfo().setStreakCount(streakCount + 1);
                        } else {//on losing streak
                            team.getStatInfo().setIsWinStreak(true);
                            team.getStatInfo().setStreakCount(1);
                        }
                        team.getStatInfo().setWinPercent((double) team.getStatInfo()
                                .getGameWins() / team.getStatInfo().getGamesPlayed());
                        team.getStatInfo().setTotalPoints(totalPoints + awayPoints);
                        team.getStatInfo().setPtsPerGame((double) team.getStatInfo()
                                .getTotalPoints() / team.getStatInfo()
                                        .getGamesPlayed());
                    } else {//Stage2
                        team.getS2StatInfo().setGameWins(gameWins + 1);
                        team.getS2StatInfo().setAwayGamesPlayed(awayGamesPlayed + 1);
                        team.getS2StatInfo().setAwayWins(awayWins + 1);
                        team.getS2StatInfo().setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            team.getS2StatInfo().setStreakCount(streakCount + 1);
                        } else {//on losing streak
                            team.getS2StatInfo().setIsWinStreak(true);
                            team.getS2StatInfo().setStreakCount(1);
                        }
                        team.getS2StatInfo().setWinPercent((double) gameWins / gamesPlayed);
                        team.getS2StatInfo().setTotalPoints(totalPoints + awayPoints);
                        team.getS2StatInfo().setPtsPerGame((double) team.getS2StatInfo()
                                .getTotalPoints() / team.getS2StatInfo()
                                        .getGamesPlayed());
                    }
                }

            } else {////////////////////////////Lose////////////////////////////
                if (team.getTeamId() == homeTeamId) {//at Home
                    if (stageNumber == 1) {//Stage 1
                        team.getStatInfo().setGameLosses(gameLosses + 1);
                        team.getStatInfo().setHomeGamesPlayed(homeGamesPlayed + 1);
                        team.getStatInfo().setHomeLosses(homeLosses + 1);
                        team.getStatInfo().setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            team.getStatInfo().setIsWinStreak(false);
                            team.getStatInfo().setStreakCount(1);
                        } else {//on losing streak
                            team.getStatInfo().setStreakCount(streakCount + 1);
                        }
                        team.getStatInfo().setWinPercent((double) team.getStatInfo()
                                .getGameWins() / team.getStatInfo().getGamesPlayed());
                        team.getStatInfo().setTotalPoints(totalPoints + homePoints);
                        team.getStatInfo().setPtsPerGame((double) team.getStatInfo()
                                .getTotalPoints() / team.getStatInfo()
                                        .getGamesPlayed());
                    } else {//Stage2
                        team.getS2StatInfo().setGameLosses(gameLosses + 1);
                        team.getS2StatInfo().setHomeGamesPlayed(homeGamesPlayed + 1);
                        team.getS2StatInfo().setHomeLosses(homeLosses + 1);
                        team.getStatInfo().setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            team.getS2StatInfo().setIsWinStreak(false);
                            team.getS2StatInfo().setStreakCount(1);
                        } else {//on losing streak
                            team.getS2StatInfo().setStreakCount(streakCount + 1);
                        }
                        team.getS2StatInfo().setWinPercent((double) team.getStatInfo()
                                .getGameWins() / team.getStatInfo().getGamesPlayed());
                        team.getS2StatInfo().setTotalPoints(totalPoints + homePoints);
                        team.getS2StatInfo().setPtsPerGame((double) team.getS2StatInfo()
                                .getTotalPoints() / team.getS2StatInfo()
                                        .getGamesPlayed());
                    }
                } else {//At Away
                    if (stageNumber == 1) {//Stage 1
                        team.getStatInfo().setGameLosses(gameLosses + 1);
                        team.getStatInfo().setAwayGamesPlayed(awayGamesPlayed + 1);
                        team.getStatInfo().setAwayLosses(awayLosses + 1);
                        team.getStatInfo().setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            team.getStatInfo().setIsWinStreak(false);
                            team.getStatInfo().setStreakCount(1);
                        } else {//on losing streak
                            team.getStatInfo().setStreakCount(streakCount + 1);
                        }
                        team.getStatInfo().setWinPercent((double) team.getStatInfo()
                                .getGameWins() / team.getStatInfo().getGamesPlayed());
                        team.getStatInfo().setTotalPoints(totalPoints + awayPoints);
                        team.getStatInfo().setPtsPerGame((double) team.getStatInfo()
                                .getTotalPoints() / team.getStatInfo()
                                        .getGamesPlayed());
                    } else {//Stage2
                        team.getS2StatInfo().setGameLosses(gameLosses + 1);
                        team.getS2StatInfo().setAwayGamesPlayed(awayGamesPlayed + 1);
                        team.getS2StatInfo().setAwayLosses(awayLosses + 1);
                        team.getS2StatInfo().setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            team.getS2StatInfo().setIsWinStreak(false);
                            team.getS2StatInfo().setStreakCount(1);
                        } else {//on losing streak
                            team.getS2StatInfo().setStreakCount(streakCount + 1);
                        }
                        team.getS2StatInfo().setWinPercent((double) team.getStatInfo()
                                .getGameWins() / team.getStatInfo().getGamesPlayed());
                        team.getS2StatInfo().setTotalPoints(totalPoints + homePoints);
                        team.getS2StatInfo().setPtsPerGame((double) team.getS2StatInfo()
                                .getTotalPoints() / team.getS2StatInfo()
                                        .getGamesPlayed());
                    }
                }
            }
        } else {

        }
        tmDao.addTeam(team);
        return team;
    }

    @Override
    public Team updateTeamStats(Team team, Series series, Tournament tournament) {
        int seriesWins;
        int seriesLosses;
        int seriesPlayed;

        if (!series.isIsComplete()) {
            return team;
        } else {//stage 1
            if (!tournament.isIsSecondStage()) {
                if (series.getSeriesWinnerName()
                        .equalsIgnoreCase(team.getTeamName())) {//series winner
                    seriesWins = team.getStatInfo().getSeriesWins();
                    team.getStatInfo().setSeriesWins(seriesWins + 1);
                } else {//seriesLoser
                    seriesLosses = team.getStatInfo().getSeriesLosses();
                    team.getStatInfo().setSeriesWins(seriesLosses + 1);
                }
                seriesPlayed = team.getStatInfo().getSeriesPlayed();
                team.getStatInfo().setSeriesPlayed(seriesPlayed + 1);
            } else {//stage 2
                if (series.getSeriesWinnerName()
                        .equalsIgnoreCase(team.getTeamName())) {//series winner
                    seriesWins = team.getS2StatInfo().getSeriesWins();
                    team.getS2StatInfo().setSeriesWins(seriesWins + 1);
                } else {//seriesLoser
                    seriesLosses = team.getS2StatInfo().getSeriesLosses();
                    team.getS2StatInfo().setSeriesLosses(seriesLosses + 1);
                }
                seriesPlayed = team.getS2StatInfo().getSeriesPlayed();
                team.getS2StatInfo().setSeriesPlayed(seriesPlayed + 1);
            }
        }
        tmDao.addTeam(team);
        return team;
    }
    
    @Override
    public Player updatePlayerStats(Player player, Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Player updatePlayerStats(Player player, Series series, Tournament tournament) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tournament userJoinTournament(Tournament tournament, String username) throws EventFullException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Team addTeamToTournament(Tournament tournament, String teamName)
            throws TournamentPersistenceException {
        int numOfParticipants = tournament.getActualNumOfParticipants();
        int nextTeamId = config.getNextTeamId();
        Team newTeam = new Team(nextTeamId);
        newTeam.setRank(1);
        newTeam.setTeamName(teamName);
        newTeam.setTournamentId(tournament.getTournamentId());
        Stat teamStat1 = new Stat();
        newTeam.setStatInfo(teamStat1);
        Stat teamStat2 = new Stat();
        newTeam.setS2StatInfo(teamStat2);
        newTeam.getStatInfo().setIsPlayer(false);
        newTeam.getS2StatInfo().setIsPlayer(false);
        tmDao.addTeam(newTeam);
        tournament.setActualNumOfParticipants(numOfParticipants + 1);

        config.setNextTeamId(nextTeamId);

        return newTeam;
    }

    @Override
    public Player addPlayerToTeam(Team team, String playerNameInput)
            throws TournamentPersistenceException {
        int numOfPlayers;
        int nextPlayerId = config.getNextPlayerId();
        Player newPlayer = new Player(nextPlayerId);
        newPlayer.setTeamId(team.getTeamId());
        newPlayer.setIsHuman(false);
        newPlayer.setPlayerName(playerNameInput);
        Stat playerStat = new Stat();
        newPlayer.setStatInfo(playerStat);
        Stat teamStat2 = new Stat();
        newPlayer.setS2StatInfo(teamStat2);
        newPlayer.getStatInfo().setIsPlayer(true);
        newPlayer.getS2StatInfo().setIsPlayer(true);
        numOfPlayers = this.getPlayersByTeamId(team.getTeamId()).size();
        team.setNumOfPlayers(numOfPlayers + 1);
        tmDao.addPlayer(newPlayer);
        
        config.setNextPlayerId(nextPlayerId);
        return newPlayer;
    }

    @Override
    public Player removePlayer(int playerId) throws NoSuchPlayerException {
        Player player = tmDao.removePlayer(playerId);
        if (player != null) {
            return player;
        } else {
            throw new NoSuchPlayerException("No Such Player exists. Please try again.");
        }
    }

    @Override
    public Tournament removeTeamFromTournament(Tournament tournament, int teamId)
            throws NoSuchTeamException {
        List<Player> teamRoster = tmDao.getAllPlayers()
                .stream()
                .filter(p -> p.getTeamId() == teamId)
                .collect(Collectors.toList());
        for (Player player : teamRoster) {
            tmDao.removePlayer(player.getPlayerId());
        }
        Team team = tmDao.removeTeam(teamId);
        if (team != null) {

        } else {
            throw new NoSuchTeamException("No such team exists Please try "
                    + "again.");
        }
        int numOfTeams = this.getTeamsByTournamentId(tournament
                .getTournamentId()).size();
        tournament.setActualNumOfParticipants(numOfTeams);

        return tournament;
    }

    @Override
    public List<Team> getTeamStandings(List<Team> teamList, Tournament tournament) {
        int rank = 1;
        if (!tournament.isIsSecondStage()) {
            Collections.sort(teamList, Comparator.comparing((Team t)
                    -> t.getStatInfo().getWinPercent()).thenComparing(t
                    -> t.getStatInfo().getPtsPerGame()).thenComparing(t
                    -> t.getStatInfo().getTotalPoints()));

//        Collections.sort(teamList, (Team team1, Team team2) 
//                -> Double.compare(team2.getStatInfo().getWinPercent(), 
//                        team1.getStatInfo().getWinPercent()));
        } else {
            Collections.sort(teamList, Comparator.comparing((Team t)
                    -> t.getS2StatInfo().getWinPercent()).thenComparing(t
                    -> t.getS2StatInfo().getPtsPerGame()).thenComparing(t
                    -> t.getS2StatInfo().getTotalPoints()));
//            Collections.sort(teamList, (Team team1, Team team2)
//                    -> Double.compare(team2.getS2StatInfo().getWinPercent(),
//                            team1.getS2StatInfo().getWinPercent()));
        }
        Collections.reverse(teamList);
        for (Team team : teamList) {
            team.setRank(rank);
            rank++;
        }
        return teamList;

    }

//    @Override
//    public List<Stat> getPlayerStandings(List<Team> teamList, Tournament tournament) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void loadTeamEntities() throws TournamentPersistenceException {
        tmDao.loadPlayers();
        tmDao.loadTeams();
    }

    @Override
    public void saveTeamEntities() throws TournamentPersistenceException {
        tmDao.writePlayers();
        tmDao.writeTeams();
    }

    
}
