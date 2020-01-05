package graphAlgos;

import java.util.Arrays;

public class HeapExample {

    public static void maxHeapify(int index, int[] heapArray, int heapSize) {
        int largest = index;
        while(largest < heapSize / 2) {
                    System.out.println(" Element parent " + heapArray[largest]);
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            if(left < heapSize && heapArray[largest] < heapArray[left]) {
                largest = left;
            }
            if(right < heapSize && heapArray[largest] < heapArray[right]) {
                largest = right;
            }
            //swap largest and index
            if(largest != index) {
                System.out.println(" Swapping " + heapArray[largest] + " "+ heapArray[index]);
                int t = heapArray[largest];
                heapArray[largest] = heapArray[index];
                heapArray[index] = t;
                index = largest;
            } else {
                break;
            }
        }
    }

    public static void buildMinHeap(int[] heapArray, int heapSize) {
         if(heapArray == null || heapArray.length == 0 || heapSize == 0) {
            return;
        }
        int n = (heapSize - 1) / 2;
        for(int i = n; i >=0; i--) {
            minHeapify(i, heapArray, heapSize);
        }       
    }

    public static void minHeapify(int index, int[] heapArray, int heapSize) {
        int smallest = index;
        while(smallest < heapSize / 2) {
            int left = 2 * smallest + 1;
            int right = 2 * smallest + 2;
            if(left < heapSize && heapArray[left] < heapArray[smallest]) {
                smallest = left;
            }
            if(right < heapSize && heapArray[right] < heapArray[smallest]) {
                smallest = right;
            }
            if(smallest != index) {
                int t = heapArray[index];
                heapArray[index] = heapArray[smallest];
                heapArray[smallest] = t;
                index = smallest;
            } else {
                break;
            }
        }
    }

    public static void buildMaxHeap(int[] heapArray, int heapSize) {
        if(heapArray == null || heapArray.length == 0 || heapSize == 0) {
            return;
        }
        int n = (heapSize - 1) /2;
        for(int i = n; i >= 0; i--)  {
            maxHeapify(i, heapArray, heapSize);
        }
    }
    public static void main(String args[]) {
 		int[] heapArray = { 1, 4, 7, 12, 15, 14, 9, 2, 3, 16 };
		
		System.out.println("Before heapify: "+Arrays.toString(heapArray));		
        buildMaxHeap(heapArray, heapArray.length);
        System.out.println("After Max heapify: "+Arrays.toString(heapArray));
        buildMinHeap(heapArray, heapArray.length);
        System.out.println("After  Min heapify: "+Arrays.toString(heapArray));  
    }
}