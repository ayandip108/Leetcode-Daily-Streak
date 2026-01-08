class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
      
        // dp[i][j] represents the maximum dot product of subsequences 
        // from nums1[0...i-1] and nums2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
      
        // Initialize with minimum value as we need at least one pair
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
      
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // Current product of nums1[i-1] and nums2[j-1]
                int currentProduct = nums1[i - 1] * nums2[j - 1];
              
                // Option 1: Skip current element from nums1
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
              
                // Option 2: Skip current element from nums2
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
              
                // Option 3: Include current pair
                // Either start new subsequence with current pair or extend previous subsequence
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j - 1], 0) + currentProduct);
            }
        }
      
        return dp[m][n];
    }
}