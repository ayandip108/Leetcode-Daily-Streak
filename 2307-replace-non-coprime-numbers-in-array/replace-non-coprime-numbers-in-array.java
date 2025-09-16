class Solution {
    /**
     * Replaces adjacent non-coprime numbers with their LCM until no adjacent pairs share a common factor > 1
     * @param nums Input array of positive integers
     * @return List of integers after all possible replacements
     */
    public List<Integer> replaceNonCoprimes(int[] nums) {
        // Use a list as a stack to store the result
        List<Integer> stack = new ArrayList<>();
      
        // Process each number in the input array
        for (int currentNum : nums) {
            // Add current number to the stack
            stack.add(currentNum);
          
            // Keep merging adjacent non-coprime numbers
            while (stack.size() > 1) {
                // Get the last two elements from the stack
                int lastElement = stack.get(stack.size() - 1);
                int secondLastElement = stack.get(stack.size() - 2);
              
                // Calculate GCD of the two elements
                int gcdValue = gcd(lastElement, secondLastElement);
              
                // If they are coprime (GCD = 1), no merge needed
                if (gcdValue == 1) {
                    break;
                }
              
                // Remove the last element
                stack.remove(stack.size() - 1);
              
                // Replace the second last element with LCM of both elements
                // LCM(a, b) = (a * b) / GCD(a, b)
                // Using long to prevent integer overflow during multiplication
                int lcmValue = (int) ((long) lastElement * secondLastElement / gcdValue);
                stack.set(stack.size() - 1, lcmValue);
            }
        }
      
        return stack;
    }

    /**
     * Calculates the Greatest Common Divisor using Euclidean algorithm
     * @param a First positive integer
     * @param b Second positive integer
     * @return GCD of a and b
     */
    private int gcd(int a, int b) {
        // Base case: when b becomes 0, a is the GCD
        if (b == 0) {
            return a;
        }
        // Recursive case: GCD(a, b) = GCD(b, a mod b)
        return gcd(b, a % b);
    }
}