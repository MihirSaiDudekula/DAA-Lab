public class Knapsack {

    // Function to solve the 0/1 Knapsack problem
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length; 
        int[][] dp = new int[n + 1][capacity + 1]; 

        // Build the DP table
        for (int i = 0; i <= n; i++) { 
            for (int w = 0; w <= capacity; w++) { 
                if (i == 0 || w == 0) { 
                    dp[i][w] = 0; 
                } else if (weights[i - 1] <= w) { 
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else { 
                    dp[i][w] = dp[i - 1][w]; 
                }
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] weights = { 1, 3, 4, 5 }; 
        int[] values = { 1, 4, 5, 7 }; 
        int capacity = 7; 

        // Calculate the maximum value that can be obtained
        int maxValue = knapsack(weights, values, capacity);
        // Print the maximum value
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}
