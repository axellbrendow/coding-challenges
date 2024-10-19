package myjava;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/count-good-nodes-in-binary-tree/

  3
3
  0
    9
      null
*/

public class CountGoodNodesInBinaryTree {
  public static int goodNodes(TreeNode root, int maxValueInThePath) {
    if (root == null) return 0;
    return (root.val >= maxValueInThePath ? 1 : 0) +
      goodNodes(root.left, Math.max(maxValueInThePath, root.val)) +
      goodNodes(root.right, Math.max(maxValueInThePath, root.val));
  }

  public static int goodNodes(TreeNode root) {
    return goodNodes(root, Integer.MIN_VALUE);
  }

  public static void main(String[] args) {
    assert goodNodes(null) == 0;
    assert goodNodes(new TreeNode()) == 1;

    assert goodNodes(new TreeNode(0, new TreeNode(1), null)) == 2;
    assert goodNodes(new TreeNode(0, null, new TreeNode(1))) == 2;
    assert goodNodes(new TreeNode(0, new TreeNode(1), new TreeNode(1))) == 3;

    assert goodNodes(new TreeNode(0, new TreeNode(0), null)) == 2;
    assert goodNodes(new TreeNode(0, null, new TreeNode(0))) == 2;
    assert goodNodes(new TreeNode(0, new TreeNode(0), new TreeNode(0))) == 3;

    assert goodNodes(new TreeNode(0, new TreeNode(-1), null)) == 1;
    assert goodNodes(new TreeNode(0, null, new TreeNode(-1))) == 1;
    assert goodNodes(new TreeNode(0, new TreeNode(-1), new TreeNode(-1))) == 1;
  }
}
