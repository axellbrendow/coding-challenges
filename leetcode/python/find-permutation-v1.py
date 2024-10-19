def find_lexicographically_smallest_permutation(signature):  # signature = 'DI'
	n = len(signature) + 1  # 3

	def dfs(arr, index):  # arr = [2, 1, 3]
		if index >= len(signature):
			return True
		for i in range(1, n + 1):  # i = 1  |  i = 3
			if signature[index] == 'D':
				if i < arr[-1] and not i in arr:
					arr.append(i)
					if dfs(arr, index + 1): return True
					arr.pop()
			elif i > arr[-1] and not i in arr:
				arr.append(i)
				if dfs(arr, index + 1): return True
				arr.pop()
		return False

	for i in range(1, n + 1):  # i = 2
		arr = [i]  # [2]
		if dfs(arr, 0):
			return arr
	return None

assert str(find_lexicographically_smallest_permutation('I')) == str([1, 2])
assert str(find_lexicographically_smallest_permutation('DI')) == str([2, 1, 3])
assert str(find_lexicographically_smallest_permutation('DDI')) == str([3, 2, 1, 4])
