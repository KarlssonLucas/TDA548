import java.util.Arrays;

public class asd {
public static void main(String[] args) {
	int [] x = {1,2,3,4};
	int temp;
	int n = 4;
	for (int i=0;i<n/2;i++) {
		temp = x[i];
		x[i] = x[n-i-1];
		x[n-1-i] = temp;
	}
	System.out.println(Arrays.toString(x));
}
}
