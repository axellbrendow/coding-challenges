from typing import List

# time O(n)
# space O(1)


def findDisappearedNumbers(nums: List[int]) -> List[int]:
    for num in nums:
        i = abs(num) - 1
        if nums[i] > 0:
            nums[i] *= -1
    output = []
    for i in range(len(nums)):
        if nums[i] > 0:
            output.append(i + 1)
    return output


nums = [4, 3, 2, 7, 8, 2, 3, 1]
assert findDisappearedNumbers(nums) == [5, 6]

nums = [1, 1]
assert findDisappearedNumbers(nums) == [2]

nums = [1, 2]
assert findDisappearedNumbers(nums) == []
