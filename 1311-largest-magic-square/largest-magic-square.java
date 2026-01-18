class Solution {
    // Prefix sum arrays for rows and columns
    private int[][] rowPrefixSum;
    private int[][] colPrefixSum;

    /**
     * Finds the largest magic square in the grid.
     * A magic square has all rows, columns, and both diagonals sum to the same value.
     * 
     * @param grid The input 2D grid
     * @return The side length of the largest magic square
     */
    public int largestMagicSquare(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
      
        // Initialize prefix sum arrays with extra row/column for easier calculation
        rowPrefixSum = new int[rows + 1][cols + 1];
        colPrefixSum = new int[rows + 1][cols + 1];
      
        // Build prefix sum arrays
        // rowPrefixSum[i][j] = sum of elements from grid[i-1][0] to grid[i-1][j-1]
        // colPrefixSum[i][j] = sum of elements from grid[0][j-1] to grid[i-1][j-1]
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                rowPrefixSum[i][j] = rowPrefixSum[i][j - 1] + grid[i - 1][j - 1];
                colPrefixSum[i][j] = colPrefixSum[i - 1][j] + grid[i - 1][j - 1];
            }
        }
      
        // Try all possible square sizes from largest to smallest
        for (int squareSize = Math.min(rows, cols); squareSize > 1; squareSize--) {
            // Try all possible top-left corners for current square size
            for (int topRow = 0; topRow + squareSize - 1 < rows; topRow++) {
                for (int leftCol = 0; leftCol + squareSize - 1 < cols; leftCol++) {
                    int bottomRow = topRow + squareSize - 1;
                    int rightCol = leftCol + squareSize - 1;
                  
                    // Check if current square is a magic square
                    if (isMagicSquare(grid, topRow, leftCol, bottomRow, rightCol)) {
                        return squareSize;
                    }
                }
            }
        }
      
        // Minimum size is 1 (single cell is always a magic square)
        return 1;
    }

    /**
     * Checks if the square defined by corners is a magic square.
     * 
     * @param grid The input grid
     * @param topRow Top row index of the square
     * @param leftCol Left column index of the square
     * @param bottomRow Bottom row index of the square
     * @param rightCol Right column index of the square
     * @return true if it's a magic square, false otherwise
     */
    private boolean isMagicSquare(int[][] grid, int topRow, int leftCol, 
                                   int bottomRow, int rightCol) {
        // Calculate the sum of the first row as reference value
        int targetSum = rowPrefixSum[topRow + 1][rightCol + 1] - 
                        rowPrefixSum[topRow + 1][leftCol];
      
        // Check if all rows have the same sum
        for (int row = topRow + 1; row <= bottomRow; row++) {
            int rowSum = rowPrefixSum[row + 1][rightCol + 1] - 
                         rowPrefixSum[row + 1][leftCol];
            if (rowSum != targetSum) {
                return false;
            }
        }
      
        // Check if all columns have the same sum
        for (int col = leftCol; col <= rightCol; col++) {
            int colSum = colPrefixSum[bottomRow + 1][col + 1] - 
                         colPrefixSum[topRow][col + 1];
            if (colSum != targetSum) {
                return false;
            }
        }
      
        // Check main diagonal (top-left to bottom-right)
        int mainDiagonalSum = 0;
        for (int i = topRow, j = leftCol; i <= bottomRow; i++, j++) {
            mainDiagonalSum += grid[i][j];
        }
        if (mainDiagonalSum != targetSum) {
            return false;
        }
      
        // Check anti-diagonal (top-right to bottom-left)
        int antiDiagonalSum = 0;
        for (int i = topRow, j = rightCol; i <= bottomRow; i++, j--) {
            antiDiagonalSum += grid[i][j];
        }
        if (antiDiagonalSum != targetSum) {
            return false;
        }
      
        return true;
    }
}