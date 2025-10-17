class Solution {
    // Memoization map: key is [position, bitmask of current partition, can change flag]
    private Map<List<Integer>, Integer> memo = new HashMap<>();
    private String inputString;
    private int maxDistinctChars;

    public int maxPartitionsAfterOperations(String s, int k) {
        this.inputString = s;
        this.maxDistinctChars = k;
        // Start DFS from position 0, empty bitmask, with 1 change allowed
        return dfs(0, 0, 1);
    }

    /**
     * Dynamic programming with memoization to find maximum partitions
     * @param index - current position in string
     * @param currentMask - bitmask representing distinct characters in current partition
     * @param canChange - flag indicating if we can still change a character (1 = yes, 0 = no)
     * @return maximum number of partitions from current state
     */
    private int dfs(int index, int currentMask, int canChange) {
        // Base case: reached end of string
        if (index >= inputString.length()) {
            return 1;
        }
      
        // Check memoization
        var memoKey = List.of(index, currentMask, canChange);
        if (memo.containsKey(memoKey)) {
            return memo.get(memoKey);
        }
      
        // Get bitmask for current character
        int charBit = 1 << (inputString.charAt(index) - 'a');
      
        // Try not changing current character
        int newMask = currentMask | charBit;
        int maxPartitions;
      
        if (Integer.bitCount(newMask) > maxDistinctChars) {
            // Adding current char exceeds k distinct chars, start new partition
            maxPartitions = dfs(index + 1, charBit, canChange) + 1;
        } else {
            // Continue with current partition
            maxPartitions = dfs(index + 1, newMask, canChange);
        }
      
        // Try changing current character (if we haven't used our change yet)
        if (canChange > 0) {
            // Try all 26 possible characters
            for (int charIndex = 0; charIndex < 26; ++charIndex) {
                newMask = currentMask | (1 << charIndex);
              
                if (Integer.bitCount(newMask) > maxDistinctChars) {
                    // Changing to this char would exceed k distinct chars, start new partition
                    maxPartitions = Math.max(maxPartitions, 
                        dfs(index + 1, 1 << charIndex, 0) + 1);
                } else {
                    // Continue with current partition after change
                    maxPartitions = Math.max(maxPartitions, 
                        dfs(index + 1, newMask, 0));
                }
            }
        }
      
        // Store result in memoization map
        memo.put(memoKey, maxPartitions);
        return maxPartitions;
    }
}
