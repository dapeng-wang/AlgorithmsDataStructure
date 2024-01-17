import java.util.Arrays;

public class NonAdjacentSum {
    static int totalMax = 0;

    public static void main(String[] args) {

        int[] arr = {5, 5, 10, 100, 10, 5};
        int n = 6;

        // Recursion
        System.out.println("Max Non Adjacent Sum is " + recursive(arr, 0, n));

        // Memoization
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println("Max Non Adjacent Sum is " + memoization(arr, 0, dp));

        // DP
        System.out.println("Max Non Adjacent Sum is " + dp(arr, n));
    }

    /**
     * Complexity O(n), Space O(n)
     */
    private static int dp(int[] arr, int n) {
        // Declare dp array
        int[][] dp = new int[n][2];
        if (n == 1) {
            return arr[0];
        }

        // Initialize the values in dp array
        dp[0][0] = 0;
        dp[0][1] = arr[0];

        // Loop to find the maximum possible sum
        for (int i = 1; i < n; i++) {
            dp[i][1] = dp[i - 1][0] + arr[i];
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
        }

        // Return the maximum sum
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }


    /**
     * Complexity O(n), Space O(n) + O(n) one is recursive stack space and another O(N) is for dp array.
     */
    private static int memoization(int[] nums, int idx, int[] dp) {
        // base case: if the current index is out of bounds, return 0
        if (idx >= nums.length) {
            return 0;
        }

        // if the result for the current index has already been computed, return it
        if (dp[idx] != -1) {
            return dp[idx];
        }

        // recursive case: compute the maximum sum by either skipping the current element or including it
        dp[idx] = Math.max(memoization(nums, idx + 1, dp), nums[idx] + memoization(nums, idx + 2, dp));

        return dp[idx];
    }


    /**
     * Complexity O(2^n), Space O(n)
     */
    private static int recursive(int[] nums, int idx, int n) {
        if (idx >= n) {
            return 0;
        }
        return Math.max(nums[idx] + recursive(nums, idx + 2, n),
                recursive(nums, idx + 1, n));
    }
}