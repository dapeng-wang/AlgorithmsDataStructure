public class QuickSort {

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Partition the array and get the index of the pivot element
            int pivotIndex = partition(array, low, high);

            // Recursively sort the sub-arrays
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        // Choose the rightmost element as the pivot
        int pivot = array[high];

        // Index of the smaller element
        int i = low - 1;

        // Traverse the array and rearrange elements
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;

                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap array[i+1] and array[high] (put the pivot in its correct place)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        // Return the index of the pivot element
        return i + 1;
    }

    public static void main(String[] args) {
        int[] array = {64, 25, 12, 22, 11};

        // Perform quick sort
        quickSort(array, 0, array.length - 1);

        // Display the sorted array
        System.out.println("Sorted array:");
        for (int element : array) {
            System.out.print(element + " ");
        }
    }
}
