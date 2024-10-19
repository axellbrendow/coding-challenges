ASCENDING = -2
DESCENDING = -3
NOT_SURE = -4

def get_pattern(val1, val2):
	return NOT_SURE if val1 == val2 else ASCENDING if val1 < val2 else DESCENDING

def get_arr_pattern(arr):
	pattern = get_pattern(arr[0], arr[1])
	for i in range(2, len(arr)):
		new_pattern = get_pattern(arr[i - 1], arr[i])
		if pattern == NOT_SURE:
			pattern = new_pattern
		elif new_pattern != NOT_SURE and new_pattern != pattern:
			return new_pattern, i
	return pattern, -1

def get_output_when_we_are_at_the_parabola_vertex(new_arr, last_index):
	new_output = []
	i = last_index - 1
	j = last_index
	while i >= 0 and j < len(new_arr):
		if new_arr[i] <= new_arr[j]:
			new_output.append(new_arr[i])
			i -= 1
		else:
			new_output.append(new_arr[j])
			j += 1
	while i >= 0:
			new_output.append(new_arr[i])
			i -= 1
	while j < len(new_arr):
			new_output.append(new_arr[j])
			j += 1
	return new_output

def apply_quadratic_function(nums, a, b, c):
	new_arr = [a * x**2 + b * x + c for x in nums]
	if len(new_arr) <= 1: return new_arr

	pattern, last_index = get_arr_pattern(new_arr)
	if last_index != -1:
		return get_output_when_we_are_at_the_parabola_vertex(new_arr, last_index)
	if pattern == ASCENDING:
		return new_arr
	if pattern == DESCENDING:
		return list(reversed(new_arr))

assert str(apply_quadratic_function([0, 0, 1, 2], 1, 1, 1)) == str([1, 1, 3, 7])
assert str(apply_quadratic_function([2, 1, 0, 0], 1, 1, 1)) == str([1, 1, 3, 7])
assert str(apply_quadratic_function([-2, -1, 0, 0, 1, 2], 1, 1, 1)) == str([1, 1, 1, 3, 3, 7])
