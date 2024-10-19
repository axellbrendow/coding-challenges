package myjava;

import myjava.shared.ListNode;

class RemoveNthNodeFromEndOfListV1 {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    final var listLength = getListLength(head);
    final var nodesToSkip = listLength - n - 1;
    var node = head;
    if (nodesToSkip == -1)
      return node.next;
    for (int i = 0; i < nodesToSkip; i++) {
      node = node.next;
    }
    if (node.next != null) {
      node.next = node.next.next;
    }
    return head;
  }

  private int getListLength(ListNode head) {
    int length = 0;
    while (head != null) {
      length++;
      head = head.next;
    }
    return length;
  }
}
