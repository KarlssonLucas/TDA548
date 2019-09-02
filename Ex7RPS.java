package exercises;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 * The Rock, paper, scissor game.
 * See https://en.wikipedia.org/wiki/Rock%E2%80%93paper%E2%80%93scissors
 *
 * This is exercising smallest step programming (no methods needed)
 *
 * Rules:
 *
 *       -----------  Beats -------------
 *       |                              |
 *       V                              |
 *      Rock (1) --> Scissors (2) --> Paper (3)
 *
 */
public class Ex7RPS {

    public static void main(String[] args) {
        new Ex7RPS().program();
    }

    final Random rand = new Random();
    final Scanner sc = new Scanner(in);

    void program() {

        int maxRounds = 5;
        int h;          // Outcome for player
        int c;       // Outcome for computer
        int result;         // Result for this round
        int round = 0;      // Number of rounds
        int total = 0;      // Final result after all rounds

        c = rand.nextInt(3)+1;
        
        
        out.println("Welcome to Rock, Paper and Scissors");
        int i = 0;
        while (i<maxRounds) {
        	System.out.println("Sten Sax Påse 1/2/3? ");
        	h = sc.nextInt();
        	
        	if (h==1 & c==2 | h==3 & c==1 | h==2 & c==3 ) {
				System.out.println(c+", human won this round");
        		total=total+1;
			} else if (c==1 & h==2 | c==3 & h==1 | c==2 & h==3) {
				System.out.println(c+", computer won this round");
				total=total-1;
			} else {
				System.out.println("draw!");
			}
        i++;
        }


        out.println("Game over! ");
        if (total == 0) {
            out.println("Draw");
        } else if (total > 0) {
            out.println("Human won.");
        } else {
            out.println("Computer won.");
        }
    }
}
