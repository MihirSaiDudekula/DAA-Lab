class BinarySearch {

    // Method to perform binary search
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                return mid; // Target found, return its index
            }
            
            if (arr[mid] < target) {
                left = mid + 1; // Target is in the right half
            } else {
                right = mid - 1; // Target is in the left half
            }
        }
        
        return -1; // Target not found, return -1
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 5, 5, 6, 9}; // Array must be sorted for binary search
        int target = 5;
        
        int result = binarySearch(numbers, target);
        
        if (result == -1) {
            System.out.println("Target " + target + " not found in the array.");
        } else {
            System.out.println("Target " + target + " found at index " + result + ".");
        }
    }
}
