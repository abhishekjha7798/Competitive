package org.abhishekjha.projectEuler;

public class NumberSpiralDiagonals {
    static long solve(int n) {
        long sum = 1; // Starting with the center value
        int currentNumber = 1;
        int step = 2;
        while (currentNumber < n*n) {
            for (int i = 0; i < 4; i++) {
                currentNumber += step;
                sum += currentNumber;
            }
            step += 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        int size = 1001; // Size of the spiral (must be odd)
        long result = solve(size);
        System.out.println("The sum of the numbers on the diagonals in a " + size + "x" + size + " spiral is: " + result);
    }
}
