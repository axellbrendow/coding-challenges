package myjava;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/balanced-binary-tree/description/
*/

public class BalancedBinaryTreeV1 {
  static record IsBalancedReturn(int height, boolean isBalanced) {
  }

  public static IsBalancedReturn isBalancedRec(TreeNode root) {
    if (root == null) return new IsBalancedReturn(0, true);
    IsBalancedReturn leftResult = isBalancedRec(root.left);
    if (!leftResult.isBalanced()) return new IsBalancedReturn(leftResult.height() + 1, false);
    IsBalancedReturn rightResult = isBalancedRec(root.right);
    return new IsBalancedReturn(
      1 + Math.max(leftResult.height(), rightResult.height()),
      rightResult.isBalanced() && Math.abs(leftResult.height() - rightResult.height()) <= 1
    );
  }

  public static boolean isBalanced(TreeNode root) {
    return isBalancedRec(root).isBalanced();
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
