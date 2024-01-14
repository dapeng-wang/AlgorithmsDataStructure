import java.util.Arrays;

public class MinCoins {
    public static void main(String[] args) {
        int coins[] = { 1, 2, 3 };
        int n = coins.length;
        int sum = 5;

        // Recursive
        System.out.println(minRecursively(coins, n, sum));

        // Memoization
        int[] dp = new int[sum+1];
        Arrays.fill(dp, -1);
        System.out.println(minMemoization(coins, n, sum, dp));

        // DP
        System.out.println(minDp(coins, n, sum));
    }

    /**
     * Time Complexity O(n*sum) Space Complexity O(sum)
     * @param coins coins can be used
     * @param n number of coins
     * @param sum sum
     * @return count of way to achieve sum with coins[0--n-1]
     */
    private static int minDp(int[] coins, int n, int sum) {
        // dp[i] will be storing the minimum number of coins
        // required for i (0 <= i <= sum) value.
        int dp[] = new int[sum + 1];

        // Base case (If given value V is 0)
        dp[0] = 0;

        // Initialize all table values as Infinite
        for (int i = 1; i <= sum; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= sum; i++) {
            // Go through all coins smaller than i
            for (int j = 0; j < n; j++) {
                if (coins[j] <= i) {
                    // sub result of previous value + current coin
                    int subResult = dp[i - coins[j]];
                    if (subResult != Integer.MAX_VALUE && subResult + 1 < dp[i]) {
                        dp[i] = subResult + 1;
                    }
                }
            }
        }

        if(dp[sum]==Integer.MAX_VALUE) {
            return -1;
        }

        return dp[sum];
    }

    /**
     * Time Complexity O(n^sum) Space Complexity O(sum)
     * @param coins input coin array
     * @param n number of coins
     * @param sum sum
     * @return min num of coins to reach sum
     */
    private static int minRecursively(int[] coins, int n, int sum) {
        System.out.printf("Count %d - %d", sum, n);
        System.out.println();
        // Always 1 way to reach 0
        if (sum == 0) {
            return 0;
        }

        int result = Integer.MAX_VALUE;

        // Try every coin that has smaller value than V
        for (int i=0; i<n; i++)
        {
            if (coins[i] <= sum)
            {
                // Take current coin
                int subProblemResult = minRecursively(coins, n, sum-coins[i]);

                // Check for INT_MAX to avoid overflow and see if
                // result can minimized
                if (subProblemResult != Integer.MAX_VALUE && subProblemResult + 1 < result)
                    result = subProblemResult + 1;
            }
        }
        return result;
    }

    /**
     * Time Complexity O(n*sum) Space Complexity O(n*sum)
     *
     * Create a 2D dp array to store the results of previously solved subproblems.
     * dp[i][j] will represent the number of distinct ways to make the sum j by using the first i coins.
     * During the recursion call, if the same state is called more than once, then we can directly return the answer stored for that state instead of calculating again.
     *
     * @param coins input coin array
     * @param n number of coins
     * @param sum sum
     * @return min coins of sub problems for each value smaller than sum
     */
    private static int minMemoization(int[] coins, int n, int sum, int[] dp) {
        System.out.printf("Count %d - %d", sum, n);
        System.out.println();

        int result = Integer.MAX_VALUE;

        // base case
        if (sum == 0) {
            result = 0;
        }

        if (dp[sum] != -1) {
            return dp[sum];
        }

        // Iterate over all coins and recursively solve subproblems
        for (int i = 0; i < n; i++) {
            if (coins[i] <= sum) {
                // Recursive call to solve for remaining value V - coins[i]
                int subResult = minMemoization(coins, n, sum - coins[i], dp);

                // If the subproblem has a valid solution and the total number of coins is smaller
                // than the current result, update the result
                if (subResult != Integer.MAX_VALUE && subResult + 1 < result) {
                    result = subResult + 1;
                }
            }
        }

        // Update memoization
        dp[sum] = result;

        return result;
    }
}