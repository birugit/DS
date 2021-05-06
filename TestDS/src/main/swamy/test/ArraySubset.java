package main.swamy.test;

import java.util.TreeMap;

/**
 * 
 * //Given an array arr and a number n, you have to find whether there exist a
 * subset in arr whose sum is n. You have to print length of the subset.
 *  1.There exists only one subset like that 
 *  2. All number in arr are positive
 */

public class ArraySubset {
	TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>();
	int k = 6;

	void calSum(int b, int e, int a[]) {

		int tempSum = 0;
		for (int i = b; i <= e; i++) {
			tempSum = tempSum + a[i];
		}
		if (tempSum == k) {

			tmap.put(e - b + 1, e - b + 1);
		}

	}

	int function(int beg, int end, int arr[]) {
		if (beg <= end) {
			calSum(beg, end, arr);
			function(beg + 1, end - 1, arr);
			function(beg + 1, end, arr);
			//function(beg, end - 1, arr);
		} else {

			return 0;

		}

		return 0;

	}

	void seeResult() {
		System.out.println(tmap.lastKey().toString());

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

		ArraySubset id = new ArraySubset();
		id.function(0, array.length - 1, array);
		id.seeResult();

	}

}
