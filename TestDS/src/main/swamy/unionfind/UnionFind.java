package main.swamy.unionfind;

public class UnionFind {
	private int[] parent;//parent of i
	private byte[] rank;//rank of subtree rooted at i(never more than 31)
	int count;//number of components
	
	//Initialize the empty Union find Data structure
	public UnionFind(int n){
		if(n<0) throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		rank = new byte[n];
		for(int i=0;i<n;i++){
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	//Returns the component identifier for the the component containing site p
	
	public int find(int p){
		//validate(p)
		while(p != parent[p]){
			parent[p] = parent[parent[p]];//path compression by halving
			p = parent[p];
					
		}
		return p;
	}
	
	//Returns the number of components
	public int count()
	{
		return count;
	}
	
	//Returns true if the two sites are in the same component
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	/**
	 * Merges the components containing the site p with components containing the site q
	 * @param args
	 */
	public void union(int p, int q){
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP == rootQ)return;
		//make root of smaller rank pointed to the root of larger rank
		if(rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
		else if(rank[rootP] > rank[rootQ] ) parent[rootQ] = rootP;
		else{
			parent[rootQ] = rootP;
			rank[rootP]++;
		}
		count--;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	10
		4 3
		3 8
		6 5
		9 4
		2 1
		8 9
		5 0
		7 2
		6 1
		1 0
		6 7
		final result:
		parent:[6, 6, 6, 4, 4, 6, 6, 6, 4, 4, 10]
		rank:[0, 0, 1, 0, 1, 0, 2, 0, 0, 0, 0]
		*/
		UnionFind uf = new UnionFind(11);
		int p,q;
		p = 4;q=3;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 3;q=8;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 6;q=5;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 9;q=4;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 2;q=1;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 8;q=9;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 5;q=0;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 7;q=2;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 6;q=1;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 1;q=0;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		p = 6;q=7;
		System.out.println(p+" : "+q+" connected:"+uf.connected(p, q));
		uf.union(p, q);
		System.out.println("components:"+uf.count());
	}

}
