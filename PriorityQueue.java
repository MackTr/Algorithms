

public class PriorityQueue extends list {

	class Nodet{
		
		
		Node t;
		Nodet next;
		
		
		
		public Nodet getNext() {
			return next;
		}
		
		public Nodet() {
			t = null;
			next = null;
		}
		public Nodet(Node t, Nodet next) {
			this.t = t;
			this.next = next;
		}
	}
	
	private Nodet head;
	
	public PriorityQueue() {
		head = null;
	}
	
	//add at start, a priority queue mehtod
	
	public void addAtStart(Node i) {
		head = new Nodet(i, head);
	}
	
	//delete at start,  a priority queue mehtod
	
	public Node deleteAtStart() {
		Nodet temp = null;
		if(head != null) {
			temp = head;
			head = head.getNext();
		}
		return temp.t;
	}
	
	//size
	public int size() {
		int c = 0;
		Nodet temp = head;
		while(temp != null) {
			c++;
			temp = temp.next;
		}
		return c;
	}
	
	//bubble sort method, I was not able to implement a insertion sort, therefore I implemented bubble sort
	
	public void Sort() {
	    if (size()>1) {
	        for (int i = 0; i < size(); i++ ) {
	            Nodet c = head;
	            Nodet nxt = head.next;
	            for (int j = 0; j < size() - 1; j++) {
	                if (c.t.getWeight() > nxt.t.getWeight()) {
	                    Node temp = c.t;
	                    c.t = nxt.t;
	                    nxt.t = temp;
	                } 
	                c = nxt;
	                nxt = nxt.next;                   
	            } 
	        }
	    }
	}
	
	
	public void showContents() {
		Nodet temp = head;
		if(temp == null)
			System.out.println("EMpty list");
			
		else
			System.out.println("Here are the elements");
		while(temp != null) {
			System.out.print(temp.t.getWeight()+"------>");
			temp = temp.next;
		}
		System.out.println("X");
	}
	
	
	public static void main (String [] args) {
		
		
		
		//T E S T I N G
		
//		PriorityQueue queue = new PriorityQueue();
//		Node n = new Node(12);
//		Node n1 = new Node(111);
//		Node n2 = new Node(123);
//		Node n3 = new Node(11);
//		queue.addAtStart(n);
//		queue.addAtStart(n1);
//		queue.addAtStart(n2);
//		queue.addAtStart(n3);
//		queue.Sort();
//		queue.showContents();
		
		
		
		
	}
	
	
}
