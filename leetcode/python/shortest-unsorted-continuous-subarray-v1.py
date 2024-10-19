# WRONG ANSWER
# 11, [1, 2, 9, 8, 7, 7, 11, 12, 4, 5, 6]*
def find_greatest_unsorted_subarray_from(start, nums):
    subarray_start = -1  # -1
    subarray_end = -2  # -2
    while start < len(nums) and nums[start] > nums[start - 1]:
        start += 1
    if start < len(nums):
        subarray_start = start - 1  # 7
        subarray_end = start  # 10
        while start < len(nums) and nums[start] <= nums[subarray_start]:
            start += 1
            subarray_end = start
        subarray_end -= 1
    return subarray_start, subarray_end


def findUnsortedSubarray(nums):  # [1, 2, 9, 8, 7, 7, 11, 12, 4, *5, 6]
    start = 1  # 11
    # 2, 10
    subarray_start, subarray_end = find_greatest_unsorted_subarray_from(
        start, nums)
    start = subarray_end + 1  # 6
    while start > 0 and start < len(nums):
        _, new_subarray_end = find_greatest_unsorted_subarray_from(start, nums)
        if new_subarray_end == -2:
            break
        subarray_end = new_subarray_end
        start = subarray_end + 1
    return subarray_end - subarray_start + 1  # 10 - 2 + 1 = 9


assert findUnsortedSubarray([1, 2, 9, 8, 7, 7, 11, 12, 4, 5, 6]) == 9
assert findUnsortedSubarray([1, 2, 3]) == 0
# assert findUnsortedSubarray([1, 2, 3, 3, 3]) == 0
# assert findUnsortedSubarray([2, 1, 3, 3]) == 2
assert findUnsortedSubarray([3, 3, 1, 4]) == 3
assert findUnsortedSubarray([1, 3, 2]) == 2
assert findUnsortedSubarray([3, 2, 1]) == 3
