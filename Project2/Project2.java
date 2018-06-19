/**
 * README
 * Author: Swrajit Paul
 * Project name: CS323project2 (assignment2)
 * program model: 
 * Main Method -> readFiles -> storeToArray
 * Main Method -> sortWith -> CountingSort | RadixSort | BucketSort 
 *                         -> print         | print     | print    
 *                         
 * Result: 24 distinct sorted files
 * IMPORTANT - THE INPUT FILES (Num8.txt to Num256.txt) MUST BE IN THE SAME FILE(FOLDER) AS THE JAVA FILE!
 * (THE PROMRAM WONT RUN CORRECTLY WITHOUT IT!)
 */

import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Project2 {
	
	// creating a array(s) for input text file to be stored
	static int[] Num8 = new int[8];
	static int[] Num16 = new int[16]; 
	static int[] Num32 = new int[32];
	static int[] Num64 = new int[64];
	static int[] Num128 = new int[128];
	static int[] Num256 = new int[256];
	
	
	public static void main(String[] args) {
		
		readFiles(); // starts the program by calling the method readFiles (starts the process of text files to be read and values to be stored into an array)
		sortWith("countingSort");
		sortWith("radixSort");
		sortWith("bucketSort");
		
		
		
	}
	
	
	/**
	 * the method is programmed to look for files with certain name 
	 * ( since the input size of the original files increase by times 2 the previous one)
	 * for example, it looks for Num8.txt first, then Num16.txt and 32, 64 and so on..
	 */
	public static void readFiles() {
		
		// for loop is initialized at 8 and is multiplied by two successively 
		for (int i = 8; i < 300; i = 2*i) {
			
			try {
				
				FileInputStream fstream = new FileInputStream("Num" + i + ".txt");
				
				
				// if the input fileName is found, it is passed to the storeToArray method
				if (i == 8) {
					storeToArray(fstream, Num8);
				}
				
				if (i == 16) {
					storeToArray(fstream, Num16);
				}
				
				if (i == 32) {
					storeToArray(fstream, Num32);
				}
				
				if (i == 64) {
					storeToArray(fstream, Num64);
				}
				
				if (i == 128) {
					storeToArray(fstream, Num128);
				}
				
				if (i == 256) {
					storeToArray(fstream, Num256);
				}
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			
			}
		
		} // end of for loop
		
	} // end of readFiles method 
	
	
	/**
	 * This method reads each line of the input text file and stores them into an array
	 * @param fileName - is the name of the input file
	 * @param arrayName - is the name of the array(same as textFileName)
	 */
	public static void storeToArray(FileInputStream fileName, int[] arrayName) {
		
		Scanner scan = new Scanner(fileName);
		
		for (int i = 0; i < arrayName.length; i++) {
			
			try {
				
				int value = Integer.parseInt(scan.nextLine());
				
				arrayName[i] = value; 
			
			} catch (Exception e) {
				
				break;
			
			}
			
		}// end of for loop
		
		scan.close();
	}// end of storeToArray
	
	
	
	/**
	 * This method is used to call different sorting methods depending on the string passed to it
	 * @param sort - is a string that this method uses to understand which sorting method to call
	 */
	
	public static void sortWith(String sort) {
		
		// if the string is countingSort, it calls countingSortMethod
		if(sort.equalsIgnoreCase("countingSort")) {
			
			/* 
			 * All of the call of sorting function follow a format similar to the template below
			 * 
			 * int[] temporaryArrayName = new int[inputArray.length];
			 * countingSort(Num8, countingsort8, num8.length);
			 * print(temporaryArrayName, "SortFileName" , SortCounter);
			*/
			
			int[] countingSort8 = new int[Num8.length];
			countingSort(Num8, countingSort8, Num8.length);
			print(countingSort8, "CountingSort8" , CountingSortCounter);
			
			setCountingCounter();
			int[] countingSort16 = new int[Num16.length];
			countingSort(Num16, countingSort16, Num16.length);
			print(countingSort16, "CountingSort16" , CountingSortCounter);
			
			setCountingCounter();
			int[] countingSort32 = new int[Num32.length];
			countingSort(Num32, countingSort32, Num32.length);
			print(countingSort32, "CountingSort32" , CountingSortCounter);
			
			setCountingCounter();
			int[] countingSort64 = new int[Num64.length];
			countingSort(Num64, countingSort64, Num64.length);
			print(countingSort64, "CountingSort64" , CountingSortCounter);

			setCountingCounter();
			int[] countingSort128 = new int[Num128.length];
			countingSort(Num128, countingSort128, Num128.length);
			print(countingSort128, "CountingSort128" , CountingSortCounter);
			
			setCountingCounter();
			int[] countingSort256 = new int[Num256.length];
			countingSort(Num256, countingSort256, Num256.length);
			print(countingSort256, "CountingSort256" , CountingSortCounter);
			
		
			
		}
		
		else if(sort.equalsIgnoreCase("radixSort")) {
			
			int[] radixSort8 = new int[Num8.length];
			radixSort8 = Num8;
			radixSort(radixSort8, 1);
			print(radixSort8, "RadixSort8" , radixSortCounter);
			
			setRadixCounter(); // sets counter to 0
			int[] radixSort16 = new int[Num16.length];
			radixSort16 = Num16;
			radixSort(radixSort16,  2);
			print(radixSort16, "RadixSort16" , radixSortCounter);
			
			setRadixCounter();
			int[] radixSort32 = new int[Num32.length];
			radixSort32 = Num32;
			radixSort(radixSort32, 2);
			print(radixSort32, "RadixSort32" , radixSortCounter);
			
			setRadixCounter();
			int[] radixSort64 = new int[Num64.length];
			radixSort64 = Num64;
			radixSort(radixSort64, 2);
			print(radixSort64, "RadixSort64" , radixSortCounter);

			setRadixCounter();
			int[] radixSort128 = new int[Num128.length];
			radixSort128 = Num128;
			radixSort(radixSort128, 3);
			print(radixSort128, "RadixSort128" , radixSortCounter);
			
			setRadixCounter();
			int[] radixSort256 = new int[Num256.length];
			radixSort256 = Num256;
			radixSort(radixSort256, 3);
			print(radixSort256, "RadixSort256" , radixSortCounter);
			
		}
	
		else if(sort.equalsIgnoreCase("bucketSort")) {
			
			int[] bucketSort8 = new int[Num8.length];
			bucketSort8 = Num8;
			bucketSort(bucketSort8);
			print(bucketSort8, "BucketSort8" , bucketSortCounter);
			
			setBucketCounter();
			int[] bucketSort16 = new int[Num16.length];
			bucketSort16 = Num16;
			bucketSort(bucketSort16);
			print(bucketSort16, "BucketSort16" , bucketSortCounter);
			
			setBucketCounter();
			int[] bucketSort32 = new int[Num32.length];
			bucketSort32 = Num32;
			bucketSort(bucketSort32);
			print(bucketSort32, "BucketSort32" , bucketSortCounter);
			
			setBucketCounter();
			int[] bucketSort64 = new int[Num64.length];
			bucketSort64 = Num64;
			bucketSort(bucketSort64);
			print(bucketSort64, "BucketSort64" , bucketSortCounter);

			setBucketCounter();
			int[] bucketSort128 = new int[Num128.length];
			bucketSort128 = Num128;
			bucketSort(bucketSort128);
			print(bucketSort128, "BucketSort128" , bucketSortCounter);
	
			setBucketCounter();
			int[] bucketSort256 = new int[Num256.length];
			bucketSort256 = Num256;
			bucketSort(bucketSort256);
			print(bucketSort256, "BucketSort256" , bucketSortCounter);
		}
		
		
	}
	

	
	// initializes countingSortCounter to 0
	public static int CountingSortCounter =0;
	
	public static void setCountingCounter() {
		
		CountingSortCounter = 0;
	
	}
	
	/**
	 * @param A an array unsorted of integers 
	 * @param B an array with length equal to that of array A, it will hold the integers after processed by the method
	 * @param k length of array A or size of array A
	 */
	public static void countingSort(int[] A, int[] B, int k) {
		
		int[] C = new int[k+1];
		
		for(int i = 0; i < k+1; i++) {
			
			C[i] = 0;
			
		}
		
		for(int j = 0; j < A.length; j++) {
			
			C[A[j]] = C[A[j]] + 1;
		}
		
		
		for(int i = 1; i < k+1; i++) {
			
			C[i] = C[i] + C[i-1];
			
		}
		
		for(int j = A.length-1; j >= 0; j--) {
			
			CountingSortCounter = CountingSortCounter + 1;
			
			B[C[A[j]]-1] = A[j];
			
			C[A[j]] = C[A[j]] - 1;
		}
		
	}
	
	
	
	
	// Radix Sort
	
	public static int radixSortCounter  = 0;
	
	public static void setRadixCounter() {
		
		radixSortCounter = 0;
		
	}
	/**
	 * 
	 * @param A an array of unsorted integers
	 * @param d the largest digits
	 */
	public static void radixSort(int[] A, int d) {
		
		int exp = 1;
		
		int[] B = new int[A.length];
		
		int n = A.length;
		
		for(int k = 1; k <= d; k++) {
			
			int[] C = new int[10];
			
			for(int i = 0; i < n ; i++) {
				
				C[(A[i] / exp) % 10]++;
				
			}
			
			for(int j = 1; j < 10; j++) {
				
				C[j] += C[j-1];
			}
			
			
			for(int i = n - 1; i >= 0; i--) {
				
				B[--C[(A[i] / exp) % 10]] = A[i];
				
			}
			
			for(int j = 0; j < n; j++) {
				
				radixSortCounter = radixSortCounter + 1;
				
				A[j] = B[j];
			}
			
			exp *= 10;
		}
		
		
	}

	
	
	// Bucket Sort
	
	public static int bucketSortCounter  = 0;
	
	public static void setBucketCounter() {
		
		bucketSortCounter = 0;
		
	}
	
	/**
	 * 
	 * @param A an array of unsorted integers
	 */
	public static void bucketSort(int[] A) {
		
		int n = A.length;
		
		ArrayList[] buckets = new ArrayList[n];
		
		for(int i = 0; i < n; i++) {
			
			buckets[i] = new ArrayList();
		}
		
		for(int i = 0; i < n; i++) {
			
			int b = A[i];
			
			buckets[b-1].add(A[i]);
		
		}
		
		for (int i = 0; i < n-1; i++) {
			
			int[] tempBucket = new int[n];
			
			int number = 0;
			
			Iterator it = buckets[i].iterator();
			
			while(it.hasNext()) {
				
				tempBucket[number] = (int) it.next();
				number++;
			
			}
			
			insertionSort(tempBucket);
			
		}
		
		
	  
	}

	public static int[] insertionSort(int[] A) {
		
		int[] duplicateArray = new int[A.length];
		
		duplicateArray = A;
		
		for(int j = 1; j < duplicateArray.length; j++) {
			
			int key = duplicateArray[j];
		
			int i = j-1;
			
			while( (i >= 0) && (duplicateArray[i] > key)) {
				
				bucketSortCounter = bucketSortCounter+1;
				
				duplicateArray[i+1] = duplicateArray[i];
				
				i = i - 1;
			}
			
			duplicateArray[i + 1] = key;
		}
	
		return duplicateArray;
	}
	
	
	
	/**
	 * This method writes the output into text files as well as displays a sample output on console
	 * @param array - an array of unsorted integer
	 * @param fileName - a string that contains the fileName of the output txt file
	 * @param counter - counter
	 */
	public static void print(int[] array, String fileName , int counter) {
		
		
		
		String name = fileName + ".txt";
		
		try {
			
			PrintStream print = new PrintStream(name);
			
			print.println(fileName + " counter: " +counter );
			
			for(int i=0; i<array.length; i++) {
				
				print.println(array[i]);
			
			}
			
			print.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		// console output
		
		int i=0;
		
		int j= array.length;
		
		// if the input file is larger than 64 integers, then it set i and j to specified value
		if (array.length > 64) {
			
			i = 50;
			
			j=100;
		
		}
		
		// print out the fileName and the counter
		System.out.println("\n" + fileName + "counter : " + counter + "\n");
		
		// prints out a statement 
		System.out.println("Partial output is shown below and full output is written onto a text file with name " + fileName + ".txt");
		
		for (; i < j; i++) {
			
			System.out.println(array[i]);
		
		}
		
	}
}
