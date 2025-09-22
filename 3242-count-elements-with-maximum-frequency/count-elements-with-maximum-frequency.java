class Solution {
    public int maxFrequencyElements(int[] nums) {
        // Array to store frequency count of each element (elements range from 1 to 100)
        int[] frequencyCount = new int[101];
      
        // Count the frequency of each element in the input array
        for (int num : nums) {
            frequencyCount[num]++;
        }
      
        // Initialize variables to track the result and maximum frequency
        int totalCount = 0;
        int maxFrequency = -1;
      
        // Iterate through frequency counts to find elements with maximum frequency
        for (int frequency : frequencyCount) {
            if (frequency > maxFrequency) {
                // Found a new maximum frequency
                maxFrequency = frequency;
                totalCount = frequency;  // Reset total count to this frequency
            } else if (frequency == maxFrequency) {
                // Found another element with the same maximum frequency
                totalCount += frequency;  // Add to the total count
            }
        }
      
        // Return the total count of elements that have the maximum frequency
        return totalCount;
    }
}
