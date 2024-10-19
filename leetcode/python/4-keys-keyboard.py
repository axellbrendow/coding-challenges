def find_max_A_i_can_print(N):
	cache = {}
	def dfs(str_len, selec_len, buffer_len, max_can_press):
		if max_can_press <= 0: return str_len

		if (str_len, selec_len, buffer_len, max_can_press) in cache:
			return cache[(str_len, selec_len, buffer_len, max_can_press)]

		cache[(str_len, selec_len, buffer_len, max_can_press)] = max(
			# Press A
			dfs(str_len + 1, 0, buffer_len, max_can_press - 1),
			# Press Ctrl A
			dfs(str_len, str_len, buffer_len, max_can_press - 1),
			# Press Ctrl C
			dfs(str_len, selec_len, selec_len, max_can_press - 1),
			# Press Ctrl V
			dfs(str_len + buffer_len, selec_len, buffer_len, max_can_press - 1),
		)
		return cache[(str_len, selec_len, buffer_len, max_can_press)]

	return dfs(0, 0, 0, N)

N = 1
assert find_max_A_i_can_print(N) == 1

N = 2
assert find_max_A_i_can_print(N) == 2

N = 3
assert find_max_A_i_can_print(N) == 3

N = 4
assert find_max_A_i_can_print(N) == 4

N = 5
assert find_max_A_i_can_print(N) == 5

N = 6
assert find_max_A_i_can_print(N) == 6

N = 7
assert find_max_A_i_can_print(N) == 9

N = 8
assert find_max_A_i_can_print(N) == 12

N = 9
assert find_max_A_i_can_print(N) == 16
