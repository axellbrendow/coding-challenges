package myjava;

import myjava.shared.ListNode;

/*-
https://leetcode.com/problems/middle-of-the-linked-list/

null -> null
7 -> 7
s
f

1 2 -> 2
s
f

1 2 3 -> 2
  s
    f

1 2 3 4 -> 3
  s
    f

1 2 3 4 5 -> 3
    s
        f

*/

public class MiddleOfTheLinkedList {
  public static ListNode middleNode(ListNode head) {
    if (head == null) return null;
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return fast.next == null ? slow : slow.next;
  }

  public static void main(String[] args) {
    assert middleNode(null) == null;
    assert middleNode(new ListNode(7)).equals(new ListNode(7));
    assert middleNode(new ListNode(1, new ListNode(3))).equals(new ListNode(3));
    assert middleNode(new ListNode(1, new ListNode(3, new ListNode(5)))).equals(new ListNode(3));
    assert middleNode(new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(7))))).equals(
      new ListNode(5)
    );
  }
}
