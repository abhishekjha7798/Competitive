package org.abhishekjha.projectEuler;

import java.math.BigInteger;

/**
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * What is the sum of the digits of the number 2^1000?
 */

public class PowerDigitSum {
    static String multiplyBy2(String number) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));
            int product = digit * 2 + carry;
            result.append(product % 10);
            carry = product / 10;
        }
        if (carry > 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }
    static int solveByBigInteger(int exponent) {
        String numberStr = BigInteger.valueOf(2).pow(exponent).toString();
        int sum = 0;
        for (char digitChar : numberStr.toCharArray()) {
            sum += Character.getNumericValue(digitChar);
        }
        return sum;
    }

    static int solve(int exponent) {
        String numberStr = "1";
        for (int i = 0; i < exponent; i++) {
            numberStr = multiplyBy2(numberStr);
        }
        int sum = 0;
        for (char digitChar : numberStr.toCharArray()) {
            sum += Character.getNumericValue(digitChar);
        }
        return sum;
    }

    public static void main(String[] args) {
        int exponent = 1000;
        int result = solve(exponent);
        System.out.println("The sum of the digits of the number 2^" + exponent + " is: " + result);
    }
}
