import java.util.*;

class Solution {
    // DSU Find with path compression
    private int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        int[] parent = new int[c + 1];
        int[] rank = new int[c + 1];

        for (int i = 1; i <= c; i++) parent[i] = i;

        // Union-Find union
        for (int[] con : connections) {
            int a = con[0], b = con[1];
            int ra = find(parent, a);
            int rb = find(parent, b);
            if (ra != rb) {
                if (rank[ra] < rank[rb]) parent[ra] = rb;
                else if (rank[ra] > rank[rb]) parent[rb] = ra;
                else {
                    parent[rb] = ra;
                    rank[ra]++;
                }
            }
        }

        // Build component → TreeSet of online stations
        Map<Integer, TreeSet<Integer>> comp = new HashMap<>();
        for (int i = 1; i <= c; i++) {
            int r = find(parent, i);
            comp.computeIfAbsent(r, k -> new TreeSet<>()).add(i);
        }

        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);

        List<Integer> result = new ArrayList<>();

        for (int[] q : queries) {
            int type = q[0], x = q[1];
            int r = find(parent, x); 
            TreeSet<Integer> set = comp.get(r);

            if (type == 1) { // Query
                if (online[x]) {
                    result.add(x);
                } else {
                    if (set.isEmpty()) result.add(-1);
                    else result.add(set.first());
                }
            } else { // Offline
                if (online[x]) {
                    online[x] = false;
                    set.remove(x);
                }
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
