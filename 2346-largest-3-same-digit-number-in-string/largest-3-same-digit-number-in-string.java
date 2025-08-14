class Solution {
    // Method to find the largest good integer in the given string.
    public String largestGoodInteger(String num) {
        // Loop backwards from '9' to '0'
        // This will ensure that the first good integer found is the largest
        for (int i = 9; i >= 0; i--) {
            // Create a string consisting of three identical digits
            String tripleDigit = String.valueOf(i).repeat(3);
          
            // Check if the num string contains the tripleDigit
            if (num.contains(tripleDigit)) {
                // Return the found good integer (since we started from 9, it's the largest)
                return tripleDigit;
            }
        }
        // If no good integer is found, return an empty string
        return "";
    }
}
