class Solution {
    public int maxSubArray(int[] nums) {
        int n=nums.length;
        int res= nums[0];
        int maxend=nums[0];
        for(int i=1;i<n;i++){
            maxend= Math.max(maxend+nums[i],nums[i]);
            res= Math.max(maxend,res);
        }
        return res;
        
    }
}