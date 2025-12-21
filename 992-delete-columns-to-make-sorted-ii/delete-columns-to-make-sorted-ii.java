class Solution {
    public int minDeletionSize(String[] A) {
        // Handle edge cases: null or single string array
        if (A == null || A.length <= 1) {
            return 0;
        }
      
        int numStrings = A.length;
        int stringLength = A[0].length();
        int deletedColumns = 0;
      
        // Track which pairs of adjacent strings are already sorted
        // If sorted[i] is true, it means A[i] < A[i+1] lexicographically
        boolean[] sorted = new boolean[numStrings];
      
        // Process each column from left to right
        columnLoop:
        for (int col = 0; col < stringLength; col++) {
            // Check if current column should be deleted
            // A column must be deleted if it breaks the sorting order
            for (int row = 0; row < numStrings - 1; row++) {
                // Only check pairs that aren't already determined to be sorted
                if (!sorted[row] && A[row].charAt(col) > A[row + 1].charAt(col)) {
                    // Column breaks sorting order, must delete it
                    deletedColumns++;
                    continue columnLoop;  // Skip to next column
                }
            }
          
            // Column is valid, update sorted status for string pairs
            // Mark pairs as sorted if current column makes them strictly increasing
            for (int row = 0; row < numStrings - 1; row++) {
                if (A[row].charAt(col) < A[row + 1].charAt(col)) {
                    sorted[row] = true;
                }
            }
        }
      
        return deletedColumns;
    }
}
