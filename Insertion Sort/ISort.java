import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ISort {
	
public static void PrintArray(int[] array, int p) {
		
	
	//first printing method, is used to print when there are no I
		if(p==0|p==(array.length-1))
			System.out.print("");
		else {
		for(int j=0; j<array.length; j++) {
			
			if(j==p) {
				System.out.print("["+array[p]+"] ");
			}
			
			else {
			System.out.print(array[j]+ " ");
			}
		}
		

		System.out.println("|");
		}
		
	
	}
	public static void PrintArray2(int[]array, int p) {
		int l=p-1;
		do {	
			for(int j=0; j<array.length; j++) {
			
			if(j==p) {
				System.out.print("["+array[j]+"] ");
			}
			else if(j==l) {
				System.out.print("i"+array[j]+"i ");
			}
			else {
			System.out.print(array[j]+ " ");
			}
		}
		
		if(l>=0) {	
		if(array[l]<array[p])
			System.out.println(">");
		else if(array[l]>array[p])
			System.out.println("<");
		else if(array[l]==array[p])
		System.out.println("|");
		}
		else {
			System.out.println("|");
		}
		
		
		l--;
		
			
		}while(l>=0);
	}
	public static void PrintArray3(int [] array) {
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println("");
	}
	
	
	public static void InsSort(int [] array, boolean Debug) {
		//triage par insertion, 
		// for loop pivot
		for(int p=1; p<array.length; p++) {
//			PrintArray(array,p);
			if(Debug) {
			PrintArray2(array,p);
			PrintArray(array,p);
			}
			int sort = array[p];
			int i = p-1;
			while(i>=0&&array[i]>sort){
				
				array[i+1]=array[i];
				i--;
			}
			array[i+1]=sort;
		}
		PrintArray3(array);
		
	}
	
	
	
	
	public static void main(String[]args) {
		boolean Debug=false;
		for(int i=0; i<args.length; i++) {
			if(args[i].equals("debug")) {
				 Debug=true;
			}		
		}
		List<String> list = new ArrayList<String>(Arrays.asList(args));
	    list.remove("debug");
	    args= list.toArray(new String[0]);
		int [] array = new int[args.length];
		for(int i=0; i<args.length; i++) {
			array[i]=Integer.parseInt(args[i]);
		}
		
		long startTime = System.nanoTime();
		InsSort(array,Debug);
		long endTime = System.nanoTime();
		System.out.println("Completed in "+(endTime - startTime) + " ns");
		
		
		
	}

}
