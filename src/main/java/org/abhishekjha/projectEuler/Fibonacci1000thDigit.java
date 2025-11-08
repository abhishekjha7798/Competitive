package org.abhishekjha.projectEuler;

/**
 * The Fibonacci sequence is defined by the recurrence relation:
 * Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
 * Hence, the sequence will be:
 * F1 = 1
 * F2 = 1
 * F3 = 2
 * F4 = 3
 * and so on.
 * What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 * */

public class Fibonacci1000thDigit {
    static long numberOfDigits(int[] n) {
        long ans = 0;
        for (int digit : n) {
            if (digit != 0) {
                break;
            }
            ans++;
        }
        return n.length - ans;
    }

    static long solve(int limit) {
        int[] a = new int[limit + 1];
        int[] b = new int[limit + 1];
        a[limit] = 1;
        b[limit] = 1;
        long index = 2;

        while (true) {
            int [] c = new int[limit + 1];
            int carry = 0;
            for (int i = limit; i >= 0; i--) {
                int sum = a[i] + b[i] + carry;
                c[i] = sum % 10;
                carry = sum / 10;
            }

            index++;
            if (numberOfDigits(c) == limit) {
                return index;
            }
            a = b;
            b = c;
        }
    }

    public static void main(String[] args) {
        int limit = 1000;
        long result = solve(limit);
        System.out.println("The index of the first term in the Fibonacci sequence to contain " + limit + " digits is: " + result);
    }
}
