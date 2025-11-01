// Combinatorics Algorithms - nCr, nPr, Catalan, etc.
import java.util.*;

public class Combinatorics {
    static final long MOD = 1000000007;

    // ========== Factorial ==========
    static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    static long factorialMod(int n, long mod) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % mod;
        }
        return result;
    }

    // ========== nCr - Combination ==========
    static long nCr(int n, int r) {
        if (r > n || r < 0) return 0;
        if (r == 0 || r == n) return 1;

        long[] numerator = new long[r];
        for (int i = 0; i < r; i++) {
            numerator[i] = n - i;
        }

        for (int i = 2; i <= r; i++) {
            long divisor = i;
            for (int j = 0; j < r; j++) {
                if (numerator[j] % divisor == 0) {
                    numerator[j] /= divisor;
                    divisor = 1;
                    break;
                }
            }
            if (divisor > 1) {
                for (int j = 0; j < r; j++) {
                    if (divisor > 1 && numerator[j] % divisor == 0) {
                        numerator[j] /= divisor;
                        divisor = 1;
                    }
                }
            }
        }

        long result = 1;
        for (long num : numerator) {
            result *= num;
        }
        return result;
    }

    // ========== nCr with MOD using modular inverse ==========
    static long nCrMod(int n, int r, long mod) {
        if (r > n || r < 0) return 0;
        if (r == 0 || r == n) return 1;

        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % mod;
        }

        long numerator = fact[n];
        long denominator = (fact[r] * fact[n - r]) % mod;
        return (numerator * modInverse(denominator, mod)) % mod;
    }

    static long modInverse(long a, long mod) {
        return modPow(a, mod - 2, mod);
    }

    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // ========== nPr - Permutation ==========
    static long nPr(int n, int r) {
        if (r > n || r < 0) return 0;
        return factorial(n) / factorial(n - r);
    }

    static long nPrMod(int n, int r, long mod) {
        if (r > n || r < 0) return 0;
        long result = 1;
        for (int i = 0; i < r; i++) {
            result = (result * (n - i)) % mod;
        }
        return result;
    }

    // ========== Catalan Numbers ==========
    static long catalan(int n) {
        return nCr(2 * n, n) / (n + 1);
    }

    static long catalanMod(int n, long mod) {
        long c = nCrMod(2 * n, n, mod);
        return (c * modInverse(n + 1, mod)) % mod;
    }

    // ========== Precompute Catalan Numbers ==========
    static long[] precomputeCatalan(int n, long mod) {
        long[] catalan = new long[n + 1];
        catalan[0] = catalan[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                catalan[i] = (catalan[i] + (catalan[j] * catalan[i - 1 - j]) % mod) % mod;
            }
        }
        return catalan;
    }

    // ========== Stirling Numbers (First Kind) ==========
    static long[][] stirlingFirst(int n) {
        long[][] s = new long[n + 1][n + 1];
        s[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                s[i][j] = (s[i - 1][j - 1] + (i - 1) * s[i - 1][j]);
            }
        }
        return s;
    }

    // ========== Stirling Numbers (Second Kind) ==========
    static long[][] stirlingSecond(int n) {
        long[][] s = new long[n + 1][n + 1];
        s[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                s[i][j] = (j * s[i - 1][j] + s[i - 1][j - 1]);
            }
        }
        return s;
    }

    // ========== Bell Numbers - Number of partitions ==========
    static long[] bellNumbers(int n) {
        long[][] bell = new long[n + 1][n + 1];
        bell[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            bell[i][0] = bell[i - 1][i - 1];
            for (int j = 1; j <= i; j++) {
                bell[i][j] = (bell[i][j - 1] + bell[i - 1][j - 1]);
            }
        }

        long[] result = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = bell[i][0];
        }
        return result;
    }

    // ========== Derangements - Permutations with no fixed points ==========
    static long derangements(int n) {
        if (n == 0) return 1;
        if (n == 1) return 0;

        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }

    // ========== Number of ways to partition n into k parts ==========
    static long partitions(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (k == 1 || k == n) return 1;
        if (k > n) return 0;

        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp[i][j] = (j * dp[i - 1][j] + dp[i - 1][j - 1]);
            }
        }
        return dp[n][k];
    }

    // ========== Pascal's Triangle ==========
    static long[][] pascalTriangle(int n) {
        long[][] pascal = new long[n + 1][];

        for (int i = 0; i <= n; i++) {
            pascal[i] = new long[i + 1];
            pascal[i][0] = pascal[i][i] = 1;

            for (int j = 1; j < i; j++) {
                pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
            }
        }
        return pascal;
    }
}

