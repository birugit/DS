package main.swamy.linkedqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import main.swamy.linkedqueue.LinkedQueue.LinkedQueueIterator;

public class LinkedQueue<Item> implements Iterable<Item>
{
	
	public class LinkedQueueIterator<Item> implements Iterator<Item> {
		Node<Item> current;
		
		public LinkedQueueIterator(Node<Item> first){
			current = first;
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
			

		}

	}

	private Node first;
	private Node last;
	private int n;
	private class Node<Item>{
		Item item;
		Node next;
	}
	
	public  LinkedQueue() {
		first = null;
		last = null;
		n = 0;
	}
	
	public boolean isEmpty(){
		return first == null;
	}

	public void enqueue(Item item){
		
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty()){
			first = last;
		}else
		{
			oldlast.next = last;
		}
		n++;
	}
	
	public Item dequeue(){
		if(first != null){
			Node oldfirst = first;
			Item item = (Item) oldfirst.item;
			first = oldfirst.next;
			return item;
		}
		return null;
	}
	
	public int size(){
		return n;
	}
	
	@Override
	public Iterator<Item> iterator() {
		
		return new LinkedQueueIterator(first);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedQueue<String> lq = new LinkedQueue<String>();
		lq.enqueue("S");
		lq.enqueue("w");
		lq.enqueue("a");
		System.out.println("q:"+lq);
		//lq.LinkedQueueIterator it = lq.iterator();
		
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Item it:this){
			sb.append(it+"");
		}
		return sb.toString();
	}

}
