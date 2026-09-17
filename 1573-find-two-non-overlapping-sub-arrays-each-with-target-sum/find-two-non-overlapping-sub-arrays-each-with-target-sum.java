//```java
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;

        // best[i] = minimum length of a valid subarray
        // completely within indices [0..i]
        int[] best = new int[n];

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        long sum = 0;
        int answer = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left++];
            }

            // Carry forward the best subarray found so far
            if (right > 0) {
                best[right] = best[right - 1];
            }

            // Found a subarray [left..right] with sum == target
            if (sum == target) {
                int len = right - left + 1;

                // Need another subarray ending before 'left'
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(answer, len + best[left - 1]);
                }

                // This is the shortest/latest valid subarray ending at right
                best[right] = Math.min(best[right], len);
            }
        }

        return answer == INF ? -1 : answer;
    }
}
