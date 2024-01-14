import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        int coins[] = { 1, 2, 3 };
        int n = coins.length;
        int sum = 5;

        // Recursive
//        System.out.println(countRecursively(coins, n, sum));

        // Memoization
        int[][] dp = new int[n+1][sum+1];
        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }
//        System.out.println(countMemoization(coins, n, sum, dp));

        // DP
        System.out.println(countDp(coins, n, sum));
    }

    /**
     * Time Complexity O(n*sum) Space Complexity O(n*sum)
     * @param coins coins can be used
     * @param n number of coins
     * @param sum sum
     * @return count of way to achieve sum with coins[0--n-1]
     */
    private static int countDp(int[] coins, int n, int sum) {
        // DP table, number of counts to reach sum in column with number of coins in row
        int[][] dp = new int[n + 1][sum + 1];

        // Represents the base case where the target sum is 0,
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                // Not use current coin (coin in the current row (i), take the counts of row above
                dp[i][j] += dp[i - 1][j];

                // Take the current coin and add to the count of the cell left in the same row
                if ((j - coins[i - 1]) >= 0) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * Time Complexity O(2^sum) Space Complexity O(sum)
     * @param coins input coin array
     * @param n number of coins
     * @param sum sum
     * @return count of way to achieve sum with coins[0--n-1]
     */
    private static int countRecursively(int[] coins, int n, int sum) {
        System.out.printf("Count %d - %d", sum, n);
        System.out.println();
        // Always 1 way to reach 0
        if (sum == 0) {
            return 1;
        }
        // No way to reach negative sum
        if (sum < 0) {
            return 0;
        }
        // No coin to pick
        if (n <= 0) {
            return 0;
        }

        return countRecursively(coins, n - 1, sum) + countRecursively(coins, n, sum - coins[n - 1]);
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
     * @return count of way to achieve sum with coins[0--n-1]
     */
    private static int countMemoization(int[] coins, int n, int sum, int[][] dp) {
        System.out.printf("Count %d - %d", sum, n);
        System.out.println();
        int result;

        // base case
        if (sum == 0) {
            result = 1;
        }
        // No way to reach negative sum or with no coin
        if (sum < 0 || n == 0) {
            return 0;
        }
        // If calculated before, return from memoization
        if (dp[n][sum] != -1) {
            return dp[n][sum];
        }

        result = countMemoization(coins, n - 1, sum, dp) + countMemoization(coins, n, sum - coins[n - 1], dp);
        // memoization
        dp[n][sum] = result;
        return dp[n][sum];
    }
}