def wiggle_sort(nums):
	index = 0
	sorted_nums = sorted(nums)
	left, right = (len(nums) - 1) // 2, len(nums) - 1
	while left >= 0 and right > (len(nums) - 1) // 2:
		nums[index] = sorted_nums[left]
		index += 1
		left -= 1
		nums[index] = sorted_nums[right]
		index += 1
		right -= 1
	if left >= 0:
		nums[index] = sorted_nums[left]

def check_wiggle_sort(output):
	for i in range(len(output) - 1):
		if i % 2 == 0 and output[i] >= output[i + 1]: return False
		elif i % 2 == 1 and output[i] <= output[i + 1]: return False
	return True

nums = [1, 2, 2, 3, 3]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [3, 3, 2, 2, 1]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [3, 2, 1]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [1, 2, 3]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [1, 2, 3, 4, 5]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [2, 3, 7]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [2, 7, 3, 9]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [3, 3, 2, 2, 1]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [1, 1, 3, 2]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [1, 5, 4, 1, 6, 1]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [2, 3, 3, 9]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [4, 5, 5, 6]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True
