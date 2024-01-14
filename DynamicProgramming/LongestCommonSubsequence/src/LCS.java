import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LCS {
    static int totalMax = 0;

    public static void main(String[] args) {

        LCS lcs = new LCS();
        String S1 = "AGGTAB";
        String S2 = "GXTXAYB";
        int m = S1.length();
        int n = S2.length();


        // Recursion
        System.out.println("Length of LCS is" + " " + recursive(S1, S2, m, n));

        // Memoization
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println("Length of lis is " + memoization(S1, S2, m, n, dp));

        // DP
        System.out.println("Length of lis is " + dp(S1, S2, m, n));

        // Space optimization
        System.out.println("Length of lis is " + spaceOptimize(S1, S2, m, n));
    }

    /**
     * Complexity O(n^2), Space O(n)
     * The outer loop will run from i = 1 to N and the inner loop will run from j = 0 to i and use the recurrence relation to solve the problem.
     */
    private static int binarySearch(int[] input) {
        int n = input.length;
        List<Integer> resultList = new ArrayList<>();

        // Initialize the answer list with the first element of input
        resultList.add(input[0]);

        for (int i = 1; i < n; i++) {
            if (input[i] > resultList.get(resultList.size() - 1)) {
                // If the current number is greater than the last element of the answer list,
                // it means we have found a longer increasing subsequence.
                // Hence, we append the current number to the answer list.
                resultList.add(input[i]);
            } else {
                // If the current number is not greater than the last element of the answer list,
                // we perform a binary search to find the smallest element in the answer list that
                // is greater than or equal to the current number.

                int low = Collections.binarySearch(resultList, input[i]);

                // We update the element at the found position with the current number.
                // By doing this, we are maintaining a sorted order in the answer list.
                if (low < 0) {
                    low = -(low + 1);
                }
                resultList.set(low, input[i]);
            }
        }

        // The size of the answer list represents the length of the longest increasing subsequence.
        return resultList.size();
    }

    /**
     * Complexity O(m*n), Space O(m*n)
     */
    private static int dp(String X, String Y, int m, int n)
    {
        int dp[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Complexity O(m*n), Space O(m)
     */
    private static int spaceOptimized(String X, String Y, int m, int n) {
        // Initializing 2 arrays of size m
        int[] prev = new int[m + 1];
        int[] cur = new int[m + 1];

        for (int idx1 = 1; idx1 < n + 1; idx1++) {
            for (int idx2 = 1; idx2 < m + 1; idx2++) {
                // If matching
                if (X.charAt(idx1 - 1) == Y.charAt(idx2 - 1)) {
                    cur[idx2] = 1 + prev[idx2 - 1];
                }
                else {
                    cur[idx2] = Math.max(cur[idx2 - 1], prev[idx2]);
                }
            }
            prev = Arrays.copyOf(cur, m + 1);
        }
        return cur[m];
    }

    /**
     * Complexity O(m*n), Space O(m*n)
     */
    private static int memoization(String X, String Y, int m, int n, int[][] dp) {
        if (m == 0 || n == 0) {
            return 0;
        }

        // use memoization to avoid redundant recursion
        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        if (X.charAt(m - 1) == Y.charAt(n - 1)) {
            dp[m][n] = 1 + memoization(X, Y, m - 1, n - 1, dp);
            return dp[m][n];
        }

        dp[m][n] = Math.max(memoization(X, Y, m, n - 1, dp), memoization(X, Y, m - 1, n, dp));
        return dp[m][n];
    }


    /**
     * Complexity O(2m*n), Space O(1)
     */
    private static int recursive(String X, String Y, int m, int n)
    {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (X.charAt(m - 1) == Y.charAt(n - 1)) {
            return 1 + recursive(X, Y, m - 1, n - 1);
        }
        else {
            return Math.max(recursive(X, Y, m, n - 1), recursive(X, Y, m - 1, n));
        }
    }
}