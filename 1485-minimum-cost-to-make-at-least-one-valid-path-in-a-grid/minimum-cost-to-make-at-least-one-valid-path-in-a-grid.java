class Solution {
    public int minCost(int[][] grid) {
        // m holds the number of rows in the grid.
        int numRows = grid.length;
        // n holds the number of columns in the grid.
        int numCols = grid[0].length;
        // vis holds information whether a cell has been visited.
        boolean[][] visited = new boolean[numRows][numCols];
        // Queue for performing BFS with modifications for 0-cost moves.
        Deque<int[]> queue = new ArrayDeque<>();
        // Starting by adding the top-left cell with 0 cost.
        queue.offer(new int[] {0, 0, 0});
        // dirs are used to navigate throughout the grid. (right, left, down, up)
        int[][] directions = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      
        // BFS starts here
        while (!queue.isEmpty()) {
            // Dequeue a cell info from the queue.
            int[] position = queue.poll();
            // i and j hold the current cell row and column, d holds the current cost.
            int i = position[0], j = position[1], cost = position[2];

            // If we've reached the bottom-right cell, return the cost.
            if (i == numRows - 1 && j == numCols - 1) {
                return cost;
            }
            // If this cell is already visited, skip it.
            if (visited[i][j]) {
                continue;
            }
            // Mark the cell as visited.
            visited[i][j] = true;

            // Explore all possible directions from the current cell.
            for (int k = 1; k <= 4; ++k) {
                int newX = i + directions[k][0], newY = j + directions[k][1];
                // Check the validity of the new cell coordinates.
                if (newX >= 0 && newX < numRows && newY >= 0 && newY < numCols) {
                    // If the current direction is the same as the arrow in this cell (no cost to move here).
                    if (grid[i][j] == k) {
                        // Add the new cell at the front of the queue to explore it sooner (as it's no cost).
                        queue.offerFirst(new int[] {newX, newY, cost});
                    } else {
                        // Otherwise, add the new cell at the end of the queue and increase the cost by 1.
                        queue.offer(new int[] {newX, newY, cost + 1});
                    }
                }
            }
        }
        // If the queue is empty and we didn't reach the bottom-right cell, return -1 as it's not possible.
        return -1;
    }
}
