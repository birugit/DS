package main.swamy.arraystack;

public class ArrayStack {
	/**
	 * Create 3 stacks in single array
	 * Two approaches
	 * 1. Fixed Division Approach
	 * 2. Flexible Division Approach
	 * 
	 */
	/*
	 * 1. Fixed Division  approach 
	 */
	int stackSize = 100;
	int[] buffer = new int[stackSize * 3];//0 to 99, 100 to 199 and 200 to 299 are 3 arrays
	int[] stackPointer = {-1, -1, -1};//stack pointer to track the top element
	
	
	void push(int stackNum, int value) throws Exception{
		/*check if we save space*/
		if(stackPointer[stackNum] + 1 >= stackSize){//Last element
			throw new Exception("Out of space");
		}
		stackPointer[stackNum]++;//increment stack pointer like 0,1, so on
		//add value
		buffer[absTopOfStack(stackNum)] = value;	
	}

	private int absTopOfStack(int stackNum) {
		return stackNum * stackSize + stackPointer[stackNum];
	}

	public static void main(String[] args) throws Exception {
		
		ArrayStack as = new ArrayStack();
		as.push(0, 2);
		as.push(0, 4);
		as.push(2, 3);

	}

}
