import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSort {
	
	
	//left child in heap
	static int leftChild(int i) {
		return(2*i+1);
	}
	//right child in heap
	static int rightChild(int i) {
		return(2*i+2);
	}
	//parent
	static int parent(int i) {
		return((i-1)/2);
	}
	
	public static void printA(int[]array) {
		for(int i=0; i<array.length; i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println("");
	}
	//basic implementation of maxHEap
	public static void maxHeap(int[]a, int i) {
		int max;
		//if left child is bigger, max is left
		if(leftChild(i)<a.length && a[leftChild(i)]>a[i])
			max=leftChild(i);
		else
			max=i;
		if(rightChild(i)<a.length && a[rightChild(i)]>a[max])
			max=rightChild(i);
		
		if(max!=i){
			int temp = a[i];
			a[i]=a[max];
			a[max]=temp;
			maxHeap(a,max);
		}
	}
	
	
	
	//leaf search, BUT with sift down included
	public static void leafSearch(int[]a, int i, int fin) {
		int j=i;
		int save=0;
		int length=fin;
		while(leftChild(j) <= length-1) {
			
			if(rightChild(j)<=length-1 && a[rightChild(j)]>a[leftChild(j)]) {
				int temp=a[j];
				a[j]=a[rightChild(j)];
				a[rightChild(j)]=temp;
				//keep going down the heap to find prime path
				j=rightChild(j);
				//for after, to put last element of prime path at end of array
				save= j;
				
			}
			else {
				int temp=a[j];
				a[j]=a[leftChild(j)];
				a[leftChild(j)]=temp;
				//keep going down the heap to find prime path
				j=leftChild(j);
				//for after, to put last element of prime path at end of array
				save=j;
			}
			
		}
		if(a[save]!=a[length-1]) {
			int temp= a[length-1];
			a[length-1]=a[save];
			a[save]=temp;
			
			
		}
		
	}
	
		
	
	
	public static void botupHeap(int[]a, int i, int fin) {
		for(int j=a.length-1; j>=0; j--) {
			maxHeap(a,j);
		}
		//decrement length to keep last elements sorted
		while(fin>0) {
		
		leafSearch(a,i,(fin));
		
		fin--;
		}
		
		
	}

	
	
	
	
	
	

	
	public static void main (String [] args) {
		
		
		List<String> list = new ArrayList<String>(Arrays.asList(args));
		while(list.contains("HSDriver")) {
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
		printA(arr);
		if(trace!=0) {
			System.out.print("trace at step "+array[1]+" ");
			maxHeap(arr,0);
			leafSearch(arr,0,(arr.length-trace));
			printA(arr);
		}
		
		
		
		long startTime = System.nanoTime();
		botupHeap(arr,0,arr.length);
		long endTime = System.nanoTime();
		
		printA(arr);
		
		System.out.println("Completed in "+(endTime - startTime) + " ns");
	
		}
	
	
	}

}
