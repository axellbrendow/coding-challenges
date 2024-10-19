package myjava;

import myjava.shared.TreeNode;

/**-
https://www.lintcode.com/problem/595/

Binary Tree Longest Consecutive Sequence

Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the
parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

    1
  2
    3
      2
        1

output -> 3 (sequence is 1 2 3)

      2 (longest -> 1, parentLen = 0, parentValue null)
    1 (longest -> 1, parentLen = 0, parentValue 2)
  2 (longest -> 2, parentLen = 1, parentValue 1)
0 (longest -> 2, parentLen = 2, parentValue 1)    3
      2
        1

start longest with 0
      2 (longest -> 1, parentLen = 0, parentValue null)
    1 (longest -> 1, parentLen = 1, parentValue 2)
  2 (longest -> 2, parentLen = 1, parentValue 1)
0   3 (longest -> 3, parentLen = 2, parentValue 2)
      2 (longest -> 3, parentLen = 3, parentValue 3)
        1 (longest -> 3, parentLen = 1, parentValue 2)

output -> 3 (sequence is 1 2 3)

*/

public class BinaryTreeLongestConsecutiveSequence {
  private int longest = 0;

  public void longestConsecutive(TreeNode root, int parentLen, Integer parentValue) {
      if (root == null) return;
      int newParentLen = parentValue == null || root.val != parentValue + 1 ? 1 : parentLen + 1;
      longest = Math.max(longest, newParentLen);
      longestConsecutive(root.left, newParentLen, root.val);
      longestConsecutive(root.right, newParentLen, root.val);
  }

  public int longestConsecutive(TreeNode root) {
      longest = 0;
      longestConsecutive(root, 0, null);
      return longest;
  }

  public static void main(String[] args) {
    assert new BinaryTreeLongestConsecutiveSequence().longestConsecutive(null) == 0;
    assert new BinaryTreeLongestConsecutiveSequence().longestConsecutive(new TreeNode(7)) == 1;
    assert new BinaryTreeLongestConsecutiveSequence().longestConsecutive(
      new TreeNode(7, new TreeNode(8), null)
    ) == 2;
    assert new BinaryTreeLongestConsecutiveSequence().longestConsecutive(
      new TreeNode(7, new TreeNode(8), new TreeNode(8))
    ) == 2;
    assert new BinaryTreeLongestConsecutiveSequence().longestConsecutive(
      new TreeNode(7, new TreeNode(9), null)
    ) == 1;
  }
}
