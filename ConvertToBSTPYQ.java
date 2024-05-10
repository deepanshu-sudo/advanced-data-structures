import java.util.Arrays;

/**
 * PYQ:
 * 
 * Let a[] is a sorted array of n positive integers. Write a recursive function void convert_to_BST(int a[], int b[], int n, int i) that copy the elements of array a[] into array b[] such that array b[] becomes a complete binary search tree of n elements, where at the time of calling this function we pass zero for the argument i. For Example if array a[] has elements 1,2,3,4,5,6,7 then after calling this function as convert_to_BST(a,b,7,0) the content of array b[] should be 4,2,6,1,3,5,7 which is a complete binary tree of 7 nodes.
 */
public class ConvertToBSTPYQ {
    static int k = 0;

    // Helper function to start the conversion
    static void convert_to_BST(int a[], int b[], int n, int i) {
        if(2*(i+1)-1 < n) {
            convert_to_BST(a, b, n, 2*(i+1)-1);
        }
        b[i] = a[k++];
        if(2*(i+1) < n) {
            convert_to_BST(a, b, n, 2*(i+1));
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        int[] b = new int[7];

        convert_to_BST(a, b, 7, 0);

        System.out.println("a is: "+Arrays.toString(a));
        System.out.println("b is: "+Arrays.toString(b));
    }
}