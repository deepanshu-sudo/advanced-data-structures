import java.util.Scanner;

public class Matrix  {
    /** data memebers */
    int rows;
    int cols;
    Object[][] element;

    /** Constructor for matrix with different number of rows and cols */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        element = new Object[rows][cols];
    }

    /** Constructor for square matrix */
    public Matrix(int rows) {
        this.rows = rows;
        this.cols = rows;
        element = new Object[rows][cols];
    }

    /** 
     * Reads the matrix from the standard input.
     */
    public void read() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Enter the element at index (" + i + ", " + j + "): ");
                element[i][j] = sc.nextDouble();
            }
        }
    }

    /**
     * Prints the matrix.
     */
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(element[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Adds two matrices.
     * @return Matrix
     * @throws IllegalArgumentException if the matrices are not of the same size.
     */
    public Matrix add(Matrix other) {
        // Check if the matrices are of the same size.
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Matrices are not of the same size.");
        }

        Matrix result = new Matrix(rows, cols);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < rows; j++) {
                result.element[i][j] = (double)element[i][j] + (double)other.element[i][j];
            }
        }

        return result;
    }

    /**
     * Multiplies Two Matrices
     * @throws IllegalArgumentException if the matrices are not of the same size.
     * @return Matrix
     */
    public Matrix multiply(Matrix other) {
        // Check if the matrices are of the same size.
        if (cols != other.rows) {
            throw new IllegalArgumentException("Matrices are not of the same size.");
        }

        Matrix result = new Matrix(rows, other.cols);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < other.cols; j++) {
                result.element[i][j] = 0.0;
                for(int k = 0; k < cols; k++) {
                    
                    // print values of i, j, k
                    System.out.println("i = " + i + ", j = " + j + ", k = " + k);

                    System.out.println("result.element[i][j] = "+result.element[i][j]);
                    System.out.println("element[i][k] = "+element[i][k]);
                    System.out.println("other.element[k][j] = "+other.element[k][j]);

                    // Print entire multplication process to console for better understanding
                    System.out.println("result.element[" + i + "][" + j + "] = " + result.element[i][j] + " + " + element[i][k] + " * " + other.element[k][j]);

                    result.element[i][j] = (double)result.element[i][j] + (double)element[i][k] * (double)other.element[k][j];

                }
            }
        }

        return result;
    }

    /** 
     * Returns the determinant of the matrix
     * First converts the matrix into UTM By using the Gauss Elimination Method
     * Then multiplies the diagonal elements to get the determinant
     * @return determinant of the matrix
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public double determinant() {
        if(rows != cols) {
            throw new IllegalArgumentException("Matrix is not square.");
        }

        // Create a temporary matrix
        Matrix temp = new Matrix(rows);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < rows; j++) {
                temp.element[i][j] = (double)element[i][j];
            }
        }

        // Convert the matrix to a Upper Triangular Matrix (forward operation)
        for(int k = 0; k <= rows - 2; k++) {
            double x = (double)temp.element[k][k];
            for(int i = k + 1; i < rows; i++) {
                double y = (double)temp.element[i][k];
                for(int j = 0; j < cols; j++) {
                    // Print values for better understanding
                    System.out.println("x = " + x + ", y = " + y + ", temp.element[" + i + "][" + j + "] = " + temp.element[i][j] + ", temp.element[" + k + "][" + j + "] = " + temp.element[k][j]);

                    
                    System.out.println("temp.element[" + i + "][" + j + "] = " + temp.element[i][j] + " - " + temp.element[k][j] + " * " + y + " / " + x);

                    temp.element[i][j] = (double)temp.element[i][j] - (double)temp.element[k][j] * y / x;

                    // Print the updated value
                    temp.print();
                }
            }
        }

        // Multiply the diagonal elements to get the determinant
        double det = 1;
        for(int i = 0; i < rows; i++) {
            det *= (double)temp.element[i][i];
        }

        return det;
    }

    /**
     * Returns the inverse of the matrix
     * Create a identity matrix of the same size as the matrix
     * First converts the matrix into UTM By using the Gauss Elimination Method and perform the same operations on the identity matrix
     * Then converts the matrix into LTM By using the Gauss Elimination Method and perform the same operations on the identity matrix
     * Once the matrix is converted to diagonal matrix, make the diagonal elements 1 and perform the same operations on the identity matrix
     * The resulting matrix will be the inverse of the original matrix
     * 
     * @return Matrix
     * @throws IllegalArgumentException if the matrix is not square.
     */
    public Matrix inverse() {
        if(rows != cols) {
            throw new IllegalArgumentException("Matrix is not square.");
        }

        // Create a temporary matrix
        Matrix temp = new Matrix(rows);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < rows; j++) {
                temp.element[i][j] = (double)element[i][j];
            }
        }

        // Create an identity matrix
        Matrix identity = new Matrix(rows);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < rows; j++) {
                if(i == j) {
                    identity.element[i][j] = 1.0;
                } else {
                    identity.element[i][j] = 0.0;
                }
            }
        }

        // Convert the matrix to a Upper Triangular Matrix
        for(int k = 0; k <= rows - 2; k++) {
            double x = (double) temp.element[k][k];
            for(int i = k + 1; i < rows; i++) {
                double y = (double) temp.element[i][k];
                for(int j = 0; j < cols; j++) {
                    temp.element[i][j] = (double)temp.element[i][j] - (double)temp.element[k][j] * y / x;
                    identity.element[i][j] = (double)identity.element[i][j] - (double)identity.element[k][j] * y / x;
                }
            }
        }

        // Convert the matrix to a Lower Triangular Matrix
        for(int k = rows - 1; k >= 1; k--) {
            double x = (double)temp.element[k][k];
            for(int i = 0; i <= k - 1; i++) {
                double y = (double)temp.element[i][k];
                for(int j = 0; j < cols; j++) {
                    temp.element[i][j] = (double)temp.element[i][j] - (double)temp.element[k][j] * y / x;
                    identity.element[i][j] = (double)identity.element[i][j] - (double)identity.element[k][j] * y / x;
                }
            }
        }

        // Make Diagonal elements 1
        for(int i = 0; i < rows; i++) {
            double x = (double)temp.element[i][i];
            for(int j = 0; j < cols; j++) {
                temp.element[i][j] = (double)temp.element[i][j] / x;
                identity.element[i][j] = (double)identity.element[i][j] / x;
            }
        }

        return identity;
    }

    /**
     * Returns the transpose of the matrix
     * @return Matrix
     */
    public Matrix transpose() {
        Matrix transpose = new Matrix(cols, rows);
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                transpose.element[j][i] = element[i][j];
            }
        }

        return transpose;
    }

    /** Main Method */
    public static void main(String[] args) {
        Matrix m1 = new Matrix(3);
        m1.read();
        m1.print();
        // System.out.println("Transpose: ");
        // m1.transpose().print();
        // System.out.println("Inverse: ");
        // m1.inverse().print();
        System.out.print("Determinant: "+m1.determinant()+"\n");

        // Matrix m2 = new Matrix(3);
        // m2.read();
        // m2.print();
        // System.out.println("Inverse: ");
        // m2.inverse().print();
        // System.out.print("Determinant: "+m2.determinant()+"\n");

        // Matrix m3 = m1.add(m2);
        // System.out.println("Sum of the two matrices is: ");
        // m3.print();

        // Matrix m4 = m1.multiply(m2);
        // System.out.println("Product of the two matrices is: ");
        // m4.print();
    }
}
