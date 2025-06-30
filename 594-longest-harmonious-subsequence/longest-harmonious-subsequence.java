class Solution {
    public int findLHS(int[] nums) {
        // Create a HashMap to keep track of the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
      
        // Count the occurrences of each number in the array.
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
      
        // Initialize variable to keep track of the longest harmonious subsequence
        int longestHarmoniousSubsequence = 0;
      
        // Iterate through the numbers in the array
        for (int num : nums) {
            // Check if the number that is one more than the current number exists in the map
            if (frequencyMap.containsKey(num + 1)) {
                // If it exists, calculate the sum of the frequencies of the current number
                // and the number that is one more than the current number
                int currentLength = frequencyMap.get(num) + frequencyMap.get(num + 1);
              
                // Update the longest harmonious subsequence if the current sum is greater
                longestHarmoniousSubsequence = Math.max(longestHarmoniousSubsequence, currentLength);
            }
        }
      
        // Return the length of the longest harmonious subsequence found
        return longestHarmoniousSubsequence;
    }
}