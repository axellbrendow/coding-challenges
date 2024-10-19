package myjava;

import java.util.*;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/binary-tree-right-side-view/description/

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
*/

public class BinaryTreeRightSideView {
  public static void rightSideView(TreeNode root, int depth, List<Integer> values) {
    if (root == null) return;
    if (depth == values.size()) values.add(root.val);
    rightSideView(root.right, depth + 1, values);
    rightSideView(root.left, depth + 1, values);
  }

  public static List<Integer> rightSideView(TreeNode root) {
    List<Integer> values = new ArrayList<>();
    rightSideView(root, 0, values);
    return values;
  }

  public static void main(String[] args) {
    assert rightSideView(null).equals(List.of());
    assert rightSideView(new TreeNode(7)).equals(List.of(7));
    assert rightSideView(new TreeNode(7, new TreeNode(3), new TreeNode(4))).equals(List.of(7, 4));

    assert rightSideView(
      new TreeNode(
        15,
        /*left*/ new TreeNode(
          7,
          /*left*/ new TreeNode(3),
          /*right*/ null
        ),
        /*right*/ null
      )
    ).equals(List.of(15, 7, 3));

    assert rightSideView(
      new TreeNode(
        15,
        /*left*/ new TreeNode(
          7,
          /*left*/ new TreeNode(3),
          /*right*/ null
        ),
        /*right*/ new TreeNode(20)
      )
    ).equals(List.of(15, 20, 3));
  }
}
