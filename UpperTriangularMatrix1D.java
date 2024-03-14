import java.util.Arrays;
import java.util.Scanner;

/**
 * This class represents a lower triangular matrix in 1D array.
 */

public class UpperTriangularMatrix1D {
    /** Data Members */
    private Object[] utm; // Array to store the elements of the matrix
    private int n; // Dimension of the matrix

    /** Constructor */
    public UpperTriangularMatrix1D(int n) {
        this.n = n;
        // Number of non-zero elements in a Upper Triangular Matrix:
        // n * (n + 1) / 2
        // And Number of zero elements in a Upper Triangular Matrix:
        // n * (n - 1) / 2
        utm = new Object[n * (n + 1) / 2];
    }

    /**
     * Calculates the row major index of the Upper Triangular Matrix element based on its row and column
     * 
     * @param i The row index of the element.
     * @param j The column index of the element.
     * @return The calculated row major index of the element.
     * 
     * How does Mapping work?
     * No. of elements in row 0 to row i-1 = ((n-0)+(n-1)+(n-2)+...+(n-i-1))
     * No. of elements up to jth column in ith row = j - i + 1
     * Minus 1 because array index starts from 0
     * 
     * So the final formula is: (n * i) - ((i * (i + 1)) / 2) + j
     */
    public int rowMap(int i, int j) {
        return (n * i) - ((i * (i + 1)) / 2) + j;
    }

    /**
     * Calculates the column major index of the Upper Triangular Matrix element based on its row and column
     * 
     * @param i The row index of the element.
     * @param j The column index of the element.
     * @return The calculated column major index of the element.
     * 
     * How does Mapping work?
     * No. of elements in column 0 to column j-1 = 1 + 2 + 3 + ... + j = (j * (j + 1)) / 2
     * No. of elements up to ith row in jth column = i + 1
     * Minus 1 because array index starts from 0
     * 
     * So the final formula is: (j * (j + 1)) / 2 + i
     */
    public int colMap(int i, int j) {
        return (j * (j + 1)) / 2 + i;
    }

    /**
     * Reads input values from the user and stores them in the utm array in row major order.
     */
    public void readRowMajor() {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                utm[rowMap(i, j)] = sc.nextDouble();
            }
        }
    }

    /**
     * Reads input values from the user and stores them in the utm array in column major order.
     */
    public void readColumnMajor() {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                System.out.print("Enter the value for [" + i + "][" + j + "]: ");
                utm[colMap(i, j)] = sc.nextDouble();
            }
        }
    }

    /**
     * Displays the elements of the utm array in row major order.
    */
    public void printRowMajor() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(j < i) {
                    System.out.print("0 ");
                } else {
                    System.out.print(utm[rowMap(i, j)] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Displays the elements of the utm array in column major order.
     */
    public void printColumnMajor() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(j < i) {
                    System.out.print("0 ");
                } else {
                    System.out.print(utm[colMap(i, j)] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Adds two upper triangular matrices.
     * Total No. of + Operations Performed: n * (n + 1) / 2
     * @return The sum of the two matrices.
     * @throws IllegalArgumentException if the dimensions of the two matrices are not the same.
     */
    public UpperTriangularMatrix1D add(UpperTriangularMatrix1D other) {
        if (n != other.n) {
            throw new IllegalArgumentException("Matrices are not of the same size.");
        }
        UpperTriangularMatrix1D sum = new UpperTriangularMatrix1D(n);
        
        // General Addition Method for both Row Order and Column Order
        for(int i = 0; i < n * (n+1) / 2; i++) {
            sum.utm[i] = (double)utm[i] + (double)other.utm[i];
        }

        return sum;
    }

    /**
     * Multiplies two upper triangular matrices.
     * @return The product of the two matrices.
     * @throws IllegalArgumentException if the dimensions of the two matrices are not the same.
     */
    public UpperTriangularMatrix1D multiply(UpperTriangularMatrix1D other) {
        if (n != other.n) {
            throw new IllegalArgumentException("Matrices are not of the same size.");
        }
        UpperTriangularMatrix1D product = new UpperTriangularMatrix1D(n);
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                product.utm[rowMap(i,j)] = 0.0;
                for(int k = i; k <= j; k++) {
                    product.utm[rowMap(i,j)] = (double)product.utm[rowMap(i,j)] + (double)utm[rowMap(i, k)] * (double)other.utm[rowMap(k, j)];
                }
            }
        }
        return product;
    }

    /**
     * Returns the determinant of the upper triangular matrix.
     * Multplies the diagonal elements to get the determinant.
     * @return The determinant of the upper triangular matrix.
     */
    public double determinant() {
        // Multiply the diagonal elements to get the determinant
        double determinant = 1;
        for(int i = 0; i < n; i++) {
            determinant *= (double)utm[rowMap(i,i)];
        }
        return determinant;
    }

    /**
     * Returns the inverse of the upper triangular matrix.
     * First creates an identity matrix of the same size as the upper triangular matrix.
     * Then converts the upper triangular matrix into an lower triangular matrix.
     * So that it becomes a diagonal matrix.
     * Then takes the inverse of the diagonal matrix by making the diagonal elements 1/diagonal element.
     * All while performing the same operations on the identity matrix.
     * 
     * @return The inverse of the upper triangular matrix.
     */
    public UpperTriangularMatrix1D inverse() {
        UpperTriangularMatrix1D identity = new UpperTriangularMatrix1D(n);
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                // Store 1 in the diagonal elements and 0 in the rest
                if(i == j) {
                    identity.utm[rowMap(i,j)] = 1.0;
                } else {
                    identity.utm[rowMap(i,j)] = 0.0;
                }
            }
        }

        // Create a temporary matrix to perform the operations
        UpperTriangularMatrix1D temp = new UpperTriangularMatrix1D(n);
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                temp.utm[rowMap(i,j)] = (double)utm[rowMap(i,j)];
            }
        }

        // Convert the UTM to LTM
        for(int k = n-1; k >= 1; k--) {
            double x = (double) temp.utm[rowMap(k,k)];
            for(int i = 0; i <= k - 1; i++) {
                double y = (double) temp.utm[rowMap(i,k)];
                for(int j = k; j <= n - 1; j++) {
                    temp.utm[rowMap(i,j)] = (double) temp.utm[rowMap(i,j)] - (double) temp.utm[rowMap(k,j)] * y / x;
                    identity.utm[rowMap(i,j)] = (double) identity.utm[rowMap(i,j)] - (double) identity.utm[rowMap(k,j)] * y / x;
                }
            }
        }

        // Make diagonal elements 1
        for(int i = 0; i < n; i++) {
            double x = (double) temp.utm[rowMap(i,i)];
            for(int j = i; j < n; j++) {
                temp.utm[rowMap(i,j)] = (double) temp.utm[rowMap(i,j)] / x;
                identity.utm[rowMap(i,j)] = (double) identity.utm[rowMap(i,j)] / x;
            }
        }

        return identity;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the dimension of the matrix: ");
        int n = sc.nextInt();
        UpperTriangularMatrix1D utm1 = new UpperTriangularMatrix1D(n);
        utm1.readRowMajor();
        System.out.println("The Upper Triangular Matrix in row major order is:");
        utm1.printRowMajor();
        System.out.println("Actual Mapping: ");
        System.out.println(Arrays.toString(utm1.utm));

        // UpperTriangularMatrix1D utm2 = new UpperTriangularMatrix1D(n);
        // utm2.readRowMajor();
        // System.out.println("The Upper Triangular Matrix in column major order is:");
        // utm2.printRowMajor();
        // System.out.println("Actual Mapping: ");
        // System.out.println(Arrays.toString(utm2.utm));

        // UpperTriangularMatrix1D utm2 = new UpperTriangularMatrix1D(n);
        // utm2.readColumnMajor();
        // System.out.println("The Upper Triangular Matrix in column major order is:");
        // utm2.printColumnMajor();
        // System.out.println("Actual Mapping: ");
        // System.out.println(Arrays.toString(utm2.utm));

        // System.out.println("The sum of the two matrices is:");
        // UpperTriangularMatrix1D sum = utm1.add(utm2);
        // sum.printRowMajor();

        // System.out.println("The product of the two matrices is:");
        // UpperTriangularMatrix1D product = utm1.multiply(utm2);
        // product.printRowMajor();

        // System.out.println("The determinant of the first matrix is: " + utm1.determinant());
        // System.out.println("The determinant of the second matrix is: " + utm2.determinant());

        System.out.println("The inverse of the first matrix is:");
        UpperTriangularMatrix1D inverse = utm1.inverse();
        inverse.printRowMajor();

        // System.out.println("The inverse of the second matrix is:");
        // UpperTriangularMatrix1D inverse2 = utm2.inverse();
        // inverse2.printRowMajor();
    }

}