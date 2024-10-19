from typing import Optional

"""
- move fast pointer by k positions
- if fast pointer stops at non null, then I know I should reverse the list
    between slow and fast
- reverseList(slow, fast)
                f
        s
head = [dummy,1,2], k = 2

                      f
                s
head = [dummy,2,1], k = 2

                    f
            s
head = [dummy,1,2,3,4,5], k = 4
stack = [1,2,3,4]
dummy.next = top of the stack
dummy = dummy.next
dummy.next = save reference to number 5

time: 3 * O(n) = O(n)
space: O(k), worst case O(n)

head = [dummy,4,3,2,1,5], k = 4

"""


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next: ListNode = next

    def __iter__(self):
        curr = self
        while curr:
            yield curr
            curr = curr.next


# dummy = [dummy,1,2,3,4,5], last = [2,3,4,5]
def reverseList(dummy, last):
    after_last = last.next  # [3,4,5]
    curr = dummy.next  # [2,3,4,5]
    stack = []  # [1->2, 2->3]
    while curr != last:
        stack.append(curr)
        curr = curr.next
    stack.append(last)

    while len(stack) > 0:
        node = stack.pop()
        dummy.next = node
        dummy = dummy.next

    dummy.next = after_last


# head = [1,2,3,4,5], k = 2
def reverseKGroup(head: Optional[ListNode], k: int) -> Optional[ListNode]:
    if k == 1:
        return head
    dummy = ListNode(None, head)  # [dummy,1,2,3,4,5]
    slow, fast = dummy, dummy  # [dummy,1,2,3,4,5], [2,3,4,5]

    def move_fast():
        nonlocal fast
        for _ in range(k):
            fast = fast.next
            if fast == None:
                break

    move_fast()

    while fast != None:
        new_slow = slow.next  # slow.next will be the last node after reversing
        reverseList(slow, fast)
        slow = fast = new_slow
        move_fast()

    return dummy.next


linkedlist = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))
expectedOutput = [2, 1, 4, 3, 5]
output = reverseKGroup(linkedlist, k=2)
assert not any([node.val != expectedOutput[i]
               for i, node in enumerate(output)])


linkedlist = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))
expectedOutput = [3, 2, 1, 4, 5]
output = reverseKGroup(linkedlist, k=3)
assert not any([node.val != expectedOutput[i]
               for i, node in enumerate(output)])
