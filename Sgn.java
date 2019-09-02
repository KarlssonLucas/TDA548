import java.util.ArrayList;

public class Sgn {
	public static void main(String[] args) {
		int x = 125;

		int a = 25;
		ArrayList<Integer> hg = new ArrayList<Integer>();
		hg = prime(x);
		System.out.println(hg);
		
		ArrayList<Integer> dg = new ArrayList<Integer>();
		dg = prime(a);
		System.out.println(dg);

		dg.retainAll(hg);
		System.out.println(dg);
		
	}
	
	private static ArrayList<Integer> prime(int number) {
	ArrayList<Integer> x = new ArrayList<Integer>();
    for(int i = 2; i< number; i++) {
        while(number%i == 0) {        
           x.add(i);
           number = number/i;

        }
     }
     if(number >2) {
        x.add(number);
     }

     return x;
     }
  }
