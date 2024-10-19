package myjava.shared;

import java.util.*;

public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;

  public TreeNode() {
  }

  public TreeNode(int val) {
    this.val = val;
  }

  public TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  @Override
  public int hashCode() {
    return Objects.hash(val, left, right);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof TreeNode)) return false;
    TreeNode other = (TreeNode) obj;
    return Objects.equals(val, other.val) &&
      Objects.equals(left, other.left) &&
      Objects.equals(right, other.right);
  }

  @Override
  public String toString() {
    return toString(this, 0);
  }

  private String toString(TreeNode node, int level) {
    if (node == null) return "";
    return toString(node.right, level + 1) +
      "    ".repeat(level) + node.val + "\n" +
      toString(node.left, level + 1);
  }
}
