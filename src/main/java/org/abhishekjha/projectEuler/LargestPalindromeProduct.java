package org.abhishekjha.projectEuler;

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class LargestPalindromeProduct {
    static boolean isPalindrome(int number) {
        String str = Integer.toString(number);
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    static int solve(int limit) {
        int largestPalindrome = 0;
        for (int i = limit; i >= 100; i--) {
            for (int j = i; j >= 100; j--) {
                int product = i * j;
                if (isPalindrome(product) && product > largestPalindrome) {
                    largestPalindrome = product;
                }
            }
        }
        return largestPalindrome;
    }

    public static void main(String[] args) {
        int limit = 999;
        int result = solve(limit);
        System.out.println("Largest palindrome made from the product of two 3-digit numbers is: " + result);
    }
}
