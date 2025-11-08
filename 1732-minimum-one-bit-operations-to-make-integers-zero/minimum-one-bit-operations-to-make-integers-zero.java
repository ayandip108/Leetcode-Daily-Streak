class Solution {
    /**
     * Computes the minimum number of one-bit operations required to reduce n to 0.
     * This uses the inverse Gray code formula to find the result.
     * 
     * The algorithm works by XORing n with itself right-shifted by 1, 2, 4, 8, etc.
     * This effectively computes the inverse Gray code transformation.
     * 
     * @param n The non-negative integer to reduce to 0
     * @return The minimum number of one-bit operations required
     */
    public int minimumOneBitOperations(int n) {
        int result = 0;
      
        // Keep XORing with right-shifted values until n becomes 0
        // Each iteration: result = result XOR n, then n = n >> 1
        while (n > 0) {
            result ^= n;  // XOR current result with current n value
            n >>= 1;      // Right shift n by 1 bit
        }
      
        return result;
    }
}
