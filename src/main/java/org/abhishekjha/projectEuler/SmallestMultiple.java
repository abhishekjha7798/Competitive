package org.abhishekjha.projectEuler;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all the numbers from 1 to 20?
 */

public class SmallestMultiple {
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static long lcm(long a, long b) {
        return a/gcd(a, b)*b;
    }

    static long lcmUptoN(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = lcm(result, i);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 20;
        long result = lcmUptoN(n);
        System.out.println("Smallest multiple evenly divisible by all numbers from 1 to " + n + " is: " + result);
    }
}
