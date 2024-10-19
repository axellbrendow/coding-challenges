"""
To explain this solution, take this sequence as an example:

a <= b <= c <= d <= e

The letter c needs to be:
    - less than or equal everyone in the right side,
        this is the same as: c should be less than or qual the maximum in its right side
    - greater than or equal everyone its left side,
        this is the same as: c should be greater than or qual the minimum in its left side

How do we know if c is breaking these rules ?

This rule should apply:
    - c >= left_side_max
    - c <= right_side_min
"""


def findUnsortedSubarray(nums):
    right = -1
    left_side_max = float('-inf')
    for i in range(len(nums)):
        if not (nums[i] >= left_side_max):
            right = i
        left_side_max = max(left_side_max, nums[i])

    left = -1
    right_side_min = float('+inf')
    for i in range(len(nums) - 1, -1, -1):
        if not (nums[i] <= right_side_min):
            left = i
        right_side_min = min(right_side_min, nums[i])

    return 0 if left == -1 and right == -1 else right - left + 1


assert findUnsortedSubarray([1, 2, 9, 8, 7, 7, 11, 12, 4, 5, 6]) == 9
assert findUnsortedSubarray([1, 2, 9, 8, 7, 7, 11, 12, 4, 5, 6, 0]) == 12
assert findUnsortedSubarray([1, 2, 3]) == 0
assert findUnsortedSubarray([1, 2, 3, 3, 3]) == 0
assert findUnsortedSubarray([2, 1, 3, 3]) == 2
assert findUnsortedSubarray([3, 3, 1, 4]) == 3
assert findUnsortedSubarray([1, 3, 2]) == 2
assert findUnsortedSubarray([3, 2, 1]) == 3
assert findUnsortedSubarray([2, 3, 3, 2, 4]) == 3
