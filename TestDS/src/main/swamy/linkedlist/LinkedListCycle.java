package main.swamy.linkedlist;

public class LinkedListCycle {
	private Node head; 
	public LinkedListCycle() { this.head = new Node("head"); } 
	public Node head() { return head;} 
	public void appendIntoTail(Node node) { 
		Node current = head; 
	//find last element of LinkedList i.e. tail
	while(current.next() != null)
	{ 
		current = current.next();
	}
	//appending new node to tail in LinkedList 
	current.setNext(node); 
	}
	/* * If singly LinkedList contains Cycle then following would be true * 1) slow and fast will point to same node i.e. they meet * On the other hand if fast will point to null or next node of * fast will point to null then LinkedList does not contains cycle. */ 
	public boolean isCyclic(){ 
		Node fast = head; 
		Node slow = head; 
	while(fast!= null && fast.next != null){
		fast = fast.next.next; 
		slow = slow.next; 
		if(fast!=null && slow!=null)
		System.out.println("fast:"+fast.data+" slow:"+slow.data);
	//if fast and slow pointers are meeting then LinkedList is cyclic 
	if(fast == slow ){ 
		System.out.println("collision:fast:"+fast.data+" slow:"+slow.data);
		findBegining(slow,fast);
		return true; 
		} 
	} 
	return false; 
	}
	
	private void findBegining(Node slow, Node fast) {
		Node fastRunner = fast; 
		Node slowRunner = head; 
		while(slowRunner != fastRunner){
			slowRunner = slowRunner.next;
			//System.out.println("SR :"+slowRunner.data);
			fastRunner = fastRunner.next;
			System.out.println("SR :"+slowRunner.data+"-->FR :"+fastRunner.data);
		}
		System.out.println("FR collision point:"+fastRunner.data);
	}
	@Override 
	public String toString(){ 
			StringBuilder sb = new StringBuilder(); 
	Node current = head.next(); while(current != null){ 
		sb.append(current).append("-->"); 
	current = current.next(); } 
	sb.delete(sb.length() - 3, sb.length()); 
	// to remove --> from last node return 
	return sb.toString(); 
	} 
	
	public static class Node { 
		private Node next; 
	private String data; 
	public Node(String data) 
	{ 
		this.data = data; 
		}
	
	public String data() { 
		return data; 
	}
	
	public void setData(String data) { 
		this.data = data;
		}
	
	public Node next() {
		return next; 
		}
	
	public void setNext(Node next) { 
		this.next = next;
		}
	
	@Override public String toString() { 
		return this.data; 
		} 
	}
	
	

	//Read more: http://javarevisited.blogspot.com/2013/05/find-if-linked-list-contains-loops-cycle-cyclic-circular-check.html#ixzz4WAmhAvC7
}
