import java.util.Arrays;

public class CuttingRod {
    public static void main(String[] args) {

        int[] arr = {1, 5, 8, 9, 10, 17, 17, 20};
        int size = arr.length;

        // Recursion
        System.out.println("Maximum Obtainable Value is " + recursive(arr, size - 1, size));

        // Memoization
        int[][] dp = new int[size][size + 1];
        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println("Maximum Obtainable Value is " + memoization(arr, size - 1, size, dp));

        // DP
        System.out.println("Maximum Obtainable Value is " + dp(arr, size));

    }

    /**
     * Complexity O(n^2), Space O(n^2)
     */
    private static int dp(int[] prices, int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (i == 1) {
                        dp[i][j] = j * prices[i - 1];
                    } else {
                        if (i > j) {
                            dp[i][j] = dp[i - 1][j];
                        } else {
                            dp[i][j] = Math.max(prices[i - 1] + dp[i][j - i], dp[i - 1][j]);
                        }
                    }
                }
            }
        }
        return dp[n][n];
    }

    /**
     * Complexity O(n^2) , Space O(n^2)+O(n)
     */
    private static int memoization(int[] price, int index, int n,
                                   int[][] dp) {

        // base case
        if (index == 0) {
            return n * price[0];
        }

        if (dp[index][n] != -1) {
            return dp[index][n];
        }

        // At any index we have 2 options either cut the rod of this length or not cut it
        int notCut = memoization(price, index - 1, n, dp);
        int cut = Integer.MIN_VALUE;
        int rod_length = index + 1;

        if (rod_length <= n) {
            cut = price[index] + memoization(price, index, n - rod_length, dp);
        }

        return dp[index][n] = Math.max(cut, notCut);
    }

    /**
     * Complexity O(2^n), Space O(n)
     */
    private static int recursive(int[] price, int index, int n) {
        // base case
        if (index == 0) {
            return n * price[0];
        }
        // At any index we have 2 options either cut the rod of this length or not cut it
        int notCut = recursive(price, index - 1, n);
        int cut = Integer.MIN_VALUE;
        int rod_length = index + 1;

        if (rod_length <= n) {
            cut = price[index] + recursive(price, index, n - rod_length);
        }
        return Math.max(notCut, cut);
    }
}