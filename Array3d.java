import java.util.Arrays;
import java.util.Scanner;

/**
 * The Array3d class represents a 3d array in the form of 2D Array and provides methods to read and print the array.
 */
public class Array3d {
    /** Data Members */
    private int[] array;
    private int rows;
    private int cols;
    private int depth;

    /**
     * Constructs a new Array3d object with the specified number of rows, columns and depth.
     *
     * @param rows the number of rows in the 3D array
     * @param cols the number of columns in the 3D array
     * @param depth the depth of the 3D array
     */
    public Array3d(int rows, int cols, int depth) {
        this.rows = rows;
        this.cols = cols;
        this.depth = depth;
        array = new int[rows * cols * depth];
    }

    /**
     * Calculates the row major index of a 3D array element based on its row, column and depth.
     *
     * @param i The row index of the element.
     * @param j The column index of the element.
     * @param k The depth index of the element.
     * @return The calculated row major index of the element.
     * 
     * How does Mapping Work?
     * No. of elements in first dimension 0 to i-1 = i * cols * depth
     * No. of elements in second dimension 0 to j-1 = j * depth
     * No. of elements up to kth depth in the ith row and jth column: k + 1
     * Minus 1 because array index starts from 0
     * 
     * So the Final formula is: i * cols * depth + j * depth + k + 1 - 1 = i * cols * depth + j * depth + k
     */
    public int rowMap(int i, int j, int k) {
        return i * cols * depth + j * depth + k;
    }

    /**
     * Calculates the column major index of a 3D array element based on its row, column and depth.
     * 
     * @param i The row index of the element.
     * @param j The column index of the element.
     * @param k The depth index of the element.
     * @return The calculated column major index of the element.
     * 
     * How does Mapping Work?
     * No. of elements in third dimension 0 to k-1 = k * rows * cols
     * No. of elements in second dimension 0 to j-1 = j * rows
     * No. of elements up to ith row in the jth column and kth depth: i + 1
     * Minus 1 because array index starts from 0
     * 
     * So the Final formula is: k * rows * cols + j * rows + i
     */
    public int colMap(int i, int j, int k) {
        return k * rows * cols + j * rows + i;
    }

    /**
     * Reads the 3D array from the standard input.
     */
    public void readRowMajor() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < depth; k++) {
                    System.out.print("Enter the element at index (" + i + ", " + j + ", " + k + "): ");
                    array[rowMap(i, j, k)] = sc.nextInt();
                }
            }
        }
    }

    /**
     * Reads the 3D array from the standard input.
     */
    public void readColMajor() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < depth; k++) {
                    System.out.print("Enter the element at index (" + i + ", " + j + ", " + k + "): ");
                    array[colMap(i, j, k)] = sc.nextInt();
                }
            }
        }
    }

    /**
     * Prints the 3D array in row major order.
     */
    public void printRowMajor() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < depth; k++) {
                    System.out.print(array[rowMap(i, j, k)] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Prints the 3D array in column major order.
     */
    public void printColMajor() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < depth; k++) {
                    System.out.print(array[colMap(i, j, k)] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * The main method to test the Array3d class.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = sc.nextInt();
        System.out.print("Enter the depth: ");
        int depth = sc.nextInt();
        Array3d arr = new Array3d(rows, cols, depth);
        Array3d arr1 = new Array3d(rows, cols, depth);
        System.out.println("Enter the elements of the 3D array in row major order: ");
        arr.readRowMajor();
        System.out.println("The 3D array in row major order is: ");
        arr.printRowMajor();
        System.out.println("Actual Mapping: "+Arrays.toString(arr.array));
        System.out.println("Enter the elements of the 3D array in column major order: ");
        arr1.readColMajor();
        System.out.println("The 3D array in column major order is: ");
        arr1.printColMajor();
        System.out.println("Actual Mapping: "+Arrays.toString(arr1.array));
    }
}
