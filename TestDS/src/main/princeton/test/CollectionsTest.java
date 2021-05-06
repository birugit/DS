package main.princeton.test;

import java.util.ArrayList;
import java.util.List;

public class CollectionsTest {

	public static void main(String[] args) {
		//Autoboxing
		//Why does the first group of statements print true, but the second false?
		/*
		 * Answer. The second prints false because b1 and b2 are references to different Integer objects. 
		 * The first and third code fragments rely on autoboxing. 
		 * Surprisingly the first prints true because values between -128 and 127 appear 
		 * to refer to the same immutable Integer objects (Java's implementation of valueOf() retrieves a cached values if the integer is between -128 and 127), 
		 * while Java constructs new objects for each integer outside this range.
		 */
		Integer a1 = 127;
		Integer a2 = 127;
		System.out.println(a1 == a2);   // true

		Integer b1 = new Integer(100);
		Integer b2 = new Integer(100);
		System.out.println(b1 == b2);   // false

		Integer c1 = 128;
		Integer c2 = 128;
		System.out.println(c1 == c2);   // false
		
		// How does autoboxing handle the following code fragment?
		//Answer. It results in a run-time error. Primitive type can store every value of their corresponding wrapper type except null.
		Integer a =  null;
	//	int b = a;
		
		//Linkedlist
		//Adding a list to itself. What is the result of the following code fragment?
	//	Answer: stack overflow Error.
		//The Java documents says that
		//"while it is permissible for lists to contain themselves as elements, 
		//extreme caution is advised: the equals and hashCode methods are no longer well defined on a such a list."
			//	List list1 = new ArrayList();
				//List list2 = new ArrayList();
				//list1.add(list2);
				//list2.add(list1);
				//System.out.println(list1.equals(list2));

			//	List list = new ArrayList();
				//list.add(list);
				//System.out.println(list.hashCode());
		
	}

}
