package org.abhishekjha.adventOfCode25;

import org.abhishekjha.utils.FileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 4: Printing Department ---
 * You ride the escalator down to the printing department. They're clearly getting ready for Christmas; they have lots of large rolls of paper everywhere, and there's even a massive printer in the corner (to handle the really big print jobs).
 *
 * Decorating here will be easy: they can make their own decorations. What you really need is a way to get further into the North Pole base while the elevators are offline.
 *
 * "Actually, maybe we can help with that," one of the Elves replies when you ask for help. "We're pretty sure there's a cafeteria on the other side of the back wall. If we could break through the wall, you'd be able to keep moving. It's too bad all of our forklifts are so busy moving those big rolls of paper around."
 *
 * If you can optimize the work the forklifts are doing, maybe they would have time to spare to break through the wall.
 *
 * The rolls of paper (@) are arranged on a large grid; the Elves even have a helpful diagram (your puzzle input) indicating where everything is located.
 *
 * For example:
 *
 * ..@@.@@@@.
 * @@@.@.@.@@
 * @@@@@.@.@@
 * @.@@@@..@.
 * @@.@@@@.@@
 * .@@@@@@@.@
 * .@.@.@.@@@
 * @.@@@.@@@@
 * .@@@@@@@@.
 * @.@.@@@.@.
 * The forklifts can only access a roll of paper if there are fewer than four rolls of paper in the eight adjacent positions. If you can figure out which rolls of paper the forklifts can access, they'll spend less time looking and more time breaking down the wall to the cafeteria.
 *
 * In this example, there are 13 rolls of paper that can be accessed by a forklift (marked with x):
 *
 * ..xx.xx@x.
 * x@@.@.@.@@
 * @@@@@.x.@@
 * @.@@@@..@.
 * x@.@@@@.@x
 * .@@@@@@@.@
 * .@.@.@.@@@
 * x.@@@.@@@@
 * .@@@@@@@@.
 * x.x.@@@.x.
 * Consider your complete diagram of the paper roll locations. How many rolls of paper can be accessed by a forklift?
 *
 * Your puzzle answer was 1428.
 *
 * --- Part Two ---
 * Now, the Elves just need help accessing as much of the paper as they can.
 *
 * Once a roll of paper can be accessed by a forklift, it can be removed. Once a roll of paper is removed, the forklifts might be able to access more rolls of paper, which they might also be able to remove. How many total rolls of paper could the Elves remove if they keep repeating this process?
 *
 * Starting with the same example as above, here is one way you could remove as many rolls of paper as possible, using highlighted @ to indicate that a roll of paper is about to be removed, and using x to indicate that a roll of paper was just removed:
 *
 * Initial state:
 * ..@@.@@@@.
 * @@@.@.@.@@
 * @@@@@.@.@@
 * @.@@@@..@.
 * @@.@@@@.@@
 * .@@@@@@@.@
 * .@.@.@.@@@
 * @.@@@.@@@@
 * .@@@@@@@@.
 * @.@.@@@.@.
 *
 * Remove 13 rolls of paper:
 * ..xx.xx@x.
 * x@@.@.@.@@
 * @@@@@.x.@@
 * @.@@@@..@.
 * x@.@@@@.@x
 * .@@@@@@@.@
 * .@.@.@.@@@
 * x.@@@.@@@@
 * .@@@@@@@@.
 * x.x.@@@.x.
 *
 * Remove 12 rolls of paper:
 * .......x..
 * .@@.x.x.@x
 * x@@@@...@@
 * x.@@@@..x.
 * .@.@@@@.x.
 * .x@@@@@@.x
 * .x.@.@.@@@
 * ..@@@.@@@@
 * .x@@@@@@@.
 * ....@@@...
 *
 * Remove 7 rolls of paper:
 * ..........
 * .x@.....x.
 * .@@@@...xx
 * ..@@@@....
 * .x.@@@@...
 * ..@@@@@@..
 * ...@.@.@@x
 * ..@@@.@@@@
 * ..x@@@@@@.
 * ....@@@...
 *
 * Remove 5 rolls of paper:
 * ..........
 * ..x.......
 * .x@@@.....
 * ..@@@@....
 * ...@@@@...
 * ..x@@@@@..
 * ...@.@.@@.
 * ..x@@.@@@x
 * ...@@@@@@.
 * ....@@@...
 *
 * Remove 2 rolls of paper:
 * ..........
 * ..........
 * ..x@@.....
 * ..@@@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@x.
 * ....@@@...
 *
 * Remove 1 roll of paper:
 * ..........
 * ..........
 * ...@@.....
 * ..x@@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@..
 * ....@@@...
 *
 * Remove 1 roll of paper:
 * ..........
 * ..........
 * ...x@.....
 * ...@@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@..
 * ....@@@...
 *
 * Remove 1 roll of paper:
 * ..........
 * ..........
 * ....x.....
 * ...@@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@..
 * ....@@@...
 *
 * Remove 1 roll of paper:
 * ..........
 * ..........
 * ..........
 * ...x@@....
 * ...@@@@...
 * ...@@@@@..
 * ...@.@.@@.
 * ...@@.@@@.
 * ...@@@@@..
 * ....@@@...
 * Stop once no more rolls of paper are accessible by a forklift. In this example, a total of 43 rolls of paper can be removed.
 *
 * Start with your original diagram. How many rolls of paper in total can be removed by the Elves and their forklifts?
 *
 * Your puzzle answer was 8936.
 *
 * Both parts of this puzzle are complete! They provide two gold stars: **
 */
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
