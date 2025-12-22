class Solution {
    public int minDeletionSize(String[] strs) {
        // Get the length of each string (number of columns)
        int columnCount = strs[0].length();
      
        // dp[i] represents the length of longest increasing subsequence ending at column i
        int[] dp = new int[columnCount];
        Arrays.fill(dp, 1); // Each column can form a subsequence of length 1
      
        // Build up the longest increasing subsequence
        for (int currentCol = 1; currentCol < columnCount; ++currentCol) {
            // Check all previous columns to see if they can form an increasing sequence
            for (int previousCol = 0; previousCol < currentCol; ++previousCol) {
                // Check if column previousCol <= column currentCol for all strings
                boolean isNonDecreasing = true;
                for (String str : strs) {
                    // If any string has decreasing characters, columns are not compatible
                    if (str.charAt(previousCol) > str.charAt(currentCol)) {
                        isNonDecreasing = false;
                        break;
                    }
                }
              
                // If columns form non-decreasing sequence, update dp value
                if (isNonDecreasing) {
                    dp[currentCol] = Math.max(dp[currentCol], dp[previousCol] + 1);
                }
            }
        }
      
        // Find the maximum length of increasing subsequence
        int maxIncreasingLength = Arrays.stream(dp).max().getAsInt();
      
        // Minimum deletions = total columns - maximum columns we can keep
        return columnCount - maxIncreasingLength;
    }
}