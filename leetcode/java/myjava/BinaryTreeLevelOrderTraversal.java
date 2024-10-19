package myjava;

import java.util.*;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/binary-tree-level-order-traversal/description/
*/

public class BinaryTreeLevelOrderTraversal {
  public static List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) return List.of();
    List<List<Integer>> levels = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int numOfElemsInCurrLevel = queue.size();
      List<Integer> level = new ArrayList<>(numOfElemsInCurrLevel);
      for (int i = 0; i < numOfElemsInCurrLevel; i++) {
        TreeNode node = queue.poll();
        level.add(node.val);
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
      }
      levels.add(level);
    }
    return levels;
  }

  public static void main(String[] args) {
    assert levelOrder(null).equals(List.of());
    assert levelOrder(new TreeNode(1)).equals(List.of(List.of(1)));
    assert levelOrder(new TreeNode(1, new TreeNode(-1), new TreeNode(2))).equals(
      List.of(
        List.of(1),
        List.of(-1, 2)
      )
    );
  }
}
