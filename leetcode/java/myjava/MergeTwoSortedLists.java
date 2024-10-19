package myjava;

import java.util.*;

import myjava.shared.ListNode;

/*-
https://leetcode.com/problems/merge-two-sorted-lists/description/

You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of
the first two lists.

Return the head of the merged linked list.

Example 1:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: list1 = [], list2 = []
Output: []

Example 3:
Input: list1 = [], list2 = [0]
Output: [0]

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
*/

public class MergeTwoSortedLists {
  public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null && list2 == null) return null;
    ListNode head = new ListNode(), curr = head;
    while (list1 != null || list2 != null) {
      if (list2 == null || (list1 != null && list1.val <= list2.val)) {
        curr.next = new ListNode(list1.val);
        list1 = list1.next;
      }
      else {
        curr.next = new ListNode(list2.val);
        list2 = list2.next;
      }
      curr = curr.next;
    }
    return head.next;
  }

  public static void main(String[] args) {
    assert mergeTwoLists(null, null) == null;
    assert mergeTwoLists(new ListNode(List.of(1)), null).equals(new ListNode(List.of(1)));
    assert mergeTwoLists(null, new ListNode(List.of(1))).equals(new ListNode(List.of(1)));

    assert mergeTwoLists(
      new ListNode(List.of(1)),
      new ListNode(List.of(1))
    ).equals(new ListNode(List.of(1, 1)));

    assert mergeTwoLists(
      new ListNode(List.of(1)),
      new ListNode(List.of(0))
    ).equals(new ListNode(List.of(0, 1)));

    assert mergeTwoLists(
      new ListNode(List.of(0)),
      new ListNode(List.of(1))
    ).equals(new ListNode(List.of(0, 1)));

    assert mergeTwoLists(
      new ListNode(List.of(1)),
      new ListNode(List.of(0, 1, 2))
    ).equals(new ListNode(List.of(0, 1, 1, 2)));
  }
}
