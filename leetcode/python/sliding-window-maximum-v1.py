from collections import deque

def maximum_value_of_each_subarray(array, k):
	output = []
	queue = deque()
	for i in range(len(array)):
		if len(queue) > 0 and i - queue[0] >= k:
			queue.popleft()
		while len(queue) > 0 and array[i] > array[queue[-1]]:
			queue.pop()
		queue.append(i)
		if i >= k - 1:
			output.append(array[queue[0]])
	return output

assert str(maximum_value_of_each_subarray([10, 5, 2, 7, 8, 7], 1)) == str([10, 5, 2, 7, 8, 7])

assert str(maximum_value_of_each_subarray([10, 5, 2, 7, 8, 7], 3)) == str([10, 7, 8, 8])
