package myjava;

import java.util.*;

import myjava.shared.ListNode;

/*-
https://leetcode.com/problems/merge-k-sorted-lists/description/

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6

Example 2:
Input: lists = []
Output: []

Example 3:
Input: lists = [[]]
Output: []

Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 10^4.

---

[
  1->2->3
  1->2->3
  1->2->3
  1->2->3
  1->2->3
]

[
  1->1->2->2->3->3
  1->1->2->2->3->3
  1->2->3
]

[
  1->1->1->1->2->2->2->2->3->3->3->3
  1->2->3
]

[
  1->1->1->1->1->2->2->2->2->2->3->3->3->3->3
]
*/

public class MergeKSortedLists {
  private static ListNode merge(ListNode l1, ListNode l2) {
    ListNode head = new ListNode(), curr = head;
    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        curr.next = new ListNode(l1.val);
        l1 = l1.next;
      }
      else {
        curr.next = new ListNode(l2.val);
        l2 = l2.next;
      }
      curr = curr.next;
    }
    while (l1 != null) {
      curr.next = new ListNode(l1.val);
      curr = curr.next;
      l1 = l1.next;
    }
    while (l2 != null) {
      curr.next = new ListNode(l2.val);
      curr = curr.next;
      l2 = l2.next;
    }
    return head.next;
  }

  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) return null;
    List<ListNode> oldLists = Arrays.stream(lists).toList();
    while (oldLists.size() > 1) {
      List<ListNode> newLists = new ArrayList<>();
      for (int i = 0; i + 1 < oldLists.size(); i += 2) {
        newLists.add(merge(oldLists.get(i), oldLists.get(i + 1)));
      }
      if (oldLists.size() % 2 == 1) newLists.add(oldLists.get(oldLists.size() - 1));
      oldLists = newLists;
    }
    return oldLists.get(0);
  }

  public static void main(String[] args) {
    assert mergeKLists(new ListNode[]{null, null, null}) == null;

    assert mergeKLists(
      new ListNode[]{
        new ListNode(List.of(1))
      }
    ).equals(new ListNode(List.of(1)));

    assert mergeKLists(
      new ListNode[]{
        new ListNode(List.of(1)),
        new ListNode(List.of(1, 2)),
      }
    ).equals(new ListNode(List.of(1, 1, 2)));

    assert mergeKLists(
      new ListNode[]{
        new ListNode(List.of(1)),
        new ListNode(List.of(1, 2)),
        new ListNode(List.of(-1, 0)),
      }
    ).equals(new ListNode(List.of(-1, 0, 1, 1, 2)));

    assert mergeKLists(
      new ListNode[]{
        new ListNode(List.of(1)),
        new ListNode(List.of(2)),
        new ListNode(List.of(3)),
        new ListNode(List.of(-1)),
        new ListNode(List.of(4)),
        new ListNode(List.of(-2)),
      }
    ).equals(new ListNode(List.of(-2, -1, 1, 2, 3, 4)));

    assert mergeKLists(
      new ListNode[]{
        new ListNode(List.of(-9,-7,-7)),
        new ListNode(List.of(-6,-4,-1,1)),
        new ListNode(List.of(-6,-5,-2,0,0,1,2)),
        new ListNode(List.of(-9,-8,-6,-5,-4,1,2,4)),
        new ListNode(List.of(-10)),
        new ListNode(List.of(-5,2,3)),
      }
    ).equals(new ListNode(List.of(-10,-9,-9,-8,-7,-7,-6,-6,-6,-5,-5,-5,-4,-4,-2,-1,0,0,1,1,1,2,2,2,3,4)));
  }
}
