class Solution {
    /**
     * Finds the largest triangle area that can be formed by any three points.
     * Uses the cross product formula to calculate triangle area.
     * 
     * @param points 2D array where each element is [x, y] coordinate
     * @return the maximum area of triangle formed by any three points
     */
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
      
        // Try all possible combinations of three points
        for (int[] point1 : points) {
            int x1 = point1[0];
            int y1 = point1[1];
          
            for (int[] point2 : points) {
                int x2 = point2[0];
                int y2 = point2[1];
              
                for (int[] point3 : points) {
                    int x3 = point3[0];
                    int y3 = point3[1];
                  
                    // Calculate vectors from point1 to point2 and point1 to point3
                    int vectorX1 = x2 - x1;  // x component of vector from p1 to p2
                    int vectorY1 = y2 - y1;  // y component of vector from p1 to p2
                    int vectorX2 = x3 - x1;  // x component of vector from p1 to p3
                    int vectorY2 = y3 - y1;  // y component of vector from p1 to p3
                  
                    // Calculate area using cross product formula
                    // Area = |cross product| / 2 = |v1 × v2| / 2
                    double currentArea = Math.abs(vectorX1 * vectorY2 - vectorX2 * vectorY1) / 2.0;
                  
                    // Update maximum area if current is larger
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }
      
        return maxArea;
    }
}