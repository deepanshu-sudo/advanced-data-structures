import java.util.Scanner;

/**
 * This class features static methods of reading, printing and muliplying matrices.
 * Matrices can be:
 * - Lower Left Triangular 
 * - Lower Right Triangular
 * - Upper Left Triangular
 * - Upper Right Triangular
 * - Diagonal
 * - Square
 * 
 * All matrices are represented in 2D arrays.
 * We don't store the null values in the 2D array as no space is allocated for them.
 * Therefore they are irregular 2D arrays.
 */

public class MatrixMultiplication {
    /**
     * A 3x3 Right Lower Triangular Matrix looks like this:
     * 
     * 0 0 1
     * 0 1 1
     * 1 1 1
     * 
     * On the index (i+j) < n - 1, the value is 0.
     * On all the other indexes, the value is not 0.
     */
    public static void readRightLowerTriangularMatrix(int[][] matrix) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[i + 1];
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("Enter the value for matrix[" + i + "][" + j + "]: ");
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    /**
     * A 3x3 Left Lower Triangular Matrix looks like this:
     * 
     * 1 0 0
     * 1 1 0
     * 1 1 1
     * 
     * On the index i < j, the value is 0.
     * On all the other indexes, the value is not 0.
     */
    public static void readLeftLowerTriangularMatrix(int[][] matrix) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[i+1];
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("Enter the value for matrix[" + i + "][" + j + "]: ");
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    /**
     * A 3x3 Right Upper Triangular Matrix looks like this:
     * 
     * 1 1 1
     * 0 1 1
     * 0 0 1
     * 
     * On the index i > j, the value is 0.
     * On all the other indexes, the value is not 0.
     */
    public static void readRightUpperTriangularMatrix(int[][] matrix) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[matrix.length-i];
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("Enter the value for matrix[" + i + "][" + j + "]: ");
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    /**
     * A 3x3 Left Upper Triangular Matrix looks like this:
     * 
     * 1 1 1
     * 1 1 0
     * 1 0 0
     * 
     * On the index (i+j) > n - 1, the value is 0.
     * On all the other indexes, the value is not 0.
     */
    public static void readLeftUpperTriangularMatrix(int[][] matrix) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[matrix.length-i];
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("Enter the value for matrix[" + i + "][" + j + "]: ");
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    /**
     * A 3x3 Diagonal Matrix looks like this:
     * 
     * 1 0 0
     * 0 1 0
     * 0 0 1
     * 
     * On the index i != j, the value is 0.
     * On all the other indexes, the value is not 0.
     */
    public static void readDiagonalMatrix(int[][] matrix) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new int[1];
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("Enter the value for matrix[" + i + "][" + j + "]: ");
                matrix[i][j] = sc.nextInt();
            }
        }
    }

    /**
     * Print right lower triangular matrix.
     */
    public static void printRightLowerTriangularMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i+j < matrix.length - 1) {
                    System.out.print("0 ");
                } else {
                    System.out.print(matrix[i][i+j-matrix.length+1] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Print left lower triangular matrix.
     */
    public static void printLeftLowerTriangularMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i < j) {
                    System.out.print("0 ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Print right upper triangular matrix.
     */
    public static void printRightUpperTriangularMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i > j) {
                    System.out.print("0 ");
                } else {
                    System.out.print(matrix[i][j-i] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Print left upper triangular matrix.
     */
    public static void printLeftUpperTriangularMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i+j > matrix.length - 1) {
                    System.out.print("0 ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Print diagonal matrix.
     */
    public static void printDiagonalMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(i != j) {
                    System.out.print("0 ");
                } else {
                    System.out.print(matrix[i][0] + " ");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Main Method
     * Test All of the methods above
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the matrix: ");
        int n = sc.nextInt();
        int[][] rltm = new int[n][];
        int[][] lltm = new int[n][];
        int[][] rutm = new int[n][];
        int[][] lutm = new int[n][];
        int[][] dm = new int[n][];

        readRightLowerTriangularMatrix(rltm);
        printRightLowerTriangularMatrix(rltm);
        readLeftLowerTriangularMatrix(lltm);
        printLeftLowerTriangularMatrix(lltm);
        readRightUpperTriangularMatrix(rutm);
        printRightUpperTriangularMatrix(rutm);
        readLeftUpperTriangularMatrix(lutm);
        printLeftUpperTriangularMatrix(lutm);
        readDiagonalMatrix(dm);
        printDiagonalMatrix(dm);
    }
}
