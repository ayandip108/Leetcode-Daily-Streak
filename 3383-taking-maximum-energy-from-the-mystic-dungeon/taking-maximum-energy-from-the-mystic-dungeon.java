class Solution {
    public int maximumEnergy(int[] energy, int k) {
        // Initialize the maximum energy to a very small value (negative large number)
        int maxEnergy = -(1 << 30);  // -2^30
        int n = energy.length;
      
        // Iterate through all possible ending positions
        // Starting from the last k positions (n-k to n-1)
        for (int endPosition = n - k; endPosition < n; endPosition++) {
            // For each ending position, traverse backwards with step k
            // and calculate the cumulative sum
            int currentSum = 0;
            for (int currentIndex = endPosition; currentIndex >= 0; currentIndex -= k) {
                // Add the energy at current position to the running sum
                currentSum += energy[currentIndex];
                // Update the maximum energy if current sum is larger
                maxEnergy = Math.max(maxEnergy, currentSum);
            }
        }
      
        return maxEnergy;
    }
}