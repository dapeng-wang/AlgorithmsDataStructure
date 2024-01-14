public class LinearSearch {

    public static int linearSearch(int[] array, int target) {
        // Iterate through each element in the array
        for (int i = 0; i < array.length; i++) {
            // Check if the current element is equal to the target
            if (array[i] == target) {
                // Return the index if a match is found
                return i;
            }
        }
        // Return -1 if the target is not found in the array
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 8, 12, 15, 24, 30};
        int target = 15;

        // Perform linear search
        int result = linearSearch(array, target);

        // Display the result
        if (result != -1) {
            System.out.println("Element " + target + " found at index " + result);
        } else {
            System.out.println("Element " + target + " not found in the array");
        }
    }
}