package main.swamy.minstack;

import java.util.Stack;
/** 
 * Second Approach to track min of a stack:
 * In this approach we create a second stack to track min
 * This approach is efficient, because just we store a single element stack2.
 * 
 */
public class SecondStackMin extends Stack<Integer> {

	private Stack<Integer> stack2;
	public SecondStackMin(){
		stack2 = new Stack<Integer>();
	}
	
	public void push(int value){
		if(value <= min()){
			stack2.push(value);
		}
		super.push(value);
	}
	
	private int min() {
		if(stack2.isEmpty()){
			return Integer.MAX_VALUE;
		}else{
		return stack2.peek();
		}
	}
	
	public Integer pop(){
		int value = super.pop();
		if(value == min() ){
			stack2.pop();
		}
		return value;
	}

	public static void main(String[] args) {
		SecondStackMin sMin = new SecondStackMin();
		sMin.push(9);
		sMin.push(4);
		sMin.push(6);
		sMin.push(2);
		sMin.pop();
	}

}
