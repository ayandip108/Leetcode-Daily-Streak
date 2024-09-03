class Solution {
    // Method to calculate the normal maximum subarray sum using Kadane's algorithm
    public int maxSubarraySum(int[] nums) {
        int n = nums.length;
        int curr = nums[0], res = nums[0];
        for (int i = 1; i < n; i++) {
            curr = Math.max(nums[i], curr + nums[i]);
            res = Math.max(curr, res);
        }
        return res;
    }

    // Method to calculate the maximum circular subarray sum
    public int maxSubarraySumCircular(int[] nums) {
        int normal_sum = maxSubarraySum(nums);

        // If all elements are negative, return the maximum element
        if (normal_sum < 0) {
            return normal_sum;
        }

        int total_sum = 0;
        for (int i = 0; i < nums.length; i++) {
            total_sum += nums[i];
            nums[i] = -nums[i];  // Invert the elements to find the minimum subarray sum
        }

        // The max circular subarray sum is calculated by subtracting the minimum subarray sum from the total sum
        int max_circular = total_sum + maxSubarraySum(nums);

        // Return the maximum of normal subarray sum and circular subarray sum
        return Math.max(normal_sum, max_circular);
    }
}
