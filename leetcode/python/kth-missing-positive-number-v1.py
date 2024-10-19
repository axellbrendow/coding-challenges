from typing import List

# time O(n + k)
# space O(n)


def findKthPositive(arr: List[int], k: int) -> int:
    values = set(arr)
    for i in range(1, len(arr) + k + 1):
        if not i in values:
            k -= 1
            if k == 0:
                return i


assert findKthPositive(arr=[2], k=1) == 1
assert findKthPositive(arr=[3, 10], k=2) == 2
assert findKthPositive(arr=[1, 3, 9, 10], k=2) == 4
assert findKthPositive(arr=[2, 3, 4, 7, 11], k=5) == 9
assert findKthPositive(arr=[1, 2, 3, 4], k=2) == 6
assert findKthPositive(arr=[5, 6, 7, 8, 9], k=9) == 14
assert findKthPositive(arr=[3, 4, 9, 10, 12, 19, 24, 25, 27, 30], k=8) == 13
