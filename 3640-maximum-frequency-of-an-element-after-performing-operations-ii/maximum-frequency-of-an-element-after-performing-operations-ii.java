class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        // Map to store the frequency of each number in the array
        Map<Integer, Integer> frequencyMap = new HashMap<>();
      
        // TreeMap to track range boundaries and their contributions
        // Used for sweep line algorithm to count overlapping ranges
        TreeMap<Integer, Integer> rangeBoundaries = new TreeMap<>();
      
        // Process each number in the input array
        for (int num : nums) {
            // Count frequency of current number
            frequencyMap.merge(num, 1, Integer::sum);
          
            // Initialize the number itself as a potential target
            rangeBoundaries.putIfAbsent(num, 0);
          
            // Mark the start of range [num - k, num + k] where elements can be changed to num
            // Increment at start of range (num - k)
            rangeBoundaries.merge(num - k, 1, Integer::sum);
          
            // Mark the end of range (exclusive) at num + k + 1
            // Decrement after end of range
            rangeBoundaries.merge(num + k + 1, -1, Integer::sum);
        }
      
        int maxResult = 0;
        int currentOverlap = 0;  // Running sum of overlapping ranges
      
        // Sweep through all boundary points in sorted order
        for (Map.Entry<Integer, Integer> entry : rangeBoundaries.entrySet()) {
            int position = entry.getKey();
            int delta = entry.getValue();
          
            // Update the current overlap count
            currentOverlap += delta;
          
            // Calculate maximum frequency achievable at this position
            // It's the minimum of:
            // 1. Total elements that can be changed to this position (currentOverlap)
            // 2. Original frequency + allowed operations
            int originalFrequency = frequencyMap.getOrDefault(position, 0);
            int achievableFrequency = Math.min(currentOverlap, originalFrequency + numOperations);
          
            // Update the maximum frequency found so far
            maxResult = Math.max(maxResult, achievableFrequency);
        }
      
        return maxResult;
    }
}