import java.util.Arrays;
import java.util.Scanner;

/**
 * This class represents a lower triangular matrix in 1D array.
 */
public class LowerTriangularMatrix1D {
    /** Data Members */
    private Object[] ltm; // Array to store the elements of the matrix
    private int n; // Dimensions of the matrix

    /** Constructor */
    public LowerTriangularMatrix1D(int n) {
        this.n = n;
        // Number of non-zero elements in a Lower Triangular Matrix:
        // n * (n + 1) / 2
        // And Number of zero elements in a Lower Triangular Matrix:
        // n * (n - 1) / 2
        ltm = new Object[n * (n + 1) / 2];
    }

    /** 
     * Calculates the row major index of a lower triangular matrix element based on its row and column.
     * 
     * @param i The row index of the element.
     * @param j The column index of the element.
     * @return The calculated row major index of the element.
     * 
     * How does Mapping Work?
     * No. of elements in row 0 to i-1 = i * (i + 1) / 2
     * No. of elements up to jth column in the ith row: j + 1
     * Minus 1 because array index starts from 0
     * 
     * So the Final formula is: i * (i + 1) / 2 + j
     */
    public int rowMap(int i, int j) {
        return i * (i + 1) / 2 + j;
    }

    /**
     * Calculates the column major index of a lower triangular matrix element based on its row and column.
     * 
     * @param i The row index of the element.
     * @param j The column index of the element.
     * @return The calculated column major index of the element.
     * 
     * How does Mapping Work?
     * No. of elements in column 0 to j-1 = (n + (n-1) + (n-2) + ... + (n-j+1)) = 
     * (n + n + n .... j times) - (1 + 2 + 3 + ... + j-1) 
     * 
     * No. of elements up to ith row in the jth column: i - j + 1
     * Minus 1 because array index starts from 0
     * 
     * So the Final formula is:
     * n * j - (j * (j + 1) / 2) + i
     */
    public int colMap(int i, int j) {
        return n * j - (j * (j + 1) / 2) + i;
    }

    /**
     * Mapping function that stores values from bottom row to top row and elements in each row from right to left
     * 
     * For example: a 3x3 LTM is stored as
     * a22, a21, a20, a11, a10, a00
     * 
     * No. of not null elements in row n-1 to i+1 +
     * No. of not null elements up to jth column in ith row 
     * Minus 1 because array index starts from 0
     * 
     * (n + n + n + ... + n-i-1 times) + (0 + 1 + 2 + ... + (n-i-2)) + i - j + 1 - 1
     * So the Final formula is: n * (n - i - 1) - ((n-i-2)(n-i-1)/2) + i - j
     * 
     * Let's see what each part denotes:
     * 1. Elements from the rows below:
     * For an element at position (i, j) in the LTM, we need to consider all the elements in the rows below row 'i'. Since each row has 'n' elements, and there are (n - i - 1) rows below row 'i', this gives us the term: n * (n - i - 1).
     * 
     * 2. Overcounting: Correcting for the Lower Triangle
     * We already know, the no. of zeroes in LTM is n * (n - 1) / 2
     * We need to remove the overcounted zeroes from the rows below row i
     * so put n = n - i - 1
     * and we get ((n-i-2)(n-i-1)/2
     * 
     * 3. Elements to the right
     * On row 'i' itself, elements to the right of column 'j' come after the element (i, j) in the array. We subtract (i - j) to account for these elements.
     */
    public int rowMapBottomToTop(int i, int j) {
        return n * (n - i - 1) - ((n - i - 2) * (n - i - 1) / 2) + i - j;
    }

    /**
     * Reads input values from the user and stores them in the ltm array in row major order.
     */
    public void readRowMajor() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                ltm[rowMap(i, j)] = sc.nextDouble();
            }
        }
    }

    /**
     * Reads input values from the user and stores them in the ltm array in column major order.
     */
    public void readColMajor() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                ltm[colMap(i, j)] = sc.nextDouble();
            }
        }
    }

    /**
     * Reads input values from the user and stores them in the ltm array in row major order from bottom to top and in each row from right to left.
     */
    public void readRowMajorBottomToTop() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                ltm[rowMapBottomToTop(i, j)] = sc.nextDouble();
            }
        }
    }
    
    /**
     * Displays the elements of the ltm array in row major order.
     */
    public void printRowMajor() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i >= j)
                    System.out.print(ltm[rowMap(i, j)] + " ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Displays the elements of the ltm array in column major order.
     */
    public void printColMajor() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i >= j)
                    System.out.print(ltm[colMap(i, j)] + " ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Displays the elements in the ltm array in row major order from bottom to top and in each row from right to left.
     */
    public void printRowMajorBottomToTop() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i >= j)
                    System.out.print(ltm[rowMapBottomToTop(i, j)] + " ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Adds two lower triangular matrices.
     * Total No. of + Operations Performed: n * (n + 1) / 2
     * @return The sum of the two matrices.
     * @throws IllegalArgumentException if the dimensions of the two matrices are not the same.
     */
    public LowerTriangularMatrix1D add(LowerTriangularMatrix1D ltm2) {
        if (n != ltm2.n) {
            throw new IllegalArgumentException("The dimensions of the two matrices are not the same.");
        }
        LowerTriangularMatrix1D sum = new LowerTriangularMatrix1D(n);

        // General Addition Method for both Row Order and Column Order
        for(int i = 0; i < n * (n + 1) / 2; i++) {
            sum.ltm[i] = (double)ltm[i] + (double)ltm2.ltm[i];
        }
        
        // Addition of two lower triangular matrices in row major order
        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j <= i; j++) {
        //         sum.ltm[rowMap(i, j)] = (double)ltm[rowMap(i, j)] + (double)ltm2.ltm[rowMap(i, j)];
        //     }
        // }

        // Addition of two lower triangular matrices in column major order
        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j <= i; j++) {
        //         sum.ltm[colMap(i, j)] = (double)ltm[colMap(i, j)] + (double)ltm2.ltm[colMap(i, j)];
        //     }
        // }

        return sum;
    }

    /**
     * Multiplies two lower triangular matrices.
     * @return The product of the two matrices.
     * @throws IllegalArgumentException if the dimensions of the two matrices are not the same.
     */
    public LowerTriangularMatrix1D multiply(LowerTriangularMatrix1D ltm2) {
        if (n != ltm2.n) {
            throw new IllegalArgumentException("The dimensions of the two matrices are not the same.");
        }
        LowerTriangularMatrix1D product = new LowerTriangularMatrix1D(n);
        
        // Multiplication of two LTMs in Row Major Order
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                product.ltm[rowMap(i,j)] = 0.0;
                for(int k = j; k <= i; k++) {

                    // Print all the values for better understanding
                    System.out.println("i: " + i + " j: " + j + " k: " + k);

                    // Print the actual values present in array 
                    System.out.println("ltm[" + rowMap(i,k) + "]: " + ltm[rowMap(i,k)] + " ltm2[" + rowMap(k,j) + "]: " + ltm2.ltm[rowMap(k,j)]);

                    // Print the calculation
                    System.out.println("ltm[" + rowMap(i,k) + "] * ltm2[" + rowMap(k,j) + "]: " + (double)ltm[rowMap(i,k)] + " * " + (double)ltm2.ltm[rowMap(k,j)]);

                    product.ltm[rowMap(i,j)] = (double)product.ltm[rowMap(i,j)] + (double) ltm[rowMap(i,k)] * (double)ltm2.ltm[rowMap(k,j)];
                }
            }
        }

        // Multiplication of two LTMs in Column Major Order
        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j < n; j++) {
        //         product.ltm[colMap(i,j)] = 0;
        //         for(int k = 0; k < n; k++) {
        //             product.ltm[colMap(i,j)] = (double)product.ltm[colMap(i,j)] + (double) ltm[colMap(i,k)] * (double)ltm2.ltm[colMap(k,j)];
        //         }
        //     }
        // }

        return product;
    }

    /**
     * Returns the determinant of the lower triangular matrix.
     * Multplies the diagonal elements to get the determinant.
     * @return The determinant of the lower triangular matrix.
     */
    public double determinant() {
        // Multiply the diagonal elements to get the determinant
        double determinant = 1;
        for(int i = 0; i < n; i++) {
            determinant *= (double)ltm[rowMap(i,i)];
        }
        return determinant;
    }

    /**
     * Returns the inverse of the lower triangular matrix.
     * First creates an identity matrix of the same size as the lower triangular matrix.
     * Then converts the lower triangular matrix into an upper triangular matrix.
     * So that it becomes a diagonal matrix.
     * Then takes the inverse of the diagonal matrix by making the diagonal elements 1/diagonal element.
     * All while performing the same operations on the identity matrix.
     * 
     * @return The inverse of the lower triangular matrix.
     */
    public LowerTriangularMatrix1D inverse() {
        LowerTriangularMatrix1D identity = new LowerTriangularMatrix1D(n);
        for(int i = 0; i < n; i++) {
            // Store 1 in the diagonal elements and 0 in the rest
            for(int j = 0; j <= i; j++) {
                if(i == j) {
                    identity.ltm[rowMap(i,j)] = 1.0;
                } else {
                    identity.ltm[rowMap(i,j)] = 0.0;
                }
            }
        }

        // Create a temporary matrix to perform the operations
        LowerTriangularMatrix1D temp = new LowerTriangularMatrix1D(n);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                temp.ltm[rowMap(i,j)] = (double)ltm[rowMap(i,j)];
            }
        }

        // Convert the LTM To UTM
        for(int k = 0; k <= n - 2; k++) {
            double x = (double) temp.ltm[rowMap(k,k)];
            for(int i = k + 1; i < n; i++) {
                double y = (double) temp.ltm[rowMap(i,k)];
                for(int j = 0; j <= k; j++) {
                    temp.ltm[rowMap(i,j)] = (double) temp.ltm[rowMap(i,j)] - (double) temp.ltm[rowMap(k,j)] * y / x;
                    identity.ltm[rowMap(i,j)] = (double) identity.ltm[rowMap(i,j)] - (double) identity.ltm[rowMap(k,j)] * y / x;
                }
            }
        }

        // Make Diagonal Elements 1
        for(int i = 0; i < n; i++) {
            double x = (double) temp.ltm[rowMap(i,i)];
            for(int j = 0; j <= i; j++) {
                temp.ltm[rowMap(i,j)] = (double) temp.ltm[rowMap(i,j)] / x;
                identity.ltm[rowMap(i,j)] = (double) identity.ltm[rowMap(i,j)] / x;
            }
        }

        return identity;
    }


    /**
     * Main method to test the class.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the dimensions of the lower triangular matrix: ");
        int n = sc.nextInt();
        // LowerTriangularMatrix1D ltm1 = new LowerTriangularMatrix1D(n);
        // ltm1.readRowMajor();
        // ltm1.printRowMajor();
        // System.out.println("Actual Mapping: ");
        // System.out.println(Arrays.toString(ltm1.ltm));
        // System.out.println("Determinant: "+ltm1.determinant());
        // System.out.println("Inverse: ");
        // ltm1.inverse().printRowMajor();

        // LowerTriangularMatrix1D ltm2 = new LowerTriangularMatrix1D(n);
        // ltm2.readRowMajor();
        // ltm2.printRowMajor();
        // System.out.println("Actual Mapping: ");
        // System.out.println(Arrays.toString(ltm2.ltm));

        // ltm1.add(ltm2).printRowMajor();

        // ltm1.multiply(ltm2).printRowMajor();

        // LowerTriangularMatrix1D ltm2 = new LowerTriangularMatrix1D(n);
        // ltm2.readColMajor();
        // ltm2.printColMajor();
        // System.out.println("Actual Mapping: ");
        // System.out.println(Arrays.toString(ltm2.ltm));

        LowerTriangularMatrix1D ltm3 = new LowerTriangularMatrix1D(n);
        ltm3.readRowMajorBottomToTop();
        ltm3.printRowMajorBottomToTop();
        System.out.println("Actual Mapping: ");
        System.out.println(Arrays.toString(ltm3.ltm));
    }
}
