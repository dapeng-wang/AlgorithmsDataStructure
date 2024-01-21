import java.util.Arrays;
import java.util.List;

public class WordBreak {
    public static void main(String[] args) {

        List<String> wordList = Arrays.asList(
                "mobile", "samsung", "sam", "sung", "man",
                "mango", "icecream", "and", "go", "i", "like",
                "ice", "cream");

        // Recursion
        System.out.println("Word break result " + recursive(wordList, "ilikesamsung"));

        // DP
        System.out.println("Word break result " + dp(wordList, "ilikesamsung"));

    }

    /**
     * Complexity O(n^2), Space O(n^2)
     */
    private static boolean dp(List<String> wordList, String word) {
        int size = word.length();
        if (size == 0) {
            return true;
        }
        // Create the DP table to store results of subproblems. The value dp[i]
        // will be true if str[0..i-1] can be segmented into dictionary words, otherwise false.
        boolean []dp = new boolean[size+1];

        for (int i=1; i<=size; i++) {
            // if dp[i] is false, then check if current prefix can make it true.
            // Current prefix is "str.substring(0, i)"
            if (dp[i] == false && wordList.contains( word.substring(0, i) )) {
                dp[i] = true;
            }

            // dp[i] is true, then check for all subStrings starting from (i+1)th character and store their results.
            if (dp[i] == true) {
                // If we reached the last prefix
                if (i == size) {
                    return true;
                }

                for (int j = i+1; j <= size; j++) {
                    // Update dp[j] if it is false and can be updated
                    // Note the parameter passed to dictionaryContains() is
                    // subString starting from index 'i' and length 'j-i'
                    if (dp[j] == false && wordList.contains( word.substring(i, j) )) {
                        dp[j] = true;
                    }

                    // If we reached the last character
                    if (j == size && dp[j] == true) {
                        return true;
                    }
                }
            }
        }

        /* Uncomment these lines to print DP table "dp[]"
         for (int i = 1; i <= size; i++)
            System.out.print(" " +  dp[i]; */

        // If we have tried all prefixes and none of them worked
        return false;
    }


    /**
     * Complexity O(2^n), Space O(n)
     */
    private static boolean recursive(List<String> wordList, String word)
    {
        // If the word is empty, it can be broken down into an empty list of words
        if (word.isEmpty()) {
            return true;
        }

        int wordLen = word.length();

        // Check if the word can be broken down into words from the wordList
        for (int i = 1; i <= wordLen; ++i) {
            String prefix = word.substring(0, i);

            if (wordList.contains(prefix) && recursive(wordList, word.substring(i))) {
                return true;
            }
        }

        return false;
    }
}