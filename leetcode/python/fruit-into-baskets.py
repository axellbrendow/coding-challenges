from collections import defaultdict
from typing import List


def max_fruits_you_can_pick(fruits: List[int]) -> int:
    fruit_count = defaultdict(int)
    max_fruits = 0
    left, right = 0, -1

    for fruit in fruits:
        if fruit not in fruit_count:
            while len(fruit_count) == 2:
                fruit_count[fruits[left]] -= 1
                if fruit_count[fruits[left]] == 0:
                    del fruit_count[fruits[left]]
                left += 1
        right += 1
        fruit_count[fruit] += 1
        max_fruits = max(max_fruits, right - left + 1)

    return max_fruits


fruits = [1]
assert max_fruits_you_can_pick(fruits) == 1

fruits = [1, 2]
assert max_fruits_you_can_pick(fruits) == 2

fruits = [1, 2, 3]
assert max_fruits_you_can_pick(fruits) == 2

fruits = [1, 2, 1]
assert max_fruits_you_can_pick(fruits) == 3

fruits = [1, 2, 2]
assert max_fruits_you_can_pick(fruits) == 3

fruits = [2, 2, 2]
assert max_fruits_you_can_pick(fruits) == 3

fruits = [1, 2, 3, 2, 1]
assert max_fruits_you_can_pick(fruits) == 3
