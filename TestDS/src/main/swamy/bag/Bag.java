package main.swamy.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

import main.swamy.bag.Bag.ListIterator;

public class Bag<Item> implements Iterable<Item> {
	public class ListIterator<Item> implements Iterator<Item> {
		Node<Item> current;
		public ListIterator(Node<Item> first) {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(!hasNext())throw new NoSuchElementException("Empty");
			Item item = current.item;
			current =  current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

	}

	private Node<Item> first;
	private int n;
	
	private static class Node<Item>{
		Node<Item> next;
		Item item;
		
	}

	public Bag(){
		first = null;
		n = 0;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public void add(Item item){
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		n++;
	}
	
	public int size(){
		return n;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Item s:this){
			sb.append(s+" ");
		}
		System.out.println();
		return sb.toString();
	}
	

	public static void main(String[] args) {
		Bag<String> bag = new Bag<String>();
		bag.add("Swamy");
		bag.add("Biru");
		bag.add("Ranhita");
		System.out.println("Bag to String:"+bag);
		for(String s: bag){
			System.out.println("Bag:"+s);
		}
	}

	@Override
	public Iterator<Item> iterator() {

		return new ListIterator<Item>(first);
	}

}
