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

    public int averageOfSubtree(TreeNode root) {

        // solve returns sum, node count, and valid node count
        int[] ans = solve(root);

        // Return the total number of valid nodes
        return ans[2];
    }

    public int[] solve(TreeNode root) {

        // Empty subtree has sum, count, and valid count as 0
        if (root == null) {
            return new int[3];
        }

        // A leaf node is always equal to the average of its subtree
        if (root.left == null && root.right == null) {
            return new int[]{root.val, 1, 1};
        }

        // Get information from the left subtree
        int[] left = solve(root.left);

        // Get information from the right subtree
        int[] right = solve(root.right);

        int[] arr = new int[3];

        // Calculate the sum of the current subtree
        arr[0] = left[0] + right[0] + root.val;

        // Calculate the number of nodes in the current subtree
        arr[1] = left[1] + right[1] + 1;

        // Add valid nodes from both subtrees
        arr[2] = left[2] + right[2];

        // Check whether the current node equals its subtree average
        if (arr[0] / arr[1] == root.val) {
            arr[2]++;
        }

        return arr;
    }
}
