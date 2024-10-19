
from typing import Optional


"""
Overall idea:

f : first_node
l : last_node

f
l
a -> b -> c -> d -> e

f
     l
b -> a -> c -> d -> e

f
          l
c -> b -> a -> d -> e

f
               l
d -> c -> b -> a -> e

f
                    l
e -> d -> c -> b -> a
"""


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next: ListNode = next

    def __eq__(self, __value: object) -> bool:
        if not isinstance(__value, ListNode):
            return False

        me = self
        them = __value

        while me and them:
            if me.val != them.val:
                return False
            me = me.next
            them = them.next

        return not me and not them


def reverseList(head: Optional[ListNode]) -> Optional[ListNode]:
    if not head:
        return None

    first_node = head
    last_node = head

    while last_node.next:
        last_node_next_next = last_node.next.next

        last_node.next.next = first_node
        first_node = last_node.next

        last_node.next = last_node_next_next

    return first_node


head = None
expected_output = None
output = reverseList(head)
assert output == expected_output

head = ListNode(5)
expected_output = ListNode(5)
output = reverseList(head)
assert output == expected_output

head = ListNode(5, ListNode(7))
expected_output = ListNode(7, ListNode(5))
output = reverseList(head)
assert output == expected_output

head = ListNode(5, ListNode(7, ListNode(-1)))
expected_output = ListNode(-1, ListNode(7, ListNode(5)))
output = reverseList(head)
assert output == expected_output
