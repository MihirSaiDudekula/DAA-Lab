import java.util.Random;

public class QuickSort {
    // Function to swap two elements
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) 
        {
            // Call Partition function to find Partition Index
            // int partitionIndex = partition(arr, low, high);
            int pivot = arr[low];
            int i = low;
            int j = high;
    
            while (i < j) {
                // Condition 1: find the first element greater than the pivot (from starting)
                while (arr[i] <= pivot && i <= high - 1) {
                    i++;
                }
                // Condition 2: find the first element smaller than the pivot (from last)
                while (arr[j] > pivot && j >= low + 1) {
                    j--;
                }
                if (i < j) {
                    swap(arr, i, j);
                }
            }
            swap(arr, low, j);
            // Recursively call quickSort() for left and right half based on partition Index
            quickSort(arr, low, j - 1);
            quickSort(arr, j + 1, high);
        }
    }

    // Driver code
    public static void main(String[] args) {
        int n, l = 1;
        long s, e;
        double t;

        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("How many elements in array?: ");
        n = sc.nextInt();
        int[] arr = new int[1000];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            // arr[i] = rand.nextInt(10) + 1;
            arr[i] = rand.nextInt(n - l + 1);
        }

        // Printing the original array
        System.out.print("Original array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        // Calling quickSort() to sort the given array
        s = System.nanoTime();
        quickSort(arr, 0, n - 1);
        e = System.nanoTime();

        // Printing the sorted array
        System.out.print("\nSorted array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        t = (double)(e - s) / 1_000_000_000;
        System.out.printf("\nThe time taken by pivot at 1st position: %f seconds\n", t);
        
        sc.close();
    }
}

