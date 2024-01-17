# Climbing Stairs to reach at the top

There are n stairs, a person standing at the bottom wants to climb stairs to reach the nth stair. The person can climb either 1 stair or 2 stairs at a time, the task is to count the number of ways that a person can reach at the top.

## Examples:

Input: n = 1
Output: 1 There is only one way to climb 1 stair

Input: n=2
Output: 2 There are two ways: (1, 1) and (2)

Input: n = 4
Output: 5 (1, 1, 1, 1), (1, 1, 2), (2, 1, 1), (1, 2, 1), (2, 2)

## Recursion Concept

The person can reach nth stair from either (n-1)th stair or from (n-2)th stair. 
Hence, for each stair n, we try to find out the number of ways to reach n-1th stair and n-2th stair and add them to give the answer for the nth stair. 
Therefore the Recurrence relation for such an approach comes out to be :

ways(n) = ways(n-1) + ways(n-2)

__Same as fibonacci__

# Count number of ways to cover a distance

Given a distance ‘dist’, count total number of ways to cover the distance with 1, 2 and 3 steps. 

## Examples:

Input: n = 3
Output: 4

Explanation:
Below are the four ways
1 step + 1 step + 1 step
1 step + 2 step
2 step + 1 step
3 step

Input: n = 4
Output: 7

Explanation:
Below are the four ways
1 step + 1 step + 1 step + 1 step
1 step + 2 step + 1 step
2 step + 1 step + 1 step
1 step + 1 step + 2 step
2 step + 2 step
3 step + 1 step
1 step + 3 step


sum = count(n-1) + count(n-2) + count(n-3)