import java.util.Random;
import java.util.Scanner;

public class Galtonmedmetoder {
	
	private static Scanner input;

	public static void main(String[] args) {
	// Kallar på metoden antal
	antal();
	
	}

	public static void antal() {
		
		//Frågar användaren om antalet bollar och skickar sedan vidare till metoden raderbollar
        input = new Scanner(System.in);
        System.out.print("antalet bollar:   ");
        int bollar = input.nextInt();
        raderbollar(bollar);
	}
	
	public static void raderbollar(int bollar) {
		//Initierar värdena som används i looparna
		int värde, bollensrad;
		int rader = 14;
		 
		//Skapar ny lista med samma antalet platser som de finns bollar och skapar en randomfunktion
        int[] ballsPosition = new int[bollar];
        Random random = new Random();

        //Forloop som körs för alla bollar och börjar räkna från vänster och plussar på vilken rad den hamnar på
        //för varje gång värde = 0 och sedan sätter bollensrad till ballspos[i] i listan
        for (int i = 0; i < bollar; i++) {
            bollensrad = 0;
            for (int j = 0; j < rader; j++) {
                värde = random.nextInt(2);
                if (värde == 0) {
                	bollensrad++;
                }
            }
            ballsPosition[i] = bollensrad;
        }

        int pos0 = 0;
        int pos1 = 0;
        int pos2 = 0;
        int pos3 = 0;
        int pos4 = 0;
        int pos5 = 0;
        int pos6 = 0;
        int pos7 = 0;
        int pos8 = 0;
        int pos9 = 0;
        int pos10 = 0;
        int pos11 = 0;
        int pos12 = 0;
        int pos13 = 0;

        // Forloop först för att köra för alla bollar sen en för att köra en gång för alla rader
        // Sen kör man en if sats där om värdet på ballspos[i] är samma som j då j är mindre än rader så ska man
        // köra en switch and case funktion där man letar efter vilket case som är lika med  ballspos[i] och
        // sedan executar pos(någotvärde)++
    for (int i = 0; i < ballsPosition.length; i++) {
        for (int j = 0; j < rader; j++) {
            if (ballsPosition[i] == j) {
                switch (ballsPosition[i]) {

                case 0:
                    pos0++;
                    break;
                case 1:
                    pos1++;
                    break;
                case 2:
                    pos2++;
                    break;
                case 3:
                    pos3++;
                    break;
                case 4:
                    pos4++;
                    break;
                case 5:
                    pos5++;
                    break;
                case 6:  
                	pos6++;
                    break;
                case 7:
                    pos7++;
                    break;
                case 8:
                    pos8++;
                    break;
                case 9:
                    pos9++;
                    break;
                case 10:
                    pos10++;
                    break;
                case 11:
                    pos11++;
                    break;
                case 12:
                    pos12++;
                    break;
                case 13:
                    pos13++;
                    break;
                }
            	}

            }

        }
    // Utskrivning av 0 eller inget vilket sedan formar normalfördelningskurvan
    for (int i = ballsPosition.length; i >= 0; i--) {
        if (pos0 > i) {
            System.out.print("0");
        } else {
            System.out.print(" ");
        }
        if (pos1 > i) {
            System.out.print("0");
        } else {
            System.out.print(" ");
        }
        if (pos2 > i) {
            System.out.print("0");
        } else {
            System.out.print(" ");
        }
        if (pos3 > i) {
            System.out.print("0");
        } else {
            System.out.print(" ");
        }
        if (pos4 > i) {
            System.out.print("0");
        } else {
            System.out.print(" ");
        }
        if (pos5 > i) {
            System.out.print("0");
        } else {
            System.out.print(" ");
        }
        if (pos6 > i) {
            System.out.print("0");
        } else {
        System.out.print(" ");
        }
        if (pos7 > i) {
            System.out.print("0");
        } else {
        System.out.print(" ");
        }
        if (pos8 > i) {
            System.out.print("0");
        } else {
        System.out.print(" ");
        }
        if (pos9 > i) {
            System.out.print("0");
        } else {
        System.out.print(" ");
        }
        if (pos10 > i) {
            System.out.print("0");
        } else {
        System.out.print(" ");
        }
        if (pos11 > i) {
            System.out.print("0");
        } else {
        System.out.print(" ");
        }
        if (pos12 > i) {
            System.out.print("0");
        } else {
        System.out.print(" ");
        }
        if (pos13 > i) {
            System.out.print("0");
        }
        System.out.println();
        }
    	sysout(bollar);
	}
	
	public static void sysout(int x) {
		System.out.println("Normalfördelning med "+x+" bollar");
	}

	
}