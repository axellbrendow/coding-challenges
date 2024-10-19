from typing import List


def canPartition(nums: List[int]) -> bool:
    numsSum = sum(nums)

    if len(nums) == 1 or numsSum % 2 == 1:
        return False

    # If both subsets should have the same sum, our target is half the array sum
    targetSum = numsSum // 2
    subsetSums = {0}

    for num in nums:
        moreSubsetSums = set()
        for subsetSum in subsetSums:
            newSum = subsetSum + num
            if newSum == targetSum:
                return True
            moreSubsetSums.add(newSum)
        subsetSums = subsetSums | moreSubsetSums

    return False


nums = [1, 5, 11, 5]
expectedOutput = True
output = canPartition(nums)
assert output == expectedOutput

nums = [1, 2, 3, 5]
expectedOutput = False
output = canPartition(nums)
assert output == expectedOutput

nums = [2, 2, 3, 5]
expectedOutput = False
output = canPartition(nums)
assert output == expectedOutput
