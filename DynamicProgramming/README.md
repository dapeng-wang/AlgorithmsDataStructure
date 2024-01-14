# Dynamic Programming

Dynamic Programming is an algorithmic paradigm that solves a given complex problem by breaking it into subproblems using recursion and storing the results of subproblems to avoid computing the same results again. 
Following are the two main properties of a problem that suggests that the given problem can be solved using Dynamic programming.

* __Overlapping Subproblems__
  * Like Divide and Conquer, Dynamic Programming combines solutions to sub-problems. Dynamic Programming is mainly used when solutions to the same subproblems are needed again and again. In dynamic programming, computed solutions to subproblems are stored in a table so that these don’t have to be recomputed. So Dynamic Programming is not useful when there are no common (overlapping) subproblems because there is no point in storing the solutions if they are not needed again. For example, Binary Search doesn’t have common subproblems. If we take the example of following a recursive program for Fibonacci Numbers, there are many subproblems that are solved again and again.
* __Optimal Substructure__
  * A given problem is said to have Optimal Substructure Property if the optimal solution of the given problem can be obtained by using the optimal solution to its subproblems instead of trying every possible way to solve the subproblems.
  * Example: The Shortest Path problem has the following optimal substructure property: 
    * If node x lies in the shortest path from a source node U to destination node V then the shortest path from U to V is a combination of the shortest path from U to X and the shortest path from X to V. The standard All Pair Shortest Path algorithm like Floyd–Warshall and Single Source Shortest path algorithm for negative weight edges like Bellman–Ford are typical examples of Dynamic Programming.
    * On the other hand, the Longest Path problem doesn’t have the Optimal Substructure property. Here by Longest Path, we mean the longest simple path (path without cycle) between two nodes. Consider the following unweighted graph given in the CLRS book. There are two longest paths from q to t: q?r?t and q?s?t. Unlike shortest paths, these longest paths do not have the optimal substructure property. 
    * Other examples
      * Longest Common Subsequence
      * Count ways to reach the n’th stair
      * Coin Change 
      * Edit Distance | DP-5 – GeeksforGeeks
      * Cutting a Rod
      * Fibonacci numbers
      
* Correctness of brute force and efficiency of greedy algorithms
* Two common use cases
  * Find an **optimal solution**
  * **Counting** the total number of **solutions** for a problem
* Identifying and solving all sub problems
* Dynamic Programming = Recursion + Memoization

## Bottom-Up Approach
* Solve the base problem from the bottom
* Use solution of base problems to solve super problem