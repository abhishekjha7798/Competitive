package org.abhishekjha.projectEuler;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */

public class LargestPrimeFactor {
    static boolean isPrime(long number) {
        if (number <= 1L) return false;
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    static long solve(long n) {
        long largestPrime = -1L;
        for (long i = 1; i * i <= n; i++) {
            if (n%i == 0) {
                if (isPrime(i) && i > largestPrime) {
                    largestPrime = i;
                } else if (n/i != i && isPrime(n/i) && n/i > largestPrime) {
                    largestPrime = n/i;
                }
            }
        }
        return largestPrime;
    }

    public static void main(String[] args) {
        long n = 600851475143L;
        long result = solve(n);
        System.out.println("Largest prime factor of " + n + " is: " + result);
    }
}
