/*Author:ARPIT PARIKH
 * GWID:G31390877
Project 2:CS 6212 Quick :Select – Deterministic (Median of Medians)
DATE:2/22/2015
*
*
*
*/
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
//MAIN CLASS 
public class kthSmallestElement {


	//RETURN KTH SMALL ELEMENT IN GIVEN ARRAY
	//k define as kth small element
	public static void QuickSelect(int array[],int k)
	{
		if(k>array.length || k<0)//if the k the value greater than the length of the array or less than zero
		{
			System.out.println(k+"The Smallest Element does not Exist");
		}
		else
		{
			System.out.println(k+"The Smallest Element in array is \n");
			int kth=findkthSmallestElement(array,0,array.length-1,k);//return the kth smallest element
			System.out.println(kth);
			
		}
	}
	//Main logic method for recursive and finding median of median
	private static int findkthSmallestElement(int[] array, int low, int high, int k) {
		// TODO Auto-generated method stub
		
		
		/*System.out.println("Finding "+k+"the smallest Element");
		
		printArray(array,low,high);*/
		
		if(low==high)//if the 
		{
			return array[low];
		}
		// sort the mth smllest element in the given array
		int partiotn =partition(array,low,high);//Define the rank of the partition array
		
		//Adjust position relative to the current subarray being processed
		int length=partiotn-low+1;
		
		
		// If mth element is the median, return it
		if(length==k)
		{
			return array[k];
		}	
		// If mth element is greater than median, search in the left subarray
		else if(k<length)
		{
			return findkthSmallestElement(array, low,partiotn-1, k);
		}
		//other wise subarray
		else 
		{
			return findkthSmallestElement(array, partiotn+1, high, k-length);
		}
		
	
	}

	private static int partition(int[] array, int low, int high) {
		// TODO Auto-generated method stub

		// Get pivot value by finding median of medians
		int pivot=kthSmallestElement.getPivotValue(array,low,high);
		
		// Find the sorted position for pivotVale and return it's index
		while(low<high)
		{
			while(array[low]<pivot)
			{
				low++;
			}
			while(array[high]>pivot)
			{
				high--;
			}
			if(array[low]==array[high])
			{
				low++;
				
			}
			else if(low<high)
			{
				int temp=array[low];
				array[low]=array[high];
				array[high]=temp;
			}
		}
		
		
		return high;
	}
    // Find pivot value, such the it is always 'closer' to the actual median
    	private static int getPivotValue(int[] array, int low, int high) {	
		// TODO Auto-generated method stub

    		// If number of elements in the array are small, return the actual median
        if (high - low + 1 <= 9) {
            Arrays.sort(array);
            return array[array.length / 2];
        }
        //Otherwise divide the array into sub array of 5 elements each, and recursively find the median
        
        // Array to hold '5 element' subArray, last subArray may have less than 5 elements


        int temp[] = null;
        int medians[] = new int[(int) Math.ceil((double) (high - low + 1) / 5)];
        int medianIndex = 0;	
        
     // Array to hold the medians of all '5-element SubArrays'
        while (low <= high) {
        	 // get size of the next element, it can be less than 5
            temp = new int[Math.min(5, high - low + 1)];
            // copy next 5 (or less) elements, in the sub array
            for (int j = 0; j < temp.length && low <= high; j++) {
                temp[j] = array[low];
                low++;
            }
            //sort sub array(same as insertion sort)
            Arrays.sort(temp);

            // find mean and store it in median Array

            medians[medianIndex] = temp[temp.length / 2];
            medianIndex++;
        }

     // Call recursively to find median of medians
        return getPivotValue(medians, 0, medians.length - 1);
    }

		//Just printing the array
	private static void printArray(int[] array, int low, int high) {
		// TODO Auto-generated method stub
        System.out.print("[  ");
        for (int i = low; i < high; i++) 
        {
            System.out.print(array[i] + "  ");
        }
        System.out.println("]");

		
		
	}
	public static void main(String args[])
	{
		
		//Taking the array with 96 Elements
        int arr1[] = { 37, 766, 237, 761, 832, 1655, 421, 145, 688, 994, 915,
				953, 910, 838, 201, 571, 842, 307, 43, 79, 473, 182, 421, 7122,
				140, 8452, 376, 304, 546, 743, 5, 394, 672, 8439, 684, 223, 50,
				601, 122, 767, 853, 120, 294, 818, 1986, 130, 947, 389, 93, 88,
				487, 403, 943, 973, 359, 226, 228, 408, 913, 743, 458, 867,
				335, 885, 19, 606, 282, 655, 470, 599, 975, 984, 693, 734, 827,
				878, 840, 618, 336, 638,41,407, 552, 287, 20, 253, 288, 907,
				111, 883, 73, 473, 762, 4, 11, 42 };
        
        
        
        /*Random random = new Random();
        int nElements=1000;
        int arr1[]=new int[nElements];
        for(int i =0; i<nElements; i++){
             arr1[i] = (int) random.nextInt(100);
            
       }*/
        System.out.println("Unsorted Array");
        int nElements1 =arr1.length;
        //java.util.Random rand = new java.util.Random();
        int k1 = nElements1; //rand.nextInt(nElements); 
        System.out.print("Array");
        printArray(arr1, 0, arr1.length - 1);
        System.out.println("number of Elements - " + k1);
        //KthMinimum.findKthLargest(arr1.clone(), 0);
         long startTime = System.nanoTime();
        kthSmallestElement.QuickSelect(arr1, 5);//K=5 =>5th smallest Element=>And counting start from 0 to 96
     
        long endTime = System.nanoTime();
        
        long duration = (endTime - startTime);
        
        System.out.println("Time:"+duration+"ns");
     
        printArray(arr1, 0, nElements1 - 1);
      
	}
}
