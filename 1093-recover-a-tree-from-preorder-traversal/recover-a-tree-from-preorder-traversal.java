import java.util.Stack;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

class Solution {
    public TreeNode recoverFromPreorder(String traversal) {
        Stack<TreeNode> nodesStack = new Stack<>();
        int currentDepth = 0;
        int currentValue = 0;

        for (int i = 0; i < traversal.length(); ++i) {
            if (traversal.charAt(i) == '-') {
                // Increment the currentDepth for each '-' character encountered
                currentDepth++;
            } else {
                // Calculate the currentValue of the current node
                currentValue = 10 * currentValue + (traversal.charAt(i) - '0');
            }

            // Check for the end of the number or the end of the string
            if (i + 1 == traversal.length() || (Character.isDigit(traversal.charAt(i)) && traversal.charAt(i + 1) == '-')) {
                TreeNode newNode = new TreeNode(currentValue);

                // If the nodesStack size is greater than the currentDepth, we pop nodes until they match
                while (nodesStack.size() > currentDepth) {
                    nodesStack.pop();
                }

                // If nodesStack is not empty, we link the newNode as a child to the node at the top of the stack
                if (!nodesStack.isEmpty()) {
                    if (nodesStack.peek().left == null) {
                        nodesStack.peek().left = newNode;
                    } else {
                        nodesStack.peek().right = newNode;
                    }
                }

                // Push the newNode onto the stack
                nodesStack.push(newNode);

                // Reset currentDepth and currentValue for the next node
                currentDepth = 0;
                currentValue = 0;
            }
        }

        // Result is the root of the binary tree, which is the bottommost node in the nodesStack
        TreeNode result = null;
        while (!nodesStack.isEmpty()) {
            result = nodesStack.peek();
            nodesStack.pop();
        }

        // Return the recovered binary tree
        return result;
    }
}