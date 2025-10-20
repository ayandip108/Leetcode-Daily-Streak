class Solution {
    /**
     * Calculates the final value after performing all operations.
     * Each operation is either an increment (++X or X++) or decrement (--X or X--).
     * 
     * @param operations Array of strings representing the operations to perform
     * @return The final value after all operations (starting from 0)
     */
    public int finalValueAfterOperations(String[] operations) {
        // Initialize result variable to track the final value
        int result = 0;
      
        // Iterate through each operation in the array
        for (String operation : operations) {
            // Check the middle character to determine operation type
            // If it's '+', increment by 1; if it's '-', decrement by 1
            if (operation.charAt(1) == '+') {
                result += 1;
            } else {
                result -= 1;
            }
        }
      
        // Return the final calculated value
        return result;
    }
}