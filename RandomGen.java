import java.util.Random;



public class RandomGen {
	
	
	public static int[] RandomA(int length) {
		Random rand = new Random();
		int [] arr = new int[length];
		for(int j=0; j<length; j++) {
			arr[j]=rand.nextInt(50)+1; 
			
		}
		return arr;
	}
	
}
