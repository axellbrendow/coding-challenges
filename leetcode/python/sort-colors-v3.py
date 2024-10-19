from typing import List


"""
This problem is also known as Dutch National Flag Problem
https://en.wikipedia.org/wiki/Dutch_national_flag_problem

time: O(n)
space: O(1)

But passes 2 times in the array
"""


def sortColors(nums: List[int]) -> None:
    """
    Do not return anything, modify nums in-place instead.
    """
    num_0s, num_1s = 0, 0
    for num in nums:
        if num == 0:
            num_0s += 1
        if num == 1:
            num_1s += 1

    for i in range(num_0s):
        nums[i] = 0
    for i in range(num_1s):
        nums[num_0s + i] = 1
    for i in range(len(nums) - num_0s - num_1s):
        nums[num_0s + num_1s + i] = 2


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
