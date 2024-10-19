from typing import List

"""
arr = [2,3,4,7,11], k = 5
Output: 9

seq = 1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16
arr =     2   3   4           7               11                    
idx =     0   1   2           3               4
How could we think about binary search in this case ?

For binary search to work, it needs to know that all elements in the left of
the middle are less than the middle and all elements on the right are greater
than the middle. The main question is, how can we divide our search space by 2
in this question ? Let's take left = 0 and right = 4, middle will be 2 and
arr[2] is 4. If we find a way to know that to the left side of four we only have
1 missing positive and our k is for example 9, we don't need to go to the left
anymore and we can discard the entire left side of 4. The key point here to discover
how many elements are missing are the array indices. If we are at index 3, the number
in index 3 should be 4 in a normal increasing sequence, if it's not 4, then we
can calculate the difference between the element in index 3 and the number 4, that
is the number of missing positives in the left side.

[2], k = 1
output = 1

[2,3], k = 2
left = 2
3 - (1 + 1) = 1
3 + (2 - 1) = 4
output = 4

[6, 7], k = 2
right = -1
return k

[6,7], k = 1

6 - 5 + (1 - 1) = 1

---

[1,2,4], k = 9
output = 12

4 - (2 + 1) = 1
4 + (9 - 1) = 4 + 8 = 12

left = 3
right = 2
mid = 2

---

1   2   3   4   5   6   7
1                       7
0                       1

7 - missing + k - 1

[1,7], k = 2
output = 3

2 + (k - missing on left side) = 2 + (2 - 0) = 4
missing on left side < k

7 - missing on left side + (k - 1) = 7 - 5 + 2 - 1 = 7 - 4 = 3
missing_on_the_left >= k

left = 1
right = 1
mid = 1

time O(logn)
space O(1)
"""


def findKthPositive(arr: List[int], k: int) -> int:
    left, right = 0, len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        missing_on_the_left = arr[mid] - (mid + 1)
        if left == right:
            if missing_on_the_left >= k:
                return arr[mid] - missing_on_the_left + (k - 1)
            else:
                return arr[mid] + (k - missing_on_the_left)
        elif missing_on_the_left >= k:
            right = mid - 1
        else:
            left = mid + 1

    if right < left:
        missing_on_the_left = arr[right] - (right + 1)
        return k if right == -1 else arr[right] + (k - missing_on_the_left)


assert findKthPositive(arr=[2], k=1) == 1
assert findKthPositive(arr=[3, 10], k=2) == 2
assert findKthPositive(arr=[1, 3, 9, 10], k=2) == 4
assert findKthPositive(arr=[2, 3, 4, 7, 11], k=5) == 9
assert findKthPositive(arr=[1, 2, 3, 4], k=2) == 6
assert findKthPositive(arr=[5, 6, 7, 8, 9], k=9) == 14
assert findKthPositive(arr=[3, 4, 9, 10, 12, 19, 24, 25, 27, 30], k=8) == 13
