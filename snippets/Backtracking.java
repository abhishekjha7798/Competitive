// Backtracking Algorithms - N-Queens, Sudoku, Permutations, Subsets
import java.util.*;

public class Backtracking {
    // ========== N-Queens Problem ==========
    static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize board
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        solveNQueensHelper(board, 0, result, n);
        return result;
    }

    static void solveNQueensHelper(char[][] board, int row, List<List<String>> result, int n) {
        if (row == n) {
            result.add(buildSolution(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValidQueenPlacement(board, row, col, n)) {
                board[row][col] = 'Q';
                solveNQueensHelper(board, row + 1, result, n);
                board[row][col] = '.';
            }
        }
    }

    static boolean isValidQueenPlacement(char[][] board, int row, int col, int n) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // Check diagonal (top-left)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // Check diagonal (top-right)
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    static List<String> buildSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }

    // ========== Sudoku Solver ==========
    static boolean solveSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValidSudokuPlacement(board, i, j, num)) {
                            board[i][j] = num;
                            if (solveSudoku(board)) return true;
                            board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isValidSudokuPlacement(int[][] board, int row, int col, int num) {
        // Check row
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == num) return false;
        }

        // Check column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) return false;
        }

        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }

        return true;
    }

    // ========== Generate All Permutations ==========
    static List<List<Integer>> generatePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        permutationsHelper(nums, current, used, result);
        return result;
    }

    static void permutationsHelper(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums[i]);
                permutationsHelper(nums, current, used, result);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    // ========== Generate All Subsets ==========
    static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        subsetsHelper(nums, 0, current, result);
        return result;
    }

    static void subsetsHelper(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            subsetsHelper(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // ========== Combination Sum ==========
    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> current = new ArrayList<>();

        combinationSumHelper(candidates, target, 0, current, result);
        return result;
    }

    static void combinationSumHelper(int[] candidates, int target, int index,
                                    List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0 || index == candidates.length) return;

        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                current.add(candidates[i]);
                combinationSumHelper(candidates, target - candidates[i], i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    // ========== Rat in Maze ==========
    static List<String> ratInMaze(int[][] maze, int n) {
        List<String> result = new ArrayList<>();
        String path = "";
        boolean[][] visited = new boolean[n][n];

        if (maze[0][0] == 1 && maze[n-1][n-1] == 1) {
            ratInMazeHelper(maze, 0, 0, n, path, result, visited);
        }

        return result;
    }

    static void ratInMazeHelper(int[][] maze, int x, int y, int n, String path,
                               List<String> result, boolean[][] visited) {
        if (x == n - 1 && y == n - 1) {
            result.add(path);
            return;
        }

        // Down
        if (x + 1 < n && maze[x + 1][y] == 1 && !visited[x + 1][y]) {
            visited[x + 1][y] = true;
            ratInMazeHelper(maze, x + 1, y, n, path + "D", result, visited);
            visited[x + 1][y] = false;
        }

        // Left
        if (y - 1 >= 0 && maze[x][y - 1] == 1 && !visited[x][y - 1]) {
            visited[x][y - 1] = true;
            ratInMazeHelper(maze, x, y - 1, n, path + "L", result, visited);
            visited[x][y - 1] = false;
        }

        // Right
        if (y + 1 < n && maze[x][y + 1] == 1 && !visited[x][y + 1]) {
            visited[x][y + 1] = true;
            ratInMazeHelper(maze, x, y + 1, n, path + "R", result, visited);
            visited[x][y + 1] = false;
        }

        // Up
        if (x - 1 >= 0 && maze[x - 1][y] == 1 && !visited[x - 1][y]) {
            visited[x - 1][y] = true;
            ratInMazeHelper(maze, x - 1, y, n, path + "U", result, visited);
            visited[x - 1][y] = false;
        }
    }

    // ========== Word Search in Grid ==========
    static boolean wordSearch(char[][] grid, String word) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if (wordSearchHelper(grid, word, 0, i, j, visited)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    static boolean wordSearchHelper(char[][] grid, String word, int index, int row, int col, boolean[][] visited) {
        if (index == word.length() - 1) {
            return true;
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];

            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length &&
                !visited[newRow][newCol] && grid[newRow][newCol] == word.charAt(index + 1)) {
                visited[newRow][newCol] = true;
                if (wordSearchHelper(grid, word, index + 1, newRow, newCol, visited)) {
                    return true;
                }
                visited[newRow][newCol] = false;
            }
        }
        return false;
    }

    // ========== Partition String into Palindromes ==========
    static List<List<String>> partitionPalindromes(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> current = new ArrayList<>();
        partitionPalindromesHelper(s, 0, current, result);
        return result;
    }

    static void partitionPalindromesHelper(String s, int start, List<String> current, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                current.add(s.substring(start, end + 1));
                partitionPalindromesHelper(s, end + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}

