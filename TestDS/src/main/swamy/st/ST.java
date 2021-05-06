package main.swamy.st;

import java.util.Iterator;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>,Value> implements Iterable<Key> {
	
	private TreeMap<Key,Value> st;

	public ST(){
		st = new TreeMap<Key,Value>();
	}
	
	public Value get(Key key){
		if(key == null) throw new NullPointerException("Called key with null value");
		return st.get(key);
	}
	
	public void put(Key key,Value value){
		if(key == null)throw new NullPointerException("Called put() with null key");
		if(value == null)st.remove(key);
		else
			st.put(key, value);
	}
	
	public Iterable<Key> keys(){
		return st.keySet();
	}
	
	public int size(){
		return st.size();
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	public static void main(String[] args) {
		ST<String,Integer> st = new ST<String,Integer>();
		st.put("Swamy", 1);
		st.put("Yash", 2);
		st.put("Biru", 3);
		System.out.println("ST:"+st.get("Yash"));
		for(String s:st.keys())
		System.out.println("Iterate:"+st.get(s));
		

	}

	@Override
	public Iterator<Key> iterator() {
		// returns keys
		return st.keySet().iterator();
	}

}
