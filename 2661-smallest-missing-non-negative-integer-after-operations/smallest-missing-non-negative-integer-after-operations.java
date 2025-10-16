class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        // Create an array to count the frequency of each remainder when divided by value
        int[] remainderCount = new int[value];
      
        // Count the frequency of each remainder (handling negative numbers properly)
        for (int num : nums) {
            // Calculate the positive remainder for negative numbers
            // (num % value + value) % value ensures we get a positive remainder
            int remainder = ((num % value) + value) % value;
            remainderCount[remainder]++;
        }
      
        // Find the smallest non-negative integer that cannot be formed
        for (int i = 0; ; i++) {
            // Check if we can form the number i
            // We need a number with remainder (i % value) to form i
            int requiredRemainder = i % value;
          
            // If no number with the required remainder is available, return i
            if (remainderCount[requiredRemainder] == 0) {
                return i;
            }
          
            // Use one number with this remainder to form i
            remainderCount[requiredRemainder]--;
        }
    }
}
