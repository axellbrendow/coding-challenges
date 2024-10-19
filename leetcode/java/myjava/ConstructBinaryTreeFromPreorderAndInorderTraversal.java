package myjava;

import java.util.*;
import java.util.stream.*;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/

Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and
inorder is the inorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]

Constraints:
1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.

---

                            x
            x                               x
    x               x               x               x
x       x       x       x       x       x       x       x

                            1
            2                               x
    3               x               x               x
4       x       x       x       x       x       x       x

preorder 1 2 3 4
inorder 4 3 2 1

                            1
            2                               x
    3               x               x               x
x       4       x       x       x       x       x       x

preorder 1 2 3 4
inorder 3 4 2 1

when I'm at node 2, I know it is 1's left child because 2 comes before 1 in the inorder traversal

                            1
            x                               2
    x               x               x               x
x       X       x       x       x       x       x       x

preorder 1 2
inorder 1 2

                            1
            3                               2
    4               x               x               x
5       X       x       x       x       x       x       x

preorder 1 3 4 5 2
inorder 5 4 3 1 2

                            1
            3                               2
    4               8               7               x
5       X       x       6       x       x       x       x

preorder 1 3 4 5 8 6 2 7
inorder 5 4 3 8 6 1 7 2

                            1
            3                               2
    4               8               x               x

preorder 1 3 4 8 2
inorder 4 3 8 1 2
*/

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> valToIndexInOrder = IntStream.range(0, inorder.length)
      .boxed()
      .collect(Collectors.toMap(i -> inorder[i], i -> i));

    TreeNode root = new TreeNode(preorder[0]);
    Deque<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    for (int i = 1; i < preorder.length; i++) {
      final var myIndex = valToIndexInOrder.get(preorder[i]);
      if (myIndex < valToIndexInOrder.get(stack.peek().val)) {
        stack.peek().left = new TreeNode(preorder[i]);
        stack.push(stack.peek().left);
      } else {
        TreeNode parent = stack.pop();
        while (!stack.isEmpty() && myIndex > valToIndexInOrder.get(stack.peek().val)) parent = stack.pop();
        parent.right = new TreeNode(preorder[i]);
        stack.push(parent.right);
      }
    }
    return root;
  }

  public static void main(String[] args) {
    assert buildTree(new int[]{1,3,4,8,2}, new int[]{4,3,8,1,2}).equals(
      new TreeNode(
        1,
        /*left*/ new TreeNode(
          3,
          /*left*/ new TreeNode(4),
          /*right*/ new TreeNode(8)
        ),
        /*right*/ new TreeNode(2)
      )
    );
  }
}
