package org.abhishekjha.projectEuler;

/**
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
 * there are exactly 6 routes to the bottom right corner.
 * How many such routes are there through a 20×20 grid?
 */

public class LatticePaths {
    static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Using combinatorial formula: C(2n, n) = (2n)! / (n!)^2
    static long solve(int n) {
        long ncr = 1;
        for (int i = 0; i < n; i++) {
            ncr*= (2L *n-i);
            ncr/= (i+1);
        }
        return ncr;
    }

    public static void main(String[] args) {
        int gridSize = 20;
        long result = solve(gridSize);
        System.out.println("Number of lattice paths through a " + gridSize + "x" + gridSize + " grid is: " + result);
    }
}
