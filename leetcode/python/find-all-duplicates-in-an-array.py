from typing import List


def findDuplicates(nums: List[int]) -> List[int]:
    output = []
    for num in nums:
        i = abs(num) - 1
        if nums[i] < 0:
            output.append(abs(num))
        else:
            nums[i] *= -1
    return output


nums = [4, 3, 2, 7, 8, 2, 3, 1]
assert findDuplicates(nums) == [2, 3]

nums = [1, 1, 2]
assert findDuplicates(nums) == [1]

nums = [1]
assert findDuplicates(nums) == []
