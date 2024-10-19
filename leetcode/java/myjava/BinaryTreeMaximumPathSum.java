package myjava;

import myjava.shared.TreeNode;

/*-
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an
edge connecting them. A node can only appear in the sequence at most once. Note that the path does not
need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

Example 1:
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Constraints:

The number of nodes in the tree is in the range [1, 3 * 10^4].
-1000 <= Node.val <= 1000

              -1
      1               9
  1       1       x       x
1   x   x   1   x   x   x   x

answer: 9

              -1
      (16)2               9(21)
  1        -5       2        3
1   3   9    15   2   2   -2   5

answer: 21

calculate best paths of each subtree
*/

public class BinaryTreeMaximumPathSum {
  private int maxSum = Integer.MIN_VALUE;

  public int maxPathSumRec(TreeNode root) {
    if (root == null) return 0;
    int leftBestPath = maxPathSumRec(root.left);
    int rightBestPath = maxPathSumRec(root.right);
    maxSum = Math.max(maxSum, root.val + leftBestPath + rightBestPath);
    int myBestPath = Math.max(
      root.val,
      Math.max(
        root.val + leftBestPath,
        root.val + rightBestPath
      )
    ); // Could be IntStream.of(root.val, root.val + leftBestPath, root.val + rightBestPath).max().getAsInt();
    maxSum = Math.max(maxSum, myBestPath);
    return myBestPath;
  }

  public int maxPathSum(TreeNode root) {
    maxSum = Integer.MIN_VALUE;
    maxPathSumRec(root);
    return maxSum;
  }

  public static void main(String[] args) {
    final var question = new BinaryTreeMaximumPathSum();

    assert question.maxPathSum(new TreeNode()) == 0;
    assert question.maxPathSum(new TreeNode(-3)) == -3;
    assert question.maxPathSum(new TreeNode(1)) == 1;

    assert question.maxPathSum(
      new TreeNode(
        1,
        /*left*/ new TreeNode(-1),
        /*right*/ new TreeNode(-1)
      )
    ) == 1;

    assert question.maxPathSum(
      new TreeNode(
        1,
        /*left*/ new TreeNode(2),
        /*right*/ new TreeNode(3)
      )
    ) == 6;

    assert question.maxPathSum(
      new TreeNode(
        -10,
        /*left*/ new TreeNode(9),
        /*right*/ new TreeNode(
          20,
          /*left*/ new TreeNode(15),
          /*right*/ new TreeNode(7)
        )
      )
    ) == 42;

    assert question.maxPathSum(
      new TreeNode(
        5,
        /*left*/ new TreeNode(
          4,
          /*left*/ new TreeNode(
            11,
            /*left*/ new TreeNode(7),
            /*right*/ new TreeNode(2)
          ),
          /*right*/ null
        ),
        /*right*/ new TreeNode(
          8,
          /*left*/ new TreeNode(13),
          /*right*/ new TreeNode(
            4,
            /*left*/ null,
            /*right*/ new TreeNode(1)
          )
        )
      )
    ) == 48;
  }
}
