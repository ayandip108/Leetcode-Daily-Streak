//```java
class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];

        // dp[r] = number of subarrays ending at the previous index
        // whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            int val = num % k;

            long[] next = new long[k];

            // Start a new subarray with only the current element
            next[val]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRemainder = (int) ((long) r * val % k);
                    next[newRemainder] += dp[r];
                }
            }

            // Add all subarrays ending at this position
            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }

            dp = next;
        }

        return result;
    }
}
