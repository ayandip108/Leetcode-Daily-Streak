class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        // Sort the capacity array in ascending order
        Arrays.sort(capacity);
      
        // Calculate the total number of apples
        int totalApples = 0;
        for (int appleCount : apple) {
            totalApples += appleCount;
        }
      
        // Use boxes starting from the largest capacity
        int boxCount = 1;
        int n = capacity.length;
      
        // Keep using boxes from largest to smallest until all apples fit
        while (true) {
            // Subtract the capacity of the current largest unused box
            totalApples -= capacity[n - boxCount];
          
            // If all apples can fit, return the number of boxes used
            if (totalApples <= 0) {
                return boxCount;
            }
          
            // Move to the next largest box
            boxCount++;
        }
    }
}
