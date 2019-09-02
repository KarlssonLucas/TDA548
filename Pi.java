import java.util.Scanner;

public class Pi {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("radie");
//		char x = scanner.nextLine().charAt(0);
		int x = scanner.nextInt();
		area(x);
		
	}
	public static double area(int n) {
		double a = (n*n*Math.PI);
		System.out.println(a);
		return a;
	}

}
