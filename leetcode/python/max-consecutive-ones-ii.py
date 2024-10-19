def find_max_consecutive_ones(bin_arr):
	i, j, z = 0, 0, -1
	max_len = -1
	while j < len(bin_arr):
		if bin_arr[j] == 0:
			if z != -1:
				bin_arr[z] = 0
				i = z + 1
				z = -1
			bin_arr[j] = 1
			z = j
		max_len = max(max_len, j - i + 1)
		j += 1
	return max_len

bin_arr = [0, 0, 0, 0, 0]
assert find_max_consecutive_ones(bin_arr) == 1

bin_arr = [0, 0, 1, 1, 0]
assert find_max_consecutive_ones(bin_arr) == 3

bin_arr = [1, 0, 1, 1, 0]
assert find_max_consecutive_ones(bin_arr) == 4
