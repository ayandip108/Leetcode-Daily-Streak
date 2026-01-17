class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        long maxSquareArea = 0;
      
        // Iterate through all pairs of rectangles
        for (int i = 0; i < bottomLeft.length; ++i) {
            // Get coordinates of the first rectangle
            int rect1LeftX = bottomLeft[i][0];
            int rect1BottomY = bottomLeft[i][1];
            int rect1RightX = topRight[i][0];
            int rect1TopY = topRight[i][1];
          
            // Compare with all subsequent rectangles to find overlapping areas
            for (int j = i + 1; j < bottomLeft.length; ++j) {
                // Get coordinates of the second rectangle
                int rect2LeftX = bottomLeft[j][0];
                int rect2BottomY = bottomLeft[j][1];
                int rect2RightX = topRight[j][0];
                int rect2TopY = topRight[j][1];
              
                // Calculate the overlapping region's width
                // The overlapping left boundary is the maximum of the two left boundaries
                // The overlapping right boundary is the minimum of the two right boundaries
                int overlapWidth = Math.min(rect1RightX, rect2RightX) - Math.max(rect1LeftX, rect2LeftX);
              
                // Calculate the overlapping region's height
                // The overlapping bottom boundary is the maximum of the two bottom boundaries
                // The overlapping top boundary is the minimum of the two top boundaries
                int overlapHeight = Math.min(rect1TopY, rect2TopY) - Math.max(rect1BottomY, rect2BottomY);
              
                // The side length of the largest square that can fit in the overlapping region
                // is the minimum of the overlap width and height
                int squareSideLength = Math.min(overlapWidth, overlapHeight);
              
                // If there is a valid overlap (positive dimensions), calculate the square area
                if (squareSideLength > 0) {
                    maxSquareArea = Math.max(maxSquareArea, 1L * squareSideLength * squareSideLength);
                }
            }
        }
      
        return maxSquareArea;
    }
}