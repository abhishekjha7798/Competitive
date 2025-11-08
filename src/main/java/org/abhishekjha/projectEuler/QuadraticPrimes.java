package org.abhishekjha.projectEuler;

/**
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.
 * The quadratic expression is of the form: n^2 + an + b, where |a| < 1000 and |b| ≤ 1000.
 */

public class QuadraticPrimes {
    static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    static int countConsecutivePrimes(int a, int b) {
        int n = 0;
        while (true) {
            int quadraticValue = n * n + a * n + b;
            if (!isPrime(quadraticValue)) {
                break;
            }
            n++;
        }
        return n;
    }

    static int[] solve(int limitA, int limitB) {
        int maxCount = 0;
        int product = 0;

        for (int a = -limitA + 1; a < limitA; a++) {
            for (int b = -limitB; b <= limitB; b++) {
                int count = countConsecutivePrimes(a, b);
                if (count > maxCount) {
                    maxCount = count;
                    product = a * b;
                }
            }
        }
        return new int[]{product, maxCount};
    }

    public static void main(String[] args) {
        int limitA = 1000;
        int limitB = 1000;
        int[] result = solve(limitA, limitB);
        System.out.println("The product of the coefficients a and b that produce the maximum number of consecutive primes is: " + result[0]);
        System.out.println("The maximum number of consecutive primes produced is: " + result[1]);
    }
}
