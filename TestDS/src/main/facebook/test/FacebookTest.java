package main.facebook.test;

import java.util.HashSet;
import java.util.Set;

public class FacebookTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/** 
		Given many coins of 3 different face values, print the combination sums of the coins up to 1000. Must be printed in order. 

		eg: coins(10, 15, 55) 
		print: 
		10 
		15 
		20 
		25 
		30 
		. 
		. 
		. 
		1000 
		*/
		int c1 =10, c2=15, c3=55;
		//printSumOfCombination(c1,c2,c3);
		
		
		
		//convert number to English
		int[] t = {0, 1, 5, 10, 11, 15, 17, 19, 21, 45, 99, 100, 120, 123, 1000, 1001, 1101, 10000, 120007,
                123456, 112233445};

        for(int i = 0; i < t.length; i++) {
            System.out.println(convert(t[i]));
        }
	}
	
	

	 private static final String[] teen_or_less = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
			    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

			    private static final String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

			    private static final String[] thousands = {"", "thousand", "million", "billion"};

			    private static String convert3(int i) {
			        String ans = "";
			        if(i > 0) {
			            int mod100 = i % 100;
			            if(mod100 < 20) {
			                ans = FacebookTest.teen_or_less[mod100];
			            } else {
			                ans = FacebookTest.teen_or_less[i % 10];
			                ans = FacebookTest.tens[(i/10)%10] + " " + ans;
			            }

			            if(i/100 > 0) {
			                ans = FacebookTest.teen_or_less[i/100] + " hundred " + ans;
			            }
			        }

			        return ans.trim();
			    }

			    public static String convert(int i) throws Exception {
			        int level = 0;

			        if(i < 0) {
			            throw new Exception("Negative!");
			        }

			        if(i == 0) {
			            return "zero";
			        }

			        String ans = "";

			        while(i > 0) {
			            if(level >= FacebookTest.thousands.length) {
			                throw new Exception("Too large");
			            }

			            String t = FacebookTest.convert3(i % 1000);
			            i /= 1000;
			            ans = t + " " + FacebookTest.thousands[level] + " " + ans;
			            level++;
			        }

			        return ans.trim();
			    }

	private static void printSumOfCombination(int c1, int c2, int c3) {
		Set<Integer> sums = new HashSet<Integer>();
		sums.add(0);
		for(int sum=1; sum <= 1000; sum++){
			if(sums.contains(sum - c1 ) || sums.contains(sum - c2) || sums.contains(sum - c3) ){
			System.out.println(sum);
			sums.add(sum);
			}
		}
		
	}

}
