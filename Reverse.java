import java.util.Arrays;

public class Reverse {
 public static void main(String[] args) {
	int [] x = {1,2,3,4};
	reverse(x);
	System.out.println(Arrays.toString(x));
 }
 
 public static int[] reverse(int[] x) {
	 
	 int temp;
	 int n = x[3];
	 for (int i=0;i<n/2;i++) {
		 temp = x[i];
		 x[i] = x[n-1-i];
		 x[n-1-i] = temp;
	 }
	return(x);
}
 
}
