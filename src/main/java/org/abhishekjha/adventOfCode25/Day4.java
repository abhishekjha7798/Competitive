package org.abhishekjha.adventOfCode25;

import org.abhishekjha.utils.FileUtil;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    static boolean isSafe(String[] grid, int x, int y, int rows, int cols) {
        if (grid[x].charAt(y) != '@') {
            return false;
        }
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        int countSym = 0;
        for (int i = 0; i < dx.length; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols) {
                if (grid[newX].charAt(newY) == '@') {
                    countSym++;
                }
            }
        }
        if (countSym > 3) {
            return false;
        }
        return true;
    }
    static int solvePart1(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();
        int countSafe = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isSafe(grid, i, j, rows, cols)) {
                    countSafe++;
                }
            }
        }
        return countSafe;
    }

    static int solvePart2(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();
        int countSafe = 0;
        List<Integer[]> safePositions = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isSafe(grid, i, j, rows, cols)) {
                    safePositions.add(new Integer[]{i, j});
                    countSafe++;
                }
            }
        }
        if (countSafe == 0) {
            return 0;
        }
        for (Integer[] safePosition : safePositions) {
            int x = safePosition[0];
            int y = safePosition[1];
            grid[x] = grid[x].substring(0, y) + '.' + grid[x].substring(y + 1);
        }
        int smallAns = solvePart2(grid);
        return countSafe + smallAns;
    }

    public static void main(String[] args) {
        String input = "..@@.@@@@.\n" +
                "@@@.@.@.@@\n" +
                "@@@@@.@.@@\n" +
                "@.@@@@..@.\n" +
                "@@.@@@@.@@\n" +
                ".@@@@@@@.@\n" +
                ".@.@.@.@@@\n" +
                "@.@@@.@@@@\n" +
                ".@@@@@@@@.\n" +
                "@.@.@@@.@.";

        input = FileUtil.readFileAsString("adventOfCode25/input/Day4.txt");

        String[] grid = input.split("\n");

        int resultPart1 = solvePart1(grid);
        System.out.println("Part 1 Result: " + resultPart1);
        int resultPart2 = solvePart2(grid);
        System.out.println("Part 2 Result: " + resultPart2);
    }
}
