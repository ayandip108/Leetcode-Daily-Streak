class Solution {
    public int largestPerimeter(int[] nums) {
        // Sort the array in ascending order to easily check triangle validity
        Arrays.sort(nums);
      
        // Iterate from the largest elements to find the maximum perimeter
        // Start from the end and check consecutive triplets
        for (int i = nums.length - 1; i >= 2; i--) {
            // Get the sum of the two smaller sides
            int sumOfTwoSmallerSides = nums[i - 1] + nums[i - 2];
          
            // Check triangle inequality: sum of two smaller sides must be greater than the largest side
            // If valid triangle is found, return its perimeter
            if (sumOfTwoSmallerSides > nums[i]) {
                return sumOfTwoSmallerSides + nums[i];
            }
        }
      
        // No valid triangle found, return 0
        return 0;
    }
}