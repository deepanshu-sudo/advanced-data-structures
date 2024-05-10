/**
 * This class contains the following Sorting Methods
 * - Selection Sort
 * - Bubble Sort
 * - Insertion Sort
 * - Merge Sort
 * - Quick Sort
 * - Shell Sort
 */

import java.util.Arrays;

public class SortingAlgos {
    /**
     * Selection Sort:
     * 
     * Approach:
     * 
     * In selection sort, we pick the smallest element and swap it with the first
     * element. Then we pick the second smallest element and swap it with the second
     * element, and so on. We repeat this process until the entire array is sorted.
     * 
     * Algorithm:
     * 
     * repeat(numOfElements - 1) times
     *      set the first unsorted element as the minimum
     *      for each of the unsorted elements
     *          if element < currentMinimum
     *              set element as new minimum
     *      swap minimum with first unsorted position
     * 
     * No. of Comparisons:
     * 
     * The number of comparisons made by the selection sort algorithm is:
     * (n-1) in the first pass, (n-2) in the second pass, (n-3) in the third pass up until 1 comparison in the last pass.
     * 
     * Total Comparisons = (n-1) + (n-2) + (n-3) + ... + 1 = n(n-1)/2
     * 
     * Max No. of Exchanges:
     * 
     * The maximum number of exchanges made by the selection sort algorithm is n-1.
     * Why? Because in the worst case, the smallest element is at the end of the array and we need to swap it with the first element.
     * 
     * Time Complexity:
     * 
     * The time complexity of the selection sort algorithm is O(n^2).
     */
    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]) 
                    minIndex = j;
            }
            if(minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * Bubble Sort
     * 
     * Approach:
     * 
     * Bubble Sort repeatedly steps through the list, compares adjacent items, and swaps them if they are in the wrong order. The pass through the list is repeated until no swaps are needed, which means the list is sorted.
     * 
     * Algorithm:
     * 
     * repeat(numOfElements - 1) times
     *     for each of the unsorted elements
     *        if element > nextElement
     *           swap element with nextElement
     * 
     * No. of Comparisons:
     * 
     * The number of comparisons made by the bubble sort algorithm is:
     * (n-1) in the first pass, (n-2) in the second pass, (n-3) in the third pass up until 1 comparison in the last pass. 
     * So the total number of comparisons is n(n-1)/2.
     * 
     * No. of Exchanges:
     * 
     * The maximum number of exchanges made by the bubble sort algorithm is n(n-1)/2.
     */
    public static void bubbleSort(int[] arr,int n) {
        for(int i = 0; i < n-1; i++) {
            boolean isSorted = true;
            for(int j = 0; j < n-i-1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSorted = false;
                }
            }
            if(isSorted)
                break;
        }
    }

    /**
     * Insertion Sort
     * 
     * Approach:
     * In Insertion Sort, we divide the array into two parts: sorted and unsorted. We pick an element from the unsorted array and place it in its correct position in the sorted array.
     * 
     * Algorithm:
     * 
     * for i = 1 to n-1
     *    set current element as key
     *    set j as i-1
     *      while j >= 0 and arr[j] > key
     *          move elements of arr[j] to one position ahead
     *          decrement j
     *    set key to its correct position
     * 
     * No. of comparisons: (n-1) + (n-2) + ... + 1 = n*(n-1)/2 Why? In insertion sort, we compare each element with the elements to its left to find the correct position, leading to n*(n-1)/2 comparisons.
     * 
     * No. of exchanges: 0 to n-1 Why? In insertion sort, the number of exchanges depends on the initial order of the elements. In the best-case scenario, where the elements are already sorted, there are 0 exchanges. In the worst-case scenario, where the elements are in reverse order, there are n-1 exchanges.
     */
    public static void insertionSort(int[] arr, int n) {
        for(int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i-1;
            while(j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    /**
     * Merge Sort
     * 
     * Approach:
     * 
     * Merge Sort is a divide and conquer algorithm. It divides the input array into two halves, calls itself for the two halves, and then merges the two sorted halves.
     * 
     * Algorithm:
     * 
     * if low < high
     *   set mid as (low + high) / 2
     *   call mergeSort for the first half
     *   call mergeSort for the second half
     *   merge the two halves
     * 
     * No. of comparisons: n * log(n) Why? In merge sort, the number of comparisons is proportional to n multiplied by the logarithm of n due to the divide-and-conquer nature of the algorithm.
     * 
     * No. of exchanges: n * log(n) Why? In merge sort, the number of exchanges is also proportional to n multiplied by the logarithm of n because of the merging step that combines the sorted subarrays.
     */
    public static void mergeSort(int[] a, int low, int high) {
        if(low < high) {
            int mid = (int) Math.floor((low + high) / 2);
            mergeSort(a, low, mid);
            mergeSort(a, mid+1, high);
            merge(a, low, mid, high);
        }
    }

    /**
     * Merge Operation for Merge Sort
     * 
     * Approach:
     * b[high-low+1] // store the merged array
     * i = low, j = mid+1, k = 0
     * while(i <= mid and j <= high)
     *      if a[i] < a[j] then
     *          b[k] = a[i]
     *          i++
     *      else
     *          b[k] = a[j]
     *          j++
     *      k++
     * // copy the remaining elements of the first half
     * while(i <= mid)
     *     b[k] = a[i]
     *     i++
     * 
     * // copy the remaining elements of the second half
     * while(j <= high)
     *    b[k] = a[j]
     *    j++
     * 
     * // copy the merged array back to the original array
     * for(i = 0; i < high-low+1; i++)
     *   a[low+i] = b[i]
     */
    public static void merge(int[] a, int low, int mid, int high) {
        int[] b = new int[high-low+1];
        int i = low, j = mid+1, k = 0;
        while(i <= mid && j <= high) {
            if(a[i] < a[j]) {
                b[k] = a[i];
                i++;
            } else {
                b[k] = a[j];
                j++;
            }
            k++;
        }
        while(i <= mid) {
            b[k] = a[i];
            i++;
            k++;
        }
        while(j <= high) {
            b[k] = a[j];
            j++;
            k++;
        }
        for(i = 0; i < high-low+1; i++) {
            a[low+i] = b[i];
        }
    }

    /**
     * Quick Sort
     * 
     * Approach:
     * 
     * Quick Sort is a divide and conquer algorithm. It picks an element as a pivot and partitions the given array around the picked pivot.
     * 
     * Algorithm:
     * 
     * if low < high
     *  set pivot as partition(a, low, high)
     *  call quickSort for the first half
     *  call quickSort for the second half
     * 
     * No. of comparisons: n * log(n) to n^2 Why? In quick sort, the number of comparisons ranges from n * log(n) to n^2, depending on the choice of pivot elements and partitioning strategy.
     * 
     * No. of exchanges: O(n log n) Why? In quick sort, the number of exchanges is typically O(n log n) due to the partitioning and swapping of elements around the pivot.
     */
    public static void quickSort(int[] a, int low, int high) {
        if(low < high) {
            int pivot = partition(a, low, high);
            quickSort(a, low, pivot-1);
            quickSort(a, pivot+1, high); 
        }
    }

    /**
     * Partition function for Quick Sort
     * 
     * Approach:
     * pivot_element = a[low]
     * j = low
     * for i = low+1 to high
     *      if(a[i] < pivot_element)
     *          j++
     *          if(i != j)
     *              swap a[i] with a[j]
     * pivot_point = j
     * if(low != pivot_point)
     *      swap a[low] with a[pivot_point]
     * return pivot_point
     */
    public static int partition(int[] a, int low, int high) {
        int pivotElement = a[low];
        int j = low;
        for(int i = low+1; i <= high; i++) {
            if(a[i] < pivotElement) {
                j++;
                if(i != j) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        int pivotPoint = j;
        if(low != pivotPoint) {
            int temp = a[low];
            a[low] = a[pivotPoint];
            a[pivotPoint] = temp;
        }
        return pivotPoint;
    }

    /**
     * Shell Sort
     * 
     * Approach:
     * 
     * Shell Sort is an optimization of the Insertion Sort algorithm. It starts by sorting pairs of elements far apart from each other and progressively reduces the gap between elements to be compared.
     * 
     * Algorithm:
     * 
     * gap = floor(n/2)
     * while(gap >= 1) do
     *    for i = gap to n do
     *        x = a[i]
     *        j = i
     *        while(j >= gap and a[j-gap] > x) do
     *              a[j] = a[j-gap]
     *              j = j - gap
     *        a[j] = x
     * gap = floor(gap/2)
     */
    public static void shellSort(int[] a, int n) {
        int gap = (int) Math.floor(n/2);
        while(gap >= 1) {
            for(int i = gap; i < n; i++) {
                int x = a[i];
                int j = i;
                while(j >= gap && a[j-gap] > x) {
                    a[j] = a[j-gap];
                    j = j - gap;
                }
                a[j] = x;
            }
            gap = (int) Math.floor(gap/2);
        }
    }

    // Main Method for Testing
    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {5,4,3,2,1};
        bubbleSort(arr2, arr2.length);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {5,4,3,2,1};
        insertionSort(arr3, arr3.length);
        System.out.println(Arrays.toString(arr3));

        int[] arr4 = {5,4,3,2,1};
        mergeSort(arr4, 0, arr4.length-1);
        System.out.println(Arrays.toString(arr4));

        int[] arr5 = {5,4,3,2,1};
        quickSort(arr5, 0, arr5.length-1);
        System.out.println(Arrays.toString(arr5));

        int[] arr6 = {5,4,3,2,1};
        shellSort(arr6, arr6.length);
        System.out.println(Arrays.toString(arr6));
    }
}