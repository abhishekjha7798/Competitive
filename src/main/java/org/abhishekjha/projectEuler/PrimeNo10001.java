package org.abhishekjha.projectEuler;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10,001st prime number?
 */

public class PrimeNo10001 {
    static boolean isPrime(long number) {
        if (number <= 1) return false;
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    static long solve(long n) {
        int count = 0;
        long candidate = 1;
        while (count < n) {
            candidate++;
            if (isPrime(candidate)) {
                count++;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        long n = 10001;
        long result = solve(n);
        System.out.println("The " + n + "th prime number is: " + result);
    }
}
