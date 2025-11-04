package org.abhishekjha.projectEuler;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all prime numbers below two million.
 */

public class SummationOfPrimes {
    static boolean isPrime(long number) {
        if (number <= 1) return false;
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    static long solve(long limit) {
        long sum = 0;
        for (long i = 2; i < limit; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        long limit = 2000000;
        long result = solve(limit);
        System.out.println("The sum of all prime numbers below " + limit + " is: " + result);
    }
}
