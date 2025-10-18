class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        // Sort the array to process elements in ascending order
        Arrays.sort(nums);
      
        int n = nums.length;
        int distinctCount = 0;  // Count of distinct elements after modification
        int previousValue = Integer.MIN_VALUE;  // Track the last assigned value
      
        // Process each element in sorted order
        for (int currentNum : nums) {
            // Calculate the optimal value for current element:
            // - Can be at most currentNum + k (upper bound of modification range)
            // - Must be at least max of:
            //   1. currentNum - k (lower bound of modification range)
            //   2. previousValue + 1 (to maintain distinctness)
            int optimalValue = Math.min(currentNum + k, Math.max(currentNum - k, previousValue + 1));
          
            // Check if we can assign a valid distinct value
            if (optimalValue > previousValue) {
                // Successfully assigned a distinct value
                distinctCount++;
                previousValue = optimalValue;
            }
            // If optimalValue <= previousValue, we cannot make this element distinct
        }
      
        return distinctCount;
    }
}
