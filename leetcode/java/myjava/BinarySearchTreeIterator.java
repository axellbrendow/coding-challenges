package myjava;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

import myjava.shared.TreeNode;

/*-
https://leetcode.com/problems/binary-search-tree-iterator/description/

15
7
3

next()
  returns the top of the stack
  go to the right (stacks the node) and then all the way to the left stacking all the nodes
*/

public class BinarySearchTreeIterator {
  private Deque<TreeNode> stack;

  public BinarySearchTreeIterator(TreeNode root) {
    stack = new LinkedList<>();
    pushAllLeftNodes(root);
  }

  private void pushAllLeftNodes(TreeNode root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }

  public int next() {
    TreeNode topNode = stack.pop();
    pushAllLeftNodes(topNode.right);
    return topNode.val;
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  public static void main(String[] args) {
    BinarySearchTreeIterator bstIter;

    bstIter = new BinarySearchTreeIterator(null);
    assert bstIter.hasNext() == false;

    bstIter = new BinarySearchTreeIterator(new TreeNode(0));
    assert bstIter.hasNext() == true;
    assert bstIter.next() == 0;

    bstIter = new BinarySearchTreeIterator(new TreeNode(0, new TreeNode(-1), new TreeNode(1)));
    assert bstIter.hasNext() == true;
    assert bstIter.next() == -1;
    assert bstIter.hasNext() == true;
    assert bstIter.next() == 0;
    assert bstIter.hasNext() == true;
    assert bstIter.next() == 1;
    assert bstIter.hasNext() == false;

    bstIter = new BinarySearchTreeIterator(
      new TreeNode(
        15,
        /*left*/ new TreeNode(
          7,
          /*left*/ new TreeNode(3),
          /*right*/ new TreeNode(10)
        ),
        /*right*/ new TreeNode(
          25,
          /*left*/ new TreeNode(22),
          /*right*/ null
        )
      )
    );
    assert bstIter.hasNext() == true;
    assert bstIter.next() == 3;
    assert bstIter.hasNext() == true;
    assert bstIter.next() == 7;
    assert bstIter.hasNext() == true;
    assert bstIter.next() == 10;
    assert bstIter.hasNext() == true;
    assert bstIter.next() == 15;
    assert bstIter.hasNext() == true;
    assert bstIter.next() == 22;
    assert bstIter.hasNext() == true;
    assert bstIter.next() == 25;
    assert bstIter.hasNext() == false;
  }
}
