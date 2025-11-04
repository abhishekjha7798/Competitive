package org.abhishekjha.projectEuler;

/**
 * The sum of the squares of the first ten natural numbers is,
 * 1^2 + 2^2 + ... + 10^2 = 385.
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 55^2 = 3025.
 * Hence, the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 - 385 = 2640.
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */

public class SumSquareDifference {
    static long solve(int n) {
        long sumOfSquares = 0;
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sumOfSquares += i * i;
            sum += i;
        }
        long squareOfSum = sum * sum;
        return squareOfSum - sumOfSquares;
    }

    public static void main(String[] args) {
        int n = 100;
        long result = solve(n);
        System.out.println("Difference between the sum of the squares and the square of the sum of the first " + n + " natural numbers is: " + result);
    }
}
