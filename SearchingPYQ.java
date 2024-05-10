/**
 * Que:
 * 
 * Let array[0:n-1] stores segregated (in odds and evens) and sorted positive integers i.e. [1,7,2,6,8,10]. Write efficient java function void binarySearchIterativeOnSegregatedSortedOddsEvens(int a[], int n, int x), based on iterative binary search algorithm, to search item x in the list of array a[]. Illustrate these in the main function.
 */

 import java.util.Scanner;

 public class SearchingPYQ {
     
     // Function to perform binary search on a segregated, sorted array of odds and evens
     public static void binarySearchIterativeOnSegregatedSortedOddsEvens(int[] a, int n, int x) {
         int start = 0;
         int end = n - 1;
         boolean isXEven = x % 2 == 0;
         
         // Find the split point where odds end and evens begin
         while (start <= end && a[start] % 2 != 0) {
             start++;
         }
         // 'start' is now the index of the first even number
 
         int low, high;
         if (isXEven) {
             low = start;  // search in the even section
             high = n - 1;
         } else {
             low = 0;      // search in the odd section
             high = start - 1;
         }
 
         // Iterative binary search in the determined range
         while (low <= high) {
             int mid = low + (high - low) / 2;
             if (a[mid] == x) {
                 System.out.println("Element " + x + " found at index " + mid);
                 return;
             } else if (a[mid] < x) {
                 low = mid + 1;
             } else {
                 high = mid - 1;
             }
         }
         
         System.out.println("Element " + x + " not found in the array.");
     }
     
     // Main function to test the implementation
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         System.out.print("Enter the number of elements: ");
         int n = scanner.nextInt();
         int[] a = new int[n];
         
         System.out.println("Enter " + n + " segregated sorted integers:");
         for (int i = 0; i < n; i++) {
             a[i] = scanner.nextInt();
         }
         
         System.out.print("Enter the element to search for: ");
         int x = scanner.nextInt();
         
         binarySearchIterativeOnSegregatedSortedOddsEvens(a, n, x);
     }
 }