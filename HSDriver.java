import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HSDriver {

	
	
	public static void main (String[]args) {
		List<String> list = new ArrayList<String>(Arrays.asList(args));
		
			boolean go = false;
			boolean go2=false;
			list.remove("HSDriver");
			if(list.contains("RandomGen")) {
				list.remove("RandomGen");
				go=true;
			}
			
		if(list.contains("FixedGen")) {
			list.remove("FixedGen");
			go2=true;
		}
			args= list.toArray(new String[0]);
			int [] array = new int[args.length];
			for(int i=0; i<args.length; i++) {
				array[i]=Integer.parseInt(args[i]);
		}
		int length=array[0];
		int trace=0;
		if(args.length>=2)
			trace=array[1];
		
		int[]arr=null;
		
		if(go) {
			arr= RandomGen.RandomA(length);
		}
		if(go2) {
			arr=FixedGen.FixedA(length);
		}
		System.out.print("sorting: ");
		HeapSort.printA(arr);
		if(trace!=0) {
			System.out.print("trace at step "+array[1]+": ");
			HeapSort.maxHeap(arr,0);
			HeapSort.leafSearch(arr,0,(arr.length-trace));
			HeapSort.printA(arr);
		}
		
		
		
		long startTime = System.nanoTime();
		HeapSort.botupHeap(arr,0,arr.length);
		long endTime = System.nanoTime();
		System.out.print("result: ");
		HeapSort.printA(arr);
		
		System.out.println("Completed in "+(endTime - startTime) + " ns");
	
		}
	
	
	}

	

