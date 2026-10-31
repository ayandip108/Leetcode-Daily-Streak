
import java.util.*;

class Solution {

    int m, n;
    int[][] grid;
    int[][][] memo;

    static final long MOD = 1_000_000_007L;

    // dir = 0: entered mirror while moving right
    // dir = 1: entered mirror while moving down
    int resolve(int r, int c, int dir) {

        if (memo[r][c][dir] != 0) {
            return memo[r][c][dir];
        }

        int nr, nc, nd;

        if (dir == 0) {
            // Reflect down
            nr = r + 1;
            nc = c;
            nd = 1;
        } else {
            // Reflect right
            nr = r;
            nc = c + 1;
            nd = 0;
        }

        // Reflection goes outside the grid
        if (nr >= m || nc >= n) {
            return memo[r][c][dir] = -1;
        }

        // Reached an empty cell
        if (grid[nr][nc] == 0) {
            return memo[r][c][dir] = nr * n + nc + 1;
        }

        // Consecutive mirror: reflect again
        return memo[r][c][dir] =
                resolve(nr, nc, nd);
    }

    public int uniquePaths(int[][] grid) {

        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        memo = new int[m][n][2];

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) {
                    continue;
                }

                long ways = dp[i][j];

                // Attempt to move right
                if (j + 1 < n) {

                    int dest;

                    if (grid[i][j + 1] == 0) {
                        dest = (i * n + j + 1) + 1;
                    } else {
                        dest = resolve(i, j + 1, 0);
                    }

                    if (dest != -1) {
                        dest--;

                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] = (dp[r][c] + ways) % MOD;
                    }
                }

                // Attempt to move down
                if (i + 1 < m) {

                    int dest;

                    if (grid[i + 1][j] == 0) {
                        dest = ((i + 1) * n + j) + 1;
                    } else {
                        dest = resolve(i + 1, j, 1);
                    }

                    if (dest != -1) {
                        dest--;

                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] = (dp[r][c] + ways) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
