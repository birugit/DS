package main.swamy.linkedstack;

import java.util.Iterator;
import java.util.NoSuchElementException;

import main.swamy.linkedstack.LinkedStack.LinkedStackIterator;

public class LinkedStack<Item> implements Iterable<Item> {
	
	public class LinkedStackIterator implements Iterator<Item> {
		
		public Node current = first;
		public LinkedStackIterator(){
			
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}

	}

	private int n;//size of stack
	private Node first;//top of the stack
	
	private class Node{
		private Item item;
		private Node next;
	}
	
	public LinkedStack(){
		first = null;
		n = 0;
	}
	
	public boolean isEmpty(){
		return first == null;
			//return true;
	}

	public int size(){
		return n;
	}
	
	public void push(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		
		n++;
	}
	
	public Item pop(){
		if(isEmpty()){
			throw new NoSuchElementException();
		}

		
		Item item = first.item;
		first = first.next;
		n--;
		return item;
		
	}
	
	public String toString(){
		StringBuffer string = new StringBuffer();
		for(Item x: this){
			string.append(string);
			string.append(" ");
		}
		return string.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedStack<String> ls = new LinkedStack<String>();
		ls.push("S");
		ls.push("w");
		ls.push("a");
		for(String i: ls){
			System.out.print(i);
		}
		System.out.println();
		System.out.println("ListStack:"+ls);
		System.out.println("pop:"+ls.pop());
		Iterator it = ls.iterator();
		while(it.hasNext()){
			System.out.println("Stack:"+it.next());
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new LinkedStackIterator();
	}

}
