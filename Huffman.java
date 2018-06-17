import java.util.Scanner;

public class Huffman extends PriorityQueue {
	

	
	public Huffman() {
		
	}
	 
	
	//making the tree, sort the nodes by weight, point the two smallest ones to the sum of their weight, delete them both and insert the combined node at the beginning

	public static Node makeTree(Node [] arr) {
		PriorityQueue queue = new PriorityQueue();
		for(int i = 0; i<arr.length; i++) {
			queue.addAtStart(arr[i]);
		}
		
		Node root = null;
		
		
		while(queue.size() >1) {
			queue.Sort();
			Node s1 = queue.deleteAtStart();
			Node s2 = queue.deleteAtStart();
			Node combined = new Node(s1.getWeight()+s2.getWeight());
			combined.left = s1;
			combined.right = s2;
			s1.parent = combined;
			s2.parent = combined;
			queue.addAtStart(combined);
			root = combined;
		}
		return root;
	}
	
	static void encoding(Node n) {
		int path [] = new int[3000];
		String letter  = null;
		String code = null;
		encodingR(n, path, 0, letter, code);
	}
	public static int counter = 0;
	public static String [] codes = new String[3000];
	public static String [] letterz = new String[3000];
	
	static void encodingR(Node n, int [] path, int L, String letter, String code) {
		boolean stop = false;
		
		if(n.getWeight() == 0)
			return;
		path[L++] = n.getWeight();
		
	
		if(n.getLetter() != null) {
			stop =true;
			letter= n.getLetter(); 
		}
		if(stop) {
		System.out.println("'"+n.getLetter()+"' occurs " + n.getWeight()+" times, and seen for the first time at " + n.getOccurence()+" with code " + code.substring(4, code.length()));
		letterz[counter] = n.getLetter();
		codes[counter] = code.substring(4, code.length());
		counter++;
		return;
		}
		
		encodingR(n.left, path, L, letter, code + "0");
		encodingR(n.right, path, L, letter, code + "1");
		return;
	}

	
	
	
	
	
	public static void main (String [] args) {
		
		makeList(false, args[0]);
		Node n = makeTree(makeList(false, args[0]));	
		encoding(n);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("");
		do {
		System.out.println("Enter a String to encode");
		String s = sc.nextLine();
		System.out.println("'"+s+ "' Huffman encoding = ");
		for(int i =1; i<s.length()+1; i++) {
			for(int j=0; j<counter; j++) {
				if(s.substring(i-1, i).equals(letterz[j])) {
					System.out.print(codes[j]);
				}
			}
		}
		System.out.println("\n\nPress \"y\" to encode another word or any other key to stop program");
		}
		while(sc.nextLine().equalsIgnoreCase("y"));
		
		
		
		sc.close();
		
		
	}
}
