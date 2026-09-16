//```java
class Solution {
    public int numberOfSets(int n, int k) {
        final int MOD = 1_000_000_007;

        long[][] dp = new long[k + 1][n];

        // dp[j][i] = number of ways to choose j segments
        // using points 0...i, where the last segment ends at i.
        //
        // A segment can have length >= 1.
        //
        // We can either:
        // 1. Extend an already open/selected segment.
        // 2. Start a new segment from an earlier point.

        long[][] ways = new long[k + 1][n];

        for (int i = 1; i < n; i++) {
            ways[1][i] = i;
        }

        for (int segments = 2; segments <= k; segments++) {
            long prefix = 0;

            for (int i = 1; i < n; i++) {
                // ways[segments - 1][j] contributes when
                // the previous segment ends at j <= i.
                prefix += ways[segments - 1][i - 1];

                if (prefix >= MOD) {
                    prefix -= MOD;
                }

                ways[segments][i] = prefix;
            }
        }

        /*
         * The above DP counts only configurations where
         * segments have distinct endpoints in a simplified form.
         *
         * A cleaner combinatorial DP is:
         *
         * dp[s][i] = ways to choose s segments among points 0..i.
         *
         * For each point i:
         *   - don't use i as an endpoint
         *   - use i as the right endpoint of the last segment
         *
         * We maintain an auxiliary open[s] value.
         */

        long[][] dp2 = new long[k + 1][n];

        for (int i = 0; i < n; i++) {
            dp2[0][i] = 1;
        }

        long[][] open = new long[k + 1][n];

        for (int i = 1; i < n; i++) {
            open[1][i] = i;
            dp2[1][i] = (dp2[1][i - 1] + open[1][i]) % MOD;
        }

        for (int s = 2; s <= k; s++) {
            for (int i = 1; i < n; i++) {

                // Keep previous configurations
                dp2[s][i] = dp2[s][i - 1];

                /*
                 * Start a new segment ending at i.
                 *
                 * Previous s-1 segments can end at any point
                 * from 0 through i-1.
                 *
                 * Because segments are allowed to share endpoints,
                 * the previous segment may also end at the starting
                 * point of this segment.
                 */
                long add = 0;

                if (i >= 1) {
                    add = dp2[s - 1][i - 1];
                }

                if (i >= 2) {
                    add = (add + open[s][i - 1]) % MOD;
                }

                open[s][i] = (open[s][i - 1] + dp2[s - 1][i - 1]) % MOD;

                dp2[s][i] = (dp2[s][i] + open[s][i]) % MOD;
            }
        }

        /*
         * Standard compact solution:
         *
         * dp[i][j] = number of ways to draw j segments
         * among first i points.
         *
         * Recurrence:
         * dp[i][j] =
         *   dp[i-1][j]
         *   + dp[i-1][j-1] * i
         *
         * This simplifies to the known recurrence below.
         */

        long[][] dpFinal = new long[k + 1][n];

        for (int i = 0; i < n; i++) {
            dpFinal[0][i] = 1;
        }

        for (int j = 1; j <= k; j++) {
            long openWays = 0;

            for (int i = 1; i < n; i++) {

                // Extend an existing segment
                openWays = (openWays + dpFinal[j - 1][i - 1]) % MOD;

                // Do not use point i
                dpFinal[j][i] = dpFinal[j][i - 1];

                // End a segment at i
                dpFinal[j][i] =
                    (dpFinal[j][i] + openWays) % MOD;
            }
        }

        return (int) dpFinal[k][n - 1];
    }
}
