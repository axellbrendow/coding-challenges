package myjava;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/invert-binary-tree/description/

If you rotate a tree using the root as the axis, everything that is on the right side will go to the left side
*/

public class InvertBinaryTree {
  private static TreeNode invertBinaryTree(TreeNode root) {
    if (root == null) return null;
    invertBinaryTree(root.left);
    invertBinaryTree(root.right);
    final var tmp = root.left;
    root.left = root.right;
    root.right = tmp;
    return root;
  }

  public static void main(String[] args) {
    assert invertBinaryTree(null) == null;
    assert invertBinaryTree(new TreeNode()).equals(new TreeNode());
    assert !invertBinaryTree(new TreeNode()).equals(new TreeNode(1));

    assert invertBinaryTree(
      new TreeNode(0, new TreeNode(), null)
    ).equals(
      new TreeNode(0, null, new TreeNode())
    );

    assert invertBinaryTree(
      new TreeNode(0, null, new TreeNode())
    ).equals(
      new TreeNode(0, new TreeNode(), null)
    );

    assert invertBinaryTree(
      new TreeNode(0, new TreeNode(1), new TreeNode(2))
    ).equals(
      new TreeNode(0, new TreeNode(2), new TreeNode(1))
    );
  }
}
