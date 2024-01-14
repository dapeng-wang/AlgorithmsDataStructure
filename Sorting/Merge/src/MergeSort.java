public class MergeSort {

    public static void mergeSort(int[] array) {
        int n = array.length;

        if (n > 1) {
            int mid = n / 2;

            // Divide the array into two halves
            int[] left = new int[mid];
            int[] right = new int[n - mid];

            System.arraycopy(array, 0, left, 0, mid);
            System.arraycopy(array, mid, right, 0, n - mid);

            // Recursively sort the two halves
            mergeSort(left);
            mergeSort(right);

            // Merge the sorted halves
            merge(array, left, right);
        }
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Merge elements from left and right arrays into the original array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copy remaining elements of left array, if any
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // Copy remaining elements of right array, if any
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] array = {64, 25, 12, 22, 11};

        // Perform merge sort
        mergeSort(array);

        // Display the sorted array
        System.out.println("Sorted array:");
        for (int element : array) {
            System.out.print(element + " ");
        }
    }
}
