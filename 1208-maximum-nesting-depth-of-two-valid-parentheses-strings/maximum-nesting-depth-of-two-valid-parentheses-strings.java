class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {

            if (seq.charAt(i) == '(') {
                depth++;

                // Put odd depths in group 1
                // and even depths in group 0.
                ans[i] = depth % 2;
            } else {
                // For ')' use the depth before decreasing.
                ans[i] = depth % 2;

                depth--;
            }
        }

        return ans;
    }
}