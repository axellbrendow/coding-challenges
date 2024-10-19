package myjava;

import myjava.shared.TreeNode;

/**-
https://www.lintcode.com/problem/614/description

Binary Tree Longest Consecutive Sequence II

Given a binary tree, find the length(number of nodes) of the longest consecutive sequence(Monotonic and
adjacent node values differ by 1) path. The path could be start and end at any node in the tree

      3*
  2       2*
X   x   0   1*

      3
  3       3
3   3   3   3

              3*
      2*               4*
  1*       3       5*       1
0*   3   4   3   3   6*   3   3

              0
      2*               4
  1*       3*       5       1
0*   3   4*   3   3   6   3   3

              0
      2*               4
  3*       1*       5       1
4*   3   0*   0   3   6   3   3

*/

public class BinaryTreeLongestConsecutiveSequenceII {
  // LintCode doesn't support Java Records, so I use this class:
  // private static class PathSizes {
  //   private final int increasing;
  //   private final int decreasing;
  //   private final int max;
  //   private PathSizes(int increasing, int decreasing, int max) {
  //     this.increasing = increasing;
  //     this.decreasing = decreasing;
  //     this.max = max;
  //   }
  // }

  private static record PathSizes(int increasing, int decreasing, int max) {}

  public static PathSizes longestConsecutive2(TreeNode root, Integer parentValue) {
    if (root == null) return new PathSizes(0, 0, 0);
    PathSizes leftPathSizes = longestConsecutive2(root.left, root.val);
    PathSizes rightPathSizes = longestConsecutive2(root.right, root.val);
    return new PathSizes(
      parentValue != null && root.val == parentValue - 1
        ? Math.max(1 + leftPathSizes.increasing, 1 + rightPathSizes.increasing)
        : 0,
      parentValue != null && root.val == parentValue + 1
      ? Math.max(1 + leftPathSizes.decreasing, 1 + rightPathSizes.decreasing)
      : 0,
      Math.max(
        leftPathSizes.max,
        Math.max(
          rightPathSizes.max,
          Math.max(
            1 + leftPathSizes.increasing + rightPathSizes.decreasing,
            1 + leftPathSizes.decreasing + rightPathSizes.increasing
          )
        )
      )
    );
  }

  public static int longestConsecutive2(TreeNode root) {
      return longestConsecutive2(root, null).max;
  }

  public static void main(String[] args) {
    assert longestConsecutive2(
      new TreeNode(
        0,
        /*left*/ new TreeNode(
          2,
          /*left*/ new TreeNode(
            3,
            /*left*/ new TreeNode(4),
            /*right*/ new TreeNode(3)
          ),
          /*right*/ new TreeNode(
            1,
            /*left*/ new TreeNode(0),
            /*right*/ new TreeNode(0)
          )
        ),
        /*right*/ new TreeNode(
          4,
          /*left*/ new TreeNode(
            5,
            /*left*/ new TreeNode(3),
            /*right*/ new TreeNode(6)
          ),
          new TreeNode(
            1,
            /*left*/ new TreeNode(3),
            /*right*/ new TreeNode(3)
          )
        )
      )
    ) == 5;
  }
}
