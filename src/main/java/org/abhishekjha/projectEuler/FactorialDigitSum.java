package org.abhishekjha.projectEuler;

/**
 * Find the sum of the digits in the number 100!
 */
public class FactorialDigitSum {
    static int solve(int num) {
        int[] digits = new int[200]; // Array to hold the digits of the factorial
        digits[0] = 1;
        int digitCount = 1;

        for (int i = 1; i <= num; i++) {
            int carry = 0;
            for (int j = 0; j < digitCount; j++) {
                int product = digits[j] * i + carry;
                digits[j] = product % 10;
                carry = product / 10;
            }
            while (carry > 0) {
                digits[digitCount++] = carry % 10;
                carry = carry / 10;
            }
        }

        int sum = 0;
        for (int digit : digits) {
            sum += digit;
        }
        return sum;
    }
    public static void main(String[] args) {
        int num = 100;
        int result = solve(num);
        System.out.println("The sum of the digits in the number " + num + "! is: " + result);
    }
}
