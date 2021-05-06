package main.swamy.bag;

import java.util.Iterator;

public class EdgeWeightedGraph {
	
	private Bag<Edge>[] adj;
	private int V;
	private int E;
	
	public EdgeWeightedGraph(int V){
		if(V < 0) throw new IllegalArgumentException("Invalid String");
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for(int v=0; v<V; v++){
			adj[v] = new Bag<Edge>();
		}
	}
	
	public EdgeWeightedGraph(){

	//	 *  % java EdgeWeightedGraph tinyEWG.txt 
		/*
8
16
4 5 0.35
4 7 0.37
5 7 0.28
0 7 0.16
1 5 0.32
0 4 0.38
2 3 0.17
1 7 0.19
0 2 0.26
1 2 0.36
1 3 0.29
2 7 0.34
6 2 0.40
3 6 0.52
6 0 0.58
6 4 0.93
		 */
		this(8);
		Edge e = new Edge(4,5,0.35);
		addEdge(e);
		Edge e1 = new Edge(4,7,0.37);
		addEdge(e1);
		Edge e2 = new Edge(5,7,0.28);
		addEdge(e2);
		
		Edge e3 = new Edge(0,7,0.16);
		addEdge(e3);
		
		Edge e4 = new Edge(1,5,0.32);
		addEdge(e4);
		Edge e5 = new Edge(0,4,0.38);
		addEdge(e5);
		Edge e6 = new Edge(2,3,0.17);
		addEdge(e6);
		Edge e7 = new Edge(1,7,0.19);
		addEdge(e7);
		Edge e8 = new Edge(0,2,0.26);
		addEdge(e8);
		Edge e9 = new Edge(1,2,0.36);
		addEdge(e9);
		Edge e10 = new Edge(1,3,0.29);
		addEdge(e10);
		Edge e11 = new Edge(2,7,0.34);
		addEdge(e11);
		Edge e12 = new Edge(6,2,0.40);
		addEdge(e12);
		Edge e13 = new Edge(3,6,0.52);
		addEdge(e13);
		Edge e14 = new Edge(6,0,0.58);
		addEdge(e14);
		Edge e15 = new Edge(6,4,0.93);
		addEdge(e15);
		
		// *  8 16
		/* *  0: 6-0 0.58000  0-2 0.26000  0-4 0.38000  0-7 0.16000  
		 *  1: 1-3 0.29000  1-2 0.36000  1-7 0.19000  1-5 0.32000  
		 *  2: 6-2 0.40000  2-7 0.34000  1-2 0.36000  0-2 0.26000  2-3 0.17000  
		 *  3: 3-6 0.52000  1-3 0.29000  2-3 0.17000  
		 *  4: 6-4 0.93000  0-4 0.38000  4-7 0.37000  4-5 0.35000  
		 *  5: 1-5 0.32000  5-7 0.28000  4-5 0.35000  
		 *  6: 6-4 0.93000  6-0 0.58000  3-6 0.52000  6-2 0.40000
		 *  7: 2-7 0.34000  1-7 0.19000  0-7 0.16000  5-7 0.28000  4-7 0.37000*/
	}

	public void addEdge(Edge e){
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	
	public Iterable<Edge> edges(){
		Bag<Edge> list = new Bag<Edge>();
		for(int v=0; v<V; v++){
			int selfLoops = 0;
			for(Edge e:adj(v)){
				if(e.other(v) > v){
					list.add(e);
				}
				else if(e.other(v) == v){
					if(selfLoops%2 == 0) list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	public static void main(String[] args) {
		EdgeWeightedGraph g = new EdgeWeightedGraph();
		System.out.println(g);
		
	//	Iterator i = (Iterator<Edge>) g.edges();
		//while(i.hasNext())
		//System.out.println(i.next());

	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(V+" "+E +"\n");
		System.out.println();
		for(int v = 0; v<V;v++){
			sb.append(v+": ");
			for(Edge e:adj(v)){
				sb.append(e+" "+"\n");
			}
			System.out.println();
		}
		return sb.toString();
	}

}
