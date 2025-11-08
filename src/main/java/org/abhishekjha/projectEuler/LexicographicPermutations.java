package org.abhishekjha.projectEuler;

/**
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
 * If all the permutations are listed numerically or alphabetically, we call it lexicographic order.
 * The lexicographic permutations of 0, 1 and 2 are:
 * 012   021   102   120   201   210
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */

public class LexicographicPermutations {
    static int[] factorial = new int[11];
    static {
        factorial[0] = 1;
        for (int i = 1; i < factorial.length; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    static String solve(int n, int length) {
        boolean[] used = new boolean[length];
        StringBuilder result = new StringBuilder();
        n--; // Convert to 0-based index

        for (int i = 0; i < length; i++) {
            int fact = factorial[length - 1 - i];
            int index = n / fact;
            n = n % fact;

            for (int j = 0; j < length; j++) {
                if (!used[j]) {
                    if (index == 0) {
                        result.append(j);
                        used[j] = true;
                        break;
                    }
                    index--;
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int n = 1000000;
        int length = 10;
        String result = solve(n, length);
        System.out.println("The " + n + "th lexicographic permutation of the digits 0 to " + (length - 1) + " is: " + result);
    }
}
