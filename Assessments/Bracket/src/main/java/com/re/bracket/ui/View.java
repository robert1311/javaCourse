/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.ui;

import com.re.bracket.dto.Game;
import com.re.bracket.dto.Player;
import com.re.bracket.dto.Series;
import com.re.bracket.dto.Stat;
import com.re.bracket.dto.Team;
import com.re.bracket.dto.Tournament;
import com.re.bracket.dto.User;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author rober
 */
public class View {

    UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public int loginMenuGetSelection() {
        return io.readInt("1) Sign Up\n"
                + "2) Log In\n"
                + "3) Exit\n", 1, 3);
    }

    public String getNewUserName() {
        return io.readString("Enter new Username: ");
    }

    public void userAddedBanner(String newUsername) {
        io.print("=== USER SUCCESSFULLY ADDED ===");
    }

    public String loginForm() {
        return io.readString("Log In with username: ");
    }

    public void successfulLoginBanner() {
        io.print("=== SUCCESSUL LOGIN ===");
    }

    public void welcomeBanner(User currentUser) {
        io.print("Welcome, " + currentUser.getUserName() + "\n"
                + "Notifications: " + currentUser.getNotifications());
    }

    public int displayMenuAndGetSelection() {
        return io.readInt("========= Make selection ==========\n"
                + "1} Create Tournament\n"
                + "2) Get Tournament\n"
                + "3) Get All Tournaments\n"
                + "4) Update Tournament\n"
                + "5) Delete Tournament\n"
                + "6) Log Off\n"
                + "===================================", 1, 6);
    }

    public Tournament getNewTournamentInfo(User currentUser, int nextTournamentId) {
        String tournamentName = io.readString("Enter the name of the Tournament");
        boolean isSignUp = io.readString("Is this tournament open for other \n"
                + "users to sign up? (y/n)").equalsIgnoreCase("y");
        int numParticipants = io.readInt("Enter the Max number of participants", 2, 256);
        int stageType = io.readInt("Choose the stage format:\n"
                + "1) One-Stage\n"
                + "2) Two-Stage\n", 1, 2);
        String format = "";
        boolean hasErrors;
        do {
            hasErrors = false;
            String formatInput = io.readString("Select Stage 1 format:\n"
                    + "R) Round-Robin\n"
                    + "S) Single-Elimination");
            if (formatInput.equalsIgnoreCase("r")) {
                format = "Round-Robin";
            } else if (formatInput.equalsIgnoreCase("s")) {
                format = "Single-Elimination";
            } else {
                hasErrors = true;
            }
        } while (hasErrors);
        String format2 = "";
        if (stageType == 2) {
            boolean hasErrors2;
            do {
                hasErrors2 = false;
                String formatInput = io.readString("Select Stage 2 format:\n"
                        + "R) Round-Robin\n"
                        + "S) Single-Elimination");
                if (formatInput.equalsIgnoreCase("r")) {
                    format2 = "Round-Robin";
                } else if (formatInput.equalsIgnoreCase("s")) {
                    format2 = "Single-Elimination";
                } else {
                    hasErrors2 = true;
                }
            } while (hasErrors2);
        } else {
            format2 = "N/A";
        }
        int numSeedsAdvance = 0;
        int numOfCycles = io.readInt("How many times will participants play "
                + "each other?");
        if (stageType == 2) {
            numSeedsAdvance = io.readInt("How many seeds can advance?", 2,
                    numParticipants);
        }

        LocalDateTime ltd = this.getStartDate();
        Tournament newTournament = new Tournament(nextTournamentId);
        newTournament.setUsername(currentUser.getUserName());
        newTournament.setS1Format(format);
        newTournament.setS2Format(format2);
        newTournament.setIsSignUp(isSignUp);
        newTournament.setMaxNumOfParticipants(numParticipants);
        newTournament.setActualNumOfParticipants(0);
        newTournament.setNumOfCycles(numOfCycles);
        newTournament.setSeedsToAdvance(numSeedsAdvance);
        newTournament.setStageType(stageType);
        newTournament.setTournamentName(tournamentName);
        newTournament.setStartDate(ltd);

        return newTournament;
    }

    public LocalDateTime getStartDate() {
        String dateInput;
        LocalDateTime ldt = null;
        boolean isInvalid;
        io.print("Enter the start date and time in the format of MM/dd/yyyy "
                + "HH:mm");
        //Write option for selecting the time NOW
        do {
            try {
                isInvalid = false;
                dateInput = "";
                dateInput += "" + io.readString("Enter Month (MM)");
                dateInput += "/";
                dateInput += "" + io.readString("Enter Day (dd)");
                dateInput += "/";
                dateInput += "" + io.readString("Enter Year (yyyy)");
                dateInput += " ";
                dateInput += "" + io.readString("Enter Hour (HH).");
                dateInput += ":";
                dateInput += "" + io.readString("Enter minute (mm).");

                ldt = LocalDateTime.parse(dateInput, DateTimeFormatter
                        .ofPattern("MM/dd/yyyy HH:mm"));

            } catch (DateTimeParseException e) {
                isInvalid = true;
            }
        } while (isInvalid);

        return ldt;
    }

    public int promptToConfirmTournament(Tournament tournament) {
        displayTournamentFrame(tournament);
        return io.readInt("1) Confirm\n"
                + "2) Cancel\n", 1, 2);

    }

    public int previewTournamentAndSelectOperation(Tournament tournament) {
        displayTournamentFrame(tournament);
        return io.readInt("Choose an operation for this tournament.\n"
                + "1) Add Participants\n"
                + "2) Generate Schedule\n"
                + "3) Delete Tournament\n"
                + "4) Tournament Menu\n", 1, 4);

    }

    public String getNewTeamName(Tournament tournament) {
        displayTournamentFrame(tournament);
        return io.readString("Enter Team Name");
    }

    public Team finishTeamForm(Team newTeam) {
        boolean hasPlayers = io.readString("Does this team have individual "
                + "players? (y/n)").equalsIgnoreCase("y");
        String captainName = io.readString("Enter captain Name. (Captain will default to "
                + "first player added, 'Pending' otherwise)");
        if (captainName.isBlank()) {
            captainName = "Pending";
        }
        int maxPlayers = io.readInt("Enter the max amount of players for this "
                + "team? (1-100)", 1, 100);
        newTeam.setCaptainName(captainName);
        newTeam.setHasPlayers(hasPlayers);
        newTeam.setMaxPlayers(maxPlayers);
        newTeam.setRank(1);

        return newTeam;
    }

    public void teamDisplayFrame(Team team) {

        io.print("Team: " + team.getTeamName() + "\n"
                + "Team ID: " + team.getTeamId() + "\n"
                + "-------------------------------\n"
                + "Has Players: " + team.isHasPlayers() + "\n"
                + "Captain Name: " + team.getCaptainName() + "\n"
                + "# Of Players: " + team.getNumOfPlayers() + "\n"
                + "Max # of Players: " + team.getMaxPlayers() + "\n");

    }

    public void teamAndRoster(Team team, List<Player> roster, Tournament tournament) {
        int counter = 1;
        String padding;
        int maxNameLength;
        String linePadding;
        int paddingIncrements;
        Stat currentStat;
        double ppg;
        int total;

        teamDisplayFrame(team);

        if (team.isHasPlayers()) {
            if (roster.size() > 0) {
                maxNameLength = roster.stream().mapToInt(p -> p.getPlayerName()
                        .length()).max().getAsInt();
            } else {
                maxNameLength = 0;
            }
            linePadding = "   ";
            for (int i = 0; i < maxNameLength; i++) {
                linePadding += " ";
            }

            io.print("----Roster----" + linePadding + "PPG     Total");

            if (!tournament.isIsSecondStage()) {
                for (Player player : roster) {
                    currentStat = player.getStatInfo();
                    ppg = currentStat.getPtsPerGame();
                    total = currentStat.getTotalPoints();
                    padding = "              ";
                    paddingIncrements = maxNameLength - player.getPlayerName().length();
                    for (int j = 0; j < paddingIncrements; j++) {
                        padding += " ";
                    }
                    io.print("" + counter + ") " + player.getPlayerName()
                            + padding + String.format("%.02f", ppg) + "      "
                            + total);
                    counter++;
                }
            } else {
                for (Player player : roster) {
                    currentStat = player.getS2StatInfo();
                    ppg = currentStat.getPtsPerGame();
                    total = currentStat.getTotalPoints();
                    padding = "     ";
                    paddingIncrements = maxNameLength - player.getPlayerName().length();
                    for (int j = 0; j < paddingIncrements; j++) {
                        padding += " ";
                    }
                    io.print("" + counter + ") " + player.getPlayerName()
                            + padding + String.format("%.02f", ppg) + "      "
                            + total);
                    counter++;
                }
            }
        }
        io.readString("Press Enter to continue.");
    }

    public boolean promptToAddPlayers(Team team) {

        return io.readString("Add Players? (y/n)").equalsIgnoreCase("y");
    }

    public String createPlayerForm(Team team, User currentUser) {
        io.print("Team: " + team.getTeamName());
        boolean isUser = io.readString("Use your username to reserve this roster"
                + " spot? Max: 1 roster spot per User per tournament (Y/N)")
                .equalsIgnoreCase("y");
        if (isUser) {
            return currentUser.getUserName();
        } else {
            return io.readString("Enter player name.");
        }
    }

    public Player finsihPlayerForm(Player player, Team team) {
        io.print("Note: Players added by admin will default to a non-user player.\n"
                + "Reserve a roster spot by using the Join Tournament option\n"
                + "when viewing a Tournament.\n");
        boolean isCaptain = io.readString("Is " + player.getPlayerName() + " "
                + "the captain? (Y/N)").equalsIgnoreCase("y");
        if (isCaptain) {
            team.setCaptainName(player.getPlayerName());
        }
        return player;
    }

    public int tournamentFullSchedule(List<Series> fullSchedule,
            Tournament tournament) {
        displayTournamentFrame(tournament);
        int numOfRounds = tournament.getS1NumOfRounds();
        String status = "";

        if (!tournament.isIsSecondStage()) {
            numOfRounds = tournament.getS1NumOfRounds();
        } else {
            numOfRounds = tournament.getS2NumOfRounds();
        }

        for (int i = 0; i < numOfRounds; i++) {
            for (Series series : fullSchedule) {
                if (series.getRoundNumber() == i + 1) {
                    if (series.isIsComplete()) {
                        status = "Complete";
                    } else if (!series.isIsComplete() & !series.isIsReady()) {
                        status = "Not Ready";
                    } else {
                        status = "Ready";
                    }
                    io.print("\nSeries ID: " + series.getSeriesId() + "\n"
                            + "----\n"
                            + "Round Number: " + series.getRoundNumber() + "\n"
                            + "Status: " + status + "\n"
                            + "=============\n"
                            + "Team 1: " + series.getTeam1Name() + "\n"
                            + "Team 2: " + series.getTeam2Name() + "\n"
                            + "#############\n"
                            + "Winner: " + series.getSeriesWinnerName() + "\n"
                            + "Loser: " + series.getSeriesLoserName() + "\n");
                }
            }
        }
        return io.readInt("Enter a series ID to navigate to the corresponding "
                + "match up.\n(Press 0 to go bak to previous menu)");
    }
    /////////////////////// GET TOURNAMNET ////////////////////

    public String getTournamentSearchInfo() {
        return io.readString("Enter TournamentId or Name");
    }

    public int listTournaments(List<Tournament> tournamentList) {
        int listCount = tournamentList.size();
        List<Integer> idList;
        int selection;
        boolean hasErrors;

        do {
            hasErrors = false;
            idList = new ArrayList<>();
            idList.add(0);
            if (listCount > 0) {
                for (Tournament tournament : tournamentList) {
                    idList.add(tournament.getTournamentId());
                    io.print("" + tournament.getTournamentId() + ") "
                            + tournament.getTournamentName());
                }
            }

            selection = io.readInt("============================\n"
                    + "Enter an Id from the list above to view. \n"
                    + "(Press 0 to exit to main menu.");
            if (!idList.contains(selection)) {
                hasErrors = true;
                io.print("Error: Select an ID from the list.");
            }
        } while (hasErrors);

        return selection;
    }

    public int displayTournamentAndNavigate(Tournament tournament, User currentUser) {
        displayTournamentFrame(tournament);
        return proceedOptions(tournament, currentUser);
    }

    public int getTournamentId() {
        return io.readInt("Enter Tournament Id:");
    }

    public int updateOperation(Tournament tournament) {
        this.displayTournamentFrame(tournament);
        return io.readInt("1) Change TournamentSettings\n"
                + "2) Remove Team\n"
                + "3) Back", 1, 3);
    }

    public Tournament updatetournamentSettings(Tournament tournament) {
        String signUp = "";
        int maxPartsInput;
        int maxParts = tournament.getMaxNumOfParticipants();
        int newMax = 0;
        int seedsInput;
        int numSeeds = tournament.getSeedsToAdvance();
        int newSeeds = 0;
        boolean hasErrors;
        if (tournament.isIsSignUp()) {
            signUp = "Sign-Up";
        } else {
            signUp = "NOT Sign-Up";
        }
        io.print("Leave blank if you do not wish to change the field in question");
        String name = io.readString("Enter Tournament Name: " + tournament
                .getTournamentName());
        if (!name.isBlank()) {
            tournament.setTournamentName(name);
        }
        boolean isSignUp = io.readString("Is this tournament open for other \n"
                + "users to sign up? (y/n)\n"
                + "CurrentStatus: " + signUp).equalsIgnoreCase("y");
        tournament.setIsSignUp(isSignUp);
        do {
            hasErrors = false;
            maxPartsInput = io.readInt("Enter the max number of participants "
                    + "allowed. (2-256)\n"
                    + "Current # of participants allowed: " + maxParts, 2, 256);

            seedsInput = io.readInt("How many seeds can advance?\n"
                    + "Current # of seeds for final stage: " + numSeeds, 0,
                    tournament.getActualNumOfParticipants());
            if (seedsInput > maxPartsInput) {
                hasErrors = true;
                io.print("Number of seeds to advance cannot exceed the number "
                        + "of max participants.\n");
            }

        } while (hasErrors);
        if (maxPartsInput != maxParts) {
            newMax = maxPartsInput;
            if (newMax < maxParts) {
                io.print("Be sure to remove teams if over participant limit"
                        + " new limit in order to start Tournament.");
            }
            tournament.setMaxNumOfParticipants(newMax);
        }
        if (seedsInput != numSeeds) {
            newSeeds = seedsInput;
            tournament.setSeedsToAdvance(newSeeds);
        }
        return tournament;
    }

    public int teamListAndSelect(List<Team> teams) {
        List<Integer> teamIds = new ArrayList<>();
        boolean hasErrors;
        int teamId;
        for (Team team : teams) {
            teamDisplayFrame(team);
            teamIds.add(team.getTeamId());
        }
        do {
            hasErrors = false;
            teamId = io.readInt("Enter a teamID to navigate to that team.\n"
                    + "(Press 0 to go back.)");
            if (teamId != 0 & !teamIds.contains(teamId)) {
                hasErrors = true;
            }
        } while (hasErrors);
        return teamId;
    }

    public int removeTeam(List<Team> teamList) {
        for (Team team : teamList) {
            io.print("\nTeamId: " + team.getTeamId() + "\n"
                    + "Team Name: " + team.getTeamName() + "\n"
                    + "Captain Name: " + team.getCaptainName() + "\n"
                    + "HasPlayers: " + team.isHasPlayers() + "\n"
                    + "Max Players: " + team.getMaxPlayers() + "\n");
        }
        return io.readInt("Enter the teamId to remove that team. \n"
                + "Press 0 to cancel and return.", 1, teamList.size());
    }

    public int getSeriesIDFromSchedule(Tournament tournament, List<Series> schedule) {
        boolean isView = io.readString("View Schedule? (y/n)")
                .equalsIgnoreCase("y");
        if (isView) {
            this.tournamentFullSchedule(schedule, tournament);
        }
        return io.readInt("Enter a Series ID.\n"
                + "(Press 0 to return to tournament options)");
    }

    public int seriesDisplayAndNavigate(Series series) {
        return io.readInt("\nSeries ID: " + series.getSeriesId() + "\n"
                + "----\n"
                + "Round Number: " + series.getRoundNumber() + "\n"
                + "Best of " + series.getBestOfNumGames() + " games\n"
                + "=============\n"
                + "Team 1: " + series.getTeam1Name() + " - "
                + series.getTeam1Wins() + "\n"
                + "Team 2: " + series.getTeam2Name() + " - "
                + series.getTeam2Wins() + "\n"
                + "Start Time: " + series.getDateTime() + "\n"
                + "Participants checked-in: " + series.isIsReady() + "\n"
                + "#############\n"
                + "Winner: " + series.getSeriesWinnerName() + "\n"
                + "Loser: " + series.getSeriesLoserName() + "\n"
                + "=======================\n"
                + "1) Update Series Status\n"
                + "2) View Games\n"
                + "3) Add Game\n"
                + "4) Back to schedule\n", 1, 4);

    }

    public Series updateSeriesForm(Series series) {
        boolean isAWinner;
        boolean isReady;
        boolean changeTime;
        boolean changeBestOf;
        int selection;
        int bestOf;
        LocalDateTime ldt = null;

        if (series.isIsComplete()) {

        } else {
            // change date/time
            changeTime = io.readString("Change Date/Time? (y/n)")
                    .equalsIgnoreCase("y");
            if (changeTime) {
                ldt = getStartDate();
                series.setDateTime(ldt);
                if (ldt.isAfter(LocalDateTime.now())) {
                    series.setIsReady(false);
                }
                isReady = io.readString("Is this series ready? (y/n)")
                        .equalsIgnoreCase("y");
                if (isReady) {
                    series.setIsReady(true);
                }
            }
            //change best of
            changeBestOf = io.readString("Change Best of # of games? (y/n)")
                    .equalsIgnoreCase("y");
            if (changeBestOf) {
                do {
                    bestOf = io.readInt("Enter a new 'Best of # games': \n"
                            + "(1-25; must be odd #)", 1, 25);
                } while (bestOf % 2 == 0);
                series.setBestOfNumGames(bestOf);
            }
            isAWinner = io.readString("Declare series winner? (y/n)")
                    .equalsIgnoreCase("y");
            if (isAWinner) {// Manually declare winner
                int winnerNum = io.readInt("Select winner:\n"
                        + "1) " + series.getTeam1Name() + "\n"
                        + "2) " + series.getTeam2Name() + "\n", 1, 2);
                if (winnerNum == 1) {// Team 1 Wins
                    series.setSeriesWinnerName(series.getTeam1Name());
                    series.setSeriesLoserName(series.getTeam2Name());
                } else {// Team 2 Wins
                    series.setSeriesWinnerName(series.getTeam2Name());
                    series.setSeriesWinnerName(series.getTeam1Name());
                }
                series.setIsComplete(true);
            }
        }
        return series;
    }

    public Game finishGameForm(Game game) {
        gameFrameDisplay(game);
        return updateGameForm(game);
    }

    public List<Player> updatePlayerScores(Team team, List<Player> players,
            Game game) {
        boolean update;
        boolean isConfirm;
        int posPts;
        int negPts;
        update = io.readString("Enter scores for players? (y/n)")
                .equalsIgnoreCase("y");
        if (update) {
            do {
                if (game.getStageNumber() == 1) {
                    for (Player player : players) {
                        posPts = io.readInt("Enter +Score for "
                                + player.getPlayerName());
                        negPts = io.readInt("Enter -Score for "
                                + player.getPlayerName());
                        player.getStatInfo().setPosPoints(posPts);
                        player.getStatInfo().setNegPoints(negPts);
                    }
                } else {//stage 2
                    for (Player player : players) {
                        posPts = io.readInt("Enter +Score for "
                                + player.getPlayerName());
                        negPts = io.readInt("Enter -Score for "
                                + player.getPlayerName());
                        player.getS2StatInfo().setPosPoints(posPts);
                        player.getS2StatInfo().setNegPoints(negPts);
                    }
                }
                isConfirm = io.readString("Confirm scores? (y/n)")
                        .equalsIgnoreCase("y");
            } while (!isConfirm);
        } else {
            //do nothing
        }

        return players;
    }

    public int gamesDisplayAndNavigate(List<Game> games) {
        for (Game game : games) {
            gameFrameDisplay(game);

        }
        return io.readInt("Enter GameID to view, check-in, or update results.\n"
                + "(Press 0 to return to Series view.)");
    }

    public int gameOperation(Game game) {
        gameFrameDisplay(game);
        return io.readInt("1) Check-In\n"
                + "2) Update Results\n"
                + "3) Delete Game\n"
                + "4) Back to games\n", 1, 4);
    }

    private void gameFrameDisplay(Game game) {
        String field = "";
        if (game.isIsNeutralField()) {
            field = "Neutral Field\n";
        } else {
            field = "Away: " + game.getAwayTeam() + "\n"
                    + "Home: " + game.getHomeTeam() + "\n";
        }
        String homeScore = "";
        String awayScore = "";
        if (game.isIsComplete()) {
            homeScore = "" + game.getHomeScore();
            awayScore = "" + game.getAwayScore();
        }
        String status = "";
        if (!game.isIsReady()) {
            status = "NOT Ready";
        } else if (game.isIsReady() & !game.isIsComplete()) {
            status = "Ready";
        } else if (game.isIsComplete()) {
            status = "Complete";
        }
        io.print("GameID: " + game.getGameId() + "\n"
                + "SeriesID: " + game.getSeriesId() + "\n"
                + "----\n"
                + "Game Number: " + game.getSeriesGameNum() + "\n"
                + status
                + field
                + "Date/Time: " + game.getDateTime() + "\n"
                + "============================\n"
                + game.getAwayTeam() + "(A): " + awayScore + "\n"
                + game.getHomeTeam() + "(H): " + homeScore + "\n"
        );
    }

    public Game updateGameForm(Game game) {
        boolean changeTime;
        boolean isReady;
        boolean keepPreference;
        boolean isNeutralField;
        boolean isComplete;
        boolean updateResults;
        int fieldSelection;
        int homeScore;
        int awayScore;
        int winningNum;
        LocalDateTime ldt;
        String homeTeamName = game.getHomeTeam();
        String awayTeamName = game.getAwayTeam();
        int homeTeamId = game.getHomeTeamId();
        int awayTeamId = game.getAwayTeamId();

        isReady = game.isIsReady();
        isComplete = game.isIsComplete();
        isNeutralField = game.isIsNeutralField();
        ldt = game.getDateTime();
        gameFrameDisplay(game);

        if (!isReady) {
            isReady = io.readString("Is this game ready? (y/n)")
                    .equalsIgnoreCase("y");
            game.setIsReady(isReady);
        }
        if (!isReady) {
            changeTime = io.readString("Change start date/time? (y/n)")
                    .equalsIgnoreCase("y");
            if (changeTime) {
                ldt = getStartDate();
                game.setDateTime(ldt);
            }
        }
        keepPreference = io.readString("Keep field preference? (y/n)")
                .equalsIgnoreCase("y");
        if (!keepPreference & !isNeutralField) {
            game.setIsNeutralField(true);
            game.setHomeTeam(homeTeamName);
            game.setAwayTeam(awayTeamName);
            game.setHomeTeamId(homeTeamId);
            game.setAwayTeamId(awayTeamId);
        } else if (!keepPreference & isNeutralField) {
            game.setIsNeutralField(false);
            fieldSelection = io.readInt("Choose Home Team.\n"
                    + "1) " + homeTeamName + "\n"
                    + "2) " + awayTeamName + "\n", 1, 2);
            if (fieldSelection == 2) {
                game.setHomeTeam(awayTeamName);
                game.setAwayTeam(homeTeamName);
                game.setHomeTeamId(awayTeamId);
                game.setAwayTeamId(homeTeamId);
            } else {

            }
        }
        if (isReady & !isComplete) {
            updateResults = io.readString("Update results? (y/n)")
                    .equalsIgnoreCase("y");
            if (updateResults) {
                homeScore = io.readInt("Enter score for " + game.getHomeTeam()//Name didn't change when fields changed
                        + "\n");
                game.setHomeScore(homeScore);
                awayScore = io.readInt("Enter score for " + game.getAwayTeam()
                        + "\n");
                game.setAwayScore(awayScore);
                winningNum = io.readInt("Select winner:\n"
                        + "1) " + homeTeamName + "\n"
                        + "2) " + awayTeamName + "\n", 1, 2);
                if (winningNum == 1) {
                    game.setWinningTeamName(homeTeamName);
                    game.setLosingTeamName(awayTeamName);
                } else {
                    game.setWinningTeamName(awayTeamName);
                    game.setLosingTeamName(homeTeamName);
                }
                game.setIsComplete(isComplete);
            }

        }
        return game;
    }

    public int teamStandingsDisplay(List<Team> standings, Tournament tournament) {
        String gamesBehindString = "";
        String streak = "";
        String padding;
        DecimalFormat df = new DecimalFormat("##.##");
        double gamesBehind = 00.00;
        int currentWins;
        int currentLosses;
        int previousWins = 0;
        int previousLosses = 0;
        int rank;
        int paddingIncrements;
        boolean isSecondStage;
        String ppgSpace;
        String totalSpace;
        String streakSpace;
        String rankSpace;
        String pntDiffSpace;
        Stat stat;
        double diffPerGame;

        isSecondStage = tournament.isIsSecondStage();
        int maxNameLength = standings.stream().mapToInt(t -> t.getTeamName()
                .length()).max().getAsInt();
        String linePadding = "   ";
        for (int i = 0; i < maxNameLength; i++) {
            linePadding += " ";
        }

        io.print("Team " + linePadding + " W - L    WIN%     GB     HOME    AWAY    "
                + " ptDiff/GM      PPG      STREAK   SERIES"
        );
        for (Team team : standings) {
            ppgSpace = " ";
            totalSpace = " ";
            streakSpace = " ";
            padding = "     ";
            rankSpace = "";
            pntDiffSpace = "   ";
            if(team.getRank() < 10){
                rankSpace = " ";
            }
            paddingIncrements = maxNameLength - team.getTeamName().length();
            for (int j = 0; j < paddingIncrements; j++) {
                padding += " ";
            }

            if (!isSecondStage) {
                stat = team.getStatInfo();
            } else {
                stat = team.getStatInfo();
            }

            currentWins = stat.getGameWins();
            currentLosses = stat.getGameLosses();
            rank = team.getRank();
            if (rank == 1) {
                gamesBehindString = " - ";
            } else {
                gamesBehind += (double) ((previousWins - currentWins) + (currentLosses
                        - previousLosses)) / 2;

                gamesBehindString = String.format("%.01f", gamesBehind);
            }

            if (stat.isIsWinStreak()) {
                streak = "W";
            } else {
                streak = "L";
            }
            
            int total = stat.getTotalPoints();
            if( total < 0){
                totalSpace = "";
            }
//            if(Math.abs(total) <10){
//                streakSpace = " ";
//            }
            if(stat.getPtsPerGame() < 0){
                ppgSpace = "";
                if(stat.getPtsPerGame() <= -10){
                    streakSpace = "";
                }
            }
            diffPerGame = (double) stat.getPntDifferential() / stat.getGamesPlayed();
            if(diffPerGame < 0){
                pntDiffSpace = "  ";
            } 
            if(Math.abs(diffPerGame) < 100){
                pntDiffSpace += " ";
                if(Math.abs(diffPerGame) < 10){
                    pntDiffSpace += " ";
                }
            }
            
            
            io.print("---------------------------------------------------------"
                    + "-------------------------------------");
            io.print("" + rankSpace + rank + ". " + team.getTeamName() + padding
                    + currentWins + " - "
                    + currentLosses + "    "
                    + String.format("%.02f", stat.getWinPercent()) + "     "
                    + gamesBehindString + "     "
                    + stat.getHomeWins() + "-"
                    + stat.getHomeLosses() + "     "
                    + stat.getAwayWins() + "-"
                    + stat.getAwayLosses() + "    " + pntDiffSpace
                    + String.format("%.02f", diffPerGame) + "      " + ppgSpace
                    + String.format("%.02f", stat.getPtsPerGame()) + "      " 
                    + streakSpace + streak 
                    + stat.getStreakCount() + "      "
                    + stat.getSeriesWins() + "-"
                    + stat.getSeriesLosses());
            previousWins = stat.getGameWins();
            previousLosses = stat.getGameLosses();
        }

        return io.readInt("\nSelect team by rank number to view Team\n"
                + "(Press 0 to go back)", 0,
                standings.size());
    }

    public boolean getGameResults() {
        return io.readString("View game results? (y/n)").equalsIgnoreCase("y");
    }

    public int gameResults(String results) {
        io.print(results);
        int gameOperation = io.readInt("Enter gameId to view team/player "
                + "results for that game.");
        return gameOperation;
    }

    public void SingleGameResult(String gameResult) {
        io.readString(gameResult + "\n"
                + "Press Enter to continue.");
    }

    public void teamStatByStatType(Stat stat, Team team) {

    }

    public int confirmDeletePrompt(Tournament tournament) {
        displayTournamentFrame(tournament);
        return io.readInt("Are you sure you want to delete current Tournament?\n"
                + "1) Confirm\n"
                + "2) Cancel\n", 1, 2);
    }

    public void deleteCancelledBanner() {
        io.print("=== TOURNAMENT NOT DELETED ===");
    }

    public void tournamentDeletedBanner() {
        io.print("=== TOURNAMENT SUCCESSFULLY DELETED ===");
    }

    ///////////////////////////////////////////////////
    public void logOffMessage(User user) {
        io.print("Goodbye, " + user.getUserName() + "!");
    }

    public void errorMessage(String message) {
        io.print("Error: " + message);
    }

    public void exitMessage() {
        io.print("GoodBye!!!");
    }

    /////////////////////////// Private Methods //////////////////////
    private void displayTournamentFrame(Tournament tournament) {

        io.print("===========================================\n"
                + "Tournament Name: " + tournament.getTournamentName() + "\n"
                + "Stage-Type: " + tournament.getStageType() + "-Stage\n"
                + "Stage-1 Fomat: " + tournament.getS1Format() + "\n"
                + "Stage-2 Format: " + tournament.getS2Format() + "\n"
                + "Is SignUp: " + tournament.isIsSignUp() + "\n"
                + "Number Of Max Participants: " + tournament.getMaxNumOfParticipants() + "\n"
                + "Actual # of participants: " + tournament.getActualNumOfParticipants() + "\n"
                + "Number of Round-Robin cycles: " + tournament.getNumOfCycles() + "\n"
                + "Current Round: " + tournament.getCurrentRound() + "\n"
                + "Seeds to adavance: " + tournament.getSeedsToAdvance() + "\n"
                + "===========================================\n");
    }

    private int proceedOptions(Tournament tournament, User currentUser) {
        int operationCount = 1;
        String operationMenu = "";
        if (tournament.isIsSignUp()) {// NOT started
            operationMenu += operationCount + ") Join Tournament\n";
            operationCount++;
            operationMenu += operationCount + ") Create Team/Add Players\n";
            operationCount++;
            operationMenu += operationCount + ") View Teams/Add Players\n";
            operationCount++;
            if (tournament.getUsername().equalsIgnoreCase(currentUser.getUserName())) {
                operationMenu += operationCount + ") Update Tournament Settings\n";
                operationCount++;
                operationMenu += operationCount + ") Start Tournament\n";
                operationCount++;
            }

        } else {// is started or not sign up
            operationMenu += operationCount + ") View Schedule\n";
            operationCount++;
            operationMenu += operationCount + ") View Standings\n";
            operationCount++;
            operationMenu += operationCount + ") Check-In\n";
            operationCount++;
        }
        if (tournament.getUsername().equalsIgnoreCase(currentUser.getUserName())) {
            operationMenu += operationCount + ") Delete Tournament\n";
            operationCount++;
        }
        operationMenu += operationCount + ") Main Menu\n";

        return io.readInt(operationMenu + "--------\nMake Selection: ", 1,
                operationCount);
    }

}
