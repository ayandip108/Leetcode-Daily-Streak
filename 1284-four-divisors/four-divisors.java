class Solution {
    /**
     * Calculates the sum of all divisors for numbers that have exactly 4 divisors.
     * @param nums Array of integers to process
     * @return Sum of divisors for all numbers with exactly 4 divisors
     */
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;
      
        // Process each number in the array
        for (int number : nums) {
            totalSum += getSumIfFourDivisors(number);
        }
      
        return totalSum;
    }

    /**
     * Calculates the sum of divisors if the number has exactly 4 divisors.
     * @param number The number to check
     * @return Sum of all divisors if count is 4, otherwise 0
     */
    private int getSumIfFourDivisors(int number) {
        // Initialize with 2 divisors (1 and the number itself)
        int divisorCount = 2;
        int divisorSum = number + 1;  // Sum includes 1 and the number itself
      
        // Check for divisors from 2 to sqrt(number)
        for (int i = 2; i <= number / i; ++i) {
            if (number % i == 0) {
                // Found a divisor i
                ++divisorCount;
                divisorSum += i;
              
                // Check if the complementary divisor (number/i) is different from i
                if (i * i != number) {
                    // Add the complementary divisor
                    ++divisorCount;
                    divisorSum += number / i;
                }
            }
        }
      
        // Return sum only if exactly 4 divisors found, otherwise return 0
        return divisorCount == 4 ? divisorSum : 0;
    }
}