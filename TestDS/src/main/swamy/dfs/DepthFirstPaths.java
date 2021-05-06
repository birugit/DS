package main.swamy.dfs;

import main.swamy.bag.Graph;
import main.swamy.linkedstack.LinkedStack;

public class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	public DepthFirstPaths(Graph g, int s){
		this.s = s;
		edgeTo = new int[g.V()];
		marked = new boolean[g.V()];
		dfs(g,s);
	}
	

	private void dfs(Graph g, int v) {
		marked[v] = true;
		for(int w: g.adj(v)){
			if(!marked[w]){
				edgeTo[w] = v;
				dfs(g,w);
			}
		}
		
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
	LinkedStack<Integer> path = new LinkedStack<Integer>();
	if(!hasPathTo(v)){
		return null;
	}
	for(int x = v; x != s; x = edgeTo[x])
		path.push(x);
	path.push(s);
	return path;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph();
		int s = 0;
		DepthFirstPaths dfs = new DepthFirstPaths(g, s);
		
		for(int v = 0; v <g.V();v++){
			if(dfs.hasPathTo(v)){
				System.out.println("s "+s+" v "+v);
				for(int x: dfs.pathTo(v)){
					
				
				if(s == x){
					System.out.print(x);
				}else{
					System.out.print(" - "+x);
				}
				}
				System.out.println();
			}
			else{
				System.out.println("s "+s+" v"+"not connected");
			}
		}
	}

}
