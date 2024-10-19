from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next: ListNode = next

    def __iter__(self):
        curr = self
        while curr:
            yield curr
            curr = curr.next


def rotateRight(head: Optional[ListNode], k: int) -> Optional[ListNode]:
    if not head:
        return None

    size = 1
    last = head
    while last.next:  # Get list size
        size += 1
        last = last.next

    k = k % size
    if k == 0:
        return head
    last.next = head  # Create a cycle in the list between first and last nodes

    for _ in range(size - k - 1):
        head = head.next

    new_head = head.next
    head.next = None  # Remove the cycle
    return new_head


linkedlist = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))
expectedOutput = [4, 5, 1, 2, 3]
output = rotateRight(linkedlist, k=2)
assert not any([node.val != expectedOutput[i]
               for i, node in enumerate(output)])


linkedlist = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))
expectedOutput = [5, 1, 2, 3, 4]
output = rotateRight(linkedlist, k=1)
assert not any([node.val != expectedOutput[i]
               for i, node in enumerate(output)])


linkedlist = ListNode(0, ListNode(1, ListNode(2)))
expectedOutput = [2, 0, 1]
output = rotateRight(linkedlist, k=4)
assert not any([node.val != expectedOutput[i]
               for i, node in enumerate(output)])
