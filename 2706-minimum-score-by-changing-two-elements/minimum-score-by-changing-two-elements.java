class Solution {
    public int minimizeSum(int[] nums) {
        // Sort the array in ascending order
        Arrays.sort(nums);
      
        // Get the length of the array
        int n = nums.length;
      
        // Calculate three possible scenarios after changing two elements:
      
        // Option 1: Change the two smallest elements to any value
        // The new range would be from the 3rd smallest to the largest
        int changeSmallestTwo = nums[n - 1] - nums[2];
      
        // Option 2: Change the smallest and largest elements
        // The new range would be from the 2nd smallest to the 2nd largest
        int changeSmallestAndLargest = nums[n - 2] - nums[1];
      
        // Option 3: Change the two largest elements to any value
        // The new range would be from the smallest to the 3rd largest
        int changeLargestTwo = nums[n - 3] - nums[0];
      
        // Return the minimum sum among all three options
        return Math.min(changeSmallestTwo, Math.min(changeSmallestAndLargest, changeLargestTwo));
    }
}
