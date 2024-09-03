class Solution {
    public int majorityElement(int[] nums) {
        int m= nums.length;
        int res=0, count=1;
        for(int i=1; i<m;i++){
            if(nums[res]==nums[i]){
                count ++;
            }else
            count --;
            if(count==0){
                res=i;
                count=1;
            }
        }
        count =0;
        for(int i=0; i<m;i++){
            if(nums[i]==nums[res]){
                count++;
            }
        }if(count > m/2){
                return nums[res];

            }else
            
        return -1;
    }
}