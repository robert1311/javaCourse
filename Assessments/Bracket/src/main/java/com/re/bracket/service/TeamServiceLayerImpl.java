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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
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
    public Team updateTeamStats(Team team, Game game) throws TournamentPersistenceException {
        String winningTeamName = game.getWinningTeamName();
        int homeTeamId = game.getHomeTeamId();
        int stageNumber = game.getStageNumber();
        Stat currentStat;
        if (stageNumber == 1) {
            currentStat = team.getStatInfo();
        } else {
            currentStat = team.getS2StatInfo();
        }
        int awayGamesPlayed = currentStat.getAwayGamesPlayed();
        int awayLosses = currentStat.getAwayLosses();
        int awayWins = currentStat.getAwayWins();
        int gameLosses = currentStat.getGameLosses();
        int gameWins = currentStat.getGameWins();
        int homeGamesPlayed = currentStat.getHomeGamesPlayed();
        int homeLosses = currentStat.getHomeLosses();
        int homeWins = currentStat.getHomeWins();
        int gamesPlayed = currentStat.getGamesPlayed();
        boolean isWinStreak = currentStat.isIsWinStreak();
        int streakCount = currentStat.getStreakCount();
        int totalPoints = currentStat.getTotalPoints();
        int homePoints = game.getHomeScore();
        int awayPoints = game.getAwayScore();

        if (game.isIsComplete()) {
            //////////////////////////Win //////////////////////////////
            if (winningTeamName.equalsIgnoreCase(team.getTeamName())) {//win
                if (team.getTeamId() == homeTeamId) {//at Home
                    currentStat.setGameWins(gameWins + 1);
                    currentStat.setHomeGamesPlayed(homeGamesPlayed + 1);
                    currentStat.setHomeWins(homeWins + 1);
                    currentStat.setGamesPlayed(gamesPlayed + 1);
                    if (isWinStreak) {//on winning streak
                        currentStat.setStreakCount(streakCount + 1);
                    } else {//on losing streak
                        currentStat.setIsWinStreak(true);
                        currentStat.setStreakCount(1);
                    }
                    currentStat.setWinPercent((double) currentStat
                            .getGameWins() / currentStat.getGamesPlayed());
                    currentStat.setTotalPoints(totalPoints + homePoints);
                    currentStat.setPtsPerGame((double) currentStat
                            .getTotalPoints() / currentStat
                                    .getGamesPlayed());
                } else {//At Away
                    currentStat.setGameWins(gameWins + 1);
                    currentStat.setAwayGamesPlayed(awayGamesPlayed + 1);
                    currentStat.setAwayWins(awayWins + 1);
                    currentStat.setGamesPlayed(gamesPlayed + 1);
                    if (isWinStreak) {//on winning streak
                        currentStat.setStreakCount(streakCount + 1);
                    } else {//on losing streak
                        currentStat.setIsWinStreak(true);
                        currentStat.setStreakCount(1);
                    }
                    currentStat.setWinPercent((double) currentStat
                            .getGameWins() / currentStat.getGamesPlayed());
                    currentStat.setTotalPoints(totalPoints + awayPoints);
                    currentStat.setPtsPerGame((double) currentStat
                            .getTotalPoints() / currentStat
                                    .getGamesPlayed());
                }
            } else {////////////////////////////Lose////////////////////////////
                if (team.getTeamId() == homeTeamId) {//at Home
                    currentStat.setGameLosses(gameLosses + 1);
                    currentStat.setHomeGamesPlayed(homeGamesPlayed + 1);
                    currentStat.setHomeLosses(homeLosses + 1);
                    currentStat.setGamesPlayed(gamesPlayed + 1);
                    if (isWinStreak) {//on winning streak
                        currentStat.setIsWinStreak(false);
                        currentStat.setStreakCount(1);
                    } else {//on losing streak
                        currentStat.setStreakCount(streakCount + 1);
                    }
                    currentStat.setWinPercent((double) currentStat
                            .getGameWins() / currentStat.getGamesPlayed());
                    currentStat.setTotalPoints(totalPoints + homePoints);
                    currentStat.setPtsPerGame((double) currentStat
                            .getTotalPoints() / currentStat
                                    .getGamesPlayed());
                } else {//At Away
                    currentStat.setGameLosses(gameLosses + 1);
                    currentStat.setAwayGamesPlayed(awayGamesPlayed + 1);
                    currentStat.setAwayLosses(awayLosses + 1);
                    currentStat.setGamesPlayed(gamesPlayed + 1);
                    if (isWinStreak) {//on winning streak
                        currentStat.setIsWinStreak(false);
                        currentStat.setStreakCount(1);
                    } else {//on losing streak
                        currentStat.setStreakCount(streakCount + 1);
                    }
                    currentStat.setWinPercent((double) currentStat
                            .getGameWins() / currentStat.getGamesPlayed());
                    currentStat.setTotalPoints(totalPoints + awayPoints);
                    currentStat.setPtsPerGame((double) currentStat
                            .getTotalPoints() / currentStat
                                    .getGamesPlayed());
                }
            }
            //log team results
            String teamResult = team.getTeamName();
            if (team.getTeamId() == game.getHomeTeamId()) {
                teamResult += "HOME ";
            } else {
                teamResult += "AWAY ";
            }
            if (team.getTeamName().equalsIgnoreCase(game.getWinningTeamName())) {
                teamResult += "WIN";
            } else {
                teamResult += "LOSS";
            }
            tmAudit.writeAudit("TeamID: " + team.getTeamId() + " - "
                    + team.getTeamName() + " GameID: " + game.getGameId() + " - "
                    + "SeriesID: " + game.getSeriesId() + " TournamentID: "
                    + team.getTournamentId() + " - " + teamResult + " : "
                    + game.getHomeScore() + " - " + game.getAwayScore() + " - "
                    + game.getDateTime().format(DateTimeFormatter
                            .ofPattern("MM/dd/yyyy HH:mm")));
        } else {
            //do not update stats
        }
        tmDao.addTeam(team);
        return team;
    }

    @Override
    public Team updateTeamStats(Team team, Series series, Tournament tournament)
            throws TournamentPersistenceException {
        int seriesWins;
        int seriesLosses;
        int seriesPlayed;
        Stat currentStat;
        if (!tournament.isIsSecondStage()) {
            currentStat = team.getStatInfo();
        } else {
            currentStat = team.getS2StatInfo();
        }

        if (!series.isIsComplete()) {
            return team;
        } else {//stage 1
            if (series.getSeriesWinnerName()
                    .equalsIgnoreCase(team.getTeamName())) {//series winner
                seriesWins = currentStat.getSeriesWins();
                currentStat.setSeriesWins(seriesWins + 1);
            } else {//seriesLoser
                seriesLosses = currentStat.getSeriesLosses();
                currentStat.setSeriesLosses(seriesLosses + 1);
            }
            seriesPlayed = currentStat.getSeriesPlayed();
            currentStat.setSeriesPlayed(seriesPlayed + 1);
            //log team results
        }
        String teamResult = team.getTeamName();
        if (team.getTeamName().equalsIgnoreCase(series.getSeriesWinnerName())) {
            teamResult += "WIN ";
        } else {
            teamResult += "LOSS ";
        }

        tmAudit.writeAudit("TeamID: " + team.getTeamId() + " - "
                + team.getTeamName() + " - " + " SeriesID: "
                + series.getSeriesId() + " TournamentID: "
                + team.getTournamentId() + " - " + teamResult + " : "
                + series.getDateTime().format(DateTimeFormatter
                        .ofPattern("MM/dd/yyyy HH:mm")));
        tmDao.addTeam(team);
        return team;
    }

    @Override
    public List<Player> updatePlayerStats(List<Player> players, Game game)
            throws TournamentPersistenceException {
        String winningTeamName = game.getWinningTeamName();
        int homeTeamId = game.getHomeTeamId();
        int stageNumber = game.getStageNumber();

        for (Player player : players) {
            Team team = tmDao.getTeam(player.getTeamId());
            Stat currentStat;
            if (stageNumber == 1) {
                currentStat = player.getStatInfo();
            } else {
                currentStat = player.getS2StatInfo();
            }
            int awayGamesPlayed = currentStat.getAwayGamesPlayed();
            int awayLosses = currentStat.getAwayLosses();
            int awayWins = currentStat.getAwayWins();
            int gameLosses = currentStat.getGameLosses();
            int gameWins = currentStat.getGameWins();
            int homeGamesPlayed = currentStat.getHomeGamesPlayed();
            int homeLosses = currentStat.getHomeLosses();
            int homeWins = currentStat.getHomeWins();
            int gamesPlayed = currentStat.getGamesPlayed();
            boolean isWinStreak = currentStat.isIsWinStreak();
            int streakCount = currentStat.getStreakCount();
            int totalPoints = currentStat.getTotalPoints();
            int posPts = currentStat.getPosPoints();//temp
            int negPts = currentStat.getNegPoints();//temp
            int homePoints = game.getHomeScore();
            int awayPoints = game.getAwayScore();

            if (game.isIsComplete()) {
                //////////////////////////Win //////////////////////////////
                if (winningTeamName.equalsIgnoreCase(team.getTeamName())) {//win
                    if (player.getTeamId() == homeTeamId) {//at Home
                        currentStat.setGameWins(gameWins + 1);
                        currentStat.setHomeGamesPlayed(homeGamesPlayed + 1);
                        currentStat.setHomeWins(homeWins + 1);
                        currentStat.setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            currentStat.setStreakCount(streakCount + 1);
                        } else {//on losing streak
                            currentStat.setIsWinStreak(true);
                            currentStat.setStreakCount(1);
                        }
                        currentStat.setWinPercent((double) currentStat
                                .getGameWins() / currentStat.getGamesPlayed());
                        currentStat.setTotalPoints(totalPoints + posPts - negPts);
                        currentStat.setPtsPerGame((double) currentStat
                                .getTotalPoints() / currentStat
                                        .getGamesPlayed());
                    } else {//At Away
                        currentStat.setGameWins(gameWins + 1);
                        currentStat.setAwayGamesPlayed(awayGamesPlayed + 1);
                        currentStat.setAwayWins(awayWins + 1);
                        currentStat.setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            currentStat.setStreakCount(streakCount + 1);
                        } else {//on losing streak
                            currentStat.setIsWinStreak(true);
                            currentStat.setStreakCount(1);
                        }
                        currentStat.setWinPercent((double) currentStat
                                .getGameWins() / currentStat.getGamesPlayed());
                        currentStat.setTotalPoints(totalPoints + posPts - negPts);
                        currentStat.setPtsPerGame((double) currentStat
                                .getTotalPoints() / currentStat
                                        .getGamesPlayed());
                    }
                } else {////////////////////////////Lose////////////////////////////
                    if (player.getTeamId() == homeTeamId) {//at Home
                        currentStat.setGameLosses(gameLosses + 1);
                        currentStat.setHomeGamesPlayed(homeGamesPlayed + 1);
                        currentStat.setHomeLosses(homeLosses + 1);
                        currentStat.setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            currentStat.setIsWinStreak(false);
                            currentStat.setStreakCount(1);
                        } else {//on losing streak
                            currentStat.setStreakCount(streakCount + 1);
                        }
                        currentStat.setWinPercent((double) currentStat
                                .getGameWins() / currentStat.getGamesPlayed());
                        currentStat.setTotalPoints(totalPoints + posPts - negPts);
                        currentStat.setPtsPerGame((double) currentStat
                                .getTotalPoints() / currentStat
                                        .getGamesPlayed());
                    } else {//At Away
                        currentStat.setGameLosses(gameLosses + 1);
                        currentStat.setAwayGamesPlayed(awayGamesPlayed + 1);
                        currentStat.setAwayLosses(awayLosses + 1);
                        currentStat.setGamesPlayed(gamesPlayed + 1);
                        if (isWinStreak) {//on winning streak
                            currentStat.setIsWinStreak(false);
                            currentStat.setStreakCount(1);
                        } else {//on losing streak
                            currentStat.setStreakCount(streakCount + 1);
                        }
                        currentStat.setWinPercent((double) currentStat
                                .getGameWins() / currentStat.getGamesPlayed());
                        currentStat.setTotalPoints(totalPoints + posPts - negPts);
                        currentStat.setPtsPerGame((double) currentStat
                                .getTotalPoints() / currentStat
                                        .getGamesPlayed());
                    }
                }
                //log player results
            } else {
                //do not update stats
            }
            String result = "";
            if (game.getWinningTeamName().equalsIgnoreCase(team.getTeamName())) {
                result += "WIN ";
            } else {
                result += "LOSS ";
            }
            tmAudit.writeAudit("PlayerID: " + player.getPlayerId() + " - "
                    + "TeamID: " + player.getTeamId() + " "
                    + "GameID: " + game.getGameId() + " - Series: "
                    + game.getSeriesId() + " " + player.getPlayerName() + " +"
                    + currentStat.getPosPoints() + "_-"
                    + currentStat.getNegPoints() + " " + result
                    + game.getHomeScore() + " - " + game.getAwayScore()
                    + game.getDateTime().format(DateTimeFormatter
                            .ofPattern("MM/dd/yyyy HH:mm")));
            currentStat.setPosPoints(0);
            currentStat.setNegPoints(0);
            tmDao.addPlayer(player);
        }
        return players;
    }

    @Override
    public List<Player> updatePlayerStats(List<Player> players, Series series,
            Tournament tournament) throws TournamentPersistenceException {
        int seriesWins;
        int seriesLosses;
        int seriesPlayed;
        Stat currentStat;
        Team team;

        for (Player player : players) {
            if (!tournament.isIsSecondStage()) {
                currentStat = player.getStatInfo();
            } else {
                currentStat = player.getS2StatInfo();
            }

            if (!series.isIsComplete()) {
                return players;
            } else {//stage 1
                team = tmDao.getTeam(player.getTeamId());
                if (series.getSeriesWinnerName()
                        .equalsIgnoreCase(team.getTeamName())) {//series winner
                    seriesWins = currentStat.getSeriesWins();
                    currentStat.setSeriesWins(seriesWins + 1);
                } else {//seriesLoser
                    seriesLosses = currentStat.getSeriesLosses();
                    currentStat.setSeriesLosses(seriesLosses + 1);
                }
                seriesPlayed = currentStat.getSeriesPlayed();
                currentStat.setSeriesPlayed(seriesPlayed + 1);
                //log player results
            }

//            String result = "";
//            if(series.getSeriesWinnerName().equalsIgnoreCase(team.getTeamName())){
//                result += "WIN";
//            } else {
//                result += "LOSE";
//            }
//            tmAudit.writeAudit("PlayerID: " + player.getPlayerId() + " - "
//                    + "GameID: " + series.getSeriesId() + " - Series: " 
//                    + series.getSeriesId() + " -TournamentID: " 
//                    + series.getTournamentId() + result + series.getDateTime() );
            tmDao.addPlayer(player);
        }
        return players;
    }

    @Override
    public Tournament userJoinTournament(Tournament tournament, String username)
            throws EventFullException {
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
    public List<Team> getTeamStandings(List<Team> teamList, 
            Tournament tournament, List<Game> completedGames) {
        int pntDifferential;
        Stat stat;
        
        for(Team team : teamList){
            pntDifferential = 0;
            for(Game game : completedGames){
                if(team.getTeamName().equalsIgnoreCase(game.getWinningTeamName())){
                    pntDifferential += Math.abs(game.getAwayScore() - game.getHomeScore());
                } else if(team.getTeamName().equalsIgnoreCase(game.getLosingTeamName())){
                    pntDifferential += -Math.abs(game.getAwayScore() - game.getHomeScore());
                } else {
                    
                }
            }
            if(!tournament.isIsSecondStage()){
                stat = team.getStatInfo();
                
            } else {
                stat = team.getS2StatInfo();
            }
            stat.setPntDifferential(pntDifferential);
            
        }
        
        int rank = 1;
        if (!tournament.isIsSecondStage()) {
            Collections.sort(teamList, Comparator.comparing((Team t)
                    -> t.getStatInfo().getWinPercent()).thenComparing(t
                    -> t.getStatInfo().getGameWins()).thenComparing(t
                    -> t.getStatInfo().getGameLosses(), Comparator
                            .reverseOrder()).thenComparing(t
                    -> t.getStatInfo().getPntDifferential()).thenComparing(t
                    -> t.getStatInfo().getPtsPerGame()).thenComparing(t
                    -> t.getStatInfo().getTotalPoints()));

//        Collections.sort(teamList, (Team team1, Team team2) 
//                -> Double.compare(team2.getStatInfo().getWinPercent(), 
//                        team1.getStatInfo().getWinPercent()));
        } else {
            Collections.sort(teamList, Comparator.comparing((Team t)
                    -> t.getS2StatInfo().getWinPercent()).thenComparing(t
                    -> t.getStatInfo().getPntDifferential()).thenComparing(t
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

    @Override
    public String getSingleGameResult(int gameId) throws TournamentPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("teamAudit.txt")));
        } catch (FileNotFoundException e) {
            throw new TournamentPersistenceException("Cannot fetch results.", e);
        }
        String result = "";
        String currentLine;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            if (currentLine.contains("PlayerID: ")& currentLine
                    .contains("GameID: " + gameId)) {
                result += currentLine + "\n";
            }
        }
        sc.close();
        
        return result;
    }

    @Override
    public String getAllGameResults(int teamId) throws TournamentPersistenceException {
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("teamAudit.txt")));
        } catch (FileNotFoundException e) {
            throw new TournamentPersistenceException("Cannot fetch results.", e);
        }
        String result = "";
        String currentLine;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            if (currentLine.contains("TeamID: " + teamId)) {
                result += currentLine + "\n";
            }
        }
        sc.close();
        
        return result;
    }

}
