class Solution {
    /**
     * Calculates the minimum time required to visit all points in order.
     * Movement is allowed in 8 directions: horizontally, vertically, and diagonally.
     * Each move takes 1 second.
     * 
     * @param points 2D array where each element represents a point [x, y]
     * @return minimum time in seconds to visit all points in order
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;
      
        // Iterate through consecutive pairs of points
        for (int i = 1; i < points.length; i++) {
            // Calculate horizontal distance between current and previous point
            int horizontalDistance = Math.abs(points[i][0] - points[i - 1][0]);
          
            // Calculate vertical distance between current and previous point
            int verticalDistance = Math.abs(points[i][1] - points[i - 1][1]);
          
            // Minimum time is the maximum of horizontal and vertical distances
            // This works because we can move diagonally to cover both distances simultaneously
            // until one is exhausted, then move straight for the remaining distance
            totalTime += Math.max(horizontalDistance, verticalDistance);
        }
      
        return totalTime;
    }
}