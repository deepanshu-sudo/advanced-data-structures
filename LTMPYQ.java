/*
 * Question:
 * define void readLTM(int[][] a, int n), void printLTM(int[][] a, int n), void det(int[][] a, int n functions. write java program to read ltm a and compute its determinants using user defined functions.
 * 
 * A Lower Triangular Matrix looks like this:
 * 
 * 1 0 0 0
 * 2 3 0 0
 * 4 5 6 0
 * 7 8 9 1
 *
 * here i > j
 */
import java.util.Scanner;

public class LTMPYQ {
    public static void readLTM(int[][] a, int n) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < n; i++) {
            a[i] = new int[i + 1];
            for(int j = 0; j < a[i].length; j++) {
                System.out.print(" Enter a[" + i + "][" + j + "]: ");
                a[i][j] = sc.nextInt();
            }
        }
    }

    public static void printLTM(int[][] a, int n) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i >= j) {
                    System.out.print(a[i][j] + " ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public static void det(int[][] a, int n) {
        int det = 1;
        for(int i = 0; i < n; i++) {
            det *= a[i][i];
        }
        System.out.println("The determinant of the matrix is: " + det);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the matrix: ");
        int n = sc.nextInt();
        int[][] a = new int[n][];
        readLTM(a, n);
        System.out.println("The Lower Triangular Matrix is: ");
        printLTM(a, n);
        System.out.println("The determinant of the matrix is: ");
        det(a, n);
    }
}
