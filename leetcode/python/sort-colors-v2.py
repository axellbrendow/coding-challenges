from typing import List


"""
This problem is also known as Dutch National Flag Problem
https://en.wikipedia.org/wiki/Dutch_national_flag_problem

Idea:
    - Count how many 0's and 1's
    - If a number is not in the range it should be, put this number in the range it should be
        and recursively use this definition for the number that will be replaced there.

time: O(n)
space: O(n)

In theory, this algorithm passes only 1 time in the array, but in practice I think it's a little bit more,
but it's less than 2 times. It's a weird algorithm 😅.
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

    pos_for_0s, pos_for_1s, pos_for_2s = 0, num_0s, num_0s + num_1s

    def fix_pos_of(num):
        nonlocal pos_for_0s, pos_for_1s, pos_for_2s

        if num == 0:
            if pos_for_0s >= num_0s:
                return
            while pos_for_0s < num_0s and nums[pos_for_0s] == 0:
                pos_for_0s += 1
            prev_num = None
            if pos_for_0s < num_0s:
                prev_num = nums[pos_for_0s]
                nums[pos_for_0s] = num
                pos_for_0s += 1
            if prev_num != None:
                fix_pos_of(prev_num)
        elif num == 1:
            if pos_for_1s >= num_0s + num_1s:
                return
            while pos_for_1s < num_0s + num_1s and nums[pos_for_1s] == 1:
                pos_for_1s += 1
            prev_num = None
            if pos_for_1s < num_0s + num_1s:
                prev_num = nums[pos_for_1s]
                nums[pos_for_1s] = num
                pos_for_1s += 1
            if prev_num != None:
                fix_pos_of(prev_num)
        else:
            if pos_for_2s >= len(nums):
                return
            while pos_for_2s < len(nums) and nums[pos_for_2s] == 2:
                pos_for_2s += 1
            prev_num = None
            if pos_for_2s < len(nums):
                prev_num = nums[pos_for_2s]
                nums[pos_for_2s] = num
                pos_for_2s += 1
            if prev_num != None:
                fix_pos_of(prev_num)

    for i in range(len(nums)):
        if i == num_0s + num_1s:
            break
        if nums[i] == 0 and i > num_0s:
            fix_pos_of(nums[i])
        if nums[i] == 1 and (i < num_0s or i >= num_0s + num_1s):
            fix_pos_of(nums[i])
        if nums[i] == 2 and i < num_0s + num_1s:
            fix_pos_of(nums[i])


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
