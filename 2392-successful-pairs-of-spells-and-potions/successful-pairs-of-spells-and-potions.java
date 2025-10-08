class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        // Sort potions array to enable binary search
        Arrays.sort(potions);
      
        // Get array lengths
        int spellsLength = spells.length;
        int potionsLength = potions.length;
      
        // Initialize result array to store count of successful pairs for each spell
        int[] result = new int[spellsLength];
      
        // Iterate through each spell
        for (int i = 0; i < spellsLength; i++) {
            // Binary search to find the leftmost potion that forms a successful pair
            int left = 0;
            int right = potionsLength;
          
            while (left < right) {
                // Calculate middle index using bit shift (equivalent to division by 2)
                int mid = (left + right) >> 1;
              
                // Check if current spell multiplied by middle potion meets success threshold
                if ((long) spells[i] * potions[mid] >= success) {
                    // If successful, search in left half (including mid)
                    right = mid;
                } else {
                    // If not successful, search in right half (excluding mid)
                    left = mid + 1;
                }
            }
          
            // Calculate number of successful pairs
            // All potions from index 'left' to end form successful pairs
            result[i] = potionsLength - left;
        }
      
        return result;
    }
}
