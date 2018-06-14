import java.util.Scanner;
import java.io.*;


public class Huffman {

	private class Node{
		
		
		private String letter;
		private int weight;
		private int occurence;
		private Node left;
		private Node right;
		Node root = null;
		
		public Node() {
			letter = null;
			weight = 0;
			occurence = 0;
			left = null;
			right = null;
		}
		
		
		public Node(String letter, int weight, int occurence, Node left, Node right) {
			this.letter = letter;
			this.weight = weight;
			this.occurence = occurence;
			this.left = left;
			this.right = right;
		}
		public Node(int weight, Node left, Node right, Node root) {
			this.weight = weight;
			this.left = left;
			this.right = right;
			this.root = root;
		}

		public String getLetter() {
			return letter;
		}

		public void setLetter(String letter) {
			this.letter = letter;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public int getOccurence() {
			return occurence;
		}

		public void setOccurence(int occurence) {
			this.occurence = occurence;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
		
		
	}
	
	private Node head;
	private Node tail;
	int size;
	private Node root;
	
	public Huffman() {
		head = null;
		tail = null;
		size = 0;
	}
	
	
	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	//find method
	
	public Node find(String l) {
		if(head == null){
			return null;
		}
		Node t = head;
		while(t != null) {
			if(t.letter.equals(l))
				return t;
			t = t.right;
		}
		return null;
	}
	
	//contains method
	
	public boolean contains(String s) {
		if(find(s) != null)
			return true;
		else
			return false;
	}
	
	
	
	//add method, when going throught, checks if letter already has a node, if it doesnt -> creates a new one, if it does -> inc weight
	
	public void add(String letter, int occ) {
		if (contains(letter)) {
			int weight = find(letter).getWeight();
			find(letter).setWeight(weight+1);	
		}
		else {
			Node temp = head;
			if(size == 0) {
				Node n = new Node(letter,1,occ,head,tail);
				head = n;
				n = null;
				size++;
			}
			else {
				while(temp.right != null) {
					temp = temp.right;
				}
				Node n = new Node(letter, 1, occ, temp, null);
				temp.right = n;
				n = null;
				size++;
			}
		}
	}
	
	//print list method
	
	public void printList() {
		if(size == 0) {
			System.out.println("Empty list");
			return;
		}
		Node temp = head;
		
		while(temp!=tail) {
			System.out.println("\"" + temp.letter + "\" occurs "+ temp.weight + " times, seen the first time at index "+ temp.occurence);
			temp = temp.right;
		}
		
	}
	//to Array method
	
	public Node [] toArray(Huffman list) {
		Node[] arr = new Node[list.size];
		Node t = head;
		int inc=0;
		
		while(t != null) {
			arr[inc] = t;
			inc++;
			t = t.right;
		}
		return arr;
	}
	
	//Insertion sort for weight, because insertion sort is stable, and occurence is already in order,this will make things all in order
	
	public static void InsSortWEIGHT(Node [] array) {
		for(int p=1; p<array.length; p++) {
			Node sort = array[p];
			int i = p-1;
			while(i>=0&&array[i].weight>sort.weight){	
				array[i+1]=array[i];
				i--;
			}
			array[i+1]=sort;
		}	
	}
	
	//printing array method
	
	public static void printArray(Node [] arr) {
		for(int i = 0; i< arr.length; i++) {
			System.out.println("\"" + arr[i].letter +"\" occurs " + arr[i].weight + " times, seen the first time at index " + arr[i].occurence);
		}
	}
	
	//reverses array
	
	public static void reverseArray(Node[] arr) {
		for(int i = 0; i<arr.length / 2; i++) {
			Node temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
	}
	
//	static int leftChild(int i) {
//		return(2*i+1);
//	}
//	//right child in heap
//	static int rightChild(int i) {
//		return(2*i+2);
//	}
//	//parent
//	static int parent(int i) {
//		return((i-1)/2);
//	}
	
	
	
	
	private static int index = 0;
	public void buildLeaves(Node [] arr) {
		if(index<arr.length-2) {
		Node t = root;
		int temp = arr[index].getWeight();
		int temp2 = arr[index-1].getWeight();
		Node n = new Node((temp+temp2), arr[index], arr[index+1], root);
		temp++;
		temp++;
		}
		
	}
	public void innerNodes(Node n) {
		
	}
	
	
	
	
	
//	traverse(Node root, String code){
//		 if(root.left == null && root.right == null){
//		  //store the code in an array and return
//		  traverse(root.left, code+0);
//		  traverse(root.right, code+1);
//		}
	
	
	

	public static void main(String[]args){
		
		String s;
		Scanner sc = null;
	
		
		Huffman list = new Huffman();
		
		try {
			sc = new Scanner(new FileInputStream("Jabberwock.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found");
			System.exit(0);
		}
		int occ = 0;
		while(sc.hasNextLine()) {
			s = sc.nextLine();
			for(int i=1; i<s.length(); i++) {
				String letter = (s.substring(i-1, i));
				list.add(letter, occ);
				occ++;
			}
		}
		
//		list.printList();
		
		Node [] arr = list.toArray(list); 
		
		
		InsSortWEIGHT(arr);
//		reverseArray(arr);
		printArray(arr);
		
		
		
		
		
		
		
		sc.close();
		
	}

}
