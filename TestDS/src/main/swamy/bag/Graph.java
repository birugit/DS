package main.swamy.bag;

import java.util.Stack;

public class Graph {
	
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	
	public Graph(int V){
		if(V < 0)throw new IllegalArgumentException();
		this.V = V;
		this.E = 0;
		 adj = (Bag<Integer>[]) new Bag[V];
		for(int v=0; v < V;v++){
			adj[v] = new Bag<Integer>();
		}
		
	}
	
	public Graph(Graph G){
		this(G.V());
		this.E = G.E();
		Stack<Integer> reverse = new Stack<Integer>();
		for(int v= 0; v< G.V(); v++){
		
		for(int w:G.adj[v]){
			reverse.push(w);
		}
		for(int w:reverse){
			G.adj[v].add(w);
		}
		}
	}

	public Graph() {
		//this(13);//
		//g.E = 13;
		//13
		//13
	/*	addEdge(0, 5);
		addEdge(4, 3);
		addEdge(0, 1);
		addEdge(9, 12);
		addEdge(6, 4);
		addEdge(5, 4);
		addEdge(0, 2);
		addEdge(11, 12);
		addEdge(9, 10);
		addEdge(0, 6);
		addEdge(7, 8);
		addEdge(9, 11);
		addEdge(5, 3);*/
		
		//addEdge();
		this(6);
		//6
		//8
		addEdge(0, 5);
		addEdge(2, 4);
		addEdge(2, 3);
		addEdge(1, 2);
		addEdge(0, 1);
		addEdge(3, 4);
		addEdge(3, 5);
		addEdge(0, 2);
		System.out.println("Degree:"+degree(0));
		System.out.println("Adjacent:"+adj(0));
	}

	private int E() {
		
		return E;
	}

	public int V() {
		
		return V;
	}
	
	public void addEdge(int v, int w){
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
		
	}
	
	public int degree(int v){
		validateVertex(v);
		return adj[v].size();
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public void validateVertex(int v){
		if(v < 0 | v >= V) throw new IndexOutOfBoundsException();
		
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(V+"vertices "+E+"Edges");
		sb.append("\n");
		for(int v=0; v< V; v++){
			sb.append(v+": ");
			 for(int w:adj[v]){
				 sb.append(w+" ");
			 }
			 sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		
		
		System.out.println(g);
	//	System.out.println(g.adj(0));
	//	System.out.println(g.degree(9));
		
		Graph gg = new Graph(g);
		System.out.println(gg);

	}

}
