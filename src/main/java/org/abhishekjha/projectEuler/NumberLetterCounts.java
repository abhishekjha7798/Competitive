package org.abhishekjha.projectEuler;

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five,
 * then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters
 * and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
 */

public class NumberLetterCounts {
    static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
    };
    static final String[] teens = {
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
    };
    static final String[] tens = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety",
    };
    static String numberToWords(int n) {
        if (n == 1000) {
            return "one thousand";
        } else if (n >= 100) {
            int hundreds = n / 100;
            int remainder = n % 100;
            if (remainder == 0) {
                return units[hundreds] + " hundred";
            } else {
                return units[hundreds] + " hundred and " + numberToWords(remainder);
            }
        } else if (n >= 20) {
            int tenPart = n / 10;
            int unitPart = n % 10;
            return tens[tenPart] + (unitPart != 0 ? " " + units[unitPart] : "");
        } else if (n >= 10) {
            return teens[n - 10];
        } else {
            return units[n];
        }
    }
    static int countLetters(String words) {
        return words.replace(" ", "").length();
    }
    static int solve(int limit) {
        int totalLetters = 0;
        for (int i = 1; i <= limit; i++) {
            String words = numberToWords(i);
            totalLetters += countLetters(words);
        }
        return totalLetters;
    }
    public static void main(String[] args) {
        int limit = 1000;
        int result = solve(limit);
        System.out.println("The total number of letters used from 1 to " + limit + " is: " + result);
    }
}
