class Solution {

    /* Function to check if a given number is a power of four */
    public boolean isPowerOfFour(int num) {
        // First, check if 'num' is positive since powers of four are always positive.
        // Then, check if 'num' is a power of two by confirming that only one bit is set in its binary representation.
        // This is done by using the bitwise AND operation between 'num' and 'num - 1', which should equal zero.
        // Finally, check if the only set bit is in the correct position to represent a power of four.
        // This is done by using bitwise AND with the hex value 0xAAAAAAAA, which has set bits at the positions
        // corresponding to the powers of two but not four. If 'num' is a power of four, this result should be zero.

        return num > 0 && (num & (num - 1)) == 0 && (num & 0xAAAAAAAA) == 0;
    }
}