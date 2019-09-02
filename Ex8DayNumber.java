package exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.System.*;

/*
 *   Program to calculate the day number for same day in a given year.
 *   - To check solution, see http://mistupid.com/calendar/dayofyear.htm
 *
 *   This is exercising functional decomposition
 *   Assume you have a top level method solving the problem. Break down
 *   top level method into smaller methods solving parts of the problem etc.
 *   During this we run tests to make sure the methods works as expected.
 *   Combine the method to solve the problem.
 *
 */
public class Ex8DayNumber {

    public static void main(String[] args) {
        new Ex8DayNumber().program();
    }

    final Scanner sc = new Scanner(in);

    void program() {

        out.print("Input the year > ");
        int year = sc.nextInt();
        out.print("Input the month number > ");
        int month = sc.nextInt();
        out.print("Input the day number > ");
        int day = sc.nextInt();

        int dayNbr = 0;
        
        dayNbr = leapyear(year)+day;
        System.out.println(dayNbr);

        dayNbr = printResult(month, day, dayNbr);
        
        System.out.println(dayNbr);
    }

    int printResult(int month, int day, int dayNbr) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        
        for (int i = 0; i <= month-2; i++) {
			System.out.println(dayNbr);
        	dayNbr = dayNbr+list.get(i);
		}
        
        return dayNbr;
    }

    private static int leapyear(int n) {
    	boolean flag = false;
    	if(n % 400 == 0)
        {
            flag = true;
        }
        else if (n % 100 == 0)
        {
            flag = false;
        }
        else if(n % 4 == 0)
        {
            flag = true;
        }
        else
        {
            flag = false;
        }
    	
    	if (flag == true) {
    		return 1;
    	} else {
    		return 0;
    	}
	}

    
    
    void test() {

        // TODO

        exit(0);  // Never use except for here (will end program)
    }


}
