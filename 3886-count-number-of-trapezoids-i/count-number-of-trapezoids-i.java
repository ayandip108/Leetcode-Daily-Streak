import java.util.HashMap;
import java.util.Map;

class Solution {
    public int countTrapezoids(int[][] points) {
        final long MOD = 1_000_000_007L;

        Map<Integer, Long> yCount = new HashMap<>();
        for (int[] point : points) {
            int y = point[1];
            yCount.put(y, yCount.getOrDefault(y, 0L) + 1L);
        }

        long sumPairs = 0L;
        long sumPairsSquared = 0L;

        for (long count : yCount.values()) {
            if (count < 2) continue;

            long pairs = count * (count - 1) / 2;
            long pairsMod = pairs % MOD;

            sumPairs = (sumPairs + pairsMod) % MOD;
            sumPairsSquared = (sumPairsSquared + pairsMod * pairsMod) % MOD;
        }

        long result = (sumPairs * sumPairs) % MOD;
        result = (result - sumPairsSquared + MOD) % MOD;

        long inverseOfTwo = (MOD + 1) / 2;
        result = (result * inverseOfTwo) % MOD;

        return (int) result;
    }
}
