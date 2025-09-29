class Solution {
    // Number of vertices in the polygon
    private int n;
    // Array storing the values of each vertex
    private int[] values;
    // Memoization table for dynamic programming
    // memo[i][j] stores the minimum score for triangulating polygon from vertex i to j
    private Integer[][] memo;

    /**
     * Finds the minimum score of triangulation for a convex polygon.
     * The score of a triangle is the product of its three vertices' values.
     * 
     * @param values Array of vertex values representing the polygon
     * @return Minimum total score of triangulation
     */
    public int minScoreTriangulation(int[] values) {
        this.n = values.length;
        this.values = values;
        this.memo = new Integer[n][n];
      
        // Start DFS from the entire polygon (vertex 0 to vertex n-1)
        return dfs(0, n - 1);
    }

    /**
     * Recursively calculates the minimum triangulation score for a sub-polygon.
     * Uses memoization to avoid redundant calculations.
     * 
     * @param i Starting vertex of the sub-polygon
     * @param j Ending vertex of the sub-polygon
     * @return Minimum score to triangulate the sub-polygon from vertex i to j
     */
    private int dfs(int i, int j) {
        // Base case: adjacent vertices form an edge, not a polygon
        // No triangulation needed, score is 0
        if (i + 1 == j) {
            return 0;
        }
      
        // Check if result is already computed (memoization)
        if (memo[i][j] != null) {
            return memo[i][j];
        }
      
        // Initialize minimum score to a large value
        int minScore = 1 << 30;  // Equivalent to 2^30
      
        // Try all possible vertices k to form a triangle with vertices i and j
        // Triangle formed: (i, k, j)
        for (int k = i + 1; k < j; k++) {
            // Calculate score: 
            // - Score of left sub-polygon (i to k)
            // - Score of right sub-polygon (k to j)  
            // - Score of current triangle (i, k, j)
            int currentScore = dfs(i, k) + dfs(k, j) + values[i] * values[k] * values[j];
            minScore = Math.min(minScore, currentScore);
        }
      
        // Store and return the minimum score for this sub-polygon
        memo[i][j] = minScore;
        return minScore;
    }
}