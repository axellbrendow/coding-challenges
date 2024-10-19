from typing import List


def two_sum(nums: List[int], target: int):
    num_to_indices = {nums[i]: [] for i in range(len(nums))}
    for i in range(len(nums)):
        num_to_indices[nums[i]].append(i)
    for i, num in enumerate(nums):
        if num + num == target:
            if len(num_to_indices[num]) > 1:
                return [num_to_indices[num][0], num_to_indices[num][1]]
        elif target - num in num_to_indices:
            return [num_to_indices[num][0], num_to_indices[target - num][0]]


assert two_sum([2, 7, 11, 15], 9) == [0, 1]
assert two_sum([3, 2, 4], 6) == [1, 2]
assert two_sum([3, 3], 6) == [0, 1]
