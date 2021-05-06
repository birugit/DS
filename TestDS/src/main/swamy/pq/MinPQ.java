package main.swamy.pq;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<Key> implements Iterable<Key> {
	public class HeapIterator implements Iterator<Key> {
		//create a new PQ
		private MinPQ<Key> copy;
		public HeapIterator(){
			if(comparator == null) copy = new MinPQ(size());
			else
				copy = new MinPQ(size(),comparator);
			
			for(int i = 1; i<= n ;i++){
				copy.insert(pq[i]);
			}
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return !copy.isEmpty();
		}

		@Override
		public Key next() {
			// TODO Auto-generated method stub
			if(!hasNext()) throw new NoSuchElementException();
			
			return copy.delMin();
		}

	}

	private Key[] pq; //stores items at indices 1 to n
	private int n; //number of items on PQ
	private Comparator<Key> comparator;	//optional comparator
	
	/**
	 * Initializes the empty priority que with the given capacity
	 * @param initCapacity is the initial capacity of Priority Queue
	 * 
	 */
	public MinPQ(int initialCapacity){
		pq = (Key[])new Object[initialCapacity + 1];
		n = 0;
	}
	
	/**
	 * Initialize an empty priority queue
	 * @param args
	 */
	
	public MinPQ(){
		this(1);
	}
	
	/**
	 * Initialize the empty PQ with given capacity and comparator
	 * @param args
	 */
	public MinPQ(int initCapacity, Comparator<Key> comparator){
		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}
	
	/**
	 * Initialize empty PQ with given comparator
	 * @param args
	 */
	public MinPQ(Comparator<Key> comparator){
		this(1, comparator);
	}
	
	/**
	 * Initialize the PQ wiht keys from Array Keys
	 * Takes time proportional to the number of keys using sink based heap construction
	 * @param keys the array of keys
	 */
	public MinPQ(Key[] keys){
		n = keys.length;
		for(int i=0;i<n;i++){
			pq[i+1] = keys[i];
		}
		for(int k=n/2;k>=1;k--){
			sink(k);
		}
		
		assert isMinHeap();
	}
	
	
	
	
	private boolean isMinHeap() {
		// TODO Auto-generated method stub
		return isMinHeap(1);
	}
	
	/**
	 * return true if PQ is empty
	 * @param k
	 * @return
	 */
	public boolean isEmpty(){
		return n == 0;
	}
	
	/**
	 * returns the number of keys on PQ
	 * @param k
	 * @return
	 */
	public int size(){
		return n;
	}
	
	/**
	 * returns the smallest key of PQ
	 * @param k
	 * @return
	 */
	public Key min(){
		if(isEmpty()) throw new NoSuchElementException("PQ underflow");
		return pq[1];
	}
	
	/**
	 * double  the size of heap
	 * @param k
	 * @return
	 */
	public void resize(int capacity){
		assert capacity > n;
		Key[] temp = (Key[]) new Object[capacity];
		for(int i =0;i<=n;i++){
			temp[i] = pq[i];
		}
		pq= temp;
	}
	
	/**
	 * Insert key x to pq
	 * @param k
	 * @return
	 */
	public void insert(Key x){
		if(n == pq.length - 1) resize(2 * pq.length);
		pq[++n] = x;
		swim(n);
		assert isMinHeap();
	}
	
	/**
	 * Delete min
	 * @param k
	 * @return
	 */
	public Key delMin(){
		if(isEmpty())throw new NoSuchElementException("PQ underflow");
		exch(1,n);
		Key min = pq[n--];
		sink(1);
		pq[n + 1] = null;//avoid loitering and help garbage collection
		if((n > 0) && (n == (pq.length -1)/4)) resize(pq.length/2);
		assert isMinHeap();
		return min;
	}
	private boolean isMinHeap(int k) {
		if(k > n)return true;
		int left = 2 * k;
		int right = 2 * k +1;
		if(left <= n && greater(k,left) )return false;
		if(right <=n && greater(k,right)) return false;
		return isMinHeap(left) && isMinHeap(right);
	}

	//Heap Helper functions
	private void swim(int k) {
		while(k > 1 && greater(k/2,k)){
			exch(k,k/2);
			k = k/2;
		}
		
	}

	private void sink(int k) {
		while(2*k <= n){
			int j= 2*k;
			if(j<n && greater(j,j+1)) j++;
			if(!greater(k,j))break;
			exch(k,j);
			k = j;
			
		}
		
	}

	//Helper functions
	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	private boolean greater(int i, int j) {
		if(comparator == null){
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
		}else{
			return comparator.compare(pq[i], pq[j]) > 0;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinPQ<String> pq = new MinPQ<String>();
		pq.insert("P");
		pq.insert("Q");
		pq.insert("E");
		pq.insert("X");
		pq.insert("A");
		pq.insert("M");
		pq.insert("P");
		pq.insert("L");
		pq.insert("E");
		System.out.println("Min:"+pq.min());
		//System.out.println("Min:"+pq.delMin());
		//System.out.println("Min:"+pq.min());
		Iterator it = pq.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}

	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return (Iterator<Key>) new HeapIterator();
	}

}
