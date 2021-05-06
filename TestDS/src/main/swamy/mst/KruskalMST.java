package main.swamy.mst;
import main.swamy.bag.Edge;
import main.swamy.bag.EdgeWeightedGraph;
import main.swamy.linkedqueue.*;
import main.swamy.pq.MinPQ;
import main.swamy.unionfind.UnionFind;

public class KruskalMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12;
	private double weight;
	private LinkedQueue<Edge> mst = new LinkedQueue<Edge>();
	
	/**
	 * Computes a weight of minimum spanning tree (forest) of an edge weighted graph
	 * @param args
	 */
	public KruskalMST(EdgeWeightedGraph G){
		//more efficient to build heap by passing array of edges
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e:G.edges()){
			pq.insert(e);
		}
		
		//run greedy algorithm
		UnionFind uf = new UnionFind(G.V());
		while(!pq.isEmpty() && mst.size() < G.V() -1){
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if(!uf.connected(v, w)){//v - w does not create cycle
				uf.union(v, w); //merge v w
				mst.enqueue(e);
				weight += e.weight();
			}
		}
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public double weight(){
		return weight;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		EdgeWeightedGraph G = new EdgeWeightedGraph();
		KruskalMST kmst = new KruskalMST(G);
		for(Edge e:kmst.edges()){
			System.out.println(e);
		}
		System.out.println("weight:"+kmst.weight());
	}

}
