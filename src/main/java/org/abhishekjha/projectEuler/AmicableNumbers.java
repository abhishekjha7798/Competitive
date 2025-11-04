package org.abhishekjha.projectEuler;

/**
 * Amicable numbers are two different numbers so related that the sum of the proper divisors of each is equal to the other number.
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore the sum of the proper divisors of 220 is 284.
 * The proper divisors of 284 are 1, 2, 4, 71 and 142; so the sum of the proper divisors of 284 is 220.
 * Evaluate the sum of all the amicable numbers under 10000.
 */
public class AmicableNumbers {
    static int sumOfProperDivisors(int number) {
        int sum = 1; // 1 is a proper divisor of any number
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                sum += i;
                if (i != number / i) {
                    sum += number / i;
                }
            }
        }
        return sum;
    }

    static int solve(int limit) {
        int totalSum = 0;
        for (int i = 2; i < limit; i++) {
            int partner = sumOfProperDivisors(i);
            if (partner != i && sumOfProperDivisors(partner) == i) {
                totalSum += i;
            }
        }
        return totalSum;
    }

    public static void main(String[] args) {
        int limit = 10000;
        int result = solve(limit);
        System.out.println("The sum of all amicable numbers under " + limit + " is: " + result);
    }
}
