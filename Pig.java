import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

/*
 * The Pig game
 * See http://en.wikipedia.org/wiki/Pig_%28dice_game%29
 *
 */
public class Pig {

    public static void main(String[] args) {
        new Pig().program();
    }

    // The only allowed instance variables (i.e. declared outside any method)
    // Accessible from any method
    final Scanner sc = new Scanner(in);
    final Random rand = new Random();

    void program() {
        //test();                 // <-------------- Uncomment to run tests!

        final int winPts = 20;    // Points to win (decrease if testing)
        Player[] players;         // The players (array of Player objects)
        Player current;            // Current player for round (must use)
        boolean aborted = false;   // Game aborted by player?

        players = new Player[2];   // Hard coded players, replace *last* of all with ... (see below)
        Player p1 = new Player();
        p1.name = "Olle";
        Player p2 = new Player();
        p2.name = "Fia";
        players[0] = p1;
        players[1] = p2;

        //players = getPlayers();  // ... this (method to read in all players)

        welcomeMsg(winPts);
        statusMsg(players);
        current = null;

        while ( true ) {

            String choiceP1 = getPlayerChoice(p1);  // Get player1 choice (r/n/q)

            while (choiceP1.equals("r")) {  // Loop for player1
                int dice = Dice(choiceP1);
                p1.roundPts = p1.roundPts + dice;

                if (p1.roundPts + p1.totalPts >= winPts) {
                    gameOverMsg(p1, false);
                    System.exit(0);
                }

                roundMsg(dice, p1);
                if (p1.roundPts == 0) {
                    break;
                }
                choiceP1 = getPlayerChoice(p1);
                if (choiceP1.equals("n")) {
                    break;
                }
            }

            p1.totalPts = p1.totalPts + p1.roundPts;    // Add player1 total

            String choiceP2 = getPlayerChoice(p2);  // Player2 choice

            while (choiceP2.equals("r")) {  // Loop for player2
                int dice = Dice(choiceP2);
                p2.roundPts = p2.roundPts + dice;

                if (p2.roundPts + p2.totalPts >= winPts) {  // Check if player2 won
                    gameOverMsg(p2, false);
                    System.exit(0);
                }

                roundMsg(dice, p2);
                if (p2.roundPts == 0) {
                    break;
                }                                            
                choiceP2 = getPlayerChoice(p2);
                if (choiceP2.equals("n")) {
                    break;
                }
            }
        }
    }

    int Dice(String choice) {
        if (choice.equals("r")) {
            int currentDice = rand.nextInt(6) + 1;
            return currentDice;
        } else {
            return 0;
        }
    }

    void welcomeMsg(int winPoints) {
        out.println("Welcome to PIG!");
        out.println("First player to get " + winPoints + " points will win!");
        out.println("Commands are: r = roll , n = next, q = quit");
        out.println();
    }

    void statusMsg(Player[] players) {
        out.print("Points: ");
        for (int i = 0; i < players.length; i++) {
            out.print(players[i].name + " = " + players[i].totalPts + " ");
        }
        out.println();
    }

    void roundMsg(int result, Player current) {
        if (result > 1) {
            out.println("Got " + result + " running total are " + current.roundPts);
        } else {
            current.roundPts = 0;
            out.println("Got 1 lost it all!");
        }
    }

    void gameOverMsg(Player player, boolean aborted) {
        if (aborted) {
            out.println("Aborted");
        } else {
            out.println("Game over! Winner is player " + player.name + " with "
                    + (player.totalPts + player.roundPts) + " points");
        }
    }

    String getPlayerChoice(Player player) {
        out.print("Player is " + player.name + " > ");
        return sc.nextLine();
    }

    Player[] getPlayers() {
         // TODO
        return null;
    }

    // ---------- Class -------------
    // Class representing the concept of a player
    // Use the class to create (instantiate) Player objects
    class Player {
        String name;     // Default null
        int totalPts;    // Total points for all rounds, default 0
        int roundPts;    // Points for a single round, default 0
    }

    // ----- Testing -----------------
    // Here you run your tests i.e. call your game logic methods
    // to see that they really work (IO methods not tested here)
    void test() {
        // This is hard coded test data
        // An array of (no name) Players (probably don't need any name to test)
        Player[] players = {new Player(), new Player(), new Player()};

        // TODO Use for testing of logcial methods (i.e. non-IO methods)

        exit(0);   // End program
    }
}



