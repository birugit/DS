package main.swamy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
	
System.out.println("Main Class");
	
//Find string is unique
  //1 using boolean array to identify duplicate
	//2 Bit vector 
	//3 Compare each char with other but its takes O(n2), O(1)
	//4 change input type and sort it, carefull that some sorts take more space

//	String uniqueChar = "aAsos";
//	boolean isUniq = false;
//	isUniq = isUniqueChar(uniqueChar);
	
//	System.out.println("is the String unique:"+ isUniq);
	
//	isUniq = isUniqueCharbit(uniqueChar);
	//System.out.println("is the String unique Bit vector:"+ isUniq);
	
	
	/*
	 * Reverse a String
	 */
//	String reverse = "I am great";
//	revreseMethod(reverse);
	
	/**anagram , Find permutation of a string with other string
	 * 
	 */
	/*1
	 * By sorting strings
	 * This approach is not efficient. but simple to implement
	 * ask is space significant? if yes.. not anagrams
	 */
	//boolean permutation = false;
	//permutation = isPremutation("dog", "  god");
	//System.out.println("Source n Target are anagrams:" +permutation);
	
	/*
	 * 2. Implement by counting characters repeated
	 */
	
	//permutation = isPremutationByCount("dog", "god");
	//System.out.println("Source n Target are anagrams:" +permutation);
	
	/**Replace spaces in string with %20
	 * 
	 * 
	 */
	/*
	 * 1 count spaces and create new char array with required size and replace from end of array
	 */
	//replaceSpaces("Hello How are you");
	
	/**
	 * Compress the string
	 */
	/*Amazon: Write a program to compress a string and send it a over a network and decompress it on the receivers end
	 * Replace the repeated characters with count
	 *                 2 
	 *1.  runtime O(p+k  ) p is size of String n k is number of char in sequence
	 * Its slow because concatenation operates in O(n2) times in string
	 */
	//compressSimple("aaabcccccdzzzbbbb");
	
	/*
	 * 2. Use StringBuffer takes O(N)
	 */
	compressUseStringBuffer("aaabcccddxxxaabbb");
	
	/*
	 * 3. Without StringBuffer
	 * takes O(N) 
	 */
	//compressUseCharArray("ssdddfgggxzz");
	
	/**
	 * set zeros to row and column of a matrix having a zero
	 */
	/*
	 * First mark row , column where zero is there, and in second scan make entire row n column zero
	 */
	int[][] matrix = {{1,1,0,1},
					  {1,1,1,1},
					  {0,1,1,0}};
	//setZero(matrix);
	
	/**
	 * isSubstring, Given two string find s1 is substring of s2 and s2 is substring of s1
	 */
	/*
	 * eg: s1=waterbattle s2=erbattlerwat
	 *   x=wat and y= erbattler -> s1=xy  ->s2=yx
	 *   x is always substring of xyxy -> s2 is substring of s1s1
	 */
	boolean substring = false;
	substring = isRotation("waterbottle", "erbottlewat");
	System.out.println("isSubstring:"+substring);
	
	/**
	 * Given two strings needle and haywards that contains ASCII characters,
	 * write an algorithm to output a list of 0-based indices of the occurances of all anagrams of needle in haystacks


	 */
	String needle = "abc";
			String haystack = "dabcabebacab";
	//List l = findIndicesSort(haystack,needle);
	//System.out.println("List:"+l);
	
	/**
	 * Given an image represented by an NxN matrix, where each pixel in the image is 4
bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
	 */
	int [][] matrix_rotate = {	{1,2,3,4},
								{5,6,7,8},
								{9,10,11,12},
								{13,14,15,16}
			
						};
	// rotate(matrix_rotate,4);


	//Add integers without arithamatic operator
	int sum = Add(2,3);
	System.out.println("sum :"+sum);

	}//end main
	
	static int Add(int x, int y){
	    while(y!=0){
	        int carry = (x&y)<<1;
	        x=x^y;
	        y=carry;
	    }
	    return x;
	}
	private static void rotate(int[][] matrix_rotate, int n) {
		//print
		System.out.println("\n");
		for(int j=0;j<n;j++){
			for(int k=0;k<n;k++){
				System.out.print("  "+matrix_rotate[j][k]);
			}
			System.out.println("\n");
		}
		
		for(int layer=0;layer<n/2;++layer){
			int first = layer;
			int last = n-1-layer;
			System.out.println("first:"+first +"  last:"+last);
			for(int i=0;i<first;++i){
				int offset = i - first;
				System.out.println("offset:"+offset+" i:"+i);
				//save top
				int top = matrix_rotate[first][i];
				
				//left --> top
				matrix_rotate[first][i] = matrix_rotate[last - offset][first];
				System.out.println("last - offset:"+ (last - offset)+" first:"+first);
				//bottom --> left
				matrix_rotate[last - offset][first] = matrix_rotate[last][last - offset];
				
				//right --> bottom
				matrix_rotate[last][last - offset] = matrix_rotate[i][last];
				
				//top --> right
				matrix_rotate[i][last] = top;
			}
		}
		
		//print
		System.out.println("\n after rotation");
		for(int j=0;j<n;j++){
			for(int k=0;k<n;k++){
				System.out.print("  "+matrix_rotate[j][k]);
			}
			System.out.println();
		}
		
	}

	public static List<Integer>findIndicesSort(String haystack, String needle){
		List<Integer> anagramIndices=new ArrayList<Integer>();
		char[] haystackChars=haystack.toCharArray();
		char[] needleChars=needle.toCharArray();
		Arrays.sort(needleChars);
		char[] movingWindow;
		for(int i=0;i<haystackChars.length-needleChars.length+1;i++){
			movingWindow=Arrays.copyOfRange(haystackChars,i,i+needleChars.length);
			Arrays.sort(movingWindow);
			if(Arrays.equals(needleChars, movingWindow))
				anagramIndices.add(i);
		}
		return anagramIndices;
	}

	private static boolean isRotation(String string1, String string2) {
		int len = string1.length();
		int len2 = string2.length();
		System.out.println("s1 len:"+len +" s2 len:"+len2);
		if(len == len2 && len > 0){
			String s1s1 = string1 + string1;
			return isSubstring(s1s1,string2);// implement this method
		}
		return false;
	}

	private static boolean isSubstring(String s1s1, String string2) {
		
		return s1s1.contains(string2);
	}

	private static void setZero(int[][] matrix) {
		boolean[] row = new boolean[matrix.length];
		boolean[] column = new boolean[matrix[0].length];
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				if(matrix[i][j] == 0){
				row[i] = true;
				column[j] = true;
				}
			}
			
			
		}
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				if(row[i]||column[j]){
					matrix[i][j]=0;
				}
			}
		}
		
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
		
		
	}

	private static void compressUseCharArray(String string) {
		System.out.println("Before compress size:"+string.length());
		int size = countCompress(string);
		if(size<string.length()){
			System.out.println("Actual is good:"+string);
		}
		
		char[] array = new char[size];
		int index=0;
		int count = 1;
		char last = string.charAt(0);
		for(int i=1; i<string.length() ;i++){
			if(string.charAt(i)==last){
				count++;
			}else{
				index = setChar(array,last, index, count );
				last = string.charAt(i);
				count = 1;
				
			}
		}
		
		for(int i=0;i<array.length;i++){
			
		
		System.out.print(array[i]);
		}
	}

	private static int setChar(char[] array, char last, int index, int count) {
		array[index] = last;
		index++;
		
		//convert count to a string and then to charArray
		char[] cnt = String.valueOf(count).toCharArray();
		for(char c:cnt){
			array[index]=c;
			index++;
		}
		
		
		return index;
	}

	private static void compressUseStringBuffer(String string) {
		
		System.out.println("before compress String:"+string);
		System.out.println("before compress size:"+string.length());
		int size = countCompress(string);
		
		if(string.length() <size)
		{
			System.out.println("Actual string is having less size:"+ string);
		}
		int count = 1;
		char last = string.charAt(0);
		StringBuffer newString = new StringBuffer();
		for(int i=1; i<string.length();i++){
			if(string.charAt(i) == last){
				count++;
			}else{
				
				newString.append(last);
				newString.append(count);
				last = string.charAt(i);
				count = 1;
			}
		}
		newString.append(last);
		newString.append(count);
		System.out.println("Compressed SB:"+newString);
		deCompressUseStringBuffer(newString.toString());
	}

	private static int countCompress(String string) {
		char last = string.charAt(0);
		int count = 1;
		int size = 0 ;
		for(int i = 1; i<string.length(); i++){
			if(string.charAt(i) == last){
				count++;
			
			}else{
				
				last = string.charAt(i);
				size += 1 + String.valueOf(count).length(); 
				count = 1;
			}
		}
		
		size += 1+String.valueOf(count).length();
		System.out.println("After Compress size:"+size);
		return size;
	}

	private static void deCompressUseStringBuffer(String string) {
		
		System.out.println("before decompress size:"+string.length());
		
		char last = string.charAt(0);
		int charCompSize = 1;
		StringBuffer newString = new StringBuffer();
		for(int i=1; i<string.length();i++){
	
			if(i%2 == 0){
				last = string.charAt(i);
			}else{
				charCompSize = Character.getNumericValue(string.charAt(i));
			}		
			for(int j=0;j<charCompSize;j++){
				newString.append(last);
				
			}
			charCompSize = 0;
	
		}
	
		System.out.println("After decompress size:"+newString.length());
		System.out.println("Decompressed SB:"+newString);
	}
	private static void compressSimple(String source) {
		char last = source.charAt(0);
		int count = 1;
		String myStr = "";
		for(int i=0; i<source.length(); i++){
			if(source.charAt(i) == last){
				count++;
			}else{
				myStr += last+ ""+count;
				count = 1;
				last = source.charAt(i);
			}
			
		}
		myStr+=last+""+count;
		System.out.println("compressed String:"+myStr);
		
	}

	private static void replaceSpaces(String source) {
		int count = 0, newCount;
		for(int i = 0; i< source.length();i++){
			if(source.charAt(i) == ' '){
				 count++;
			}
		}
		newCount = source.length() + count * 2;
		char[] newCharArray = new char[newCount];
		for(int i=source.length()-1; i>=0;i--){
			if(source.charAt(i) == ' '){
				newCharArray[newCount - 1] = '0';
				newCharArray[newCount - 2] = '2';
				newCharArray[newCount - 3] = '%';
				newCount = newCount - 3;
			}else{
				newCharArray[newCount - 1] = source.charAt(i);
				newCount = newCount - 1;
			}
		}
		
		for(int i=0; i<=newCharArray.length -1;i++){
		System.out.print(newCharArray[i]);
		}
	}

	private static boolean isPermutationByCount(String source, String target) {
		if(source.length() != target.length())
		{
			return false;
		}
		
		int[] count = new int[256];
		char[] letters = source.toCharArray();
		
		for(char c:letters){
			count[c]++;
		}
		
		for(int i=0; i>target.length(); i++){
			int t = target.charAt(i);
			if(--count[t]<0){
				return false;
			}
		}
		
		return true;
	}

	private static boolean isPermutation(String source, String target) {
		if(source.length() != target.length()){
		return false;
		}
		return sort(source).equals(sort(target));
		
	}

	private static Object sort(String string) {
		char[] str = string.toCharArray();
		java.util.Arrays.sort(str);
				
		return new String(str);
	}

	private static void revreseMethod(String reverse) //throws ArrayIndexOutOfBoundsException 
	{
		// TODO Auto-generated method stub
		char[] charReverse = new char[reverse.length()];
		charReverse=reverse.toCharArray();
		System.out.println(charReverse);
		
		for(int i = reverse.length()-1; i >= 0;i--){
		
			System.out.print(charReverse[i]);
			
		}
	}

	//Time complexity O(n) , space O(1)
	private static boolean isUniqueChar(String uniqueChar) {
		// TODO Auto-generated method stub
		//ASCCI or UNICODE? in string
		//if length greater than 256, its not unique
		if(uniqueChar.length() > 256)
			return false;
		
		boolean[] charSet = new boolean[256];
		
		for(int i=0; i < uniqueChar.length(); i++){
			int val = uniqueChar.charAt(i);
			System.out.println("charat Str:"+val);
			if(charSet[val]) return false;//Already found this char 
			
			charSet[val] = true;
	}
		return true;

	}
	
	//reduce the space complexity by factor of 8 by using bit vector
	private static boolean isUniqueCharbit(String uniqChar) {
		
		int checker = 0;
		
		for(int i = 0; i < uniqChar.length(); i++){
			int val = uniqChar.charAt(i) - 'a';
			if((checker & (1 << val)) > 0){
				return false;
			}
			checker |= 1 << val;
		}
		return true;
	}
}
