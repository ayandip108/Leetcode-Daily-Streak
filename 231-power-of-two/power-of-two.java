class Solution {
    /**
     * Checks if a number is a power of two.
     *
     * A power of two has exactly one bit set in binary representation.
     * If we subtract 1 from a number which is a power of 2 (e.g., 8 is 1000 in binary),
     * we get a number which has all the bits set after the bit that was set in original number (e.g., 7 is 0111).
     * Taking an AND of n and n-1 will yield 0 if n is a power of two.
     *
     * @param n The number to be checked.
     * @return true if n is a power of two, false otherwise.
     */
    public boolean isPowerOfTwo(int n) {
        // Check if n is greater than 0 and if n AND (n-1) is 0
        return n > 0 && (n & (n - 1)) == 0;
    }
}
