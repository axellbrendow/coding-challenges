package myjava;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
*/

public class MaximumDepthOfBinaryTree {
  public static int maxDepth(TreeNode root, int depth) {
    if (root == null) return depth;
    return Math.max(maxDepth(root.left, depth + 1), maxDepth(root.right, depth + 1));
  }

  public static int maxDepth(TreeNode root) {
    return maxDepth(root, 0);
  }

  public static void main(String[] args) {
    assert maxDepth(null) == 0;
    assert maxDepth(new TreeNode()) == 1;
    assert maxDepth(new TreeNode(0, new TreeNode(), null)) == 2;
    assert maxDepth(new TreeNode(0, null, new TreeNode())) == 2;
    assert maxDepth(new TreeNode(0, new TreeNode(), new TreeNode())) == 2;
  }
}
