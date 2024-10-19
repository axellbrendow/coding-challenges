def find_decreasing_sequence(signature, start):
	try:
		d_start = signature.index('D', start)
	except:
		return -1, -1
	d_end = d_start
	try:
		d_end = signature.index('D', d_start + 1)
	except:
		pass
	return d_start, d_end

def swap(start, end, permutation):
	while start < end:
		permutation[start], permutation[end] = permutation[end], permutation[start]
		start += 1
		end -= 1

def find_lexicographically_smallest_permutation(signature):  # signature = 'DI'
	n = len(signature) + 1  # 3
	permutation = list(range(1, n + 1))
	start, end = find_decreasing_sequence(signature, 0)
	while start >= 0:
		swap(start, end + 1, permutation)
		start, end = find_decreasing_sequence(signature, end + 1)
	return permutation

assert str(find_lexicographically_smallest_permutation('I')) == str([1, 2])
assert str(find_lexicographically_smallest_permutation('DI')) == str([2, 1, 3])
assert str(find_lexicographically_smallest_permutation('DDI')) == str([3, 2, 1, 4])
