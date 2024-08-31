class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n=nums.length;
        int res=0,curr=0;
        for(int i=0;i<n;i++){
            if(nums[i]==0){
                curr=0;
            }else{
                curr++;
                res= Math.max(res,curr);
            }
        }
        return res;
        
    }
}