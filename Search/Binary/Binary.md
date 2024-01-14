# Binary Search

The binarySearch method takes a sorted array of integers and a target value to search for. 
It uses a while loop to repeatedly narrow down the search range by adjusting the low and high indices based on whether the target is smaller or larger than the middle element. 
The search continues until the target is found or the search range becomes empty.

Complexity O(log n), best case O(1), average O(log n)

It's important to note that binary search's efficiency relies on the array being sorted. 
If the array is not sorted, a preprocessing step would be needed to sort it, which typically has a time complexity of O(n log n) or higher. 
However, once the array is sorted, binary search can be applied efficiently.

