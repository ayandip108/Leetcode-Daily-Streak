class Solution {
    /**
     * Counts the number of steps to reduce a binary string to "1".
     * If the number is odd, add 1; if even, divide by 2.
     * 
     * @param s Binary string representation of a positive integer
     * @return Number of steps required to reduce to "1"
     */
    public int numSteps(String s) {
        // Track if there's a carry from previous addition operations
        boolean hasCarry = false;
      
        // Counter for total number of steps
        int stepCount = 0;
      
        // Process the binary string from right to left (LSB to MSB)
        // Stop at index 1 since we don't process the most significant bit here
        for (int i = s.length() - 1; i > 0; i--) {
            char currentBit = s.charAt(i);
          
            // Apply carry from previous operation if exists
            if (hasCarry) {
                if (currentBit == '0') {
                    // 0 + carry(1) = 1, no carry forward
                    currentBit = '1';
                    hasCarry = false;
                } else {
                    // 1 + carry(1) = 10 (binary), bit becomes 0, carry forward 1
                    currentBit = '0';
                }
            }
          
            // If current bit is 1 (number is odd), we need to add 1
            if (currentBit == '1') {
                stepCount++;  // Step for adding 1
                hasCarry = true;  // Adding 1 to odd number creates carry
            }
          
            // Always perform division by 2 (right shift)
            stepCount++;
        }
      
        // Handle final carry at the most significant bit position
        // If there's a carry, it means we need one more division step
        if (hasCarry) {
            stepCount++;
        }
      
        return stepCount;
    }
}
