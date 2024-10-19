from collections import Counter
from typing import List


# Bucket sort approach, O(n) linear time
# Quickselect is also an interesting algorithm for this problem and solves it in linear time too
def topKFrequent(nums: List[int], k: int) -> List[int]:
    num_frequency = Counter(nums)
    buckets = [[] for i in range(len(nums) + 1)]
    for num in num_frequency:
        buckets[num_frequency[num]].append(num)
    output = []
    for bucket in reversed(buckets):
        for element in bucket:
            output.append(element)
            if len(output) == k:
                return output
    return output


nums = [1, 1, 1, 2, 2, 3]
k = 2
assert topKFrequent(nums, k) == [1, 2]

nums = [1]
k = 1
assert topKFrequent(nums, k) == [1]
