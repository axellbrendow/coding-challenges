package myjava;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/balanced-binary-tree/description/
*/

public class BalancedBinaryTreeV2 {
  public static int isBalancedRec(TreeNode root) {
    if (root == null) return 0;
    int leftHeight = isBalancedRec(root.left);
    if (leftHeight == -1) return -1;
    int rightHeight = isBalancedRec(root.right);
    return rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1
      ? -1
      : 1 + Math.max(leftHeight, rightHeight);
  }

  public static boolean isBalanced(TreeNode root) {
    return isBalancedRec(root) != -1;
  }

  public static void main(String[] args) {
    assert isBalanced(null) == true;
    assert isBalanced(new TreeNode()) == true;

    assert isBalanced(new TreeNode(0, new TreeNode(), null)) == true;
    assert isBalanced(new TreeNode(0, null, new TreeNode())) == true;
    assert isBalanced(new TreeNode(0, new TreeNode(), new TreeNode())) == true;

    assert isBalanced(
      new TreeNode(
        0,
        /*left*/ new TreeNode(
          0,
          /*left*/ new TreeNode(),
          /*right*/ null
        ),
        /*right*/ null
      )
    ) == false;

    assert isBalanced(
      new TreeNode(
        0,
        /*left*/ new TreeNode(
          0,
          /*left*/ new TreeNode(),
          /*right*/ null
        ),
        /*right*/ new TreeNode()
      )
    ) == true;
  }
}
