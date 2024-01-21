import java.util.Arrays;

public class EditDistance {
    public static void main(String[] args) {

        String str1 = "sunday";
        String str2 = "saturday";

        // Recursion
        System.out.println("Edit distance is " + recursive(str1, str2, str1.length(), str2.length()));

        // Memoization
        int n = str1.length(), m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println("Edit distance is " + memoization(str1, str2, str1.length(), str2.length(), dp));

        // DP
        System.out.println("Edit distance is " + dp(str1, str2, str1.length(), str2.length()));

    }

    /**
     * Complexity O(n * w), Space O(n * w)
     */
    private static int dp(String str1, String str2, int m, int n) {
        // Create a table to store results of subproblems
        int[][] dp = new int[m + 1][n + 1];

        // Fill d[][] in bottom up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is to insert all characters of second string
                if (i == 0) {
                    dp[i][j] = j; // Min. operations = j
                }

                // If second string is empty, only option is to remove all characters of second string
                else if (j == 0) {
                    dp[i][j] = i; // Min. operations = i
                }

                // If last characters are same, ignore last char and recur for remaining string
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // If the last character is different, consider all possibilities and find the minimum
                else {
                    dp[i][j]
                            = 1
                            + Math.min(Math.min(
                                    dp[i][j - 1], // Insert
                                    dp[i - 1][j]), // Remove
                            dp[i - 1][j - 1]); // Replace
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Complexity O(m x n) , Space O( m *n)+O(m+n) , (m*n) extra array space and (m+n) recursive stack space.
     */
    private static int memoization(String s1, String s2, int n, int m, int[][] dp) {

        // If any String is empty, return the remaining characters of other String
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }

        // To check if the recursive tree for given n & m has already been executed
        if (dp[n][m] != -1) {
            return dp[n][m];
        }

        // If characters are equal, execute recursive function for n-1, m-1
        if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
            return dp[n][m] = memoization(s1, s2, n - 1, m - 1, dp);
        }
        // If characters are nt equal, we need to find the minimum cost out of all 3 operations.
        else {

            int insert, del, replace; // temp variables

            insert = memoization(s1, s2, n, m - 1, dp);
            del = memoization(s1, s2, n - 1, m, dp);
            replace = memoization(s1, s2, n - 1, m - 1, dp);
            return dp[n][m] = 1 + Math.min(insert, Math.min(del, replace));
        }
    }

    /**
     * Complexity O(3m), Space O(1)
     */
    private static int recursive(String str1, String str2, int m,
                                 int n) {
        // If first string is empty, the only option is to
        // insert all characters of second string into first
        if (m == 0) {
            return n;
        }

        // If second string is empty, the only option is to
        // remove all characters of first string
        if (n == 0) {
            return m;
        }

        // If last characters of two strings are same,
        // nothing much to do. Get the count for remaining strings.
        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return recursive(str1, str2, m - 1, n - 1);
        }

        // If last characters are not same, consider all
        // three operations on last character of first
        // string, recursively compute minimum cost for all
        // three operations and take minimum of three
        // values.
        return 1 + Math.min(Math.min(recursive(str1, str2, m, n - 1), // Insert
                        recursive(str1, str2, m - 1, n)), // Remove
                recursive(str1, str2, m - 1, n - 1)); // Replace
    }
}