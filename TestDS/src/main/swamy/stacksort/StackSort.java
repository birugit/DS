package main.swamy.stacksort;

import java.util.Stack;
/**
 * 
 * @author Swamy
 *O(N2) time and O(N) space
 *we can also use merge or quick sort, if tehy accept two stacks
 *Sol: pop item from s1 and compare with items on s2 top,
 *     if s2 top is greater push to s1
 *     push tmp to s2, repeat the process till empty
 */
public class StackSort {

	Stack<Integer> s1, s2;
	public StackSort() {
	s1 = new Stack<Integer>();
	s2 = new Stack<Integer>();
	}

	public static void main(String[] args) {
		StackSort ss = new StackSort();
		ss.s1.push(7);
		ss.s1.push(10);
		ss.s1.push(5);
		System.out.println("S1 Before Sort:"+ss.s1.toString());
		ss.s2.push(1);
		ss.s2.push(3);
		ss.s2.push(8);
		ss.s2.push(12);
		System.out.println("S2 Before Sort:"+ss.s2.toString());
		ss.s2 = ss.sort(ss.s1);
		System.out.println("Sorted Stack:"+ss.s2.toString());

	}

	private Stack<Integer> sort(Stack<Integer> s) {
		
		while(!s.isEmpty()){
			int tmp = s.pop();
			while(!s2.isEmpty() && s2.peek() > tmp){
				s.push(s2.pop());
			}
			s2.push(tmp);
		}
		return s2;
	}

}
