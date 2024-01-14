# Heap Sort

* __HeapSort is a comparison-based sorting algorithm that uses a binary heap data structure to build a max-heap (for ascending order) or min-heap (for descending order). The algorithm then repeatedly extracts the root element (which is the maximum or minimum, depending on the heap type) and rebuilds the heap until the array is sorted.__
* the heapSort method first builds a max heap by calling the heapify method on each non-leaf node in reverse order. 
* Then, it repeatedly extracts the maximum element from the heap (root of the heap), swaps it with the last element, and heapifies the reduced heap. 
* It is not as cache-efficient as some other algorithms, but it is often used in scenarios where a stable sort is not required, and constant space is crucial.

Complexity __O(n log n)__


https://www.youtube.com/watch?v=MtQL_ll5KhQ