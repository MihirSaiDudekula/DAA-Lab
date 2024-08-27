class LinearSearch {

    // Method to perform linear search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Target found, return its index
            }
        }
        return -1; // Target not found, return -1
    }

    public static void main(String[] args) {
        int[] numbers = {5, 2, 9, 1, 5, 6};
        int target = 5;
        
        int result = linearSearch(numbers, target);
        
        if (result == -1) {
            System.out.println("Target " + target + " not found in the array.");
        } else {
            System.out.println("Target " + target + " found at index " + result + ".");
        }
    }
}
