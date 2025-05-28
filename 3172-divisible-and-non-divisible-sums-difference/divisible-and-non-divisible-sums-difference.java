class Solution {
    // Method to calculate the difference between the sum of numbers not divisible by 'm' 
    // and the sum of numbers divisible by 'm' within the range 1 to 'n'
    public int differenceOfSums(int n, int m) {
        // Initialize answer to store the final result
        int answer = 0;
      
        // Loop through numbers from 1 to 'n'
        for (int i = 1; i <= n; ++i) {
            // Check if the current number is divisible by 'm'
            if (i % m == 0) {
                // If it is divisible, subtract it from the answer
                answer -= i;
            } else {
                // If not, add it to the answer
                answer += i;
            }
        }
      
        // Return the computed difference
        return answer;
    }
}