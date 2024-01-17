# Non-Adjacent Sum (Stickler Thief)

Given an array arr[] of positive numbers, The task is to find the maximum sum of a subsequence such that no 2 numbers in the sequence should be adjacent in the array.

## Examples:

Input: arr[] = {5, 5, 10, 100, 10, 5}

Output: 110

Explanation: Pick the subsequence {5, 100, 5}.
The sum is 110 and no two elements are adjacent. This is the highest possible sum.

Input: arr[] = {3, 2, 7, 10}
Output: 13

Explanation: The subsequence is {3, 10}. This gives sum = 13.

## Recursion Concept

Each element has two choices: either it can be the part of the subsequence with the highest sum or it cannot be part of the subsequence. 
So to solve the problem, build all the subsequences of the array and find the subsequence with the maximum sum such that no two adjacent elements are present in the subsequence.

* The robber has two options: to rob the current house (option “a”) or not to rob it (option “b”).
* If the robber chooses option “a”, they cannot rob the previous house (i-1), but can proceed to the one before that (i-2) and receive all the accumulated loot.
* If option “b” is selected, the robber will receive all the potential loot from the previous house (i-1) and all the houses before it.
* The decision of which option to choose ultimately comes down to determining which is more profitable: the loot obtained from the current house and previous houses before the one before the last, or the loot obtained from the previous house and all prior houses.
* The formula used to make this calculation is: rob(i) = Math.max( rob(i – 2) + currentHouseValue, rob(i – 1) ).


## Bottom-Up

* As seen above, each element has two choices. If one element is picked then its neighbours cannot be picked. Otherwise, its neighbours may be picked or may not be.
* So the maximum sum till ith index has two possibilities: the subsequence includes arr[i] or it does not include arr[i].
* If arr[i] is included then the maximum sum depends on the maximum subsequence sum till (i-1)th element excluding arr[i-1].
* Otherwise, the maximum sum is the same as the maximum subsequence sum till (i-1) where arr[i-1] may be included or excluded.
* So build a 2D dp[N][2] array where dp[i][0] stores maximum subsequence sum till ith index with arr[i] excluded and dp[i][1] stores the sum when arr[i] is included.
* The values will be obtained by the following relations: dp[i][1] = dp[i-1][0] + arr[i] and dp[i][0] = max(dp[i-1][0], dp[i-1][1])