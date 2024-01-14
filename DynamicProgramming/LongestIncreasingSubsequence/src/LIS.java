import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LIS {
    static int totalMax = 0;

    public static void main(String[] args) {

        int input[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
        int n = input.length;

        // Recursion
        System.out.println("Length of lis is " + recursive(input, n));
        
        // Memoization
        int[][] dp = new int[n+1][n+1];
        for(int[] row: dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("Length of lis is " + memoization(input, n, 0, -1, dp));

        // DP
        System.out.println("Length of lis is " + dp(input, n));

        // Binary search
        System.out.println("Length of lis is " + binarySearch(input));
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
     * Complexity O(n^2), Space O(n)
     * The outer loop will run from i = 1 to N and the inner loop will run from j = 0 to i and use the recurrence relation to solve the problem.
     */
    private static int dp(int[] input, int n) {
        // DP table, LIS ending at position i
        int[] table = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {
            table[i] = 1;
        }

        // Bottom up
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j] && table[i] < table[j] + 1) {
                    table[i] = table[j] + 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (table[i] > max) {
                max = table[i];
            }
        }
        return max;
    }

    /**
     * Complexity O(n^2), Space O(n^2)
     *
     * To make use of recursive calls, this function must return two things:
     *  1) Length of LIS ending with element arr[n-1]. We use max_ending_here for this purpose
     *  2) Overall maximum as the LIS may end with an element before arr[n-1] max_ref is used this purpose.
     * The value of LIS of full array of size n is stored in max_ref which is our final result
     *
     *
     * @param input  input array
     * @param n array length
     * @param index current index
     * @param prevIndex previous index
     * @param dp dp memoization
     * @return
     */
    private static int memoization(int[] input, int n, int index, int prevIndex, int[][] dp) {
        if (index == n) {
            return 0;
        }

        if (dp[index][prevIndex + 1] != -1) {
            return dp[index][prevIndex + 1];
        }

        int notTake = 0 + memoization(input, n, index + 1, prevIndex, dp);
        int take = Integer.MIN_VALUE;
        if (prevIndex == -1 || input[index] > input[prevIndex]) {
            take = 1 + memoization(input, n, index + 1, index, dp);
        }

        return dp[index][prevIndex + 1] = Math.max(take, notTake);
    }


    /**
     * Complexity O(2^n), Space O(1)
     */
    private static int recursive(int[] input, int n) {
        // base case
        if (n == 1) {
            return 1;
        }

        int currentMax = 1;

        for (int i = 1; i < n; i++) {
            // Recursively get all LIS ending at position i,
            int maxAtPositionI = recursive(input, i);
            // if current element is > element at position 1 and we have new max
            if (input[i - 1] < input[n - 1] && maxAtPositionI + 1 > currentMax) {
                currentMax = maxAtPositionI + 1;
            }
        }
        // Compare max_ending_here with the overall max. And
        // update the overall max if needed
        if (totalMax < currentMax) {
            totalMax = currentMax;
        }

        // Return length of LIS ending with arr[n-1]
        return currentMax;
    }
}