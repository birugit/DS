package main.swamy.linkedlist;


public class LinkedListTest {
	public static void main(String args[]) { 
		//creating LinkedList with 5 elements including head
		LinkedListCycle linkedListCycle = new LinkedListCycle(); 
	
/*	linkedListCycle.appendIntoTail(new LinkedListCycle.Node("101")); 
		linkedListCycle.appendIntoTail(new LinkedListCycle.Node("201")); 
		linkedListCycle.appendIntoTail(new LinkedListCycle.Node("301")); 
		linkedListCycle.appendIntoTail(new LinkedListCycle.Node("401"));
		linkedListCycle.appendIntoTail(new LinkedListCycle.Node("201"));
		linkedListCycle.appendIntoTail(new LinkedListCycle.Node("301"));
		 */
		
		//creating LinkedList with 5 elements including head 
		
		linkedListCycle.appendIntoTail(new LinkedListCycle.Node("101")); 
		LinkedListCycle.Node cycle = new LinkedListCycle.Node("501"); 
		linkedListCycle.appendIntoTail(cycle); 
		linkedListCycle.appendIntoTail(new LinkedListCycle.Node("301")); 
		linkedListCycle.appendIntoTail(new LinkedListCycle.Node("401")); 
		linkedListCycle.appendIntoTail(cycle);

		//Read more: http://javarevisited.blogspot.com/2013/05/find-if-linked-list-contains-loops-cycle-cyclic-circular-check.html#ixzz4WArRrcFy
		//System.out.println("Linked List : " + linkedListCycle);
		if(linkedListCycle.isCyclic()){ System.out.println("Linked List is cyclic as it contains cycles or loop"); }
		else{ System.out.println("LinkedList is not cyclic, no loop or cycle found"); 
		} 
		} 
	}

		//Read more: http://javarevisited.blogspot.com/2013/05/find-if-linked-list-contains-loops-cycle-cyclic-circular-check.html#ixzz4WAp4RZp4

