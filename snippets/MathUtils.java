// Math utilities - Common mathematical algorithms
public class MathUtils {
    // Prime checking
    static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Modular exponentiation - (base^exp) % mod
    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) result = (result * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return result;
    }

    // Modular multiplicative inverse using Extended Euclidean Algorithm
    static long modInverse(long a, long m) {
        long m0 = m, x0 = 0, x1 = 1;
        if (m == 1) return 0;
        while (a > 1) {
            long q = a / m;
            long t = m;
            m = a % m;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }
        if (x1 < 0) x1 += m0;
        return x1;
    }

    // Factorial modulo - Calculate n! % mod
    static long factorialMod(int n, long mod) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % mod;
        }
        return result;
    }

    // nCr (Combination) modulo using precomputed factorials
    static long nCr(int n, int r, long[] fact, long mod) {
        if (r > n || r < 0) return 0;
        long num = fact[n];
        long den = (fact[r] * fact[n - r]) % mod;
        return (num * modInverse(den, mod)) % mod;
    }

    // Power function
    static long power(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) result *= base;
            base *= base;
            exp /= 2;
        }
        return result;
    }

    // Digit sum
    static int digitSum(long n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    // Count digits
    static int countDigits(long n) {
        if (n == 0) return 1;
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }
}

