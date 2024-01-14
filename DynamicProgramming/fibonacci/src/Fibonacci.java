import java.util.Map;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 10;

        System.out.printf("Min coins = %d", n, fib(n));
    }

    /*
     * Complexity O(n)
     */
    private static int fib(int n) {
        // DP table 1xn
        int[] memo = new int[n];
        int result = 0;
        for (int i = 1; i <= n ; i++) {
            if (i <= 2) {
                result = 1;
            } else {
                result = memo[i-3] + memo[i-2];
            }
            memo[i - 1] = result;
        }
        return memo[n - 1];
    }

    /*
     * Complexity O(2^n/2)
     */
    private static int fibRecursiveWithoutMemoization(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fibRecursiveWithoutMemoization(n - 1) + fibRecursiveWithoutMemoization(n - 2);
        }
    }

    /*
     * Complexity O(n)
     */
    private static int fibRecursiveWithMemoization(int n, Map<Integer, Integer> memory) {
        Integer result = memory.get(n);
        if (result != null) {
            return result;
        }
        if (n <= 2) {
            result = 1;
        } else {
            result = fibRecursiveWithMemoization(n - 1, memory) + fibRecursiveWithMemoization(n - 2, memory);
        }
        memory.put(n ,result);
        return result;
    }
}