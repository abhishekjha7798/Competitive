// Game Theory Algorithms - Nim, Sprague-Grundy, Game States
import java.util.*;

public class GameTheory {
    // ========== Nim Game - XOR of all piles ==========
    static int nimWinner(int[] piles) {
        int xorSum = 0;
        for (int pile : piles) {
            xorSum ^= pile;
        }
        return xorSum == 0 ? 0 : 1;  // 0 = second player wins, 1 = first player wins
    }

    static int[] nimWinningMove(int[] piles) {
        int xorSum = 0;
        for (int pile : piles) {
            xorSum ^= pile;
        }

        for (int i = 0; i < piles.length; i++) {
            int target = piles[i] ^ xorSum;
            if (target < piles[i]) {
                return new int[]{i, piles[i] - target};  // Remove from pile i
            }
        }
        return null;
    }

    // ========== Grundy Number (Sprague-Grundy Theorem) ==========
    static int computeGrundy(int n, Map<Integer, Integer> memo, int[] moves) {
        if (n == 0) return 0;
        if (memo.containsKey(n)) return memo.get(n);

        Set<Integer> reachable = new HashSet<>();
        for (int move : moves) {
            if (n - move >= 0) {
                reachable.add(computeGrundy(n - move, memo, moves));
            }
        }

        int grundy = 0;
        while (reachable.contains(grundy)) {
            grundy++;
        }

        memo.put(n, grundy);
        return grundy;
    }

    // ========== Grundy Numbers for Multiple Games ==========
    static int computeGrundyMultiple(int[] positions, Map<Integer, Integer> memo, int[] moves) {
        int xorSum = 0;
        for (int pos : positions) {
            xorSum ^= computeGrundy(pos, memo, moves);
        }
        return xorSum;
    }

    // ========== Game State Memoization - DP approach ==========
    static boolean canWin(int n, Map<Integer, Boolean> memo) {
        if (n == 0) return false;
        if (memo.containsKey(n)) return memo.get(n);

        // Try all possible moves (example: subtract 1 or 2)
        for (int move : new int[]{1, 2}) {
            if (n - move >= 0 && !canWin(n - move, memo)) {
                memo.put(n, true);
                return true;
            }
        }

        memo.put(n, false);
        return false;
    }

    // ========== Fibonacci Nim ==========
    static boolean fibonacciNimWinner(int[] piles) {
        // First player wins if total stones is not a Fibonacci number
        long total = 0;
        for (int pile : piles) {
            total += pile;
        }

        // Generate Fibonacci numbers up to total
        Set<Long> fibonacci = new HashSet<>();
        long a = 1, b = 2;
        fibonacci.add(a);
        fibonacci.add(b);
        while (b <= total) {
            long temp = a + b;
            fibonacci.add(temp);
            a = b;
            b = temp;
        }

        return !fibonacci.contains(total);
    }

    // ========== Wythoff's Game ==========
    static boolean wythoffWinner(int a, int b) {
        // (a, b) is losing position if they are Wythoff pair
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        int k = (int) (a / goldenRatio);

        int a0 = (int) (k * goldenRatio);
        int b0 = (int) (k * goldenRatio * goldenRatio);

        return !(a == a0 && b == b0) && !(a == b0 && b == a0);
    }

    // ========== Coin Change Game - Minimum Moves ==========
    static int coinChangeGame(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount];
    }

    // ========== Optimal Strategy for Game ==========
    // Pick from beginning or end of array, maximize score difference
    static int optimalGameStrategy(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        // Base case: single element
        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
        }

        // Fill for chains of length 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(arr[i] - dp[i + 1][j],
                                   arr[j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }

    // ========== Box Stacking - DP Game ==========
    static int boxStacking(int[][] boxes) {
        // Sort by area
        Arrays.sort(boxes, (a, b) -> Integer.compare(a[0] * a[1], b[0] * b[1]));

        int n = boxes.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = boxes[i][2];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (boxes[j][0] < boxes[i][0] &&
                    boxes[j][1] < boxes[i][1] &&
                    boxes[j][2] < boxes[i][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + boxes[i][2]);
                }
            }
        }

        int maxHeight = 0;
        for (int h : dp) {
            maxHeight = Math.max(maxHeight, h);
        }
        return maxHeight;
    }
}

