class Solution {
    /**
     * Determines if a string of digits has a specific property after triangular reduction.
     * The algorithm repeatedly replaces each adjacent pair of digits with their sum modulo 10,
     * reducing the array size by 1 each iteration until only 2 elements remain.
     * 
     * @param s A string consisting of digit characters
     * @return true if the final two digits are equal, false otherwise
     */
    public boolean hasSameDigits(String s) {
        // Convert the input string to a character array for manipulation
        char[] digitArray = s.toCharArray();
        int arrayLength = digitArray.length;
      
        // Perform triangular reduction
        // Start from the last position and work backwards to position 1
        // This ensures we end up with exactly 2 elements
        for (int currentLength = arrayLength - 1; currentLength > 1; --currentLength) {
            // For each iteration, compute new values for positions 0 to currentLength-1
            // Each new value is the sum of adjacent digits modulo 10
            for (int position = 0; position < currentLength; ++position) {
                // Calculate sum of current digit and next digit
                int currentDigit = digitArray[position] - '0';
                int nextDigit = digitArray[position + 1] - '0';
                int sumModTen = (currentDigit + nextDigit) % 10;
              
                // Store the result back as a character
                digitArray[position] = (char) (sumModTen + '0');
            }
        }
      
        // Check if the final two remaining digits are equal
        return digitArray[0] == digitArray[1];
    }
}
