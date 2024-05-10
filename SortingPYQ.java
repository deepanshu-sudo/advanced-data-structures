/**
 * segBubbleSort V1:
 * 
 * Let array a[0:n-1] stores positive integers without duplicates. Define efficient Java function void segBubbleSort(int a[], n), based on bubble sort to segregate the list into even and odd numbers and sort the list of even numbers in increasing order and list of odd number in decreasing order. For example if the list is [10,3,1,9,2,7,8] then this function arrange the elements in the array as [2,8,10,9,7,3,1]. Also defines the void read(int a[],int n) and void print(int a[], int n) functions and illustrate these functions in main function. (Note: use single if statement for swap the elements.)
 * 
 * segBubbleSort V2:
 * 
 * Array = [3,2,8,1,4,6]
 * Return = [1,3,2,4,6,8]
 */

 import java.util.Scanner;

public class SortingPYQ {
    
    public static void segBubbleSortV1(int[] a, int n) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                // Check if we need to swap elements based on even-odd segregation and sort order
                if ((a[i-1] % 2 == 0 && a[i] % 2 == 0 && a[i-1] > a[i]) || // both even, wrong order
                    (a[i-1] % 2 != 0 && a[i] % 2 != 0 && a[i-1] < a[i]) || // both odd, wrong order
                    (a[i-1] % 2 != 0 && a[i] % 2 == 0)) { // even follows odd, need to swap
                    // Swap elements
                    int temp = a[i];
                    a[i] = a[i-1];
                    a[i-1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static void segBubbleSortV2(int[] a, int n) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                // Check if we need to swap elements based on even-odd segregation and sort order
                if ((a[i-1] % 2 == 1 && a[i] % 2 == 1 && a[i-1] > a[i]) || // both odd, wrong order
                    (a[i-1] % 2 == 0 && a[i] % 2 == 0 && a[i-1] > a[i]) || // both even, wrong order
                    (a[i-1] % 2 == 0 && a[i] % 2 == 1)) { // even follows odd, need to swap
                    // swap elements
                    int temp = a[i];
                    a[i] = a[i-1];
                    a[i-1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
    
    // Function to read the input array
    static void read(int a[], int n) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
    }
    
    // Function to print the array
    static void print(int a[], int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    // Main function to demonstrate the segBubbleSort functionality
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] a = new int[n];
        
        System.out.println("Enter the elements of the array: ");
        read(a, n);
        
        segBubbleSortV1(a, n);
        System.out.println("The segregated v1 sorted array is: ");
        print(a, n);

        segBubbleSortV2(a, n);
        System.out.println("The segregated v2 sorted array is: ");
        print(a, n);
    }
}