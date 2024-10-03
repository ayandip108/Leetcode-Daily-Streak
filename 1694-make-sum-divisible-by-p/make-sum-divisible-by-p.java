import java.util.HashMap;

public class Solution {
    public int minSubarray(int[] nums, int p) {
        HashMap<Integer, Integer> hmp = new HashMap<>();
        int n = nums.length;
        int target = 0;
        int sum = 0;
        for (int num : nums) {
            target = (num + target) % p;
        }
        if (target == 0) {
            return 0;
        }
        hmp.put(0, -1);
        int ans = n;
        for (int i = 0; i < n; i++) {
            sum = (sum + nums[i]) % p;
            int key = (sum - target + p) % p;
            if (hmp.containsKey(key)) {
                ans = Math.min(ans, i - hmp.get(key));
            }
            hmp.put(sum % p, i);
        }
        if (ans < n) {
            return ans;
        } else {
            return -1;
        }
    }
}