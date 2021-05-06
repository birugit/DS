package main.swamy.nonrecdfs;

import java.util.Iterator;
import java.util.Stack;

import main.swamy.bag.Graph;
/*
 * 6
8
0 5
2 4
2 3
1 2
0 1
3 4
3 5
0 2
 */
public class NonRecursiveDFS {
	private boolean[] marked;
	int[] edgeTo;
	private int s;
	

	public NonRecursiveDFS(Graph G, int s) {
		marked = new boolean[G.V()];
		
		edgeTo = new int[G.V()];
		
		//dfs path
		this.s = s;
		
		// to be able to iterate over each adjacency list, keeping track of which
        // vertex in each adjacency list needs to be explored next
		Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
		for(int v=0;v<G.V();v++)
			adj[v] = G.adj(v).iterator();
		
		//depth first search using explicit stack
		Stack<Integer> stack = new Stack<Integer>();
		marked[s] = true;
		stack.push(s);
		
		while(!stack.isEmpty()){
			int v = stack.peek();
			if(adj[v].hasNext()){
				int w = adj[v].next();
				if(!marked[w]){
					marked[w] = true;
					
					//This is for DFS path
					edgeTo[w] = v;
					stack.push(w);
					System.out.println("dfs:"+w);
				}
			}
			else{
				System.out.println("Done:"+v);
				stack.pop();
			}
		}
		
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		for(int x=v; x != s; x=edgeTo[x])
			path.push(x);
		path.push(s);
		
		return path;
	}
	public static void main(String[] args) {
		Graph g = new Graph();
		int s = 0;
		NonRecursiveDFS dfs = new NonRecursiveDFS(g, s);
		for(int v=0; v<g.V();v++)
			System.out.println(dfs.marked(v)+" ");
		System.out.println();
		
		for(int v=0; v<g.V();v++){
			if(dfs.hasPathTo(v)){
			System.out.print(s+" to"+v +" : ");
			for(int x:dfs.pathTo(v)){
				if(x == s)
					System.out.print(x);
				else
					System.out.print("-"+x);
			}
			System.out.println();
		}else{
			System.out.println(s+" Not connected "+v);
		}
		}

	}

}
