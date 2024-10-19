def find_num_pairs(left, right, nums, target):
    ans = 0
    while left < right:
        if nums[left] + nums[right] < target:
            ans += right - left
            left += 1
        else:
            right -= 1
    return ans


def find_num_triplets(nums, target):
    nums.sort()
    ans = 0
    for i in range(len(nums) - 2):
        ans += find_num_pairs(i + 1, len(nums) - 1, nums, target - nums[i])
    return ans


nums = [-2, 0, 1, 3]
target = 2
assert find_num_triplets(nums, target) == 2

nums = [-2, 0, -1, 3]
target = 2
assert find_num_triplets(nums, target) == 3

nums = [3, 1, 0, -2]
target = 4
assert find_num_triplets(nums, target) == 3
