import java.awt.*;
import java.util.Scanner;

public class Cars {

    private Saab95 saab95 = new Saab95();
    private Volvo240 volvo240 = new Volvo240();
    private Scanner sc = new Scanner(System.in);

    public void update() {
        while (true) {
            String s = sc.nextLine();
            switch (s) {
                case "w":
                    saab95.gas(0.9);
                    break;
                case "s":
                    saab95.brake(0.2);
                    break;
                case "a":
                    saab95.turnLeft();
                    break;
                case "d":
                    saab95.turnRight();
                    break;
                case "t":
                    saab95.setTurboOn();
                    break;
                case "y":
                    saab95.setTurboOff();
                    break;
                case "e":
                    saab95.startEngine();
                    break;
                case "q":
                    saab95.stopEngine();
                    break;
            }
            saab95.move();
            System.out.println(saab95.getX());
            System.out.println(saab95.getY());
        }

    }
    public static void main(String[] args) {
        new Cars().update();
    }
}
