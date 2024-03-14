import java.util.Arrays;
import java.util.Scanner;

/**
 * The Array2d class represents a 1D array in the form of 2D Array and provides methods to read and print the array.
 */
public class Array2d {
    /** Data Members */
    private int[] array;
    private int rows;
    private int cols;


    /**
     * Constructs a new Array2d object with the specified number of rows and columns.
     *
     * @param rows the number of rows in the 2D array
     * @param cols the number of columns in the 2D array
     */
    public Array2d(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        array = new int[rows * cols];
    }

    /**
     * Calculates the row major index of a 2D array element based on its row and column.
     *
     * @param i The row index of the element.
     * @param j The column index of the element.
     * @return The calculated row major index of the element.
     * 
     * How does Mapping Work?
     * No. of elements in row 0 to i-1 = i * cols
     * No. of elements up to jth column in the ith row: j + 1 
     * Minus 1 because array index starts from 0
     * 
     * So the Final formula is: i * cols + j + 1 - 1 = i * cols + j
     */
    public int rowMap(int i, int j) {
        return i * cols + j;
    }

    /**
     * Calculates the column major index of a 2D array element based on its row and column.
     * 
     * @param i The row index of the element.
     * @param j The column index of the element.    
     * @return The calculated column major index of the element.
     * 
     * How does Mapping Work?
     * No. of elements in column 0 to j-1 = j * rows
     * No. of elements up to ith row in the jth column: i + 1
     * Minus 1 because array index starts from 0
     * 
     * So the Final formula is: j * rows + i + 1 - 1 = j * rows + i
     */
    public int colMap(int i, int j) {
        return j * rows + i;
    }

    /**
     * Mapping function that stores values from bottom row to top row and elements in each row from left to right
     * 
     * Let m = total no. of rows
     * Let n = total no. of columns
     * No. of element in row m-1 to i+1 = (m - i - 1) * n
     * No. of elements up to jth column in the ith row: j + 1
     * Minus 1 because array index starts from 0
     * 
     * So the Final formula is: (m - i - 1) * n + j = mn - in - n + j
     */
    public int rowMapBottomToTop(int i, int j) {
        return rows * cols - i * cols - cols + j;
    }

    /**
     * Mapping function that stores values from bottom row to top row and elements in each row from right to left
     * 
     * Let m = total no. of rows
     * Let n = total no. of columns
     * No. of element in row m-1 to i+1 = (m - i - 1) * n
     * No. of elements up to jth column in the ith row: n - j + 1
     * Minus 1 because array index starts from 0
     * 
     * So the Final formula is: (m - i - 1) * n + n - j + 1 - 1 = mn - in - n + n - j = mn - in - j
     */
    public int rowMapBottomToTopRightToLeft(int i, int j) {
        return rows * cols - i * cols - j -1;
    }
    

    /**
     * Reads input values from the user and stores them in the 2D array in row major order.
     */
    public void readRowMajor() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                array[rowMap(i, j)] = sc.nextInt();
            }
        }
    }

    /**
     * Reads input values from the user and stores them in the 2D array in column major order.
     */
    public void readColMajor() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                array[colMap(i, j)] = sc.nextInt();
            }
        }
    }

    /**
     * Reads input values from the user and stores them in the 2D array from bottom row to top row and elements in each row from left to right
     */
    public void readRowMajorBottomToTop() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                array[rowMapBottomToTop(i, j)] = sc.nextInt();
            }
        }
    }

    /**
     * Reads input values from the user and stores them in the 2D array from bottom row to top row and elements in each row from right to right
     */
    public void readRowMajorBottomToTopRightToLeft() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                array[rowMapBottomToTopRightToLeft(i, j)] = sc.nextInt();
            }
        }
    }

    /**
     * Prints the elements of the 2D array stored in row major.
     */
    public void printRowMajor() {
        System.out.println("Array is: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[rowMap(i, j)] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints the elements of the 2D array stored in column major.
     */
    public void printColMajor() {
        System.out.println("Array is: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[colMap(i, j)] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints the elements of the 2D array stored in row major from bottom row to top row and elements in each row from left to right
     */
    public void printRowMajorBottomToTop() {
        System.out.println("Array is: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[rowMapBottomToTop(i, j)] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints the elements of the 2D array stored in row major from bottom row to top row and elements in each row from right to right
     */
    public void printRowMajorBottomToTopRightToLeft() {
        System.out.println("Array is: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[rowMapBottomToTopRightToLeft(i, j)] + " ");
            }
            System.out.println();
        }
    }

    /**
     * The main method is the entry point of the program.
     * It creates an instance of the Array2d class, reads input values, and prints the array.
     */
    public static void main(String[] args) {
        // Array2d arr = new Array2d(2, 3);
        // arr.readRowMajor();
        // arr.printRowMajor();
        // System.out.println("Actual Mapping: ");
        // System.out.println(Arrays.toString(arr.array));

        // Array2d arr2 = new Array2d(2, 3);
        // arr2.readColMajor();
        // arr2.printColMajor();
        // System.out.println("Actual Mapping: ");
        // System.out.println(Arrays.toString(arr2.array));

        // Array2d arr3 = new Array2d(2, 3);
        // arr3.readRowMajorBottomToTop();
        // arr3.printRowMajorBottomToTop();
        // System.out.println("Actual Mapping: ");
        // System.out.println(Arrays.toString(arr3.array));

        Array2d arr4 = new Array2d(3,3);
        arr4.readRowMajorBottomToTopRightToLeft();
        arr4.printRowMajorBottomToTopRightToLeft();
        System.out.println("Actual Mapping: ");
        System.out.println(Arrays.toString(arr4.array));
    }
}
