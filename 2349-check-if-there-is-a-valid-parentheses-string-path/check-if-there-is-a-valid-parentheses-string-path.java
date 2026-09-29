class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A valid parentheses string must have even length.
        int len = m + n - 1;
        if (len % 2 == 1) {
            return false;
        }

        // dp[j][balance] = whether we can reach current cell (i,j)
        // with the given balance.
        boolean[][] dp = new boolean[n][len + 1];

        // Start from (0,0)
        if (grid[0][0] == ')') {
            return false;
        }

        dp[0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Start cell already initialized.
                if (i == 0 && j == 0) {
                    continue;
                }

                int change = (grid[i][j] == '(') ? 1 : -1;

                boolean[] current = new boolean[len + 1];

                for (int balance = 0; balance <= len; balance++) {

                    if (i > 0 && dp[j][balance]) {
                        int newBalance = balance + change;

                        if (newBalance >= 0 && newBalance <= len) {
                            current[newBalance] = true;
                        }
                    }

                    if (j > 0 && dp[j - 1][balance]) {
                        int newBalance = balance + change;

                        if (newBalance >= 0 && newBalance <= len) {
                            current[newBalance] = true;
                        }
                    }
                }

                dp[j] = current;
            }
        }

        return dp[n - 1][0];
    }
}