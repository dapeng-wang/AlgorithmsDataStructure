import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Knapsack {
    static int totalMax = 0;

    public static void main(String[] args) {

        int[] profit = new int[]{60, 100, 120};
        int[] weight = new int[]{10, 20, 30};
        int w = 50;
        int n = profit.length;

        // Recursion
        System.out.println("Max profit is " + recursive(w, weight, profit, n));

        // Memoization
        // Declare the table dynamically
        int[][] dp = new int[n + 1][w + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("Max profit is " + memoization(w, weight, profit, n, dp));

        // DP
        System.out.println("Max profit is " + dp(w, weight, profit, n));

    }

    /**
     * Complexity O(n * w), Space O(n * w)
     */
    private static int dp(int w, int[] weights, int[] values, int n) {
        int i, j;
        int[][] dp = new int[n + 1][w + 1];

        // Build table dp[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= w; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - weights[i - 1]],
                                        dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][w];
    }

    /**
     * Complexity O(n * w), Space O(n * w) + O(n)
     */
    private static int memoization(int w, int[] weights, int[] values, int n, int[][] dp) {
        // Base condition
        if (n == 0 || w == 0) {
            return 0;
        }

        if (dp[n][w] != -1) {
            return dp[n][w];
        }

        if (weights[n - 1] > w) {
            // Store the value of function call stack in table before return
            return dp[n][w] = memoization(w, weights, values, n - 1, dp);
        } else {
            // Return value of table after storing
            return dp[n][w]
                    = Math.max(values[n - 1] + memoization(w - weights[n - 1], weights, values, n - 1, dp),
                    memoization(w, weights, values, n - 1, dp));
        }
    }


    /**
     * Complexity O(2^n), Space O(n)
     */
    private static int recursive(int w, int[] weights, int[] values, int n) {
        // Base Case
        if (n == 0 || w == 0) {
            return 0;
        }

        if (weights[n - 1] > w) {
            // If weight of the nth item is more than Knapsack capacity w,
            // then this item cannot be included in the optimal solution
            return recursive(w, weights, values, n - 1);
        } else {
            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
            return Math.max(values[n - 1] + recursive(w - weights[n - 1], weights, values, n - 1),
                    recursive(w, weights, values, n - 1));
        }
    }
}