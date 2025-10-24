class Solution {
    public int nextBeautifulNumber(int n) {
        // Start checking from n + 1 onwards
        for (int candidate = n + 1; ; candidate++) {
            // Array to count occurrences of each digit (0-9)
            int[] digitCount = new int[10];
          
            // Count the frequency of each digit in the current number
            for (int temp = candidate; temp > 0; temp /= 10) {
                digitCount[temp % 10]++;
            }
          
            // Check if the number is beautiful
            boolean isBeautiful = true;
          
            // A beautiful number has each digit d appearing exactly d times
            for (int temp = candidate; temp > 0; temp /= 10) {
                int digit = temp % 10;
                // If digit value doesn't match its frequency, not beautiful
                if (digit != digitCount[digit]) {
                    isBeautiful = false;
                    break;
                }
            }
          
            // Return the first beautiful number found
            if (isBeautiful) {
                return candidate;
            }
        }
    }
}
