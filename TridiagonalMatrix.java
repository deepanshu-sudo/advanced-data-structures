import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

/**
 * This class represents a tridiagonal matrix in 1D array.
 */

public class TridiagonalMatrix {
    /** Data Members */
    private Object[] tdm; // Array to store the elements of the matrix
    private int n; // Dimension of the matrix

    /** Constructor */
    public TridiagonalMatrix(int n) {
        this.n = n;
        // Number of non-zero elements in a tridiagonal matrix is 3n-2
        // Number of zero elements is n^2 - 3n + 2
        tdm = new Object[3*n - 2];
    }

    /**
     * Calculates the row major index of the Tridiagonal Matrix element based on its row and column
     * 
     * @param i The row index of the element.
     * @param j The column index of the element.
     * @return The calculated row major index of the element.
     * 
     * How does Mapping Work?
     * No. of not null elements from row 0 to row i-1 = 2+3+3+....+i times
     * No. of not null elements upto jth column in ith row: j-i+2
     * Minus 1 because array index starts from 0
     * 
     * So the final formula is:
     * (2+3+3+....+i) + (j-i+2) - 1 = 3i - 1 + j - i + 2 - 1 = 2i + j
     */
    public int rowMap(int i, int j) {
        return 2*i + j;
    }

    /**
     * Calculates the column major index of the Tridiagonal Matrix element based on its row and column
     * 
     * @param i The row index of the element.
     * @param j The column index of the element.
     * @return The calculated column major index of the element.
     * 
     * How does Mapping Work?
     * No. of not null elements from column 0 to column j-1 = 2+3+3+....+j times
     * No. of not null elements upto ith row in jth column: i-j+2
     * Minus 1 because array index starts from 0
     * 
     * So the final formula is:
     * (2+3+3+....+j) + (i-j+2) - 1 = 3j - 1 + i - j + 2 - 1 = 2j + i
     */
    public int colMap(int i, int j) {
        return 2*j + i;
    }

    /**
     * Reads input values from the user and stores them in the tdm array in row major order.
     * It should read only the not null elements.
     * The number of read operations is 3n-2.
     */
    public void readRowMajor() {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < n; i++) {
            for(int j = Math.max(0, i-1); j <= Math.min(i+1, n-1); j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                tdm[rowMap(i, j)] = sc.nextDouble();
            }
        }
    }

    /**
     * Reads input values from the user and stores them in the tdm array in column major order.
     * It should read only the not null elements.
     * The number of read operations is 3n-2.
     */
    public void readColumnMajor() {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < n; i++) {
            for(int j = Math.max(0, i-1); j <= Math.min(i+1, n-1); j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                tdm[colMap(i, j)] = sc.nextDouble();
            }
        }
    }

    /**
     * Displays the elements of the tdm array in row major order.
    */
    public void printRowMajor() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(Math.abs(i-j) < 2) {
                    System.out.print(tdm[rowMap(i, j)] + "\t");
                } else {
                    System.out.print("0 \t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Displays the elements of the tdm array in column major order.
    */
    public void printColumnMajor() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(Math.abs(i-j) < 2) {
                    System.out.print(tdm[colMap(i, j)] + "\t");
                } else {
                    System.out.print("0 \t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Adds two tridiagonal matrices.
     * Total No. of + Operations Performed: 3n - 2
     * @return The sum of the two matrices.
     * @throws IllegalArgumentException if the dimensions of the two matrices are not the same.
     */
    public TridiagonalMatrix add(TridiagonalMatrix other) {
        if(n != other.n) {
            throw new IllegalArgumentException("The dimensions of the two matrices are not the same.");
        }

        TridiagonalMatrix sum = new TridiagonalMatrix(n);
        // General Method for Addition
        for(int i = 0; i < 3*n-2; i++) {
            sum.tdm[i] = (double)tdm[i] + (double)other.tdm[i];
        }

        // Method that uses row map
        // for(int i = 0; i < n; i++) {
        //     for(int j = Math.max(0,i-1); j < Math.min(i+1,n-1); j++) {
        //         sum.tdm[rowMap(i, j)] = (double)tdm[rowMap(i, j)] + (double)other.tdm[rowMap(i, j)];
        //     }
        // }

        // Method that uses column map
        // for(int i = 0; i < n; i++) {
        //     for(int j = Math.max(0,i-1); j < Math.min(i+1,n-1); j++) {
        //         sum.tdm[colMap(i, j)] = (double)tdm[colMap(i, j)] + (double)other.tdm[colMap(i, j)];
        //     }
        // }
        
        return sum;
    }

    /**
     * Pending to Implement Multiplication of Two TriDiagonal Matrices, Determinant, and Inverse of a TriDiagonal Matrix
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the dimension of the tridiagonal matrix: ");
        int n = sc.nextInt();
        TridiagonalMatrix tdm1 = new TridiagonalMatrix(n);
        TridiagonalMatrix tdm2 = new TridiagonalMatrix(n);
        System.out.println("Enter the elements of the first tridiagonal matrix in row major order:");
        tdm1.readRowMajor();
        System.out.println("Enter the elements of the second tridiagonal matrix in row major order:");
        tdm2.readColumnMajor();
        System.out.println("The first tridiagonal matrix in row major order:");
        tdm1.printRowMajor();
        System.out.println("Actual Mapping: "+Arrays.toString(tdm1.tdm));
        System.out.println("\nThe second tridiagonal matrix in row major order:");
        tdm2.printColumnMajor();
        System.out.println("Actual Mapping: "+Arrays.toString(tdm2.tdm));
    }
}