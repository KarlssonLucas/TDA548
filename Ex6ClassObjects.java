package exercises;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

/*
 *  Using class objects to represent heroes
 *  https://en.wikipedia.org/wiki/Gladiators_(1992_UK_TV_series)
 *
 * See:
 * - ClassObjects
 * - MethodsArrObj
 */
public class Ex6ClassObjects {

    public static void main(String[] args) {
        new Ex6ClassObjects().program();
    }

    final Scanner sc = new Scanner(in);

    void program() {
        Hero h1 = new Hero();
        Hero h2 = new Hero();
        
        h1.name = sc.next();
        h1.strength = sc.nextInt();
        
        h2.name = sc.next();
        h2.strength = sc.nextInt();
        
        if (h1.strength > h2.strength) {
			System.out.println(h1.name);
		} else {
			System.out.println(h2.name);
		}
    }

    // ------ The class to use  -----------
    // A class for objects that describes a Hero
    class Hero {
        String name;
        int strength;
    }


}
