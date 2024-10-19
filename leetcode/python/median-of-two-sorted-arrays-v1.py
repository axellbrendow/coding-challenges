from typing import List

# Inspired by https://www.youtube.com/watch?v=q6IEA26hvXc&ab_channel=NeetCode


def findMedianSortedArrays(nums1: List[int], nums2: List[int]) -> float:
    if len(nums1) < len(nums2):
        small_arr, greater_arr = nums1, nums2
    else:
        small_arr, greater_arr = nums2, nums1

    total = len(nums1) + len(nums2)
    half = total // 2

    l, r = 0, len(small_arr) - 1

    while True:
        small_mid = (l + r) // 2
        greater_mid = half - small_mid - 2

        small_left_elem = small_arr[small_mid] if small_mid >= 0 \
            else float("-infinity")

        small_right_elem = small_arr[small_mid + 1] if small_mid + 1 < len(small_arr) \
            else float("+infinity")

        greater_left_elem = greater_arr[greater_mid] if greater_mid >= 0 \
            else float("-infinity")

        greater_right_elem = greater_arr[greater_mid + 1] if greater_mid + 1 < len(greater_arr) \
            else float("+infinity")

        if small_left_elem <= greater_right_elem and greater_left_elem <= small_right_elem:
            if total % 2 == 1:
                return min(small_right_elem, greater_right_elem)
            else:
                left_elem = max(small_left_elem, greater_left_elem)
                right_elem = min(small_right_elem, greater_right_elem)
                return (left_elem + right_elem) / 2

        elif small_left_elem > greater_right_elem:
            r = small_mid - 1

        else:
            l = small_mid + 1


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
