package org.abhishekjha.projectEuler;

/**
 * A Pythagorean triplet is a set of three natural numbers, a, b, c, for which,
 * a^2 + b^2 = c^2.
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product abc.
 */

public class PythagoreanTriplet {
    static long solve(int sum) {
        for (int i=1; i <= sum; i++) {
            for (int j=i; i+j <= sum; j++) {
                int k = sum - i - j;
                if (i*i + j*j == k*k) {
                    return (long)i * j * k;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int sum = 1000;
        long result = solve(sum);
        System.out.println("The product abc of the Pythagorean triplet for which a + b + c = " + sum + " is: " + result);
    }
}
