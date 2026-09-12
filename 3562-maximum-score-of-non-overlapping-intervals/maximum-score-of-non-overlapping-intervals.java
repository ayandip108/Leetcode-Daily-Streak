//```java
import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        List<Integer> ids;

        State(long score, List<Integer> ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        Interval[] a = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);

            a[i] = new Interval(
                x.get(0),
                x.get(1),
                x.get(2),
                i
            );
        }

        // Sort by starting position
        Arrays.sort(a, (x, y) -> {
            if (x.l != y.l)
                return Integer.compare(x.l, y.l);

            if (x.r != y.r)
                return Integer.compare(x.r, y.r);

            return Integer.compare(x.idx, y.idx);
        });

        // next[i] = first interval with left > current right
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = i + 1;
            int hi = n;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (a[mid].l > a[i].r)
                    hi = mid;
                else
                    lo = mid + 1;
            }

            next[i] = lo;
        }

        /*
         * dp[i][k] =
         * best result using intervals from i onward,
         * choosing at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 0; k <= 4; k++) {

                // Skip current interval
                State skip = dp[i + 1][k];

                if (k == 0) {
                    dp[i][k] = skip;
                    continue;
                }

                // Take current interval
                State rest = dp[next[i]][k - 1];

                List<Integer> takeIds =
                    new ArrayList<>(rest.ids);

                takeIds.add(a[i].idx);

                Collections.sort(takeIds);

                State take = new State(
                    (long) a[i].w + rest.score,
                    takeIds
                );

                dp[i][k] = better(take, skip);
            }
        }

        List<Integer> answer = dp[0][4].ids;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private State better(State a, State b) {

        // Maximum score
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        // Same score -> lexicographically smaller indices
        int n = Math.min(a.ids.size(), b.ids.size());

        for (int i = 0; i < n; i++) {
            if (!a.ids.get(i).equals(b.ids.get(i))) {
                return a.ids.get(i) < b.ids.get(i) ? a : b;
            }
        }

        return a.ids.size() <= b.ids.size() ? a : b;
    }
}
