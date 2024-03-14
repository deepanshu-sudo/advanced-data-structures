import java.util.Scanner;

/**
 * DiagonalMatrix
 */
public class DiagonalMatrix {
    /** Data Members */
    int n;
    Object[] element;

    /** Constructor */
    public DiagonalMatrix(int n) {
        this.n = n;
        element = new Object[n];
    }

    /**
     * Reads the matrix from the standard input.
     */
    public void read() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the element at index (" + i + ", " + i + "): ");
            element[i] = sc.nextDouble();
        }
    }

    /**
     * Prints the matrix.
     */
    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    System.out.print(element[i] + " ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Adds two matrices.
     * @return The sum of the two matrices.
     * @throws IllegalArgumentException if the matrices are not of the same size.
     */
    public DiagonalMatrix add(DiagonalMatrix other) {
        if (n != other.n) {
            throw new IllegalArgumentException("Matrices are not of the same size.");
        }

        DiagonalMatrix sum = new DiagonalMatrix(n);
        for (int i = 0; i < n; i++) {
            sum.element[i] = (double) element[i] + (double) other.element[i];
        }

        return sum;
    }

    /**
     * Multiplies two matrices.
     * @return The product of the two matrices.
     * @throws IllegalArgumentException if the matrices are not of the same size.
     */
    public DiagonalMatrix multiply(DiagonalMatrix other) {
        if (n != other.n) {
            throw new IllegalArgumentException("Matrices are not of the same size.");
        }

        DiagonalMatrix product = new DiagonalMatrix(n);
        for (int i = 0; i < n; i++) {
            product.element[i] = (double) element[i] * (double) other.element[i];
        }

        return product;
    }

    /**
     * Returns the determinant of the matrix.
     * @return The determinant of the matrix.
     * @throws UnsupportedOperationException if the matrix is not a square matrix.
     */
    public double determinant() {
        double det = 1;
        for (int i = 0; i < n; i++) {
            det *= (double) element[i];
        }

        return det;
    }

    /**
     * Returns the inverse of the matrix.
     * @return The inverse of the matrix.
     * @throws UnsupportedOperationException if the matrix is not a square matrix.
     * @throws UnsupportedOperationException if the matrix is singular.
     */
    public DiagonalMatrix inverse() {
        DiagonalMatrix inverse = new DiagonalMatrix(n);
        for (int i = 0; i < n; i++) {
            if ((double) element[i] == 0) {
                throw new UnsupportedOperationException("Matrix is singular.");
            }
            inverse.element[i] = 1 / (double) element[i];
        }

        return inverse;
    }

    /**
     * Returns the transpose of the matrix.
     * @return The transpose of the matrix.
     */
    public DiagonalMatrix transpose() {
        return this;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the matrix: ");
        int n = sc.nextInt();

        DiagonalMatrix matrix1 = new DiagonalMatrix(n);
        matrix1.read();
        System.out.println("Matrix1:");
        matrix1.print();

        DiagonalMatrix matrix2 = new DiagonalMatrix(n);
        matrix2.read();
        System.out.println("Matrix2:");
        matrix2.print();

        DiagonalMatrix sum = matrix1.add(matrix2);
        System.out.println("Sum of the two matrices:");
        sum.print();

        DiagonalMatrix product = matrix1.multiply(matrix2);
        System.out.println("Product of the two matrices:");
        product.print();

        System.out.println("Determinant of matrix1: " + matrix1.determinant());
        System.out.println("Determinant of matrix2: " + matrix2.determinant());

        DiagonalMatrix inverse1 = matrix1.inverse();
        System.out.println("Inverse of matrix1:");
        inverse1.print();

        DiagonalMatrix inverse2 = matrix2.inverse();
        System.out.println("Inverse of matrix2:");
        inverse2.print();
    }
}
