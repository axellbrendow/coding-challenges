def find_next_greater_elements(nums1, nums2):  # n2 [1, 2, -3, 4, -5, 6], n1 [6, 2, -5]
	pos = {num: i for i, num in enumerate(nums2)}
	greater = {i: -1 for i in range(len(nums2))}
	stack = []  # [5]
	for i, num in enumerate(nums2):  # i = 6, num = 6
		while len(stack) > 0 and num > nums2[stack[-1]]:
			greater[stack.pop()] = i
		stack.append(i)
	return [
		nums2[greater[pos[nums1[i]]]] if greater[pos[nums1[i]]] != -1 else -1 \
		for i in range(len(nums1))
	]  # i = 2, [ -1, 3, 5 ]

assert str(find_next_greater_elements([2,4], [1,2,3,4])) == str([3,-1])
assert str(find_next_greater_elements([4,1,2], [1,3,4,2])) == str([-1,3,-1])
assert str(find_next_greater_elements([6, 2, -5], [1, 2, -3, 4, -5, 6])) == str([-1, 4, 6])
