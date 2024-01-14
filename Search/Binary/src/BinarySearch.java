public class BinarySearch {

    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if the target is equal to the middle element
            if (array[mid] == target) {
                return mid; // Target found, return the index
            } else if (array[mid] < target) {
                low = mid + 1; // If target is greater, search in the right half
            } else {
                high = mid - 1; // If target is smaller, search in the left half
            }
        }

        return -1; // Target not found in the array
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 8, 12, 15, 24, 30};
        int target = 15;

        // Perform binary search
        int result = binarySearch(array, target);

        // Display the result
        if (result != -1) {
            System.out.println("Element " + target + " found at index " + result);
        } else {
            System.out.println("Element " + target + " not found in the array");
        }
    }
}
