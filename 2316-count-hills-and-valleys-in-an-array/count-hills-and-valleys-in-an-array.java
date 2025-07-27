class Solution {
    public int countHillValley(int[] nums) {
        // Initialize the counter for hills and valleys found.
        int count = 0;

        // Loop through the input array, checking for hills or valleys.
        // The 'previousIndex' variable will track the index of the last
        // element in the sequence that is not equal to the current element.
        for (int currentIndex = 1, previousIndex = 0; currentIndex < nums.length - 1; ++currentIndex) {
          
            // Skip the current element if it's equal to the next one
            // since we're looking for unique hills or valleys.
            if (nums[currentIndex] == nums[currentIndex + 1]) {
                continue;
            }
          
            // Check for a hill: the current number is greater than both its adjacent numbers.
            if (nums[currentIndex] > nums[previousIndex] && nums[currentIndex] > nums[currentIndex + 1]) {
                count++;
            }
          
            // Check for a valley: the current number is less than both its adjacent numbers.
            if (nums[currentIndex] < nums[previousIndex] && nums[currentIndex] < nums[currentIndex + 1]) {
                count++;
            }
          
            // Update the previousIndex to the current index.
            previousIndex = currentIndex;
        }
        // Return the total number of hills and valleys found.
        return count;
    }
}
