package main.swamy.minstack;

import java.util.Stack;
/**
 * 
 * @author Swamy
 *In this approach one, create a new NodeStack with min value to track min always
 *If stack is large lot of memory consumed,
 *we have to store new Object each time, if stack size is n, n number of objects iwth value n min
 */
public class StackWithMin extends Stack<NodeWithMin> {

	public void push(int value){
		int newMin = Math.min(value, min());
		super.push(new NodeWithMin(value, newMin));
		
	}
	private int min() {
		if(this.isEmpty()){
			return Integer.MAX_VALUE;//Error
		}
		return peek().min;
	}
	public static void main(String[] args) {
		StackWithMin min =  new StackWithMin();
		min.push(4);
		min.push(1);
		min.push(2);
		min.push(3);
		min.push(6);
		
		System.out.println("Min:"+min.min());

	}


}
