package myjava;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/same-tree/description/

Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:
Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:
Input: p = [1,2,1], q = [1,1,2]
Output: false
 

Constraints:

The number of nodes in both trees is in the range [0, 100].
-10^4 <= Node.val <= 10^4
*/

public class SameTree {
  public static boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  public static void main(String[] args) {
    assert isSameTree(null, null) == true;
    assert isSameTree(null, new TreeNode()) == false;
    assert isSameTree(new TreeNode(), null) == false;

    assert isSameTree(
      new TreeNode(
        0,
        /*left*/ new TreeNode(3),
        /*right*/ null
      ),
      new TreeNode(
        0,
        /*left*/ new TreeNode(3),
        /*right*/ new TreeNode()
      )
    ) == false;

    assert isSameTree(
      new TreeNode(
        0,
        /*left*/ new TreeNode(3),
        /*right*/ null
      ),
      new TreeNode(
        0,
        /*left*/ new TreeNode(3),
        /*right*/ null
      )
    ) == true;
  }
}
