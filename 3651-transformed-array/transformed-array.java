class Solution {
    public int[] constructTransformedArray(int[] nums) {
        // Get the length of the input array
        int arrayLength = nums.length;
      
        // Initialize the result array with the same length as input
        int[] result = new int[arrayLength];
      
        // Iterate through each index of the input array
        for (int currentIndex = 0; currentIndex < arrayLength; currentIndex++) {
            // Check if the current element is zero
            if (nums[currentIndex] == 0) {
                // If zero, the result at this index is also zero
                result[currentIndex] = 0;
            } else {
                // Calculate the target index based on the current value
                // Formula: (currentIndex + nums[currentIndex]) mod arrayLength
                // Adding arrayLength before final mod ensures proper handling of negative values
                int targetIndex = ((currentIndex + nums[currentIndex] % arrayLength + arrayLength) % arrayLength);
              
                // Assign the value at the target index to the result array
                result[currentIndex] = nums[targetIndex];
            }
        }
      
        // Return the transformed array
        return result;
    }
}