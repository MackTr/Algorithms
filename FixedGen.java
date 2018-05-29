
public class FixedGen {
	
	
	public static int[] FixedA(int length) {
		
		int [] arr = new int[length];
		for(int j=length-1; j>0; j--) {
			arr[j]=j;
			
		}
		return arr;
	}

}
