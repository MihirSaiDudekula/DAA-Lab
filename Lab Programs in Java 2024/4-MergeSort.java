import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array: " + Arrays.toString(array));
        
        mergeSort(array, 0, array.length - 1);
        
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
    
    public static void mergeSort(int[] array, int left, int right) {
            
            if(left==right)
            {
                //base case
                return;
            }
            else{
                int mid = (left + right) / 2;
                
                mergeSort(array, left, mid);
                mergeSort(array, mid + 1, right);
                
                //by the time we reach this line, only have singular nodes that have to be recursively merged together.
                //so just before popping off the call stack, sort correctly and merge
                merge(array, left, mid, right);
            }
        }
    
    public static void merge(int[] array, int left, int mid, int right) {
        
        //this code is the same as merging 2 sorted arrays

        int n1 = mid - left + 1;
        //size of left subarray 

        int n2 = right - mid;
        // size fo right subarray
        
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        for (int i = 0; i < n1; i++)
            leftArray[i] = array[left + i];
        for (int j = 0; j < n2; j++)
            rightArray[j] = array[mid + 1 + j];
        
        int i = 0, j = 0;
        int k = left;
        
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
