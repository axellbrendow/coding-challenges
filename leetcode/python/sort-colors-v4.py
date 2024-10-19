from typing import List


"""
This problem is also known as Dutch National Flag Problem
https://en.wikipedia.org/wiki/Dutch_national_flag_problem
Three-way partitioning algorithm

One pass algorithm

time: O(n)
space: O(1)
"""


def sortColors(nums: List[int]) -> None:
    """
    Do not return anything, modify nums in-place instead.
    """
    low, mid, high = 0, 0, len(nums) - 1
    while mid <= high:
        if nums[mid] == 0:
            nums[low], nums[mid] = nums[mid], nums[low]
            low += 1
            mid += 1
        elif nums[mid] == 1:
            mid += 1
        else:
            nums[mid], nums[high] = nums[high], nums[mid]
            high -= 1


nums = [2, 0, 2, 1, 1, 0]
expectedOutput = [0, 0, 1, 1, 2, 2]
sortColors(nums)
assert nums == expectedOutput

nums = [2, 0, 1]
expectedOutput = [0, 1, 2]
sortColors(nums)
assert nums == expectedOutput

nums = [2, 2, 0]
expectedOutput = [0, 2, 2]
sortColors(nums)
assert nums == expectedOutput
