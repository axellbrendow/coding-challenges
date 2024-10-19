from typing import List

"""
arr = [2,3,4,7,11], k = 5
Output: 9

seq = 1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16
arr =     2   3   4           7               11                    

The overall idea is to loop over the elements and get the differences between them,
these differences indicate the number of elements missing between them.

prev = 0
missing = 0
missing = missing + 2 - 0 - 1 = 0 + 1
missing = missing + 3 - 2 - 1 = 1 + 0
missing = missing + 4 - 3 - 1 = 1 + 0
missing = missing + 7 - 4 - 1 = 1 + 2
missing = missing + 11 - 7 - 1 = 3 + 3 = 6

11 - (missing - k + 1) = 11 - (6 - 5 + 1) = 11 - 2 = 9

arr = [1,2,3,4], k = 2
Output: 6

prev = 0
count = 0

arr[-1] + k

seq = 1   2   3   4   5   6
arr = 1   2   3   6        

missing = 1

arr[-1] + k - missing = 4 + 2 - 1 = 5

time O(n)
space O(1)
"""


def findKthPositive(arr: List[int], k: int) -> int:
    prev = 0
    missing = 0
    for i in range(len(arr)):
        missing += arr[i] - prev - 1
        if missing >= k:
            return arr[i] - (missing - k + 1)
        prev = arr[i]
    return arr[-1] + k - missing


assert findKthPositive(arr=[2], k=1) == 1
assert findKthPositive(arr=[3, 10], k=2) == 2
assert findKthPositive(arr=[1, 3, 9, 10], k=2) == 4
assert findKthPositive(arr=[2, 3, 4, 7, 11], k=5) == 9
assert findKthPositive(arr=[1, 2, 3, 4], k=2) == 6
assert findKthPositive(arr=[5, 6, 7, 8, 9], k=9) == 14
assert findKthPositive(arr=[3, 4, 9, 10, 12, 19, 24, 25, 27, 30], k=8) == 13
