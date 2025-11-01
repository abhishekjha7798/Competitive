// Matrix Multiplication Algorithms
import java.util.*;

public class MatrixAlgorithms {
    // ========== Matrix Multiplication - A * B ==========
    static long[][] matrixMultiply(long[][] A, long[][] B) {
        int n = A.length;
        int m = B[0].length;
        int p = B.length;

        long[][] C = new long[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    // ========== Matrix Multiplication with MOD ==========
    static long[][] matrixMultiplyMod(long[][] A, long[][] B, long mod) {
        int n = A.length;
        int m = B[0].length;
        int p = B.length;

        long[][] C = new long[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k++) {
                    C[i][j] = (C[i][j] + (A[i][k] * B[k][j]) % mod) % mod;
                }
            }
        }
        return C;
    }

    // ========== Matrix Exponentiation - A^n ==========
    static long[][] matrixPower(long[][] A, long n) {
        int size = A.length;
        long[][] result = new long[size][size];

        // Initialize result as identity matrix
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        long[][] base = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                base[i][j] = A[i][j];
            }
        }

        while (n > 0) {
            if ((n & 1) == 1) {
                result = matrixMultiply(result, base);
            }
            base = matrixMultiply(base, base);
            n >>= 1;
        }
        return result;
    }

    // ========== Matrix Exponentiation with MOD ==========
    static long[][] matrixPowerMod(long[][] A, long n, long mod) {
        int size = A.length;
        long[][] result = new long[size][size];

        // Initialize result as identity matrix
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        long[][] base = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                base[i][j] = A[i][j] % mod;
            }
        }

        while (n > 0) {
            if ((n & 1) == 1) {
                result = matrixMultiplyMod(result, base, mod);
            }
            base = matrixMultiplyMod(base, base, mod);
            n >>= 1;
        }
        return result;
    }

    // ========== Chain Matrix Multiplication - A1 * A2 * ... * An ==========
    static long[][] chainMatrixMultiply(long[][][] matrices) {
        int n = matrices.length;
        if (n == 0) return null;
        if (n == 1) return matrices[0];

        long[][] result = matrices[0];
        for (int i = 1; i < n; i++) {
            result = matrixMultiply(result, matrices[i]);
        }
        return result;
    }

    // ========== Array of Matrix Multiplication ==========
    static long[][] arrayMatrixMultiply(long[][][] matrices, long[] multipliers, long mod) {
        // Multiply each matrix by corresponding value and then multiply all together
        int n = matrices.length;
        long[][] result = null;

        for (int i = 0; i < n; i++) {
            long[][] scaled = new long[matrices[i].length][matrices[i][0].length];
            for (int r = 0; r < matrices[i].length; r++) {
                for (int c = 0; c < matrices[i][0].length; c++) {
                    scaled[r][c] = (matrices[i][r][c] * multipliers[i]) % mod;
                }
            }

            if (result == null) {
                result = scaled;
            } else {
                result = matrixMultiplyMod(result, scaled, mod);
            }
        }
        return result;
    }

    // ========== Matrix Transpose ==========
    static long[][] transpose(long[][] A) {
        int n = A.length;
        int m = A[0].length;
        long[][] T = new long[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                T[j][i] = A[i][j];
            }
        }
        return T;
    }

    // ========== Matrix Determinant (2x2 and 3x3) ==========
    static long determinant2x2(long[][] A) {
        return A[0][0] * A[1][1] - A[0][1] * A[1][0];
    }

    static long determinant3x3(long[][] A) {
        return A[0][0] * (A[1][1] * A[2][2] - A[1][2] * A[2][1]) -
               A[0][1] * (A[1][0] * A[2][2] - A[1][2] * A[2][0]) +
               A[0][2] * (A[1][0] * A[2][1] - A[1][1] * A[2][0]);
    }

    // ========== Matrix Addition ==========
    static long[][] matrixAdd(long[][] A, long[][] B) {
        int n = A.length;
        int m = A[0].length;
        long[][] C = new long[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    // ========== Matrix Subtraction ==========
    static long[][] matrixSubtract(long[][] A, long[][] B) {
        int n = A.length;
        int m = A[0].length;
        long[][] C = new long[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }
}

