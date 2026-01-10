class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
      
        // dp[i][j] represents the minimum ASCII sum of deleted characters
        // to make s1[0...i-1] and s2[0...j-1] equal
        int[][] dp = new int[m + 1][n + 1];
      
        // Initialize first column: delete all characters from s1[0...i-1]
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
      
        // Initialize first row: delete all characters from s2[0...j-1]
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
      
        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // Characters match, no deletion needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Characters don't match, delete from either s1 or s2
                    // Choose the option with minimum ASCII sum
                    dp[i][j] = Math.min(
                        dp[i - 1][j] + s1.charAt(i - 1),  // Delete from s1
                        dp[i][j - 1] + s2.charAt(j - 1)   // Delete from s2
                    );
                }
            }
        }
      
        return dp[m][n];
    }
}