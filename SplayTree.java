import java.util.Scanner;
import java.io.*;


public class SplayTree extends PriorityQueue {

	
	static Node root = null;
	
	public SplayTree() {
		root = null;
	}
	
	//static variables to keep track of compares, zigs, and zags
	
	static int counter;
	static int counterZig;
	static int counterZag;
	
	
	//add method
	
	public void add(int value, String index) {
		if(root == null) {
			root = new Node(value, index);
			return;
		}
		root = splay(root, index);
		
		int compare = index.compareTo(root.index);
		counter++;
		
		if(compare < 0) {
			Node n = new Node(value, index);
			n.left = root.left;
			n.right = root;
			root.left = null;
			root = n;
		}
		else if (compare > 0) {
			Node n = new Node(value, index);
			n.right = root.right;
			n.left = root;
			root.right = null;
			root = n;
		}
		else {
			root.value = value;
		}
	}
	
	//remove method
	
	public void remove(String index) {
		if(root == null) {
			return;
		}
		
		//splay 
		root = splay(root, index);
		
		int compare = index.compareTo(root.index);
		counter++;
		
		if(compare == 0) {
			if(root.left == null)
				root = root.right;
			else {
				Node n = root.right;
				root = root.left;
				splay(root, index);
				root.right = n;
			}
		}
	}
	
	//splay method
	
	Node splay(Node n, String index) {
		if(n == null)
			return null;
		
		int compare1 = index.compareTo(n.index);
		counter++;
		if(compare1 <0) {
			if(n.left == null)
				return n;
			int compare2 = index.compareTo(n.left.index); 
			counter++;
			if(compare2 < 0) {
				n.left.left = splay(n.left.left, index);
				n = zigzigRight(n);
			}
			else if (compare2 >0) {
				n.left.right = splay(n.left.right, index);
				if(n.left.right != null)
					n.left = zigzigLeft(n.left);
			}
			if (n.left == null)
				return n;
			else
				return zigzigRight(n);
		}
		else if(compare1 > 0) {
			if(n.right == null)
				return n;
			int compare2 = index.compareTo(n.right.index); 
			counter++;
			if(compare2 < 0) {
				n.right.left = splay(n.right.left, index);
				if(n.right.left != null)
					n.right = zigzigRight(n.right);
			}
			else if(compare2 > 0) {
				n.right.right = splay(n.right.right, index);
				n = zigzigLeft(n);
			}
			if(n.right == null) {
				counterZag++;
				return n;
			}
			else
				return zigzigLeft(n);
		}
		else
			return n;
	}
	
	
	//zigzzig right
	
	Node zigzigRight(Node n) {
		counterZig++;
		Node temp = n.left;
		n.left = temp.right;
		temp.right = n;
		return temp;
	}
	//zigzigleft
	
	Node zigzigLeft(Node n) {
		counterZig++;
		Node temp = n.right;
		n.right = temp.left;
		temp.left = n;
		return temp;
	}
	//find method
	
	
	
	public Node findST(Node root, String index) {
		if(root == null)
			return null;
		
		if(root == null || root.index == null)
			return root;
		
		int temp = index.compareTo(root.index);
		counter++;
		if(temp > 0)
			return findST(root.right, index);
		
		return findST(root.left, index);
		
	}

    public static void main (String [] args) {
    	
    	SplayTree st = new SplayTree();
    	Scanner sc = null;
    	int lines = 0;
    	
    	
    	try {
			sc = new Scanner(new FileInputStream(args[0]));
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found");
			System.out.println(args[0]);
			System.exit(0);
		}
    	int [] t = new int [3];
    	while(sc.hasNextLine()) {
    		String s = sc.nextLine();
    		
    		if(lines == Integer.parseInt(args[1])) { 
    			t[0] = counter;
    			t[1] = counterZig;
    			t[2] = counterZag;
     		}
    		lines++;
    		String letter = (s.substring(0, 1));
			
    		if(letter.equals("a")) {
    			String number = s.substring(1,s.length());
    			st.add(Integer.parseInt(number), number);
    			
    		}
    		if(letter.equals("r")) {
    			String number = s.substring(1,s.length());
    			st.remove(number);
    			
    		}
    		else if(letter.equalsIgnoreCase("f")){
    			String number = s.substring(1,s.length());
    			st.findST(root,number);
    		}	
    	}
    	System.out.println(t[0]+ " compares ");
    	System.out.println(t[1]+ " Zig-Zigs");
    	System.out.println(t[2]+ " Zig-Zags");
    	
    	sc.close();
    }
	
	
}
