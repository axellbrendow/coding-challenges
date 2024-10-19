from typing import List

# time O(n)
# space O(n)


def findDisappearedNumbers(nums: List[int]) -> List[int]:
    num_present = [False] * len(nums)
    for num in nums:
        num_present[num - 1] = True
    output = []
    for num in range(len(nums)):
        if not num_present[num]:
            output.append(num + 1)
    return output


nums = [4, 3, 2, 7, 8, 2, 3, 1]
assert findDisappearedNumbers(nums) == [5, 6]

nums = [1, 1]
assert findDisappearedNumbers(nums) == [2]

nums = [1, 2]
assert findDisappearedNumbers(nums) == []
