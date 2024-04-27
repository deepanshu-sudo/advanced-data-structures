import java.util.Arrays;

/**
 * PYQ:
 * 
 * Let a[] is a sorted array of n positive integers. Write a recursive function void convert_to_BST(int a[], int b[], int n, int i) that copy the elements of array a[] into array b[] such that array b[] becomes a complete binary search tree of n elements, where at the time of calling this function we pass zero for the argument i. For Example if array a[] has elements 1,2,3,4,5,6,7 then after calling this function as convert_to_BST(a,b,7,0) the content of array b[] should be 4,2,6,1,3,5,7 which is a complete binary tree of 7 nodes.
 */
public class ConvertToBSTPYQ {
    static void convert_to_BST_helper(int a[], int b[], int start, int end, int i) {
        if (start > end) {
            return;
        }
    
        // Middle index of the current subarray
        int mid = start + (end - start) / 2;
    
        // Place the middle element at the current index i in b[]
        b[i] = a[mid];
    
        // Recursively fill the left and right subtrees
        convert_to_BST_helper(a, b, start, mid - 1, 2 * (i+1) - 1);
        convert_to_BST_helper(a, b, mid + 1, end, 2 * (i+1));
    }
    
    // Helper function to start the conversion
    static void convert_to_BST(int a[], int b[], int n, int i) {
        convert_to_BST_helper(a, b, 0, n - 1, 0);
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        int[] b = new int[7];

        convert_to_BST(a, b, 7, 0);

        System.out.println("a is: "+Arrays.toString(a));
        System.out.println("b is: "+Arrays.toString(b));
    }
}