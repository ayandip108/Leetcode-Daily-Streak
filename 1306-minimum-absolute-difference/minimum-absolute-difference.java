class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        // Sort the array to ensure adjacent elements have the smallest differences
        Arrays.sort(arr);
      
        int arrayLength = arr.length;
      
        // Initialize minimum difference with a large value (2^30)
        int minDifference = 1 << 30;
      
        // Find the minimum absolute difference between adjacent elements
        for (int i = 0; i < arrayLength - 1; i++) {
            int currentDifference = arr[i + 1] - arr[i];
            minDifference = Math.min(minDifference, currentDifference);
        }
      
        // Store all pairs with the minimum absolute difference
        List<List<Integer>> result = new ArrayList<>();
      
        // Collect all pairs that have the minimum difference
        for (int i = 0; i < arrayLength - 1; i++) {
            if (arr[i + 1] - arr[i] == minDifference) {
                // Add the pair to result list
                result.add(List.of(arr[i], arr[i + 1]));
            }
        }
      
        return result;
    }
}