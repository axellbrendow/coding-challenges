package myjava;

import myjava.shared.ListNode;

class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int reminder = 0;
    int carry = 0;
    final var output = new ListNode();
    var currOutputNode = output;

    while (l1 != null && l2 != null) {
      reminder = (carry + l1.val + l2.val) % 10;
      carry = (carry + l1.val + l2.val) / 10;
      l1 = l1.next;
      l2 = l2.next;
      currOutputNode.next = new ListNode(reminder);
      currOutputNode = currOutputNode.next;
    }

    while (l1 != null) {
      reminder = (carry + l1.val) % 10;
      carry = (carry + l1.val) / 10;
      l1 = l1.next;
      currOutputNode.next = new ListNode(reminder);
      currOutputNode = currOutputNode.next;
    }

    while (l2 != null) {
      reminder = (carry + l2.val) % 10;
      carry = (carry + l2.val) / 10;
      l2 = l2.next;
      currOutputNode.next = new ListNode(reminder);
      currOutputNode = currOutputNode.next;
    }

    if (carry > 0) {
      currOutputNode.next = new ListNode(carry);
    }

    return output.next;
  }

  public static void main(String[] args) {
    AddTwoNumbers solution = new AddTwoNumbers();

    var l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
    var l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
    var expectedOutput = new ListNode(7, new ListNode(0, new ListNode(8)));
    assert expectedOutput.equals(solution.addTwoNumbers(l1, l2));

    l1 = new ListNode(2, new ListNode(4));
    l2 = new ListNode(9, new ListNode(5));
    expectedOutput = new ListNode(1, new ListNode(0, new ListNode(1)));
    assert expectedOutput.equals(solution.addTwoNumbers(l1, l2));
  }
}
