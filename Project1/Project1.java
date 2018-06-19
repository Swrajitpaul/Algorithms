/**
 * README
 * Author: Swrajit Paul
 * Project name: CS323SwrajitPaul (assignment1)
 * 
 * I used threads in this project to overcome stackOverflow. 
 * The code should work without it, except for quick sort when the input gets very large (due to its recursive nature)
 * The result of the usage of threads is inconclusive! But it seems to allow the program to work outside of eclipse
 * 
 * program model: 
 * Main Method -> readFiles -> storeToArray
 * Main Method -> sortWith -> InsertionSort | MergeSort | HeapSort | quickSort
 *                         -> print         | print     | print    | print
 *                         
 * Result: 32 distinct sorted files
 * 
 * IMPORTANT - THE INPUT FILES (Num8.txt to Num16384.txt) MUST BE IN THE SAME FILE(FOLDER) AS THE JAVA FILE!
 * (THE PROMRAM WONT RUN CORRECTLY WITHOUT IT!)
 */

import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

public class Project1 {
	
	// creating a array(s) for input text file to be stored
	static int[] Num8 = new int[8];
	static int[] Num16 = new int[16]; 
	static int[] Num32 = new int[32];
	static int[] Num64 = new int[64];
	static int[] Num128 = new int[128];
	static int[] Num256 = new int[256];
	static int[] Num512 = new int[512];
	static int[] Num1024 = new int[1024];
	static int[] Num2048 = new int[2048];
	static int[] Num4096 = new int[4096];
	static int[] Num8192 = new int[8192];
	static int[] Num16384 = new int[16384];
	
	
	public static void main(String[] args) {
		
		readFiles(); // starts the program by calling the method readFiles (starts the process of text files to be read and values to be stored into an array)
		
		
		// there are four threads, each containing a distinct sort method
		// thread names start from zero to three
		Thread zero = new Thread(new Runnable() {

		    @Override
		    public void run() {
		    	
		    	sortWith("insSort");
		    	
		    }
		            
		}); // thread one
		        
		Thread one = new Thread(new Runnable() {

		    @Override
		    public void run() {
		    	try {
					
		    		zero.join();
				
		    	} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				
		    	sortWith("mergeSort");
				
			}
		            
		}); // thread two
		
		Thread two = new Thread(new Runnable() {

		    @Override
		    public void run() {
		    	try {
					
		    		one.join();
		    		
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				
				}
				
		    	sortWith("heapSort");
				
				   
		    }
		            
		}); // thread three

		
		Thread three = new Thread(new Runnable() {

		    @Override
		    public void run() {
		    	try {
					
		    		two.join();
				
		    	} catch (InterruptedException e) {
					
		    		e.printStackTrace();
				
		    	}
		    	sortWith("quickSort");
		    }
		            
		}); // thread four
		        
		zero.start(); // starts the first thread: first sorting method
		
		if(zero.isAlive()) { // if current thread is alive, it starts the next thread
			
			one.start();
			
			if (one.isAlive()) 
				
				two.start();
				
				if (two.isAlive()) 
					
					three.start();
	
		}
		
       
		
		
	}
	
	
	/**
	 * the method is programmed to look for files with certain name 
	 * ( since the input size of the original files increase by times 2 the previous one)
	 * for example, it looks for Num8.txt first, then Num16.txt and 32, 64 and so on..
	 */
	public static void readFiles() {
		
		// for loop is initialized at 8 and is multiplied by two successively 
		for (int i = 8; i < 20000; i = 2*i) {
			
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
				
				if (i == 512) {
					storeToArray(fstream, Num512);
				}
				
				if (i == 1024) {
					storeToArray(fstream, Num1024);
				}
				
				if (i == 2048) {
					storeToArray(fstream, Num2048);
				}
				
				if (i == 4096) {
					storeToArray(fstream, Num4096);
				}
				
				if (i == 8192) {
					storeToArray(fstream, Num8192);
				}
				
				if (i == 16384) {
					storeToArray(fstream, Num16384);
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
		
		// if the string is insSort, it calls insertionSortMethod
		if(sort.equalsIgnoreCase("insSort")) {
			
			/* 
			 * All of the call of sorting function follow a format similar to the template below
			 * 
			 * int[] temporaryArrayName = new int[inputArray.length];
			 * temporaryArrayName  = insertionSort(Num8);
			 * print(temporaryArrayName, "SortFileName" , SortCounter);
			*/
			
			int[] insSort8 = new int[Num8.length];
			insSort8 = insertionSort(Num8);
			print(insSort8, "InsertionSort8" , insertSortCounter);
			
			setInsCounter();
			int[] insSort16 = new int[Num16.length];
			insSort16 = insertionSort(Num16);
			print(insSort16, "InsertionSort16" , insertSortCounter);
			
			setInsCounter();
			int[] insSort32 = new int[Num32.length];
			insSort32 = insertionSort(Num32);
			print(insSort32, "InsertionSort32" , insertSortCounter);
			
			setInsCounter();
			int[] insSort64 = new int[Num64.length];
			insSort64 = insertionSort(Num64);
			print(insSort64, "InsertionSort64" , insertSortCounter);

			setInsCounter();
			int[] insSort128 = new int[Num128.length];
			insSort128 = insertionSort(Num128);
			print(insSort128, "InsertionSort128" , insertSortCounter);
			
			setInsCounter();
			int[] insSort256 = new int[Num256.length];
			insSort256 = insertionSort(Num256);
			print(insSort256, "InsertionSort256" , insertSortCounter);
			
			setInsCounter();
			int[] insSort512 = new int[Num512.length];
			insSort512 = insertionSort(Num512);
			print(insSort512, "InsertionSort512" , insertSortCounter);
			
			setInsCounter();
			int[] insSort1024 = new int[Num1024.length];
			insSort1024 = insertionSort(Num1024);
			print(insSort1024, "InsertionSort1024" , insertSortCounter);
			
			setInsCounter();
			int[] insSort2048 = new int[Num2048.length];
			insSort2048 = insertionSort(Num2048);
			print(insSort2048, "InsertionSort2048" , insertSortCounter);
			
			setInsCounter();
			int[] insSort4096 = new int[Num4096.length];
			insSort4096 = insertionSort(Num4096);
			print(insSort4096, "InsertionSort4096" , insertSortCounter);
			
			setInsCounter();
			int[] insSort8192 = new int[Num8192.length];
			insSort8192 = insertionSort(Num8192);
			print(insSort8192, "InsertionSort8192" , insertSortCounter);
	
			setInsCounter();
			int[] insSort16384 = new int[Num16384.length];
			insSort16384 = insertionSort(Num16384);
			print(insSort16384, "InsertionSort16384" , insertSortCounter);
			
		}
		
		else if(sort.equalsIgnoreCase("mergeSort")) {
			
			int[] mergeSort8 = new int[Num8.length];
			mergeSort8 = Num8;
			mergeSort(mergeSort8, 0 , mergeSort8.length-1);
			print(mergeSort8, "MergeSort8" , mergeSortCounter);
			
			setCounter(); // sets counter to 0
			int[] mergeSort16 = new int[Num16.length];
			mergeSort16 = Num16;
			mergeSort(mergeSort16,  0 , mergeSort16.length-1);
			print(mergeSort16, "MergeSort16" , mergeSortCounter);
			
			setCounter();
			int[] mergeSort32 = new int[Num32.length];
			mergeSort32 = Num32;
			mergeSort(mergeSort32, 0 , mergeSort32.length-1);
			print(mergeSort32, "MergeSort32" , mergeSortCounter);
			
			setCounter();
			int[] mergeSort64 = new int[Num64.length];
			mergeSort64 = Num64;
			mergeSort(mergeSort64, 0 , mergeSort64.length-1);
			print(mergeSort64, "MergeSort64" , mergeSortCounter);

			setCounter();
			int[] mergeSort128 = new int[Num128.length];
			mergeSort128 = Num128;
			mergeSort(mergeSort128, 0 , mergeSort128.length-1);
			print(mergeSort128, "MergeSort128" , mergeSortCounter);
			
			setCounter();
			int[] mergeSort256 = new int[Num256.length];
			mergeSort256 = Num256;
			mergeSort(mergeSort256, 0 , mergeSort256.length-1);
			print(mergeSort256, "MergeSort256" , mergeSortCounter);
			
			setCounter();
			int[] mergeSort512 = new int[Num512.length];
			mergeSort512 = Num512;
			mergeSort(mergeSort512, 0 , mergeSort512.length-1);
			print(mergeSort512, "MergeSort512" , mergeSortCounter);
			
			setCounter();
			int[] mergeSort1024 = new int[Num1024.length];
			mergeSort1024 = Num1024;
			mergeSort(mergeSort1024, 0 , mergeSort1024.length-1);
			print(mergeSort1024, "MergeSort1024" , mergeSortCounter);
			
			setCounter();
			int[] mergeSort2048 = new int[Num2048.length];
			mergeSort2048 = Num2048;
			mergeSort(mergeSort2048, 0 , mergeSort2048.length-1);
			print(mergeSort2048, "MergeSort2048" , mergeSortCounter);
			
			setCounter();
			int[] mergeSort4096 = new int[Num4096.length];
			mergeSort4096 = Num4096;
			mergeSort(mergeSort4096, 0 , mergeSort4096.length-1);
			print(mergeSort4096, "MergeSort4096" , mergeSortCounter);
			
			setCounter();
			int[] mergeSort8192 = new int[Num8192.length];
			mergeSort8192 = Num8192;
			mergeSort(mergeSort8192, 0 , mergeSort8192.length-1);
			print(mergeSort8192, "MergeSort8192" , mergeSortCounter);
	
			setCounter();
			int[] mergeSort16384 = new int[Num16384.length];
			mergeSort16384 = Num16384;
			mergeSort(mergeSort16384, 0 , mergeSort16384.length-1);
			print(mergeSort16384, "MergeSort16384" , mergeSortCounter);
			
		}
	
		else if(sort.equalsIgnoreCase("heapSort")) {
			
			int[] heapSort8 = new int[Num8.length];
			heapSort8 = heapSort(Num8);
			print(heapSort8, "HeapSort8" , heapSortCounter);
			
			int[] heapSort16 = new int[Num16.length];
			heapSort16 = heapSort(Num16);
			print(heapSort16, "HeapSort16" , heapSortCounter);
			
			int[] heapSort32 = new int[Num32.length];
			heapSort32 = heapSort(Num32);
			print(heapSort32, "HeapSort32" , heapSortCounter);
			
			int[] heapSort64 = new int[Num64.length];
			heapSort64 = heapSort(Num64);
			print(heapSort64, "HeapSort64" , heapSortCounter);

			int[] heapSort128 = new int[Num128.length];
			heapSort128 = heapSort(Num128);
			print(heapSort128, "HeapSort128" , heapSortCounter);
			
			int[] heapSort256 = new int[Num256.length];
			heapSort256 = heapSort(Num256);
			print(heapSort256, "HeapSort256" , heapSortCounter);
			
			int[] heapSort512 = new int[Num512.length];
			heapSort512 = heapSort(Num512);
			print(heapSort512, "HeapSort512" , heapSortCounter);
			
			int[] heapSort1024 = new int[Num1024.length];
			heapSort1024 = heapSort(Num1024);
			print(heapSort1024, "HeapSort1024" , heapSortCounter);
			
			int[] heapSort2048 = new int[Num2048.length];
			heapSort2048 = heapSort(Num2048);
			print(heapSort2048, "HeapSort2048" , heapSortCounter);
			
			int[] heapSort4096 = new int[Num4096.length];
			heapSort4096 = heapSort(Num4096);
			print(heapSort4096, "HeapSort4096" , heapSortCounter);
			
			int[] heapSort8192 = new int[Num8192.length];
			heapSort8192 = heapSort(Num8192);
			print(heapSort8192, "HeapSort8192" , heapSortCounter);
	
			int[] heapSort16384 = new int[Num16384.length];
			heapSort16384 = heapSort(Num16384);
			print(heapSort16384, "HeapSort16384" , heapSortCounter);
			 
		}
		
		else if(sort.equalsIgnoreCase("quickSort")) {
			
			int[] quickSort8 = new int[Num8.length];
			quickSort8 = quickSort(Num8);
			print(quickSort8, "quickSort8" , quickSortCounter);
			
			int[] quickSort16 = new int[Num16.length];
			quickSort16 = quickSort(Num16);
			print(quickSort16, "quickSort16" , quickSortCounter);
			
			int[] quickSort32 = new int[Num32.length];
			quickSort32 = quickSort(Num32);
			print(quickSort32, "quickSort32" , quickSortCounter);
			
			int[] quickSort64 = new int[Num64.length];
			quickSort64 = quickSort(Num64);
			print(quickSort64, "quickSort64" , quickSortCounter);

			int[] quickSort128 = new int[Num128.length];
			quickSort128 = quickSort(Num128);
			print(quickSort128, "quickSort128" , quickSortCounter);
			
			int[] quickSort256 = new int[Num256.length];
			quickSort256 = quickSort(Num256);
			print(quickSort256, "quickSort256" , quickSortCounter);
			
			int[] quickSort512 = new int[Num512.length];
			quickSort512 = quickSort(Num512);
			print(quickSort512, "quickSort512" , quickSortCounter);
			
			int[] quickSort1024 = new int[Num1024.length];
			quickSort1024 = quickSort(Num1024);
			print(quickSort1024, "quickSort1024" , quickSortCounter);
			
			int[] quickSort2048 = new int[Num2048.length];
			quickSort2048 = quickSort(Num2048);
			print(quickSort2048, "quickSort2048" , quickSortCounter);
			
			int[] quickSort4096 = new int[Num4096.length];
			quickSort4096 = quickSort(Num4096);
			print(quickSort4096, "quickSort4096" , quickSortCounter);
			
			int[] quickSort8192 = new int[Num8192.length];
			quickSort8192 = quickSort(Num8192);
			print(quickSort8192, "quickSort8192" , quickSortCounter);
	
			int[] quickSort16384 = new int[Num16384.length];
			quickSort16384 = quickSort(Num16384);
			print(quickSort16384, "quickSort16384" , quickSortCounter);
			
		}
		
		
	}
	
	
	
	// initializes insertionSortCounter to 0
	public static int insertSortCounter =0;
	
	public static void setInsCounter() {
		insertSortCounter = 0;
	}
	
	/**
	 * InsertionSort
	 * @param A an array of integers
	 * @return int[] array of sorted integers
	 */
	public static int[] insertionSort(int[] A) {
		
		int[] duplicateArray = new int[A.length];
		
		duplicateArray = A;
		
		for(int j = 1; j < duplicateArray.length; j++) {
			
			int key = duplicateArray[j];
		
			int i = j-1;
			
			while( (i >= 0) && (duplicateArray[i] > key)) {
				
				insertSortCounter = insertSortCounter+1;
				
				duplicateArray[i+1] = duplicateArray[i];
				
				i = i - 1;
			}
			
			duplicateArray[i + 1] = key;
		}
		
		return duplicateArray;
	}
	
	
	
	
	
	public static int mergeSortCounter= 0;
	
	public static void setCounter() {
		mergeSortCounter = 0;
	}
	
	/**
	 * 
	 * @param Array an array of unsorted integers
	 * @param low lowest index
	 * @param high highest index
	 */
	public static void mergeSort(int[] Array, int low, int high) {
		
		
		if(low < high) {
			
			int middle = ((int)Math.floor((low + high)/2));
			
			mergeSort(Array, low, middle);
			
			mergeSort(Array, middle+1, high);
			
			Merge(Array, low, middle, high);
		
		}
		
	
	}
	
	
	public static void Merge(int[] A, int low, int middle, int high) {
		
		int n1 = ( middle - low) +1 ;
		
		int n2 = ( high - middle) ;
		
		int[] left = new int[n1+1];
		
		int[] right = new int[n2+1];
		
		for (int i = 0; i < n1; i++) {
			
			left[i] = A[low+i];	
		}
		

		for (int j = 0; j < n2; j++) {
			
			right[j] = A[middle + j + 1];
			
		}
		
		left[n1] = Integer.MAX_VALUE;
		
		right[n2] = Integer.MAX_VALUE;
		
		int i = 0;
		
		int j = 0;
		
		for(int k = low; k <= high; k++) {
			
			mergeSortCounter++;
			
			if (left[i] <= right[j]) {
				
				A[k] = left[i];
			    
				i = i + 1;
				
			}
			
			else {
				
				A[k] = right[j];
				
				j = j + 1;
			
			}
		
		}
		
	}
	
	
	
	
	
	public static int heapSortCounter = 0;
	
	public static int heapSize;
	/**
	 * 
	 * @param A array of unsorted integers
	 * @return int[] array of sorted integers
	 */
	public static int[] heapSort(int[] A) {
		
		heapSortCounter =0;
		
		int[] duplicateArray = new int[A.length];
		
		duplicateArray = A;
		
		buildMaxHeap(duplicateArray);
		
		for (int i = duplicateArray.length-1 ; i >0 ; i--) {
			
			int temporay = duplicateArray[0]; 
			
			duplicateArray[0] = duplicateArray[i];
			
			duplicateArray[i] = temporay;
			
			heapSize = heapSize - 1;
			
			maxHeapify(duplicateArray , 0);
			
		}
		
		return duplicateArray;
	
	}
	
	/**
	 * BuildMaxHeap
	 * @param A -Array of integers
	 */
	public static void buildMaxHeap(int[] A) {
		
		heapSize = A.length-1;
		
		for(int i = (int) Math.floor((A.length/2)); i >= 0; i--) {
			
			maxHeapify(A, i); 
		}
		
	}
	
	/**
	 * 
	 * @param A array of integers
	 * @param i integer 
	 */
	public static void maxHeapify(int[] A, int i) {
		
		int l = left(i);
		
		int r = right(i);
		
		int largest;
		
		if((l <= heapSize) && (A[l] > A[i])) {
			
			largest = l;
		}
		
		else { 
			
			largest = i;
		}
		if((r <= heapSize) && (A[r] > A[largest])) 
			
			largest = r;
		
		if (largest != i) {
			
			int T =A[i]; 
			
			A[i] = A[largest];
			
			A[largest] = T;
			
			heapSortCounter++;
			
			maxHeapify(A, largest);
		
		}
		
	}
	
	public static int left(int i) {
		return 2*i +1; // creates a left child with index 2 times index plus one
	}
	
	public static int right(int i) {
		return 2*i + 2; // creates a right child with index 2 times index plus two
	}
	
	
	
	
	
    public static int quickSortCounter = 0;
	/**
	 * 
	 * @param Array an array of unsorted integers
	 * @return int[] n array of sorted integers
	 */
	public static int[] quickSort(int[] Array) {
		
		quickSortCounter = 0;
		
		int[] duplicateArray = new int[Array.length];
		
		duplicateArray = Array;
		
		QuickSort(duplicateArray, 0, duplicateArray.length-1);
		
		return duplicateArray;
	}
	/**
	 * 
	 * @param array n array of unsorted integers
	 * @param p lowest index
	 * @param r highest index
	 */
	public static void QuickSort(int[] array, int p, int r) {
		
		
		if (p < r) {
			
			int q = partition(array, p, r);
			
			QuickSort(array, p, q-1);
			
			QuickSort(array, q+1, r);
			
		}
		
	}
	
	/**
	 * @param A  an array of unsorted integers
	 * @param p lowest value
	 * @param r highest value
	 * @return int a value
	 */
	public static int partition(int[] A, int p, int r) {
		
		int x = A[r];
		
		int i = p-1;
		
		for (int j = p; j <= r-1; j++) {
			
			quickSortCounter++;
			
			if (A[j] <= x) {
				
				i = i + 1;
				
				int temporaryValue = A[i];
				
				A[i] = A[j];
				
				A[j] = temporaryValue;
				
			}
			
		}
		
		int t = A[i+1];
		
		A[i+1] = A[r];
		
		A[r] = t;
		
		return i+1;
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
