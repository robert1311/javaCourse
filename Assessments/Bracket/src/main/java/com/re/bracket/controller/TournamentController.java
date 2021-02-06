/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.controller;

import com.re.bracket.dao.NoSuchGameException;
import com.re.bracket.dao.NoSuchSeriesException;
import com.re.bracket.dao.NoSuchTeamException;
import com.re.bracket.dao.NoSuchTournamentException;
import com.re.bracket.dao.TeamAuditDao;
import com.re.bracket.dao.TeamAuditDaoFileImpl;
import com.re.bracket.dao.TeamDao;
import com.re.bracket.dao.TeamDaoFileImpl;
import com.re.bracket.dao.TournamentAuditDao;
import com.re.bracket.dao.TournamentAuditDaoFileImpl;
import com.re.bracket.dao.TournamentConfigurationDao;
import com.re.bracket.dao.TournamentConfigurationDaoFileImpl;
import com.re.bracket.dao.TournamentDao;
import com.re.bracket.dao.TournamentDaoFileImpl;
import com.re.bracket.dao.TournamentPersistenceException;
import com.re.bracket.dao.UserAuditDao;
import com.re.bracket.dao.UserAuditDaoFileImpl;
import com.re.bracket.dao.UserDao;
import com.re.bracket.dao.UserDaoFileImpl;
import com.re.bracket.dto.Game;
import com.re.bracket.dto.Player;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
import com.re.bracket.dto.Tournament;
import com.re.bracket.dto.User;
import com.re.bracket.service.EventFullException;
import com.re.bracket.service.InvalidDateException;
import com.re.bracket.service.InvalidInputException;
import com.re.bracket.service.InvalidResultsException;
import com.re.bracket.service.InvalidUserNameException;
import com.re.bracket.service.LoginFailException;
import com.re.bracket.service.PlayerNameTakenException;
import com.re.bracket.service.PlayersNotCheckedInException;
import com.re.bracket.service.SeriesNotReadyException;
import com.re.bracket.service.TeamNameTakenException;
import com.re.bracket.service.TeamServiceLayer;
import com.re.bracket.service.TeamServiceLayerImpl;
import com.re.bracket.service.TournamentNotCompleteException;
import com.re.bracket.service.TournamentServiceLayer;
import com.re.bracket.service.TournamentServiceLayerImpl;
import com.re.bracket.service.UserServiceLayer;
import com.re.bracket.service.UserServiceLayerImpl;
import com.re.bracket.ui.UserIO;
import com.re.bracket.ui.UserIOConsoleImpl;
import com.re.bracket.ui.View;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author rober
 */
public class TournamentController {

    TournamentServiceLayer tService;
    TeamServiceLayer tmService;
    UserServiceLayer uService;
    View view;

    public TournamentController(TournamentServiceLayer tService,
            TeamServiceLayer tmService, UserServiceLayer uService, View view) {
        this.uService = uService;
        this.tmService = tmService;
        this.tService = tService;
        this.view = view;
    }

    public void run() {

        int loginSelection;
        boolean isRunning;

        do {
            loadProgram();
            isRunning = true;
            loginSelection = view.loginMenuGetSelection();
            switch (loginSelection) {
                case 1:
                    signUp();
                    break;
                case 2:
                    User authorizedUser = logIn();
                    tournamentRun(authorizedUser);
                    break;
                case 3:
                    isRunning = false;
                    break;
            }
            saveProgram();
        } while (isRunning);
        view.exitMessage();
    }

    public void tournamentRun(User currentUser) {
        boolean keepGoing = true;
        int selection;
        while (keepGoing) {
            view.welcomeBanner(currentUser);
            selection = view.displayMenuAndGetSelection();

            try {
                switch (selection) {
                    case 1:
                        createTournament(currentUser);
                        break;
                    case 2:
                        getTournament(currentUser);
                        break;
                    case 3:
                        getAllTournaments(currentUser);
                        break;
                    case 4:
                        updateTournament(currentUser);
                        break;
                    case 5:
                        deleteTournament(currentUser);
                        break;
                    case 6:
                        logOff(currentUser);
                        keepGoing = false;
                }
            } catch (TournamentPersistenceException e) {
                view.errorMessage(e.getMessage());
            }
        }
    }

    /////////////////// Primary Tournament Menu Methods ////////////////////////
    private void signUp() {
        String newUsername;
        boolean hasErrors;
        do {
            hasErrors = false;
            newUsername = view.getNewUserName();
            try {
                uService.createNewUser(newUsername);
                view.userAddedBanner(newUsername);
            } catch (InvalidUserNameException e) {
                hasErrors = true;
                view.errorMessage(e.getMessage());
            } catch (TournamentPersistenceException f) {
                view.errorMessage(f.getMessage());
            }

        } while (hasErrors);
    }

    private User logIn() {
        String loginInput;
        boolean hasErrors;
        User loggedInUser = null;
        do {
            hasErrors = false;
            loginInput = view.loginForm();
            try {
                loggedInUser = uService.getLoggedInUser(loginInput);
                view.successfulLoginBanner();
            } catch (LoginFailException e) {
                hasErrors = true;
                view.errorMessage(e.getMessage());
            }
        } while (hasErrors);
        return loggedInUser;
    }

    private void createTournament(User currentUser) {
        int nextTournamentId = 0;
        List<Team> teamList = new ArrayList<>();
        List<Series> fullSchedule = null;
        try {
            nextTournamentId = tService.getNextTournamentId();

            Tournament newTournament = view.getNewTournamentInfo(currentUser,
                    nextTournamentId);
            int confirm = view.promptToConfirmTournament(newTournament);
            if (confirm == 1) {//adds tournament to storage
                tService.saveTournament(newTournament);
                tService.setNextTournamentId();
                int operation;
                boolean keepAdding = true;
                boolean addPlayers;
                boolean keepAddingPlayers;
                while (keepAdding) {//add team, delete Tourney, or user menu
                    operation = view.previewTournamentAndSelectOperation(
                            newTournament);

                    switch (operation) {
                        case 1://add Team and Players
                            try {
                                Team newTeam = addTeamToTournament(newTournament);
                                teamList.add(newTeam);
                                do {
                                    keepAddingPlayers = true;
                                    addPlayers = view.promptToAddPlayers(newTeam);
                                    if (addPlayers) {
                                        addPlayerToTeam(newTeam, currentUser, newTournament);
                                    } else {
                                        keepAddingPlayers = false;
                                    }
                                } while (keepAddingPlayers);
                            } catch (TeamNameTakenException | EventFullException e) {
                                view.errorMessage(e.getMessage());
                            }
                            break;
                        case 2://finalize schedule
                            try {
                                fullSchedule = finalizeScheduleCore(newTournament,
                                        teamList);
                                engageSchedule(newTournament, fullSchedule);
                            } catch (TournamentNotCompleteException
                                    | TournamentPersistenceException e) {
                                view.errorMessage(e.getMessage());
                            }
                            break;
                        case 3://Delete Tournament
                            deleteCore(newTournament);
                            view.tournamentDeletedBanner();
                            keepAdding = false;
                            break;
                        case 4:
                            keepAdding = false;
                            break;
                    }
                }
            } else {
                tService.deleteTournament(newTournament.getTournamentId());
            }
        } catch (TournamentPersistenceException | NoSuchTournamentException e) {
            view.errorMessage(e.getMessage());
        }
    }

    private void getTournament(User currentUser)
            throws TournamentPersistenceException {
        String searchInput = view.getTournamentSearchInfo();
        List<Tournament> results = tService.getAllTournaments()
                .stream()
                .filter(t -> t.getTournamentId() == Integer.
                parseInt(searchInput)
                || t.getTournamentName().equalsIgnoreCase(searchInput))
                .collect(Collectors.toList());
        listTournamentOperationsCore(results, currentUser);

    }

    private void getAllTournaments(User currentUser)
            throws TournamentPersistenceException {
        List<Tournament> tournamentList = tService.getAllTournaments();
        listTournamentOperationsCore(tournamentList, currentUser);
    }

    private void updateTournament(User currentUser)
            throws TournamentPersistenceException {
        try {
            Tournament tournament = getCore();

            proceedWithTournamentOperation(tournament, currentUser);
        } catch (NoSuchTournamentException e) {
            view.errorMessage(e.getMessage());
        }
    }

    private void deleteTournament(User currentUser) {
        Tournament tournament;
        try {
            tournament = getCore();
            if (tournament.getUsername().equalsIgnoreCase(currentUser
                    .getUserName())) {
                deleteCore(tournament);
            } else {
                view.errorMessage("Error! Not your tournament");
            }
        } catch (NoSuchTournamentException e) {
            view.errorMessage(e.getMessage());
        }
    }

    private void logOff(User user) {
        view.logOffMessage(user);
    }

    private void loadProgram() {
        try {
            uService.loadUsers();
            tmService.loadTeamEntities();
            tService.loadTournamentEntities();
        } catch (TournamentPersistenceException e) {
            view.errorMessage(e.getMessage());
        }
    }

    private void saveProgram() {
        try {
            uService.saveUsers();
            tmService.saveTeamEntities();
            tService.saveTournamentEntities();
        } catch (TournamentPersistenceException e) {
            view.errorMessage(e.getMessage());
        }
    }

    ///////////////////////////// Core Methods /////////////////////////////////
    private Tournament getCore() throws NoSuchTournamentException {
        int tournamentId = view.getTournamentId();
        return tService.getTournament(tournamentId);

    }

    private void updateCore(Tournament tournament) {
        int updateOperation = view.updateOperation(tournament);
        List<Team> teamList;
        boolean hasErrors;
        do {
            hasErrors = false;
            switch (updateOperation) {
                case 1:
                    view.updatetournamentSettings(tournament);
                    break;
                case 2:
                    teamList = tmService.getTeamsByTournamentId(tournament
                            .getTournamentId());
                    int teamId = view.removeTeam(teamList);
                    try {
                        tmService.removeTeamFromTournament(tournament, teamId);
                        tService.saveTournament(tournament);
                    } catch (NoSuchTeamException e) {
                        hasErrors = true;
                        view.errorMessage(e.getMessage());
                    }
                    break;
                case 3:
                    break;
            }
        } while (hasErrors);
        tService.saveTournament(tournament);
    }

    private void listTournamentOperationsCore(List<Tournament> tournamentList,
            User currentUser) throws TournamentPersistenceException {
        int tournamentId = view.listTournaments(tournamentList);
        if (tournamentId != 0) {
            try {
                Tournament selected = tService.getTournament(tournamentId);
                proceedWithTournamentOperation(selected, currentUser);
            } catch (NoSuchTournamentException e) {
                view.errorMessage(e.getMessage());
            }
        }
    }

    private void deleteCore(Tournament toRemove) {
        int confirm = view.confirmDeletePrompt(toRemove);
        List<Team> teams = tmService.getTeamsByTournamentId(
                toRemove.getTournamentId());
        if (confirm == 1) {
            try {
                for (Team team : teams) {
                    tmService.removeTeamFromTournament(toRemove, team.getTeamId());
                }
                tService.deleteTournament(toRemove.getTournamentId());
            } catch (NoSuchTournamentException | NoSuchTeamException e) {
                view.errorMessage(e.getMessage());
            }
            view.tournamentDeletedBanner();
        } else {
            view.deleteCancelledBanner();
        }
    }

    /////////////////////////// Secondary methods //////////////////////////////
    private void proceedWithTournamentOperation(Tournament tournament,
            User currentUser) throws TournamentPersistenceException {

        List<Team> teamList = tmService
                .getTeamsByTournamentId(tournament.getTournamentId());
        //Need a list of tournament's completed games to send to standings method
        List<Game> completedGames = tService.getCompletedGames(tournament);
        List<Team> teamStandings = tmService.getTeamStandings(
                teamList, tournament, completedGames);
        boolean isStarted;
        boolean isCreator = tournament.getUsername()
                .equalsIgnoreCase(currentUser.getUserName());
        boolean keepGoing;
        boolean addPlayers;
        boolean keepAddingPlayers;
        int teamSelection;
        int operation;
        Team selected = null;
        do {
            keepGoing = true;
            isStarted = !tournament.isIsSignUp();
            if(tournament.getCurrentRound() == 0 & !tournament.isIsSignUp()){
            tournament.setCurrentRound(1);
        }
            operation = view.displayTournamentAndNavigate(tournament,
                    currentUser);

            switch (operation) {
                case 1:
                    if (!isStarted) {
                        // Join Tournament
//                    try {
//                        tmService.userJoinTournament(tournament, 
//                                currentUser.getUserName());
//                    } catch (EventFullException e) {
//                        view.errorMessage(e.getMessage());
//                    }
                    } else {
                        // View Schedule
                        List<Series> schedule = tService
                                .getScheduleByTournament(tournament);
//                        view.tournamentFullSchedule(schedule, tournament);
                        engageSchedule(tournament, schedule);
                    }
                    break;

                case 2:
                    if (!isStarted) {
                        // Create team/players
                        try {
                            Team newTeam = addTeamToTournament(tournament);
                            teamList.add(newTeam);
                            do {
                                keepAddingPlayers = false;
                                addPlayers = view.promptToAddPlayers(newTeam);
                                if (addPlayers) {
                                    keepAddingPlayers = true;
                                    addPlayerToTeam(newTeam, currentUser, tournament);
                                }
                            } while (keepAddingPlayers);
                        } catch (TeamNameTakenException | EventFullException e) {
                            view.errorMessage(e.getMessage());
                        }
                        tmService.saveTeamEntities();
                    } else {
                        //view Standings
                        teamSelection = view.teamStandingsDisplay(teamStandings,
                                tournament);
                        if (teamSelection == 0) {
                            // Go Back
                        } else {// View Team
                            String results;
                            int resultOperation;
                            for (Team team : teamStandings) {
                                if (teamSelection == team.getRank()) {
                                    selected = team;
                                    break;
                                }
                            }
                            List<Player> roster = tmService.getPlayersByTeamId(
                                    selected.getTeamId());
                            view.teamAndRoster(selected, roster, tournament);
                            if (view.getGameResults()) {
                                results = tmService.getAllGameResults(
                                        selected.getTeamId());
                                resultOperation = view.gameResults(results);
                                if (resultOperation == 0) {
                                    //do nothing more
                                } else {
                                    String singleResult = tmService
                                            .getSingleGameResult(
                                                    resultOperation);
                                    view.SingleGameResult(singleResult);
                                }
                            }
                        }
                    }

                    break;
                case 3:
                    if (!isStarted) {
                        //ViewTeams
                        List<Team> teams = tmService.getTeamsByTournamentId(
                                tournament.getTournamentId());
                        int teamId;
                        Team team;
                        boolean noTeam;
                        List<Player> roster;
                        boolean addToRoster;
                        do {
                            noTeam = false;
                            teamId = view.teamListAndSelect(teams);
                            if (teamId == 0) {

                            } else {
                                try {
                                    team = tmService.getTeam(teamId);
                                    roster = tmService.getPlayersByTeamId(teamId);
                                    view.teamAndRoster(team, roster, tournament);
                                    do {
                                        addToRoster = view.promptToAddPlayers(team);
                                        if (addToRoster) {
                                            addPlayerToTeam(team, currentUser, tournament);
                                        }
                                    } while (addToRoster);
                                } catch (NoSuchTeamException e) {
                                    noTeam = true;
                                    view.errorMessage(e.getMessage());
                                }

                            }
                        } while (noTeam);
                        tmService.saveTeamEntities();
                    } else {
                        //Check-In

                    }
                    break;
                case 4:
                    if (!isStarted & isCreator) {
                        //Update Tournament
                        updateCore(tournament);

                    } else if (isStarted & isCreator) {
                        //Delete Tournament
                        deleteCore(tournament);
                    } else if (!isCreator) {
                        //Main Menu
                        keepGoing = false;
                    }
                    break;
                case 5:
                    if (!isStarted & isCreator) {
                        // Start Tournament
                        try {
                            tService.finalizeSchedule(tournament, teamList);
                        } catch (InvalidDateException
                                | TournamentNotCompleteException e) {
                            view.errorMessage(e.getMessage());
                        }

                    } else {
                        //Main Menu
                        keepGoing = false;
                    }
                    break;
                case 6:
                    if (!isStarted & isCreator) {
                        //Delete Tournament
                        deleteCore(tournament);

                    }
                    break;
                case 7:
                    if (!isStarted & isCreator) {
                        // Main Menu
                        keepGoing = false;
                    }
                    break;
                default:
                    view.errorMessage("error");///change after moving to SL
            }
        } while (keepGoing);
    }

    private Team addTeamToTournament(Tournament tournament)
            throws TournamentPersistenceException, TeamNameTakenException,
            EventFullException {
        Team newTeam = null;
        String teamName = view.getNewTeamName(tournament);
        newTeam = tmService.validateNewTeamName(tournament, teamName);
        newTeam = view.finishTeamForm(newTeam);
        return newTeam;
    }

    private void addPlayerToTeam(Team team, User currentUser, Tournament tournament)
            throws TournamentPersistenceException {
        Player player = null;
        String playerName;
        boolean hasErrors;
        List<Player> roster = tmService.getPlayersByTeamId(team.getTeamId());
        if (team.getNumOfPlayers() >= team.getMaxPlayers()) {
            view.teamAndRoster(team, roster, tournament);
        } else {
            do {
                hasErrors = false;
                view.teamAndRoster(team, roster, tournament);
                playerName = view.createPlayerForm(team, currentUser);
                try {
                    player = tmService.validatePlayerInfo(playerName, team);
                    view.finsihPlayerForm(player, team);
                } catch (PlayerNameTakenException e) {
                    hasErrors = true;
                    view.errorMessage(e.getMessage());
                }
            } while (hasErrors);
        }
    }

    private List<Series> finalizeScheduleCore(Tournament tournament,
            List<Team> teamList) throws TournamentNotCompleteException,
            TournamentPersistenceException {
        List<Series> fullSchedule = null;
        boolean hasErrors;
        LocalDateTime newStartDate = null;
        do {
            hasErrors = false;
            try {
                fullSchedule = tService
                        .finalizeSchedule(tournament,
                                teamList);
            } catch (InvalidDateException e) {
                hasErrors = true;
                view.errorMessage(e.getMessage());
                newStartDate = view
                        .getStartDate();
                tournament.setStartDate(newStartDate);
            }
        } while (hasErrors);

        return fullSchedule;
    }

    private void engageSchedule(Tournament tournament, List<Series> fullSchedule)
            throws TournamentPersistenceException {
        int scheduleOperation;
        boolean keepEngaging;

        do {
            keepEngaging = true;
            scheduleOperation = view
                    .tournamentFullSchedule(fullSchedule, tournament);
            if (scheduleOperation != 0) {
                try {
                    seriesIDSelected(tournament, scheduleOperation);
                } catch (NoSuchSeriesException e) {
                    view.errorMessage(e.getMessage());
                }
            } else {
                //Back to previous Menu
                keepEngaging = false;
            }
        } while (keepEngaging);
    }

    ///////////////////////////// Tertiary Methods /////////////////////////////
    private void seriesIDSelected(Tournament tournament, int seriesId) throws
            NoSuchSeriesException, TournamentPersistenceException {
        boolean keepNavigating;
        Series series = null;
        int navigation;

        series = tService.getSeries(seriesId);
        do {
            keepNavigating = true;
            navigation = view.seriesDisplayAndNavigate(series);
            switch (navigation) {
                case 1:
                    try {
                        updateSeriesStatus(series, tournament);
                    } catch (NoSuchTeamException | SeriesNotReadyException e) {
                        view.errorMessage(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        gamesListOperations(tournament, series.getSeriesId());
                    } catch (NoSuchGameException e) {
                        view.errorMessage(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        addGame(series, tournament);
                    } catch (TournamentPersistenceException e) {
                        view.errorMessage(e.getMessage());
                    }
                    break;
                case 4:
                    //Back to schedule
                    keepNavigating = false;
                    break;
            }
        } while (keepNavigating);

    }

    ///////////////////////////// Quarternary Methods /////////////////////////////
    private void updateSeriesStatus(Series series, Tournament tournament) throws
            NoSuchTeamException, SeriesNotReadyException,
            TournamentPersistenceException {
        boolean navErrors;
        Team homeTeam;
        Team awayTeam;

        do {
            navErrors = false;
            series = view.updateSeriesForm(series);
            try {
                //for more than 2 teams use for loop to update series for each team
                series = tService.updateSeries(series);
                homeTeam = tmService.getTeam(series.getTeam1Id());
                awayTeam = tmService.getTeam(series.getTeam2Id());
                tmService.updateTeamStats(homeTeam, series, tournament);
                tmService.updateTeamStats(awayTeam, series, tournament);
            } catch (InvalidResultsException e) {
                navErrors = true;
                view.errorMessage(e.getMessage());
            }
        } while (navErrors);
    }

    private void gamesListOperations(Tournament tournament, int seriesId)
            throws NoSuchGameException, TournamentPersistenceException {
        List<Game> games;
        int gameListNavigation;
        int gameOperation;
        Game game;
        boolean keepOperating;

        games = tService.getGamesbySeriesId(seriesId);
        gameListNavigation = view.gamesDisplayAndNavigate(games);
        if (gameListNavigation != 0) {
            game = tService.getGame(gameListNavigation);
            do {
                keepOperating = true;
                gameOperation = view.gameOperation(game);
                switch (gameOperation) {
                    case 1:
                        //Check-in
                        break;
                    case 2:
                        //Update Results
                        updateGameResults(game, tournament);
                        break;
                    case 3:
                        //Delete Game
                        tService.removeGame(game.getGameId());
                        break;
                    case 4:
                        //Back to gameList
                        keepOperating = false;
                        break;
                }
            } while (keepOperating);
        }
    }

    private void addGame(Series series, Tournament tournament)
            throws TournamentPersistenceException {

        Game game = tService.addGameToSeries(series);
        view.finishGameForm(game);
        updateGameResults(game, tournament);

    }

    private void updateGameResults(Game game,
            Tournament tournament) throws TournamentPersistenceException {
        Team homeTeam;
        Team awayTeam;
        Series series;
        List<Player> players = null;
        try {
            tService.updateGame(game);

            homeTeam = tmService.getTeam(game.getHomeTeamId());
            tmService.updateTeamStats(homeTeam, game);
            if (homeTeam.isHasPlayers()) {
                players = tmService.getPlayersByTeamId(homeTeam.getTeamId());
                players = view.updatePlayerScores(homeTeam, players, game);
                tmService.updatePlayerStats(players, game);
            }

            awayTeam = tmService.getTeam(game.getAwayTeamId());
            tmService.updateTeamStats(awayTeam, game);
            if (homeTeam.isHasPlayers()) {
                players = tmService.getPlayersByTeamId(awayTeam.getTeamId());
                players = view.updatePlayerScores(awayTeam, players, game);
                tmService.updatePlayerStats(players, game);
            }

            series = tService.getSeries(game.getSeriesId());
            tService.updateSeries(series);

            tmService.updateTeamStats(homeTeam, series, tournament);
            tmService.updateTeamStats(awayTeam, series, tournament);
            tmService.updatePlayerStats(players, series, tournament);
            tmService.updatePlayerStats(players, series, tournament);
        } catch (PlayersNotCheckedInException
                | NoSuchTeamException | NoSuchSeriesException
                | SeriesNotReadyException
                | InvalidResultsException e) {
            view.errorMessage(e.getMessage());
        }
    }
}
