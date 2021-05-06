package main.swamy.towersofhanoi;

import java.util.Stack;

public class Towers {
	Stack<Integer> disks;
	int index;
	public Towers(int i){
		disks = new Stack<Integer>();
		index = i;
	}
	
	public int index(){
		return index;
	}

	public String toString(){
		return "Index:"+index +"disks:"+disks.toString();
	}
	public static void main(String[] args) {
		
		int n = 3;
		Towers[] tower = new Towers[n];
		//create 3 towers
		for(int i = 0; i<3; i++){
			tower[i]= new Towers(i);
		}

		//push the disks
		for(int i = n-1; i >= 0; i --){
			tower[0].add(i);
		}
		tower[0].moveDisks(n, tower[2], tower[1]);
	}

	private void moveDisks(int n, Towers destination, Towers buffer) {
		//System.out.println("n outside:"+n);
		if(n > 0){
			//System.out.println("n  1 inside:"+n);
			/* move top n - 1 disks from origin to buffer, using destination * as a buffer. */
			moveDisks(n - 1, buffer, destination);
			/* move top from origin to destination*/
			moveTopTo(destination);
			/* move top n - 1 disks from buffer to destination, using * origin as a buffer. */
			buffer.moveDisks(n - 1, destination, this);
			////System.out.println("n  2inside:"+n);
		}
		
	}

	private void moveTopTo(Towers destination) {
		int top = disks.pop();
		destination.add(top);
		System.out.println("moving disk"+ top + ": tower" +index()+" --> tower"+ destination.index());
		
	}

	private void add(int d) {
		if(!disks.isEmpty() && disks.peek()<= d){
			System.out.println("Error placing disk"+d);
		}else{
			disks.push(d);
		}
		
		
	}

}
