# Wrong solution

def find_closest_greater_than(val, nums, start):
	closest = -1
	for i in range(start, len(nums)):
		if nums[i] > val and (closest == -1 or nums[i] < nums[closest]):
			closest = i
	return closest

def find_closest_smaller_than(val, nums, start):
	closest = -1
	for i in range(start, len(nums)):
		if nums[i] < val and (closest == -1 or nums[i] > nums[closest]):
			closest = i
	return closest

def wiggle_sort(nums):
	for i in range(len(nums) - 1):
		if i % 2 == 0:  # current element < next element
			if nums[i] > nums[i + 1]:
				nums[i], nums[i + 1] = nums[i + 1], nums[i]
			elif nums[i] == nums[i + 1]:
				index = find_closest_smaller_than(nums[i], nums, i + 1)
				if index == -1: continue
				nums[i], nums[index] = nums[index], nums[i]
		else:  # current element > next element
			if nums[i] < nums[i + 1]:
				nums[i], nums[i + 1] = nums[i + 1], nums[i]
			elif nums[i] == nums[i + 1]:
				index = find_closest_greater_than(nums[i], nums, i + 1)
				if index == -1: continue
				nums[i], nums[index] = nums[index], nums[i]

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

# These two test cases make the algorithm fail

nums = [2, 3, 3, 9]  # output should be something like [3, 9, 2, 3]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True

nums = [4, 5, 5, 6]  # same situation, output should be something like [5, 6, 4, 5]
wiggle_sort(nums)
assert check_wiggle_sort(nums) == True
