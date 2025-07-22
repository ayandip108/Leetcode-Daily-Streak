class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int left = 0;
        int right = 0;
        int n = nums.length;
        HashMap<Integer, Boolean> valIdx = new HashMap<>();

        int maxSum = Integer.MIN_VALUE;
        int winSum = 0;
        while (right < n) {
            if (valIdx.containsKey(nums[right])) {
                valIdx.remove(nums[left]);
                winSum -= nums[left];
                left++;
            } else {
                winSum += nums[right];
                valIdx.put(nums[right], true);
                right++;
                maxSum = Math.max(maxSum, winSum);
            }
        }
        return maxSum;
    }
}