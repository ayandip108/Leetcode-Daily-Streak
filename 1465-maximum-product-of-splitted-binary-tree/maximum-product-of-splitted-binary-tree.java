/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Maximum product found so far
    private long maxProductValue;
  
    // Total sum of all nodes in the tree
    private long totalSum;
  
    /**
     * Finds the maximum product of two subtree sums after removing one edge.
     * The product is calculated by splitting the tree into two parts.
     * 
     * @param root The root of the binary tree
     * @return The maximum product modulo 10^9 + 7
     */
    public int maxProduct(TreeNode root) {
        final int MODULO = (int) 1e9 + 7;
      
        // First pass: calculate the total sum of all nodes
        totalSum = calculateTotalSum(root);
      
        // Second pass: find the maximum product by trying each possible split
        findMaxProduct(root);
      
        // Return the result with modulo applied
        return (int) (maxProductValue % MODULO);
    }
  
    /**
     * Performs DFS to find the maximum product by considering each subtree.
     * For each subtree, calculates the product of its sum with the remaining tree's sum.
     * 
     * @param root Current node being processed
     * @return Sum of the subtree rooted at current node
     */
    private long findMaxProduct(TreeNode root) {
        // Base case: null node contributes 0 to the sum
        if (root == null) {
            return 0;
        }
      
        // Calculate sum of current subtree (current node + left subtree + right subtree)
        long currentSubtreeSum = root.val + findMaxProduct(root.left) + findMaxProduct(root.right);
      
        // Only consider this split if the subtree sum is less than total
        // (to avoid duplicate calculation of the same split)
        if (currentSubtreeSum < totalSum) {
            // Calculate product: one part is currentSubtreeSum, 
            // the other part is (totalSum - currentSubtreeSum)
            long product = currentSubtreeSum * (totalSum - currentSubtreeSum);
            maxProductValue = Math.max(maxProductValue, product);
        }
      
        return currentSubtreeSum;
    }
  
    /**
     * Calculates the sum of all nodes in the tree using recursion.
     * 
     * @param root Current node being processed
     * @return Sum of all nodes in the subtree rooted at current node
     */
    private long calculateTotalSum(TreeNode root) {
        // Base case: null node has sum of 0
        if (root == null) {
            return 0;
        }
      
        // Recursive case: sum current node value with sums of left and right subtrees
        return root.val + calculateTotalSum(root.left) + calculateTotalSum(root.right);
    }
}