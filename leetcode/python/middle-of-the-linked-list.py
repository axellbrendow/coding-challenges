from typing import Optional

"""
f
s
1 -> 2 -> 3 -> 4

          f
     s
1 -> 2 -> 3 -> 4

                    f
          s
1 -> 2 -> 3 -> 4 -> null

---

f
s
1 -> 2 -> 3

          f
     s
1 -> 2 -> 3 -> null

---

f
s
1 -> 2 -> null

          f
     s
1 -> 2 -> null
"""


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next: ListNode = next


def middleNode(head: Optional[ListNode]) -> Optional[ListNode]:
    slow, fast = head, head
    while fast != None and fast.next != None:
        slow = slow.next
        fast = fast.next.next
    return slow


head = ListNode(5)
expected_output = head
output = middleNode(head)
assert output == expected_output

head = ListNode(5, ListNode(7))
expected_output = head.next
output = middleNode(head)
assert output == expected_output

head = ListNode(5, ListNode(7, ListNode(-1)))
expected_output = head.next
output = middleNode(head)
assert output == expected_output
