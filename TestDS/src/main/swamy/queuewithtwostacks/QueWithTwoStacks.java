package main.swamy.queuewithtwostacks;

import java.util.Stack;

public class QueWithTwoStacks {
	Stack<Integer> newestStack;
	Stack<Integer> oldestStack;
	
	public QueWithTwoStacks() {
		
		newestStack = new Stack<Integer>();
		oldestStack = new Stack<Integer>();
	}

	public void add(int item){
		newestStack.push(item);
	}
	
	public Integer pop(){
		shiftItems();
		return oldestStack.pop();
	}
	/*
	 * Reverse the newestStack in oldestStack
	 */
	private void shiftItems() {
		if(oldestStack.isEmpty()){
			while(!newestStack.isEmpty()){
				oldestStack.push(newestStack.pop());
			}
		}
		
		
	}

	public static void main(String[] args) {
		QueWithTwoStacks qwts = new QueWithTwoStacks();
		
		qwts.add(6);
		qwts.add(7);
		qwts.add(10);
		qwts.add(5);
		qwts.add(4);
		System.out.println("newestStack:"+qwts.newestStack.toString());
		
		//dequeue from first
		Integer i = qwts.pop();
		System.out.println("oldestStack FIFO:"+qwts.oldestStack.toString());
		System.out.println("First Element in que:"+i);

	}

}
