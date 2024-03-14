/**
 * This class represents a tridiagonal matrix in 1D array.
 * This is only needed to be completed to represent the multiplication of two tri-diagonal matrices.
 */

public class PentaDiagonalMatrix {
    /** Data Members */
    private Object[] pdm; // Array to store the elements of the matrix
    private int n; // Dimension of the matrix

    /** Constructor */
    public PentaDiagonalMatrix(int n) {
        this.n = n;
        // Number of non-zero elements in a penta-diagonal matrix is 5n-4
        // Number of zero elements is n^2 - 5n + 4
        pdm = new Object[5*n - 6];
    }
}
