/*
 * Created by RobE3
 */
package com.re.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Dev10
 */
public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean playAgain = false;

        System.out.println("Let's play Rock Paper Scissors!");

        // Will keep playing the game if user agrees to when prompted
        do {

            int wins = 0;
            int losses = 0;
            int ties = 0;
            int roundsCounter = 0;

            /*Ask user for a valid number of rounds that they want to play*/
            int roundsToPlay = validRoundsInput();

            System.out.println("Here we go! (hit enter to continue)");
            // sc.next();

            /*Keep playing rounds of Rock Paper Scissors until you reach the number 
        of desired rounds to play */
            while (roundsCounter < roundsToPlay) {
                boolean hasError = false;
                System.out.println("\n-------- ROUND " + (roundsCounter + 1 + " "
                        + "--------\n"));

                /* call method to execute a round of rock paper scissors
                    and return the result*/
                String result = rockPaperScissorsRound();
                switch (result) {
                    case "win":
                        wins++;
                        break;
                    case "loss":
                        losses++;
                        break;
                    case "tie":
                        ties++;
                        break;
                }

                roundsCounter++;
            }

            //print wins, losses, ties and declare winner
            System.out.println("\nEnd Of Game! Here's the results.");
            playAgain = displayResultsAndPlayAgain(wins, ties, losses);

        } while (playAgain);
        System.out.println("Thanks for playing! Goodbye!!!");
    }

    public static int validRoundsInput() {
        Scanner sc = new Scanner(System.in);

        int roundsInput = 0;
        boolean isError;

        do {
            isError = false;
            System.out.println("How many rounds from 1 to 10 do you want to "
                    + "play? (1-10)");
            roundsInput = sc.nextInt();
            if (roundsInput < 1 || roundsInput > 10) {
                System.out.println("ERROR: Please choose a number from 1 - 10");
                isError = true;
            }
        } while (isError);
        return roundsInput;
    }

    public static String rockPaperScissorsRound() {
        int userShoot;
        int compShoot;
        boolean hasError = false;

        Scanner shoot = new Scanner(System.in);
        Random random = new Random();

        /*Before the computer chooses, the user enters their choice of rock,
            paper, or scissors using 1, 2 ,3 respectively*/
        do {
            hasError = false;
            System.out.println("Rock, Paper, Scissors...(1 = Rock, 2 = Paper, "
                    + "3 = Scissors)\n");
            userShoot = shoot.nextInt();
            if (userShoot < 1 | userShoot > 3) {
                hasError = true;
                System.out.println("Please enter a valid input");
            }
        } while (hasError);
        //computer choice is determined by random number from 1-3 inclusive;
        compShoot = random.nextInt(3) + 1;

        System.out.println("Shoot!\n");

        System.out.println("YOU:      COMP:\n"
                + "------------------");
        /* Switch is for the cases of the user input against computer's choice*/
        switch (userShoot) {
            case 1: // USER chooses ROCK
                switch (compShoot) {
                    case 1: // comp chooses Rock
                        System.out.println("Rock      Rock\n"
                                + "\nIt's a Tie!");
                        return "tie";
                    case 2: // comp chooses Paper
                        System.out.println("Rock      Paper\n"
                                + "\nPaper covers rock\n"
                                + "Aww, You lost this round...\n");

                        return "loss";
                    case 3: // comp chooses Scissors
                        System.out.println("Rock      Scissors\n"
                                + "\nRock breaks Scissors\n"
                                + "You won this round!");
                        return "win";
                }

            case 2: // USER chooses PAPER
                switch (compShoot) {
                    case 1: // comp chooses Rock
                        System.out.println("Paper      Rock\n"
                                + "\nPaper covers rock\n"
                                + "You won this round!");
                        return "win";
                    case 2: // Comp chooses Paper
                        System.out.println("Paper      Paper\n"
                                + "\nIt's a Tie!");
                        return "tie";
                    case 3: // Comp chooses Scissors
                        System.out.println("Paper      Scissors\n"
                                + "\nScissors cut paper\n"
                                + "Aww, You lost this round...");
                        return "loss";
                }

            case 3: // USER chooses SCISSORS
                switch (compShoot) {
                    case 1: // Comp chooses Rock
                        System.out.println("Scissors      Rock\n"
                                + "\nRock breaks Scissors\n"
                                + "Aww, You lost this round...");
                        return "loss";
                    case 2: // Comp chooses Paper
                        System.out.println("Scissors      Paper\n"
                                + "\nScissors cut paper\n"
                                + "You won this round!");
                        return "win";
                    case 3: // Comp chooses Scissors
                        System.out.println("Scissors      Scissors\n"
                                + "\nIt's a Tie!");
                        return "tie";
                }

        }
        return "invalid";
    }

    public static boolean displayResultsAndPlayAgain(int wins, int ties,
            int losses) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Wins: " + wins + "\n"
                + "losses: " + losses + "\n"
                + "ties: " + ties);
        if (wins > losses) {
            System.out.println("You win the game!!! Congrats!!!");
        } else if (losses > wins) {
            System.out.println("Bummer, you lost this game... You'll get "
                    + "em next time!");
        } else if (wins == losses) {
            System.out.println("You tied! You both had the same wins and "
                    + "losses");
        }

        // Ask user if they want to play again
        System.out.println("Good game! Play again? (y/n)");
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("y")) {
            return true;
        }
        return false;
    }
}
