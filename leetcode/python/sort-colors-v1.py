from typing import List


"""
This problem is also known as Dutch National Flag Problem
https://en.wikipedia.org/wiki/Dutch_national_flag_problem

time: O(n)
space: O(n)

counting sort

nums = [2,0,2,1,1,0]
count = [0, 0, 0]
count = [2, 2, 2]

accumulated = [2, 4, 6]
output = [0, 0, 0, 0, 0, 0]

accumulated = [2, 4, 5]
output = [0, 0, 0, 0, 0, 2]

accumulated = [2, 4, 4]
output = [0, 0, 0, 0, 2, 2]

accumulated = [2, 3, 4]
output = [0, 0, 0, 1, 2, 2]

accumulated = [2, 2, 4]
output = [0, 0, 1, 1, 2, 2]

accumulated = [1, 2, 4]
output = [0, 0, 1, 1, 2, 2]

accumulated = [0, 2, 4]
output = [0, 0, 1, 1, 2, 2]
"""


# nums = [2,0,2,1,1,0]
def sortColors(nums: List[int]) -> None:
    """
    Do not return anything, modify nums in-place instead.
    """
    output = [0 for _ in range(len(nums))]  # [0, 0, 0, 0, 0, 2]
    count = [0, 0, 0]  # [2, 4, 5]

    for num in nums:  # num = 1
        count[num] += 1

    for i in range(1, len(count)):  # i = 2
        count[i] += count[i - 1]

    for i in range(len(count) - 1, -1, -1):  # i = 2
        while (i == 0 and count[i] > 0) or (i > 0 and count[i] > count[i - 1]):
            count[i] -= 1
            output[count[i]] = i

    for i in range(len(nums)):
        nums[i] = output[i]


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
