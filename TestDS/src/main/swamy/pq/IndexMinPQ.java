package main.swamy.pq;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer>{
	
	private int maxN;
	private int n;
	private int[] pq;
	private int[] qp; // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
	private Key[] keys;
	
	public IndexMinPQ(int maxN){
		if(maxN < 0) throw new IllegalArgumentException();
		this.maxN = maxN;
		n = 0;
		keys = (Key[]) new Comparable[maxN + 1];
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		
		for(int i=0; i< maxN; i++){
			qp[i] = -1;
		}
	}
	
	public boolean isEmpty(){
		return n==0;
	}
	
	public boolean contains(int i){
		if(i< 0 || i>= maxN)throw new IndexOutOfBoundsException();
		return qp[i] != -1;
	}
	
	public int size(){
		return n;
	}
	
	public void insert(int i, Key key){
		if(i<0 || i>= maxN) throw new IndexOutOfBoundsException();
		if(contains(i)) throw new IllegalArgumentException("Index is Already present in q");
		n++;
		qp[i] = n;
		pq[n] = i;
		keys[i] = key;
		swim(n);
	}
	
	/**
	 * General help functions
	 * 
	 */
	  /***************************************************************************
	    * General helper functions.
	    ***************************************************************************/
	    private boolean greater(int i, int j) {
	        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	    }

	    private void exch(int i, int j) {
	        int swap = pq[i];
	        pq[i] = pq[j];
	        pq[j] = swap;
	        qp[pq[i]] = i;
	        qp[pq[j]] = j;
	    }


	/**
	 * Heap helper functions
	 * @param k
	 */
	private void swim(int k) {
		while(k > 1 && greater(k/2,k)){
			exch(k, k/2);
				k = k/2;
			
		}
		
	}

	  private void sink(int k) {
	        while (2*k <= n) {
	            int j = 2*k;
	            if (j < n && greater(j, j+1)) j++;
	            if (!greater(k, j)) break;
	            exch(k, j);
	            k = j;
	        }
	    }

	  
	public static void main(String[] args) {
		 // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }
        
        // print each key using the iterator
        for (int i : pq) {
            System.out.println(i + " " + strings[i]);
        }

	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return new HeapIterator();
	}

	   private class HeapIterator implements Iterator<Integer> {
	        // create a new pq
	        private IndexMinPQ<Key> copy;

	        // add all elements to copy of heap
	        // takes linear time since already in heap order so no keys move
	        public HeapIterator() {
	            copy = new IndexMinPQ<Key>(pq.length - 1);
	            for (int i = 1; i <= n; i++)
	                copy.insert(pq[i], keys[pq[i]]);
	        }

	        public boolean hasNext()  { return !copy.isEmpty();                     }
	        public void remove()      { throw new UnsupportedOperationException();  }

	        public Integer next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            return copy.delMin();
	        }
	    }
	   
	   /**
	     * Removes a minimum key and returns its associated index.
	     * @return an index associated with a minimum key
	     * @throws NoSuchElementException if this priority queue is empty
	     */
	    public int delMin() {
	        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
	        int min = pq[1];
	        exch(1, n--);
	        sink(1);
	        assert min == pq[n+1];
	        qp[min] = -1;        // delete
	        keys[min] = null;    // to help with garbage collection
	        pq[n+1] = -1;        // not needed
	        return min;
	    }

}
