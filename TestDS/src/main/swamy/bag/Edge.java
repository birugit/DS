package main.swamy.bag;

public class Edge implements Comparable<Edge> {

	private int v;
	private int w;
	private double weight;
	
	public Edge(int v, int w, double weight){
		if(v < 0) throw new IndexOutOfBoundsException("Vertex must be non negative");
		if(w < 0) throw new IndexOutOfBoundsException("Vertex must be non negative");
		if(Double.isNaN(weight)) throw new  IllegalArgumentException("weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge that) {
		return Double.compare(this.weight,that.weight);
	}
	
	public double weight(){
		return weight;
	}
	
	public int either(){
		return v;
	}
	
	//return other side of edge
	public int other(int vertex){
		if(vertex == v) return w;
		else if(vertex == w) return v;
		else throw new IllegalArgumentException("IllegalEnd");
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("V:"+v);
		sb.append(" W:"+w);
		sb.append(" weight:"+weight);
		return sb.toString();
	}
	
	public static void main(String[] arg){
		Edge e = new Edge(4,5,5.5);
		System.out.println("Edge:"+e);
	}

}
