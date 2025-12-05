class Solution {
    /**
     * Counts the number of valid partitions where the difference between 
     * left and right partition sums is even.
     * 
     * @param nums the input array to partition
     * @return the count of valid partitions
     */
    public int countPartitions(int[] nums) {
        // Initialize left sum and right sum
        int leftSum = 0;
        int rightSum = 0;
      
        // Calculate initial right sum (total sum of all elements)
        for (int num : nums) {
            rightSum += num;
        }
      
        // Counter for valid partitions
        int partitionCount = 0;
      
        // Iterate through possible partition points (excluding the last element)
        // since we need at least one element in the right partition
        for (int i = 0; i < nums.length - 1; i++) {
            // Move current element from right partition to left partition
            leftSum += nums[i];
            rightSum -= nums[i];
          
            // Check if the difference between left and right sums is even
            if ((leftSum - rightSum) % 2 == 0) {
                partitionCount++;
            }
        }
      
        return partitionCount;
    }
}