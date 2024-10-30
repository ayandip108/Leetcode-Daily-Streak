import java.util.*;

public class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] lis = new int[n];
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int pos = Collections.binarySearch(temp, nums[i]);
            if (pos < 0) pos = -(pos + 1); 
            if (pos < temp.size()) {
                temp.set(pos, nums[i]);
            } else {
                temp.add(nums[i]);
            }
            lis[i] = pos + 1;
        }

        int[] lds = new int[n];
        temp.clear();
        for (int i = n - 1; i >= 0; i--) {
            int pos = Collections.binarySearch(temp, nums[i]);
            if (pos < 0) pos = -(pos + 1); 
            if (pos < temp.size()) {
                temp.set(pos, nums[i]);
            } else {
                temp.add(nums[i]);
            }
            lds[i] = pos + 1;
        }

        int maxMountainLen = 0;
        for (int i = 1; i < n - 1; i++) {
            if (lis[i] > 1 && lds[i] > 1) {
                maxMountainLen = Math.max(maxMountainLen, lis[i] + lds[i] - 1);
            }
        }

        return n - maxMountainLen;
    }
}