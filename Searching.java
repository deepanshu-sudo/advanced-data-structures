/**
 * This class contains the following search methods:
 * 1. Linear Search
 * 2. Binary Search
 * 3. Interpolation Search
 */
public class Searching {
    /**
     * Linear Search:
     * 
     * Linear search is a simple search algorithm that searches for a target value within an array. It compares each element of the array to the target value until a match is found or the whole array has been searched.
     * 
     * Algorithm:
     * 1. Start from the leftmost element of the array and one by one compare the target value with each element of the array.
     * 2. If the target value matches with an element, return the index.
     * 3. If the target value does not match with any of the elements, return -1.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Binary Search:
     * 
     * Binary search is a search algorithm that finds the position of a target value within a sorted array. It compares the target value to the middle element of the array and continues to narrow down the search range until the target value is found or the search range is empty.
     * 
     * Algorithm:
     * 1. Compare the target value with the middle element of the array.
     * 2. If the target value matches with the middle element, return the index.
     * 3. If the target value is greater than the middle element, repeat the search on the subarray to the right of the middle element.
     * 4. If the target value is less than the middle element, repeat the search on the subarray to the left of the middle element.
     * 5. If the search range is empty, return -1.
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }

    /**
     * Interpolation Search:
     * 
     * Interpolation search is an improved search algorithm that finds the position of a target value within a sorted array. It uses the position of the target value to estimate its position within the array and continues to narrow down the search range until the target value is found or the search range is empty.
     * 
     * Algorithm:
     * Calculate the denominator: denominator = (arr[high] - arr[low])
     * Calculate the position: position = low + ((target - arr[low]) * (high - low) / denominator)
     * Compare the target value with the position:
     * If the target value matches with the position, return the index.
     * If the target value is greater than the position, repeat the search on the subarray to the right of the position.
     * If the target value is less than the position, repeat the search on the subarray to the left of the position.
     * If the search range is empty, return -1.
     * 
     * Time Complexity: O(log log n) on average
     * Space Complexity: O(1)
     */
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        
        while (low <= high && target >= arr[low] && target <= arr[high]) {
            int denominator = (arr[high] - arr[low]);
            int position;
            
            if(denominator == 0) {
                position = low;
            } else {
                position = low + ((target - arr[low]) * (high - low) / denominator);
            }
            
            if (arr[position] == target) {
                return position;
            } else if (arr[position] < target) {
                low = position + 1;
            } else {
                high = position - 1;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 7, 10, 40};
        int target = 7;

        System.out.println("Linear Search:");
        int linearIndex = linearSearch(arr, target);
        if (linearIndex != -1) {
            System.out.println("Target value found at index: " + linearIndex);
        } else {
            System.out.println("Target value not found in the array.");
        }

        System.out.println("\nBinary Search:");
        int binaryIndex = binarySearch(arr, target);
        if (binaryIndex != -1) {
            System.out.println("Target value found at index: " + binaryIndex);
        } else {
            System.out.println("Target value not found in the array.");
        }

        System.out.println("\nInterpolation Search:");
        int interpolationIndex = interpolationSearch(arr, target);
        if (interpolationIndex != -1) {
            System.out.println("Target value found at index: " + interpolationIndex);
        } else {
            System.out.println("Target value not found in the array.");
        }
    }
}
