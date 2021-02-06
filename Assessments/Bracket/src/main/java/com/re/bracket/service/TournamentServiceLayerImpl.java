
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.service;

import com.re.bracket.dao.NoSuchGameException;
import com.re.bracket.dao.NoSuchSeriesException;
import com.re.bracket.dao.NoSuchTournamentException;
import com.re.bracket.dao.TournamentAuditDao;
import com.re.bracket.dao.TournamentConfigurationDao;
import com.re.bracket.dao.TournamentDao;
import com.re.bracket.dao.TournamentPersistenceException;
import com.re.bracket.dto.Game;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Team;
import com.re.bracket.dto.Tournament;
import com.re.bracket.dto.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 *
 * @author rober
 */
public class TournamentServiceLayerImpl implements TournamentServiceLayer {

    private TournamentDao tDao;
    private TournamentAuditDao tAudit;
    private TournamentConfigurationDao config;

    public TournamentServiceLayerImpl(TournamentDao tDao,
            TournamentAuditDao tAudit, TournamentConfigurationDao config) {
        this.tDao = tDao;
        this.tAudit = tAudit;
        this.config = config;
    }

    @Override
    public int getNextTournamentId() throws TournamentPersistenceException {
        return config.getNextTournamentId();
    }

    @Override
    public void setNextTournamentId() throws TournamentPersistenceException {
        config.settNextTournamentId(config.getNextTournamentId());
    }

    public Tournament saveTournament(Tournament tournament) {
        return tDao.addTournament(tournament);
    }

    @Override
    public List<Series> finalizeSchedule(Tournament tournament,
            List<Team> teamList) throws InvalidDateException,
            TournamentPersistenceException, TournamentNotCompleteException {

        int numOfParticipants = teamList.size();
        int numOfRounds = 0;
        List<Series> schedule = null;
        LocalDateTime ldt = tournament.getStartDate();

        if (ldt.isBefore(LocalDate.now().atStartOfDay().withHour(0).withMinute(0))) {
            throw new InvalidDateException("Start Date too far in the past. "
                    + "Date can be as early as beginning of today.");
        }

        if (teamList.size() < 2) {
            throw new TournamentNotCompleteException("Must have a t least 2 "
                    + "teams added to this tournament before finalizing a "
                    + "schedule.");
        }
        tournament.setIsSignUp(false);
        tournament.setMaxNumOfParticipants(numOfParticipants);
        tournament.setIsSecondStage(false);
        tournament.setCurrentRound(0);
        if (tournament.getS1Format().equalsIgnoreCase("Round-Robin")) {
            if (!tournament.isIsSecondStage()) {

            } else {
                tournament.setS1NumOfRounds(numOfRounds);
            }
            schedule = buildRoundRobin(tournament, teamList);
        } else if (tournament.getS1Format().equalsIgnoreCase("Single-Elimination")) {
            schedule = buildSingleElimination(tournament, teamList);
        }
        tournament.setCurrentRound(1);
        this.saveTournament(tournament);

        return schedule;
    }

    private List<Series> buildRoundRobin(Tournament tournament,
            List<Team> teamList) throws TournamentPersistenceException {
        int nextSeriesId;
        int numOfRounds;
        int numOfCycles;
        int thisRoundNumber;
        int randomRoundIndex;
        int roundNumber = 1;
        int roundsPerCycle = teamList.size() - 1;
        int cycledRoundsCount = 0;
        boolean isAvailable;
        Series newSeries;
        List<Integer> usedRoundIndexes = new ArrayList<>();
        List<Team> opposingTeams;
        List<Team> usedTeams = new ArrayList<>();
        List<Series> teamSeriesRoundList;
        List<Series> seriesSoFar = new ArrayList<>();
        List<Series> fullSchedule = new ArrayList<>();

        //How many times teams play each other.
        numOfCycles = tournament.getNumOfCycles();

        /* Add a round to account for the Bye rounds team's will have in the 
        case of there being an odd number of teams. */
        if (roundsPerCycle % 2 == 0) {
            roundsPerCycle++;
        }

        /* Create each team's schedule 1 cycle at a time. This ensures team's 
        play all other teams once before they play any of them again. */
        for (int i = 0; i < numOfCycles; i++) {
            opposingTeams = teamList;
            if (opposingTeams.size() % 2 != 0) {
                Team bye = new Team(0);
                bye.setTeamName("BYE");
                opposingTeams.add(bye);
            }
            /* Random number (0 - roundsPerCycle) generated to mix up the order 
            team's play each other in each cycle. */
            randomRoundIndex = (int) ((Math.random() * (roundsPerCycle - 1)));

            /* Iterate through each team and create a series for each 
            opposing team. */
            for (Team team : teamList) {// Current Team.
                for (Team opposingTeam : opposingTeams) {// Opposing Team.

                    /* Cross reference a list of Teams whose cycle of series 
                    have already been created. If the opposing team matches to 
                    one of those in the list OR if current Team is the opposing 
                    Team, a series will NOT be created. */
                    if (usedTeams.contains(opposingTeam) | team.getTeamName()
                            .equalsIgnoreCase(opposingTeam.getTeamName())) {
                        // Do nothing; skips to the next opposing team.
                    } else {

                        /* The opposing Team is available for matchmaking; First,
                        cross reference all of the series created where the
                        current Team was listed as the opposing Team. 
                        Get the round numbers for those matches and make those 
                        rounds unavaialable to use for this series match up; this 
                        prevents creating a series with the a team having more 
                        than one series match up in the same round. */
                        for (Series existingSeries : seriesSoFar) {
                            if (existingSeries.getTeam2Name().equalsIgnoreCase(team.
                                    getTeamName())) {
                                usedRoundIndexes.add(existingSeries.getRoundNumber()
                                        - cycledRoundsCount
                                        - 1);
                            }
                        }

                        /* Get a list from all the series so far; one that will
                        contain series where the opposing Team is listed either 
                        as Team 1 or Team 2... */
                        teamSeriesRoundList = seriesSoFar
                                .stream()
                                .filter(s -> s.getTeam1Name()
                                .equalsIgnoreCase(opposingTeam.getTeamName())
                                | s.getTeam2Name()
                                        .equalsIgnoreCase(opposingTeam
                                                .getTeamName()))
                                .collect(Collectors.toList());

                        /* ... Then eliminate the corresponding round number 
                        indexes for each of those series match ups. */
                        for (Series teamSeries : teamSeriesRoundList) {
                            int usedRoundNum = teamSeries.getRoundNumber();
                            usedRoundIndexes.add(usedRoundNum
                                    - cycledRoundsCount - 1);
                        }

                        /* Determine the round number to be used, based on the 
                        random round index generated and the list of available
                        round numbers. */
                        do {
                            isAvailable = false;
                            if (randomRoundIndex > roundsPerCycle - 1) {
                                randomRoundIndex = 0;
                            }
                            if (!usedRoundIndexes.contains(randomRoundIndex)) {
                                isAvailable = true;
                                roundNumber = randomRoundIndex + 1;
                            } else {
                                randomRoundIndex++;
                            }
                        } while (!isAvailable);

                        /* Create a new series with the appropriate round 
                        number; add appropriate incrementation to the round
                        number for each cycle*/
//                        LocalDateTime ldt = LocalDatez
                        randomRoundIndex++;
                        nextSeriesId = config.getNextSeriesId();
                        newSeries = new Series(nextSeriesId);
                        newSeries.setDateTime(tournament.getStartDate());
                        newSeries.setIsComplete(false);
                        newSeries.setIsReady(true);
                        newSeries.setNumGamesPlayed(0);
                        newSeries.setBestOfNumGames(1);
                        newSeries.setTeam1Wins(0);
                        newSeries.setTeam2Wins(0);
                        newSeries.setRoundNumber(roundNumber + cycledRoundsCount);
                        newSeries.setSeriesLoserName("");
                        newSeries.setSeriesWinnerName("");
                        newSeries.setTeam1Name(team.getTeamName());
                        newSeries.setTeam2Name(opposingTeam.getTeamName());
                        newSeries.setTeam1Id(team.getTeamId());
                        newSeries.setTeam2Id(opposingTeam.getTeamId());
                        newSeries.setTournamentId(tournament.getTournamentId());
                        tDao.addSeries(newSeries);
                        config.setNextSeriesId(newSeries.getSeriesId());
                        seriesSoFar.add(newSeries);
                        thisRoundNumber = newSeries.getRoundNumber();
                        usedRoundIndexes.add(thisRoundNumber
                                - cycledRoundsCount - 1);
                        /* Series will be added to the seriesSoFar list used for 
                        subsequent matchmaking */
                    }
                    //when done making this Team's unmade matchups, clear indexes
                    usedRoundIndexes.removeAll(usedRoundIndexes);
                }
                //add each team to list after done making their schedule
                usedTeams.add(team);
            }
            /* Add all the series created each cycle to the full schedule and 
            clear necessary lists. */
            fullSchedule.addAll(seriesSoFar);
            seriesSoFar.removeAll(seriesSoFar);
            usedTeams.removeAll(usedTeams);
            cycledRoundsCount = cycledRoundsCount + roundsPerCycle;
        }
        numOfRounds = numOfCycles * roundsPerCycle;
        tournament.setS1NumOfRounds(numOfRounds);
        return fullSchedule;
    }

    @Override
    public List<Series> getScheduleByTournament(Tournament tournament) {
        int numOfParticipants = tournament.getActualNumOfParticipants();
        int matchupCount;
        int maxRoundComplete;
        if (numOfParticipants % 2 != 0) {
            numOfParticipants++;
        }
        matchupCount = (int) (numOfParticipants / 2);
        List<Series> schedule = tDao.getAllSeries()
                .stream()
                .filter(s -> s.getTournamentId() == tournament.getTournamentId())
                .collect(Collectors.toList());

        maxRoundComplete = schedule.stream().filter(s -> s.isIsComplete())
                .mapToInt(s -> s.getRoundNumber()).max().orElse(0);
        tournament.setCurrentRound(maxRoundComplete);
//        if(tournament.getCurrentRound() == 0 & !tournament.isIsSignUp()){
//            tournament.setCurrentRound(1);
//        }

//        List<Series> currentRoundCompleteedSeries = schedule.stream().filter(s
//                -> s.getRoundNumber() == maxRoundComplete)
//                .collect(Collectors.toList());
//        if(currentRoundCompleteedSeries.size() == matchupCount){
//            tournament.setCurrentRound(maxRoundComplete + 1);
//        } 
        tDao.addTournament(tournament);

        return schedule;
    }

    private List<Series> buildSingleElimination(Tournament tournament,
            List<Team> teamList) throws TournamentPersistenceException {
        int numOfTeams = tournament.getMaxNumOfParticipants();
        int rounds = 0;
        int numRound1Series = 0;
        int teamByes = 0;
        if (numOfTeams == 2) {//2 teams
            rounds = 1;
        } else if (numOfTeams > 2 & numOfTeams <= 4) {
            rounds = 2;
        } else if (numOfTeams > 4 & numOfTeams <= 8) {
            rounds = 3;
        } else if (numOfTeams > 8 & numOfTeams <= 16) {
            rounds = 4;
        } else if (numOfTeams > 16 & numOfTeams <= 32) {
            rounds = 5;
        } else if (numOfTeams > 32 & numOfTeams <= 64) {
            rounds = 6;
        } else if (numOfTeams > 64 & numOfTeams <= 128) {
            rounds = 7;
        } else if (numOfTeams > 128 & numOfTeams <= 256) {
            rounds = 8;
        }
        if (tournament.isIsSecondStage()) {
            tournament.setS2NumOfRounds(rounds);
        } else {
            tournament.setS1NumOfRounds(rounds);
        }
        numRound1Series = numOfTeams - rounds;
        teamByes = numOfTeams - (2 * numRound1Series);
//        tDao.addTournament(tournament);

        return generateMatchUps(tournament, teamList, numRound1Series,
                teamByes);
    }

    private List<Series> generateMatchUps(Tournament tournament, List<Team> teams,
            int numRound1Series, int teamByes) throws TournamentPersistenceException {

        int seriesId;
        int lowestSeed = Collections.max(teams, (Team team1, Team team2)
                -> Integer.compare(team1.getRank(), team2.getRank())).getRank();

        int highestSeed = Collections.min(teams, (Team team1, Team team2)
                -> Integer.compare(team1.getRank(), team2.getRank())).getRank();
        Series newSeries;
        List<Series> matchUps = new ArrayList<>();

        for (int i = 0; i < numRound1Series; i++) {
            highestSeed = highestSeed + teamByes + i;
            for (Team betterTeam : teams) {
                if (betterTeam.getRank() == highestSeed) {
                    for (Team worserTeam : teams) {
                        if (worserTeam.getRank() == lowestSeed) {//addSeriesNumber
                            seriesId = config.getNextSeriesId();
                            newSeries = new Series(seriesId);
                            newSeries.setDateTime(LocalDateTime.now());
                            newSeries.setIsComplete(false);
                            newSeries.setIsReady(true);//////make user choice
                            newSeries.setNumGamesPlayed(0);
                            newSeries.setRoundNumber(1);
                            newSeries.setSeriesLoserName("");
                            newSeries.setSeriesWinnerName("");
                            newSeries.setTeam1Name(betterTeam.getTeamName());
                            newSeries.setTeam2Name(worserTeam.getTeamName());
                            newSeries.setTournamentId(tournament.getTournamentId());
                            tDao.addSeries(newSeries);
                            config.setNextSeriesId(seriesId);
                            lowestSeed--;
                            matchUps.add(newSeries);
                        }
                    }
                }
            }
        }
        return matchUps;
    }

    //make seriesNumber property in Series in order to reference Teams
    //series history in Tourney and use it to match Up the remaining teams 
    @Override
    public Tournament startTournament(Tournament tournament)
            throws TournamentNotReadyException {
        LocalDateTime ldt = tournament.getStartDate().withSecond(0).withNano(0);
        if (tournament.isIsSignUp() & tournament.getStartDate()
                .isAfter(LocalDateTime.now())) {
            throw new TournamentNotReadyException("Cannot start until "
                    + tournament.getStartDate() + "\n. If you wish to start now "
                    + "change the start date and time so other "
                    + "participants will be notified.");
        }
        tournament.setIsSignUp(false);
        tournament.setCurrentRound(1);

        return this.saveTournament(tournament);
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return tDao.getAllTournaments();
    }

    @Override
    public List<Tournament> getAllTournamentsByUsername(String username) {
        return this.getAllTournaments()
                .stream()
                .filter(t -> t.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }

    @Override
    public Tournament getTournament(int tournamentId) throws NoSuchTournamentException {
        return tDao.getTournament(tournamentId);
    }

//    @Override
//    public Tournament buildBracket(Tournament tournament, List<Team> allTeams) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<Series> getScheduleByTournament(int tournamentId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public Series getSeries(int seriesId) throws NoSuchSeriesException {
        return tDao.getSeries(seriesId);
    }

    @Override
    public Game addGameToSeries(Series series)
            throws TournamentPersistenceException {
        int gameId = config.getNextGameId();
        int seriesId = series.getSeriesId();
        String awayTeam = series.getTeam2Name();
        String homeTeam = series.getTeam1Name();
        List<Game> seriesGames = this.getGamesbySeriesId(series.getSeriesId());
        int gamesCount = seriesGames.size();
        int stageNumber;
        boolean isSecondStage = tDao.getTournament(series.getTournamentId()).isIsSecondStage();
        if (isSecondStage) {
            stageNumber = 2;
        } else {
            stageNumber = 1;
        }
        Game newGame = new Game(gameId);
        newGame.setIsReady(true);
        newGame.setHomeTeam(homeTeam);
        newGame.setAwayTeam(awayTeam);
        newGame.setHomeTeamId(series.getTeam1Id());
        newGame.setAwayTeamId(series.getTeam2Id());
        newGame.setSeriesId(seriesId);
        newGame.setWinningTeamName("");
        newGame.setLosingTeamName("");
        newGame.setIsComplete(false);
        newGame.setIsNeutralField(true);
        newGame.setDateTime(series.getDateTime());
        newGame.setSeriesGameNum(gamesCount + 1);
        newGame.setStageNumber(stageNumber);

        tDao.addGame(newGame);
        config.setNextGameId(gameId);

        return newGame;
    }

    @Override
    public Game getGame(int gameId) throws NoSuchGameException {
        return tDao.getGame(gameId);
    }

    @Override
    public List<Game> getGamesbySeriesId(int seriesId) {
        return tDao.getAllGames()
                .stream()
                .filter(g -> g.getSeriesId() == seriesId)
                .collect(Collectors.toList());
    }

    @Override
    public Game updateGame(Game game) throws PlayersNotCheckedInException,
            TournamentPersistenceException {

//        final String I = "][";
//        int checkInsNeeded = 5;
//        String checkIns = "JohnDoe123][Rob3113][JoeSmith456][SallyMae789";
//        String[] checkInTokens = checkIns.split(I);
//
//        if (checkInTokens.length < checkInsNeeded) {
//            game.setIsComplete(false);
//            game.setAwayScore(0);
//            game.setHomeScore(0);
//            game.setWinningTeamName("");
//            game.setLosingTeamName("");
//            game.setIsReady(false);
//        }
        if (!game.isIsReady()) {
            throw new PlayersNotCheckedInException("Not all participants are "
                    + "checked in.");
        }
        game.setIsComplete(true);
        tAudit.writeAudit("GameID: " + game.getGameId() + " - Series Gm #: "
                + game.getSeriesGameNum() + "-Stage: " + game.getStageNumber()
                + " - Home: " + game.getHomeTeam() + ":" + game.getHomeScore()
                + " - Away: " + game.getAwayTeam() + ":" + game.getAwayScore()
                + " - Winner: " + game.getWinningTeamName() + " - "
                + game.getDateTime());
        tDao.addGame(game);
        return game;
    }

    @Override
    public Game removeGame(int gameId) {
        return tDao.removeGame(gameId);
    }

    @Override
    public Series removeSeries(int seriesId) throws NoSuchSeriesException {
        return tDao.removeSeries(seriesId);
    }

    @Override
    public Series updateSeries(Series series) throws SeriesNotReadyException,
            InvalidResultsException {
        List<Game> games = tDao.getAllGames()
                .stream()
                .filter(g -> g.getSeriesId() == series.getSeriesId())
                .collect(Collectors.toList());
        int gamesPlayed = games.size();
        int bestOf = series.getBestOfNumGames();
        int targetWins = (int) (bestOf + 1) / 2;
        int team1Wins = 0;
        int team2Wins = 0;
        Tournament tournament = tDao.getTournament(series.getTournamentId());
        if (!series.isIsReady() | series.getDateTime().isAfter(LocalDateTime
                .now().withSecond(0).withNano(0))) {
            throw new SeriesNotReadyException("Series cannot be updated until "
                    + "previous rounds have been completed, \nparticipants "
                    + "checked-in (if needed), and current time is not before "
                    + "start time.");
        }
        for (Game game : games) {
            if (game.getWinningTeamName().equalsIgnoreCase(series
                    .getTeam1Name())) {
                team1Wins++;
            } else if (game.getWinningTeamName().equalsIgnoreCase(series
                    .getTeam2Name())) {
                team2Wins++;
            } else {

            }
            //update stats here?
            if (gamesPlayed >= targetWins) {
                if (team1Wins + team2Wins > bestOf) {
                    throw new InvalidResultsException("The number of games "
                            + "exceed the predetermined game limit.\nDelete "
                            + "games to fit the tournament criteria.");
                } else {//series complete
                    if (team1Wins == targetWins) {
                        series.setSeriesWinnerName(series.getTeam1Name());
                        series.setSeriesLoserName(series.getTeam2Name());
                        series.setIsComplete(true);
                        break;
                    } else if (team2Wins == targetWins) {
                        series.setSeriesWinnerName(series.getTeam2Name());
                        series.setSeriesLoserName(series.getTeam1Name());
                        series.setIsComplete(true);
                        break;
                    }
                }
            }
        }
        series.setTeam1Wins(team1Wins);
        series.setTeam2Wins(team2Wins);
        series.setNumGamesPlayed(gamesPlayed);
        tDao.addSeries(series);

        return series;
    }

    @Override
    public List<Game> getCompletedGames(Tournament tournament) {
        List<Series> matchups = getScheduleByTournament(tournament);
        List<Integer> seriesIds = new ArrayList<>();
        matchups
                .stream()
                .mapToInt(s -> s.getSeriesId())
                .forEach(i -> seriesIds.add(i));

        List<Game> allCompletedGames = tDao.getAllGames()
                .stream()
                .filter(g -> seriesIds.contains(g.getSeriesId()))
                .filter(c -> c.isIsComplete())
                .collect(Collectors.toList());
        
        return allCompletedGames;
    
        
    }

//    @Override
//    public Tournament updateTournament(Tournament tournament) {
//        
//    }
    @Override
    public Tournament deleteTournament(int tournamentId) throws NoSuchTournamentException {
        return tDao.removeTournament(tournamentId);
    }

    @Override
    public void loadTournamentEntities() throws TournamentPersistenceException {
        tDao.loadGames();
        tDao.loadSeries();
        tDao.loadTournaments();
    }

    @Override
    public void saveTournamentEntities() throws TournamentPersistenceException {
        tDao.writeGames();
        tDao.writeSeries();
        tDao.writeTournaments();
    }

}
