package main.swamy.setofstacks;

import java.util.ArrayList;



public class SetOfStacks {

	ArrayList<Stack> stacks = new ArrayList<Stack>();
	
	int capacity;
	public SetOfStacks(int capacity){
		capacity = capacity;
	}
	

 static void main(String[] args) {
		
	 SetOfStacks setOfStacks = new SetOfStacks(3);
	
	 
	}

 
 public class Stack<Item> {

		private int capacity;//size of stack
		private Node first;//top of the stack
		private int size = 0;
		Node top, bottom;
		private class Node{
			public Node(int value) {
				item = value;
			}
			private int item;
			private Node next;
		}
		
		public Stack(int capacity){
			first = null;
			this.capacity = capacity;
		
	}

		public void push(int value){
			Stack lastStack = getLastStack();
			if(lastStack != null && !lastStack.isFull()){
				lastStack.push(value);
				
			}else{
				Stack stack = new Stack(capacity);
				stack.push(value);
				stacks.add(stack);
				
			}
		}
		private Stack getLastStack() {
			if(stacks.size() == 0) return null;
			else
			return stacks.get(stacks.size() - 1);
		}

		

		public boolean isFull() {
			// TODO Auto-generated method stub
			return size == capacity;
		}
	}

}
