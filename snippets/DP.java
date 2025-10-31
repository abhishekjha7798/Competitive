// Dynamic Programming - Common patterns
public class DP {
    // Fibonacci with memoization
    static long[] fib;
    static long fibonacci(int n) {
        if (fib == null) fib = new long[n + 1];
        if (n <= 1) return n;
        if (fib[n] != 0) return fib[n];
        return fib[n] = fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 0/1 Knapsack Problem
    static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    // Longest Common Subsequence (LCS)
    static int lcs(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    // Coin Change Problem - Minimum coins needed
    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    // Longest Increasing Subsequence (LIS) - O(n^2)
    static int lis(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLen = 0;
        for (int len : dp) maxLen = Math.max(maxLen, len);
        return maxLen;
    }

    // Edit Distance (Levenshtein Distance)
    static int editDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    // Climbing Stairs - Number of ways to reach nth stair
    static int climbStairs(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Longest Common Substring (with length)
    static int longestCommonSubstring(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen;
    }

    // Unbounded Knapsack - Items can be used unlimited times
    static int unboundedKnapsack(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 1; i <= capacity; i++) {
            for (int j = 0; j < weights.length; j++) {
                if (weights[j] <= i) {
                    dp[i] = Math.max(dp[i], values[j] + dp[i - weights[j]]);
                }
            }
        }
        return dp[capacity];
    }

    // House Robber - Maximum money that can be robbed
    static int houseRobber(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    // Maximum Product Subarray
    static int maxProductSubarray(int[] nums) {
        if (nums.length == 0) return 0;

        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);
            result = Math.max(result, maxProd);
        }
        return result;
    }

    // Partition Equal Subset Sum
    static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }
}

