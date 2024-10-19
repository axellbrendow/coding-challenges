package myjava;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/validate-binary-search-tree/description/
*/

public class ValidateBinarySearchTree {
  private static boolean isValidBST(TreeNode root, long minValue, long maxValue) {
    if (root == null) return true;
    if (!(minValue < root.val && root.val < maxValue)) return false;
    return isValidBST(root.left, minValue, root.val) && isValidBST(root.right, root.val, maxValue);
  }

  private static boolean isValidBST(TreeNode root) {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public static void main(String[] args) {
    assert isValidBST(null) == true;
    assert isValidBST(new TreeNode()) == true;
    assert isValidBST(new TreeNode(Integer.MAX_VALUE)) == true;
    assert isValidBST(new TreeNode(Integer.MIN_VALUE)) == true;
    assert isValidBST(new TreeNode(0, new TreeNode(-1), null)) == true;
    assert isValidBST(new TreeNode(0, new TreeNode(1), null)) == false;
    assert isValidBST(new TreeNode(0, null, new TreeNode(-1))) == false;
    assert isValidBST(new TreeNode(0, null, new TreeNode(1))) == true;
    assert isValidBST(new TreeNode(0, new TreeNode(1), new TreeNode(2))) == false;
    assert isValidBST(new TreeNode(0, new TreeNode(-1), new TreeNode(2))) == true;
    assert isValidBST(new TreeNode(0, new TreeNode(-1), new TreeNode(2))) == true;

    assert isValidBST(
      new TreeNode(
        5,
        /*left*/ new TreeNode(
          4,
          /*left*/ null,
          /*right*/ new TreeNode(7)
        ),
        /*right*/ null
      )
    ) == false;
  }
}
