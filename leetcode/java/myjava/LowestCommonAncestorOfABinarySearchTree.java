package myjava;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between two
nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to
be a descendant of itself)."

Constraints:
The number of nodes in the tree is in the range [2, 10^5].
-10^9 <= Node.val <= 10^9
All Node.val are unique.
p != q
p and q will exist in the BST.
*/

public class LowestCommonAncestorOfABinarySearchTree {
  public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      TreeNode pSearchPointer = root, qSearchPointer = root, lowestCommonAncestor = null;
      while (pSearchPointer != null && pSearchPointer == qSearchPointer) {
          lowestCommonAncestor = pSearchPointer; // Works because p != q, if p can be == q then should use min()

          if (p.val < pSearchPointer.val) pSearchPointer = pSearchPointer.left;
          else if (p.val > pSearchPointer.val) pSearchPointer = pSearchPointer.right;
          
          if (q.val < qSearchPointer.val) qSearchPointer = qSearchPointer.left;
          else if (q.val > qSearchPointer.val) qSearchPointer = qSearchPointer.right;
      }
      return lowestCommonAncestor;
  }

  public static void main(String[] args) {
    assert lowestCommonAncestor(null, null, null) == null;
  }
}
