import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class Pig {

    public static void main(String[] args) {
        new Pig().program();
    }

    final Scanner sc = new Scanner(in);
    final Random rand = new Random();

    void program() {

        final int winPts = 20;    // Points to win (decrease if testing)
        Player[] players = getPlayers();         // The players (array of Player objects)
        Player current = players[0];            // Current player for round (must use)
        boolean aborted = false;   // Game aborted by player?


        welcomeMsg(winPts);
        statusMsg(players);

        current.roundPts = 0; // Reset round points
        int playerN = rand.nextInt(players.length);

        while (true) {
            if (playerN >= players.length) { //Makes playerN stays within the bounds
                playerN -= players.length;
            }

            current = players[playerN];


            while (true) {  // Loop for players
                String choicePlayer = getPlayerChoice(current);  // Check if player wants to roll again else break
                if (choicePlayer.equals("n")) {
                    break;
                } else if (choicePlayer.equals("q")) {
                    gameOverMsg(current, true);
                }

                int dice = Dice(choicePlayer);  // Rolling a dice
                current.roundPts = current.roundPts + dice;

                roundMsg(dice, current);  // Message that displays every round

                if ((current.roundPts + current.totalPts) >= winPts) {    // Check for winner
                    current.totalPts = current.roundPts + current.totalPts;
                    gameOverMsg(current, false);
                }

                if (current.roundPts == 0) {  // If player rolled a 1
                    break;
                }

            }
            playerN++;
            current.totalPts += current.roundPts;    // Add player total
        }

    }

    int Dice(String choice) {   // Roll dice else return 0
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
            out.println("Got " + result + ", current round total " + current.roundPts + ", overall total " + current.totalPts);
        } else {
            current.roundPts = 0;
            out.println("Got 1 lost it all!");
        }
    }

    void gameOverMsg(Player player, boolean aborted) {
        if (aborted) {
            out.println(player.name + " Aborted");
        } else {
            out.println("Game over! Winner is player " + player.name + " with "
                    + (player.totalPts + " points"));
        }
        System.exit(0);
    }

    String getPlayerChoice(Player player) {
        out.print("Player is " + player.name + " > ");
        return sc.nextLine();
    }

    Player[] getPlayers() {
        Player[] players;
        out.println("How many players? > ");
        players = new Player[Integer.parseInt(sc.nextLine())];   // Hard coded players, replace *last* of all with ... (see below)
        for (int i = 0; i <= players.length - 1; i++) {
            players[i] = new Player();
            out.println("Enter name of player " + (i + 1) + " :");
            String str = sc.nextLine();
            players[i].name = str;
        }

        return players;
    }
}

// ---------- Class -------------
// Class representing the concept of a player
// Use the class to create (instantiate) Player objects
class Player {
    String name;     // Default null
    int totalPts;    // Total points for all rounds, default 0
    int roundPts;    // Points for a single round, default 0
}





