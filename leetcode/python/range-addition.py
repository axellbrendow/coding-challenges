def prepare_for_lazy_evaluation(arr, updates):
	for start, end, inc in updates:
		arr[start] += inc
		if end + 1 < len(arr):
			arr[end + 1] -= inc

def do_cumulative_sum(arr):
	for i in range(1, len(arr)):
		arr[i] += arr[i - 1]

def exec_updates(arr_len, updates):
	arr = [0] * arr_len
	prepare_for_lazy_evaluation(arr, updates)
	do_cumulative_sum(arr)
	return arr

arr_len = 5
updates = [
	[1,  3,  2],
	[2,  4,  3],
	[0,  2, -2]
]
output = [-2, 0, 3, 5, 3]
assert str(exec_updates(arr_len, updates)) == str(output)
