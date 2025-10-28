class Solution {
    public int countValidSelections(int[] nums) {
        // Calculate the total sum of all elements in the array
        int totalSum = Arrays.stream(nums).sum();
      
        // Initialize result counter and left sum accumulator
        int validCount = 0;
        int leftSum = 0;
      
        // Iterate through each element in the array
        for (int currentValue : nums) {
            if (currentValue != 0) {
                // Add non-zero values to the left sum
                leftSum += currentValue;
            } else {
                // At a zero position, check balance conditions
              
                // Perfect balance: left sum equals right sum
                // leftSum == totalSum - leftSum => 2 * leftSum == totalSum
                if (leftSum * 2 == totalSum) {
                    // Both directions are valid when perfectly balanced
                    validCount += 2;
                } 
                // Near balance: difference between left and right is at most 1
                // |leftSum - rightSum| <= 1 => |leftSum - (totalSum - leftSum)| <= 1
                // => |2 * leftSum - totalSum| <= 1
                else if (Math.abs(leftSum * 2 - totalSum) <= 1) {
                    // One direction is valid when nearly balanced
                    validCount++;
                }
            }
        }
      
        return validCount;
    }
}
