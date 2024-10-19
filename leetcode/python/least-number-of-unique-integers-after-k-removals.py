from typing import List
from collections import Counter

"""
arr = [5,5,4], k = 1, output = 1

If I remove the first 5, then I have 2 unique integers
[5,4]

If I remove the second 5, then I have 2 unique integers
[5,4]

If I remove the first 4, then I have 1 unique integer
[5,5]

---

arr = [4,3,1,1,3,3,2], k = 3, output = 2

[1,3,3,2]

[1,3,3,2]

[1,1,3,2]

[1,1,3,2]

- Generate all subsets of size k -> O(exponential)
- For each of them, remove the subsets elements from the array -> O(k)
- Store the solution which gives the smallest number of unique integers -> O(n) where n is the arr size

time: O(exponential) * (O(k) + O(n))

---

for 0..len
    for 1..len
        for 2..len
            copy, remove i, j, k and count number of unique integers

[4,3,1,1,3,3,2]

frequency:

{
    4: 1,
    2: 1,
    1: 2,
    3: 3,
}

time: O(nlogn)
"""


def findLeastNumOfUniqueInts(arr: List[int], k: int) -> int:
    count = Counter(arr)
    arr.sort(key=lambda elem: (count[elem], elem))
    return len(set(arr[k:]))


arr = [4, 3, 1, 1, 3, 3, 2]
assert findLeastNumOfUniqueInts(arr, k=3) == 2

arr = [5, 1, 3, 1, 3]
assert findLeastNumOfUniqueInts(arr, k=3) == 1
