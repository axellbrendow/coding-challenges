package myjava;

import java.util.*;

import myjava.shared.ListNode;

/*-
https://leetcode.com/problems/reorder-list/description/

You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Example 1:
Input: head = [1,2,3,4]
Output: [1,4,2,3]

Example 2:
Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]

Example 3:
Input: head = [1,2,3,4,5,6,7,8,9,10,11]
Output: [1,11,2,10,3,9,4,8,5,7,6]
1.next = 11 (index 10)
11.next = 1.next (saved)
curr = curr.next (11)
if (curr != null) curr = curr.next
-------
2.next = 10 (index 9)
10.next = 2.next (saved)
curr = curr.next (10)
if (curr != null) curr = curr.next
{
  0: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> null,
  1: 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> null,
  2: 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> null,
  3: 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> null,
  4: 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> null,
  5: 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> null,
  6: 7 -> 8 -> 9 -> 10 -> 11 -> null,
  7: 8 -> 9 -> 10 -> 11 -> null,
  8: 9 -> 10 -> 11 -> null,
  9: 10 -> 11 -> null,
  10: 11 -> null,
}

Example 4:
Input: head = [1]
Output: [1]
{
  0: 1 -> null
}

Constraints:

The number of nodes in the list is in the range [1, 5 * 10^4].
1 <= Node.val <= 1000
*/

public class ReorderListV1 {
  public static void reorderList(ListNode head) {
    Map<Integer, ListNode> indexMap = new HashMap<>();
    ListNode node = head;
    int i = 0;
    while (node != null) {
      indexMap.put(i++, node);
      final var next = node.next;
      node.next = null;
      node = next;
    }

    node = new ListNode(0, null);
    int numNodes = i, j = 0, left = 0, right = numNodes - 1;
    while (left <= right) {
      if (j % 2 == 0) node.next = indexMap.get(left++);
      else node.next = indexMap.get(right--);
      node = node.next;
      j++;
    }
  }

  public static void main(String[] args) {
    ListNode head;

    head = new ListNode(1);
    reorderList(head);
    assert head.toString().equals("1 -> null");

    head = new ListNode(1, new ListNode(2));
    reorderList(head);
    assert head.toString().equals("1 -> 2 -> null");

    head = new ListNode(1, new ListNode(2, new ListNode(3)));
    reorderList(head);
    assert head.toString().equals("1 -> 3 -> 2 -> null");

    head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
    reorderList(head);
    assert head.toString().equals("1 -> 4 -> 2 -> 3 -> null");

    head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
    reorderList(head);
    assert head.toString().equals("1 -> 5 -> 2 -> 4 -> 3 -> null");
  }
}
