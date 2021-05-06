package main.swamy.arraystack;

public class ThreeStackFlexibleDivision {
		static int numberOfStacks = 3;
		static int defaultSize = 4;
		static int totalSize = defaultSize * numberOfStacks;
		static StackData[] stacks = {new StackData(0,defaultSize),
				new StackData(defaultSize,defaultSize),
				new StackData(2*defaultSize,defaultSize)
			
		};
		static int[] buffer = new int[totalSize];
		
		public static void main(String[] args) throws Exception {
		push(0,10);
		push(1,20);
		push(2,30);
	}
		
		public static int numberOfElements(){
			return stacks[0].size + stacks[1].size + stacks[2].size;
		}
		
		public static int nextElement(int index){
			if(index + 1 == totalSize) return 0;
			else
				return index +1;
		}
		
		public static int previousElement(int index){
			if(index == 0) return totalSize -1;
			else
				return index - 1;
		}
		
		public static void shift(int stackNum){
			StackData stack = stacks[stackNum];
			if(stack.size > stack.capacity){
				int nextStack = (stackNum + 1) % numberOfStacks;
				stack.capacity++;
			}
			//shift elements in reverse order
			for(int i = (stack.start + stack.capacity - 1) %  totalSize;
			stack.isWithinStack(i, totalSize);
					i = previousElement(i)){
						buffer[i] = buffer[previousElement(i)];
					}
			
			buffer[stack.start] = 0;
			stack.start = nextElement(stack.start);//move stack start
			stack.pointer = nextElement(stack.pointer);//move pointer
			stack.capacity--;//return capacity to original
		}
		/*Expand stack by shifting over the stacks */
		public static void expand(int stackNum){
		shift((stackNum + 1) % numberOfStacks);
		stacks[stackNum].capacity++;
		}
		
		public static void push(int stackNum, int value)throws Exception{
			StackData stack = stacks[stackNum];
			/*check that we have spaces*/
			if(stack.size >= stack.capacity){
				if(numberOfElements() >= totalSize){
					throw new Exception("Out of Space");
				}else{//just need to shift things around
					expand(stackNum);
					
				}
				stack.size ++;
				stack.pointer = nextElement(stack.pointer);
				buffer[stack.pointer] = value;
			}
		}

}
