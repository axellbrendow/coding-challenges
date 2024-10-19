package myjava;

import java.util.*;

import myjava.shared.ListNode;

/*-
https://leetcode.com/problems/reverse-linked-list/description/

Given the head of a singly linked list, reverse the list, and return the reversed list.

a -> null

a -> b -> null
b -> a -> null

a -> b -> c -> null
c -> b -> a -> null

a -> b -> c -> d -> null
d -> c -> b -> a -> null

a -> b -> c -> d -> e -> null
a -> null
b -> a -> null
c -> d -> e -> null

e -> d -> c -> b -> a -> null

Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000

Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

public class ReverseLinkedList {
  public static ListNode reverseList(ListNode head) {
    ListNode prev = null, curr = head, next = null;
    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }

  public static void main(String[] args) {
    assert reverseList(null) == null;
    assert reverseList(new ListNode(List.of(1))).equals(new ListNode(List.of(1)));
    assert reverseList(new ListNode(List.of(1, 2))).equals(new ListNode(List.of(2, 1)));
    assert reverseList(new ListNode(List.of(1, 2, 3))).equals(new ListNode(List.of(3, 2, 1)));

    assert reverseList(
      new ListNode(List.of(1, 2, 3, 4))
    ).equals(new ListNode(List.of(4, 3, 2, 1)));

    assert reverseList(
      new ListNode(List.of(1, 2, 3, 4, 5))
    ).equals(new ListNode(List.of(5, 4, 3, 2, 1)));
  }
}
