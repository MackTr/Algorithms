import java.util.Scanner;



import java.io.*;


public class list {

	static class Node {
		
		//variables
		private String letter;
		private int weight;
		private int occurence;
		Node left;
		Node right;
		Node root;
		Node parent;
		private String code;
		int value;
		String index;
		
		public Node() {
			letter = null;
			weight = 0;
			occurence = 0;
			left = null;
			right = null;
			code = null;
			value = 0;
			index = null;
		}
		
		
		public Node(String letter, int weight, int occurence, Node left, Node right) {
			this.letter = letter;
			this.weight = weight;
			this.occurence = occurence;
			this.left = left;
			this.right = right;
			code = "";
		}
		public Node(int weight) {
			letter = null;
			this.weight = weight;
			code = "";
		}
		public Node(int value, String index) {
			this.index = index;
			this.value = value;
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
		public String getCode() {
			return code;
		}
		
	}
	
	private static Node head;
	private Node tail;
	int size;
	
	
	public list() {
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
	
	public static Node find(String l) {
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
		if(head == null) {
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
	
	public Node [] toArray(list list) {
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
	//reset list method
	public void reset() {
		head = null;
		
	}
	
	//make list method
	public static Node [] makeList(boolean print, String file) {
		list list = new list();
		
		Scanner sc = null;
		String s;

		try {
			sc = new Scanner(new FileInputStream(file));
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found");
			System.exit(0);
		}
		int occ = 0;
		while(sc.hasNextLine()) {
			s = sc.nextLine();
			for(int i=1; i<s.length()+1; i++) {
				String letter = (s.substring(i-1, i));
				list.add(letter, occ);
				occ++;
			}
		}
		
		
		Node [] arr = list.toArray(list); 
		list.reset();
		
		
		InsSortWEIGHT(arr);
		if(print == true)
			printArray(arr);
		
		sc.close();
		return arr;
	}
		

	public static void main(String[]args){
		
		
		makeList(true, args[0]);
		

		
		
		
		
	}

}
