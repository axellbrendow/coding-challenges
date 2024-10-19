from typing import List

# Monotonic stack, the values in the stack are always increasing and I
# always keep the index of the greatest element found so far in the stack


def findUnsortedSubarray(nums: List[int]):
    left, right = float('+inf'), -1
    stack = []

    for i in range(len(nums)):
        if len(stack) > 0 and nums[i] < nums[stack[-1]]:
            greatest_elem_index = stack[-1]
            while len(stack) > 0 and nums[i] < nums[stack[-1]]:
                left = min(left, stack.pop())
            stack.append(greatest_elem_index)
            right = i
        else:
            stack.append(i)

    return 0 if left == float('+inf') and right == -1 else right - left + 1


assert findUnsortedSubarray([1, 2, 9, 8, 7, 7, 11, 12, 4, 5, 6]) == 9
assert findUnsortedSubarray([1, 2, 9, 8, 7, 7, 11, 12, 4, 5, 6, 0]) == 12
assert findUnsortedSubarray([1, 2, 3]) == 0
assert findUnsortedSubarray([1, 2, 3, 3, 3]) == 0
assert findUnsortedSubarray([2, 1, 3, 3]) == 2
assert findUnsortedSubarray([3, 3, 1, 4]) == 3
assert findUnsortedSubarray([1, 3, 2]) == 2
assert findUnsortedSubarray([3, 2, 1]) == 3
assert findUnsortedSubarray([2, 3, 3, 2, 4]) == 3
