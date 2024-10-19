# TIME LIMIT EXCEEDED

from typing import List

"""
BRUTE FORCE

a <= b <= c <= d <= e

nums = [2,6,6,8,10,9,15]
nums = [2]
nums = [2,6]
nums = [2,*6,4*]
nums = [2,*6,4*,8]
nums = [2,*6,4*,8,10]
nums = [2,*6,4,8,10,9*]
nums = [2,*6,4,8,10,9*,15]

time: O(n x n x nlogn) = O(n³ logn)
"""


def is_sorted(arr):
    for i in range(len(arr) - 1):
        if not (arr[i] <= arr[i + 1]):
            return False
    return True


def findUnsortedSubarray(nums: List[int]) -> int:
    if is_sorted(nums):
        return 0
    for size in range(1, len(nums) + 1):
        for start in range(len(nums) - (size - 1)):
            nums_copy = nums.copy()
            nums_copy[start:start + size] = sorted(nums[start:start + size])
            if is_sorted(nums_copy):
                return size


assert findUnsortedSubarray([1, 2, 9, 8, 7, 7, 11, 12, 4, 5, 6]) == 9
assert findUnsortedSubarray([1, 2, 9, 8, 7, 7, 11, 12, 4, 5, 6, 0]) == 12
assert findUnsortedSubarray([1, 2, 3]) == 0
assert findUnsortedSubarray([1, 2, 3, 3, 3]) == 0
assert findUnsortedSubarray([2, 1, 3, 3]) == 2
assert findUnsortedSubarray([3, 3, 1, 4]) == 3
assert findUnsortedSubarray([1, 3, 2]) == 2
assert findUnsortedSubarray([3, 2, 1]) == 3
assert findUnsortedSubarray([2, 3, 3, 2, 4]) == 3
