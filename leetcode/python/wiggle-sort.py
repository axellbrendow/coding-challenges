def wiggle_sort(nums):
	for i in range(len(nums) - 1):
		if i % 2 == 0:
			if nums[i] > nums[i + 1]:
				nums[i], nums[i + 1] = nums[i + 1], nums[i]
		else:
			if nums[i] < nums[i + 1]:
				nums[i], nums[i + 1] = nums[i + 1], nums[i]

nums = []
wiggle_sort(nums)
assert str(nums) == str([])

nums = [1]
wiggle_sort(nums)
assert str(nums) == str([1])

nums = [1, 2]
wiggle_sort(nums)
assert str(nums) == str([1, 2])

nums = [2, 1]
wiggle_sort(nums)
assert str(nums) == str([1, 2])

nums = [2, 1, 4]
wiggle_sort(nums)
assert str(nums) == str([1, 4, 2])

nums = [3, 5, 2, 1, 6, 4]
wiggle_sort(nums)
assert str(nums) == str([3, 5, 1, 6, 2, 4])
