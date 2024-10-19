package myjava;

import myjava.shared.ListNode;

class RemoveNthNodeFromEndOfListV3 {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    final var dummy = new ListNode();
    dummy.next = head;
    var slowPointer = dummy;
    var fastPointer = dummy;

    // Move the fastPointer `n` steps to make sure I'll have a distance
    // of `n` steps between the slow pointer and the fast pointer.
    for (int i = 0; i < n; i++) {
      fastPointer = fastPointer.next;
    }

    // Move both pointers keeping the distance of `n` steps
    while (fastPointer.next != null) {
      fastPointer = fastPointer.next;
      slowPointer = slowPointer.next;
    }

    // The slow pointer stops one node before the nth node from the end of the list
    slowPointer.next = slowPointer.next.next;
    return dummy.next;
  }
}
