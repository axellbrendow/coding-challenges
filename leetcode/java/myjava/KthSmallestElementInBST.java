package myjava;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/kth-smallest-element-in-a-bst/

Constraints:

The number of nodes in the tree is n.
1 <= k <= n <= 10^4
0 <= Node.val <= 10^4
*/

public class KthSmallestElementInBST {
  private int kthSmallest;

  public int kthSmallest(TreeNode root, int k, int smallestChildOrder) {
    if (root == null) return smallestChildOrder;
    int myOrder = kthSmallest(root.left, k, smallestChildOrder);
    if (myOrder == -1) return -1;
    if (myOrder == k) {
      kthSmallest = root.val;
      return -1;
    }
    return kthSmallest(root.right, k, myOrder + 1);
  }

  public int kthSmallest(TreeNode root, int k) {
    kthSmallest = Integer.MAX_VALUE;
    kthSmallest(root, k, 1);
    return kthSmallest;
  }

  public static void main(String[] args) {
    final var solution = new KthSmallestElementInBST();
    assert solution.kthSmallest(
      new TreeNode(
        5,
        /*left*/ new TreeNode(
          3,
          /*left*/ new TreeNode(
            2,
            /*left*/ new TreeNode(1),
            /*right*/ null
          ),
          /*right*/ null
        ),
        /*right*/ null
      ),
      /*k*/ 3
    ) == 3;
  }
}
