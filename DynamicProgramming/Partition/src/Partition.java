import java.util.Arrays;

public class Partition {

    public static void main(String[] args) {

        int[] arr = {3, 1, 5, 9, 12};
        int n = arr.length;
        boolean result = true;


        // Calculate sum of the elements in array
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // If sum is odd, there cannot be two subsets with equal sum
        if (sum % 2 != 0) {
            result = false;
        }

        // Recursion
        if (result && recursive(arr, n, sum / 2)) {
            System.out.println("Can be divided into two subsets of equal sum");
        } else {
            System.out.println("Can not be divided into two subsets of equal sum");
        }


        // Memoization
        // Declare the table dynamically
        int[][] dp = new int[n + 1][sum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        if (result && memoization(arr, n, sum / 2, dp) == 1) {
            System.out.println("Can be divided into two subsets of equal sum");
        } else {
            System.out.println("Can not be divided into two subsets of equal sum");
        }

        // DP
        if (result && dp(arr, n, sum / 2)) {
            System.out.println("Can be divided into two subsets of equal sum");
        } else {
            System.out.println("Can not be divided into two subsets of equal sum");
        }
    }

    /**
     * Complexity O(n * w), Space O(n * w)
     */
    private static boolean dp(int[] arr, int n, int sum) {
        boolean dp[][] = new boolean[sum + 1][n + 1];

        // initialize top row as true
        for (int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }

        // initialize leftmost column, except dp[0][0], as 0
        for (int i = 1; i <= sum / 2; i++) {
            dp[i][0] = false;
        }

        // Fill the partition table in bottom up manner
        for (int i = 1; i <= sum / 2; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1];
                if (i >= arr[j - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - arr[j - 1]][j - 1];
                }
            }
        }
        return dp[sum][n];
    }

    /**
     * Complexity O(sum * n), Space O(sum * n)
     */
    private static int memoization(int[] arr, int n, int sum, int[][] dp) {
        // Base Cases
        if (sum == 0) {
            return 1;
        }
        if (n == 0 && sum != 0) {
            return 0;
        }

        // return solved subproblem
        if (dp[n][sum] != -1) {
            return dp[n][sum];
        }

        // If last element is greater than sum, then ignore it
        if (arr[n - 1] > sum) {
            return memoization(arr, n - 1, sum, dp);
        }

        /* else, check if sum can be obtained by any of the following
                (a) including the last element
                (b) excluding the last element
        */
        if (memoization(arr, n - 1, sum, dp) != 0
                || memoization(arr, n - 1, sum - arr[n - 1], dp) != 0) {
            return dp[n][sum] = 1;
        }
        return dp[n][sum] = 0;
    }


    /**
     * Returns true if there is a subset of arr[] with sum equal to given sum
     * Complexity O(2^n), Space O(n)
     */
    private static boolean recursive(int[] arr, int n, int sum) {
        // Base Cases
        if (sum == 0) {
            return true;
        }
        if (n == 0 && sum != 0) {
            return false;
        }

        // If last element is greater than sum, then ignore it
        if (arr[n - 1] > sum) {
            return recursive(arr, n - 1, sum);
        }

        /*
        else, check if sum can be obtained by any of the following
        (a) including the last element
        (b) excluding the last element
        */
        return recursive(arr, n - 1, sum) || recursive(arr, n - 1, sum - arr[n - 1]);
    }
}