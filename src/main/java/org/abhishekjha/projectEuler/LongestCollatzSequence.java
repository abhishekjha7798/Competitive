package org.abhishekjha.projectEuler;


/**
 * The following iterative sequence is defined for the set of positive integers:
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * Using the rule above and starting with 13, we generate the following sequence:
 * 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
 * Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * Which starting number, under one million, produces the longest chain?
 * NOTE:Once the chain starts the terms are allowed to go above one million.
*/

public class LongestCollatzSequence {
    static long solve(long limit) {
        long maxLength = 0;
        long startingNumber = 0;

        for (long i = 1; i < limit; i++) {
            long n = i;
            long length = 1;

            while (n != 1) {
                if (n % 2 == 0) {
                    n /= 2;
                } else {
                    n = 3 * n + 1;
                }
                length++;
            }

            if (length > maxLength) {
                maxLength = length;
                startingNumber = i;
            }
        }

        return startingNumber;
    }

    public static void main(String[] args) {
        long limit = 1000000;
        long result = solve(limit);
        System.out.println("The starting number under " + limit + " that produces the longest Collatz sequence is: " + result);
    }
}
