package org.abhishekjha.projectEuler;

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
 *    3
 *   7 4
 *  2 4 6
 * 8 5 9 3
 * That is, 3 + 7 + 4 + 9 = 23.
 * Find the maximum total from top to bottom of the triangle below:
                                                       75
                                                     95 64
                                                   17 47 82
                                                 18 35 87 10
                                               20 04 82 47 65
                                              19 01 23 75 03 34
                                            88 02 77 73 07 63 67
                                           99 65 04 28 06 16 70 92
                                         41 41 26 56 83 40 80 70 33
                                       41 48 72 33 47 32 37 16 94 29
                                      53 71 44 65 25 43 91 52 97 51 14
                                    70 11 33 28 77 73 17 78 39 68 17 57
                                  91 71 52 38 17 14 91 43 58 50 27 29 48
                                63 66 04 68 89 53 67 30 73 16 69 87 40 31
 *                             04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
 * NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route.
 * However, Problem 67, is the same challenge with a triangle containing one-hundred rows;
 * it cannot be solved by brute force, and requires a clever method!
 */

// solve first using recursion, then by dynamic programming
public class MaximumPathSum1 {
    static int[][] triangle = {
            {75},
            {95, 64},
            {17, 47, 82},
            {18, 35, 87, 10},
            {20, 4, 82, 47, 65},
            {19, 1, 23, 75, 3, 34},
            {88, 2, 77, 73, 7, 63, 67},
            {99, 65, 4, 28, 6, 16, 70, 92},
            {41, 41, 26, 56, 83, 40, 80, 70, 33},
            {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
            {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
            {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
            {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
            {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
            {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23}
    };

    // using recursion
    static long solve(int i, int j) {
        if (i == triangle.length) {
            return 0;
        }
        long leftSum = solve(i + 1, j);
        long rightSum = solve(i + 1, j + 1);
        return triangle[i][j] + Math.max(leftSum, rightSum);
    }

    static long solveDP() {
        int n = triangle.length;
        long[][] dp = new long[n][n];

        // Initialize the last row of dp with the last row of the triangle
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle[n - 1][j];
        }

        // Fill the dp table from bottom to top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        long maxTotal = solve(0, 0);
        long millis = System.currentTimeMillis();
//        long maxTotal = solveDP();
        System.out.println("Maximum total from top to bottom of the triangle is: " + maxTotal);
        System.out.println("Time taken: " + (System.currentTimeMillis() - millis) + " ms");
    }
}
