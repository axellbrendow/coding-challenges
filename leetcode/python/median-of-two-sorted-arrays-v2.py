from typing import List

# Inspired by https://www.youtube.com/watch?v=nv7F4PiLUzo&ab_channel=takeUforward
# Inspired by https://www.youtube.com/watch?v=NTop3VTjmxk&ab_channel=takeUforward

"""
The code for this question treats indexes as positions where we're placing a separator.

So if we say left = 0, you should imagine the following (imagine the pipe '|' is a separator):

[|1 2 3]

If we say right = 1, you should imagine the following:

[1 | 2 3]

So right = 1 doesn't mean an index in the array, it means an imaginary index for a separator.

---

   left=0          right=5
nums1 = [1,5,6,8,11]
nums2 = [2,3,4,7,10]
expected_output = (5 + 6) / 2

Time complexity should be log_2(len(nums1) + len(nums2)) = log_2(10) which is a number between 3 and 4,
this means the algorithm should find an answer in +/- 4 iterations.

To see where the median will fit, we can imagine both arrays as one:

separator indexes: 0  1   2   3   4     5     6   7   8    9   10
merged array:      [1   2   3   4   5   |   6   7   8   10   11]

                                        The + 1 here is to always make the left side greater than the right side in odd sizes
median_separator = (len(nums1) + len(nums2) + 1) / 2 = (5 + 5 + 1) / 2 = 11 / 2 = 5

nums1_separator = (left + right) / 2 = (0 + 5) / 2 = 5 / 2 = 2
nums2_separator = median_separator - nums1_separator = 5 - 2 = 3

separator indexes: 0 1  2  3 4  5
nums1 =            [1 5 | 6 8 11]

separator indexes: 0 1 2  3  4  5
nums2 =            [2 3 4 | 7 10]

answer = (max(5, 4) + min(6, 7)) / 2 = (5 + 6) / 2

---

   left=0      right=1
nums1 = [100001]
nums2 = [100000]
expected_output = (100000 + 100001) / 2

Time complexity should be log_2(len(nums1) + len(nums2)) = log_2(2) which is 1,
this means the algorithm should find an answer in +/- 1 iterations.

To see where the median will fit, we can imagine both arrays as one:

separator indexes: 0       1       2
merged array:      [100000 | 100001]

                                        The + 1 here is to always make the left side greater than the right side in odd sizes
median_separator = (len(nums1) + len(nums2) + 1) / 2 = (1 + 1 + 1) / 2 = 3 / 2 = 1

nums1_separator = (left + right) / 2 = (0 + 1) / 2 = 0
nums2_separator = median_separator - nums1_separator = 1 - 0 = 1

nums1 = [|100001]
nums2 = [100000|]

answer = (max(-inf, 100000) + min(100001, +inf)) / 2 = (100000 + 100001) / 2

---

   left=0       right=4
nums1 = [1,2,3,6]
nums2 = [4,5]
expected_output = (3 + 4) / 2

Time complexity should be log_2(len(nums1) + len(nums2)) = log_2(6) which is a number between 2 and 3.

median_separator = (len(nums1) + len(nums2) + 1) / 2 = (4 + 2 + 1) / 2 = 7 / 2 = 3

nums1_separator = (left + right) / 2 = (0 + 4) / 2 = 2
nums2_separator = median_separator - nums1_separator = 3 - 2 = 1

nums1 = [1 2 | 3 6]
nums2 = [4 | 5]  <--- This partition is invalid because the number 4 should be swapped with the number 3 in the other side.

left = nums1_separator + 1 = 2 + 1 = 3
nums1_separator = (left + right) / 2 = (3 + 4) / 2 = 3
nums2_separator = median_separator - nums1_separator = 3 - 3 = 0

nums1 = [1 2 3 | 6]
nums2 = [| 4 5]

answer = (max(3, -inf) + min(4, 6)) / 2 = (3 + 4) / 2

---

   left=0     right=3
nums1 = [1,2,3]
nums2 = [4,5,6]
expected_output = (3 + 4) / 2

Time complexity should be log_2(len(nums1) + len(nums2)) = log_2(6) which is a number between 2 and 3.

median_separator = (len(nums1) + len(nums2) + 1) / 2 = (3 + 3 + 1) / 2 = 7 / 2 = 3

nums1_separator = (left + right) / 2 = (0 + 3) / 2 = 1
nums2_separator = median_separator - nums1_separator = 3 - 1 = 2

nums1 = [1 | 2 3]
nums2 = [4 5 | 6]  <--- This partition is invalid because 4 and 5 are greater than 2.

left = nums1_separator + 1 = 1 + 1 = 2
nums1_separator = (left + right) / 2 = (2 + 3) / 2 = 2
nums2_separator = median_separator - nums1_separator = 3 - 2 = 1

nums1 = [1 2 | 3]
nums2 = [4 | 5 6]  <--- This partition is invalid because 4 is greater than 2.

left = nums1_separator + 1 = 2 + 1 = 3
nums1_separator = (left + right) / 2 = (3 + 3) / 2 = 3
nums2_separator = median_separator - nums1_separator = 3 - 3 = 0

nums1 = [1 2 3|]
nums2 = [|4 5 6]

answer = (max(3, -inf) + min(+inf, 4)) / 2 = (3 + 4) / 2

---

   left=0 right=0
nums1 = []
nums2 = [1,2,3,4,5]
expected_output = 3

Time complexity should be log_2(len(nums1) + len(nums2)) = log_2(5) which is a number between 2 and 3.

median_separator = (len(nums1) + len(nums2) + 1) / 2 = (5 + 0 + 1) / 2 = 6 / 2 = 3

nums1_separator = (left + right) / 2 = (0 + 0) / 2 = 0
nums2_separator = median_separator - nums1_separator = 3 - 0 = 3

nums1 = [|]
nums2 = [1 2 3 | 4 5]

answer = nums2[nums2_separator - 1] = 3

---

   left=0 right=1
nums1 = [7]
nums2 = [1,2,3,4,5]
expected_output = (3 + 4) / 2

Time complexity should be log_2(len(nums1) + len(nums2)) = log_2(6) which is a number between 2 and 3.

median_separator = (len(nums1) + len(nums2) + 1) / 2 = (1 + 5 + 1) / 2 = 7 / 2 = 3

nums1_separator = (left + right) / 2 = (0 + 1) / 2 = 0
nums2_separator = median_separator - nums1_separator = 3 - 0 = 3

nums1 = [|7]
nums2 = [1 2 3 | 4 5]

answer = (max(-inf, 3) + min(4, 7)) / 2 = (3 + 4) / 2

---

nums1 = [1,2,3,4,5]
nums2 = [-1,0]
expected_output = 2

Swap nums1 and nums2 to let the smaller array as nums1.

   left=0    right=2
nums1 = [-1,0]
nums2 = [1,2,3,4,5]

Time complexity should be log_2(len(nums1) + len(nums2)) = log_2(7) which is a number between 2 and 3.


median_separator = (len(nums1) + len(nums2) + 1) / 2 = (2 + 5 + 1) / 2 = 8 / 2 = 4

nums1_separator = (left + right) / 2 = (0 + 2) / 2 = 1
nums2_separator = median_separator - nums1_separator = 4 - 1 = 3

nums1 = [-1 | 0]
nums2 = [1 2 3 | 4 5]  <--- This partition is invalid because 1, 2 and 3 are greater than 0.

left = nums1_separator + 1 = 1 + 1 = 2
nums1_separator = (left + right) / 2 = (2 + 2) / 2 = 2
nums2_separator = median_separator - nums1_separator = 4 - 2 = 2

nums1 = [-1 0|]
nums2 = [1 2 | 3 4 5]

answer = max(2, 0) = 2
"""


def findMedianSortedArrays(nums1: List[int], nums2: List[int]) -> float:
    if len(nums1) > len(nums2):
        nums2, nums1 = nums1, nums2

    total = len(nums1) + len(nums2)
    left, right = 0, len(nums1)
    median_separator = (total + 1) // 2

    while left <= right:
        nums1_separator = (left + right) // 2
        nums2_separator = median_separator - nums1_separator

        nums1_left_side_greatest_value = nums1[nums1_separator - 1] \
            if 0 <= nums1_separator - 1 < len(nums1) else float('-inf')

        nums1_right_side_smallest_value = nums1[nums1_separator] \
            if 0 <= nums1_separator < len(nums1) else float('+inf')

        nums2_left_side_greatest_value = nums2[nums2_separator - 1] \
            if 0 <= nums2_separator - 1 < len(nums2) else float('-inf')

        nums2_right_side_smallest_value = nums2[nums2_separator] \
            if 0 <= nums2_separator < len(nums2) else float('+inf')

        if (
            nums1_left_side_greatest_value <= nums2_right_side_smallest_value
            and nums2_left_side_greatest_value <= nums1_right_side_smallest_value
        ):
            if total % 2 == 0:
                return (
                    max(nums1_left_side_greatest_value, nums2_left_side_greatest_value) +
                    min(nums1_right_side_smallest_value,
                        nums2_right_side_smallest_value)
                ) / 2
            else:
                return max(nums1_left_side_greatest_value, nums2_left_side_greatest_value)
        elif nums1_left_side_greatest_value > nums2_right_side_smallest_value:
            right = nums1_separator - 1
        else:
            left = nums1_separator + 1

    return 0


nums1 = [100001]
nums2 = [100000]
expected_output = (100000 + 100001) / 2
output = findMedianSortedArrays(nums1, nums2)
assert output == expected_output

nums1 = [1, 5, 6, 8, 11]
nums2 = [2, 3, 4, 7, 10]
expected_output = (5 + 6) / 2
output = findMedianSortedArrays(nums1, nums2)
assert output == expected_output

nums1 = [1, 3, 5, 8, 11]
nums2 = [2, 4, 6]
expected_output = (4 + 5) / 2
output = findMedianSortedArrays(nums1, nums2)
assert output == expected_output

nums1 = [1, 2, 3, 6]
nums2 = [4, 5]
expected_output = (3 + 4) / 2
output = findMedianSortedArrays(nums1, nums2)
assert output == expected_output

nums1 = [1, 2, 3]
nums2 = [4, 5, 6]
expected_output = (3 + 4) / 2
output = findMedianSortedArrays(nums1, nums2)
assert output == expected_output

nums1 = []
nums2 = [1, 2, 3, 4, 5]
expected_output = 3
output = findMedianSortedArrays(nums1, nums2)
assert output == expected_output

nums1 = [7]
nums2 = [1, 2, 3, 4, 5]
expected_output = (3 + 4) / 2
output = findMedianSortedArrays(nums1, nums2)
assert output == expected_output

nums1 = [1, 2, 3, 4, 5]
nums2 = [-1, 0]
expected_output = 2
output = findMedianSortedArrays(nums1, nums2)
assert output == expected_output

nums1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
nums2 = [-12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0]
expected_output = 1
output = findMedianSortedArrays(nums1, nums2)
assert output == expected_output
